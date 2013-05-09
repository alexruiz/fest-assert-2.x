/*
 * Created on Jan 27, 2011
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright @2011-2012 the original author or authors.
 */
package org.fest.assertions.internal;

import static junit.framework.Assert.assertSame;
import static junit.framework.Assert.fail;

import static org.fest.assertions.test.FailureMessages.actualIsNull;
import static org.fest.assertions.test.FakeFile.newNonExistingResource;
import static org.fest.assertions.test.FakeFile.newWritableFile;
import static org.fest.assertions.test.TestFailures.expectedAssertionErrorNotThrown;
import static org.fest.test.ExpectedException.none;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import org.fest.assertions.description.Description;
import org.fest.assertions.error.ErrorMessageFactory;
import org.fest.test.ExpectedException;
import org.fest.util.IORuntimeException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for {@link Files#assertEqualContent(Description, File, File, Charset)}.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class Files_assertEqualContent_Test {
  @Rule
  public ExpectedException thrown = none();

  private Charset charset;
  private FileContentComparator comparator;
  private Failures failures;
  private Files files;

  @Before
  public void setUp() {
    charset = Charset.forName("UTF-8");
    comparator = mock(FileContentComparator.class);
    failures = spy(new Failures());
    files = new Files();
    files.comparator = comparator;
    files.failures = failures;
  }

  @Test
  public void should_fail_if_expected_is_null() {
    File actual = newWritableFile("/usr/local/actual.txt");
    thrown.expect(NullPointerException.class);
    files.assertEqualContent(mock(Description.class), actual, null, charset);
  }

  @Test
  public void should_fail_if_actual_is_null() {
    File expected = newWritableFile("/usr/local/expected.txt");
    thrown.expect(AssertionError.class, actualIsNull());
    files.assertEqualContent(mock(Description.class), null, expected, charset);
  }

  @Test
  public void should_fail_if_charset_is_null() {
    File actual = newWritableFile("/usr/local/actual.txt");
    File expected = newWritableFile("/usr/local/expected.txt");
    thrown.expect(NullPointerException.class);
    files.assertEqualContent(mock(Description.class), actual, expected, null);
  }

  @Test
  public void should_fail_if_expected_is_not_file() {
    File actual = newWritableFile("/usr/local/actual.txt");
    File expected = newNonExistingResource("/usr/local/expected.txt");
    thrown.expect(IllegalArgumentException.class);
    files.assertEqualContent(mock(Description.class), actual, expected, charset);
  }

  @Test
  public void should_fail_if_actual_is_not_file() {
    File actual = newNonExistingResource("/usr/local/actual.txt");
    File expected = newWritableFile("/usr/local/expected.txt");
    thrown.expectMessage("[Testing] expecting path:</usr/local/actual.txt> to represent an existing file");
    files.assertEqualContent(new TestDescription("Testing"), actual, expected, charset);
  }

  @Test
  public void should_pass_if_files_have_equal_content() throws IOException {
    File actual = newWritableFile("/usr/local/actual.txt");
    File expected = newWritableFile("/usr/local/expected.txt");
    when(comparator.compareContents(actual, expected, charset)).thenReturn(new FileDiffs());
    files.assertEqualContent(mock(Description.class), actual, expected, charset);
  }

  @Test
  public void should_throw_error_wrapping_catched_IOException() throws IOException {
    File actual = newWritableFile("/usr/local/actual.txt");
    File expected = newWritableFile("/usr/local/expected.txt");
    IOException cause = new IOException();
    when(comparator.compareContents(actual, expected, charset)).thenThrow(cause);
    try {
      files.assertEqualContent(mock(Description.class), actual, expected, charset);
      fail("Expected a IORuntimeException to be thrown");
    } catch (IORuntimeException e) {
      assertSame(cause, e.getCause());
    }
  }

  @Test
  public void should_fail_if_files_do_not_have_equal_content() throws IOException {
    Description description = new TestDescription("Testing");
    File actual = newWritableFile("/usr/local/actual.txt");
    File expected = newWritableFile("/usr/local/expected.txt");
    FileDiffs diffs = new FileDiffs();
    diffs.add("line:1, expected:<line1> but was:<%s>");
    diffs.add("line:2, expected:<line2> but was:<EOF>");
    when(comparator.compareContents(actual, expected, charset)).thenReturn(diffs);
    try {
      files.assertEqualContent(description, actual, expected, charset);
    } catch (AssertionError e) {
      String lineSeparator = System.getProperty("line.separator");
      String message =
          "[Testing] files expected:</usr/local/expected.txt> and actual:</usr/local/actual.txt> do not have equal content:"
          + lineSeparator
          + "  line:1, expected:<line1> but was:<%s>"
          + lineSeparator
          + "  line:2, expected:<line2> but was:<EOF>"
          + lineSeparator
          + "using charset:<UTF-8>";
      assertEquals(message, e.getMessage());
      verify(failures).failure(same(description), any(ErrorMessageFactory.class));
      return;
    }
    expectedAssertionErrorNotThrown();
  }
}
