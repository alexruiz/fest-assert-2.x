/*
 * Created on Jan 28, 2011
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
 * Copyright @2011-2013 the original author or authors.
 */
package org.fest.assertions.api;

import static java.nio.charset.Charset.defaultCharset;

import static org.fest.test.ExpectedException.none;
import static org.junit.Assert.assertSame;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;

import org.fest.assertions.test.FakeFile;
import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for {@link FileAssert#hasContentEqualTo(File, Charset)}.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class FileAssert_hasContentEqualTo_with_charset_Test {
  @Rule
  public ExpectedException thrown = none();
  private File actual;
  private File expected;
  private final Charset charSet = defaultCharset();
  private FileAssert assertions;

  @Before
  public void setUp() {
    try {
      actual = createFile(actual, charSet);
      expected = File.createTempFile("expected", "txt");
    } catch (IOException e) {
      e.printStackTrace();
    }
    assertions = new FileAssert(actual);
  }

  private File createFile(File file, Charset charSet) throws IOException {
    file = File.createTempFile("actual", "txt");
    PrintWriter writer = new PrintWriter(file);
    writer.println("actual file");
    writer.close();
    return file;
  }

  @Test
  public void should_pass_that_content_in_actual_is_equal_to_expected() {
    assertions.hasContentEqualTo(actual, charSet);
  }

  @Test
  public void should_return_this() {
    FileAssert returned = assertions.hasContentEqualTo(actual, charSet);
    assertSame(assertions, returned);
  }

  @Test
  public void should_fail_if_content_in_actual_is_not_equal_to_expected() {
    thrown.expect(AssertionError.class);
    assertions.hasContentEqualTo(expected,charSet);
  }

  @Test
  public void should_fail_if_actual_is_not_file() {
    thrown.expect(AssertionError.class);
    assertions = new FileAssert(FakeFile.newDirectory("directory"));
    assertions.hasContentEqualTo(expected,charSet);
  }

  @Test
  public void should_throw_error_if_actual_is_null() {
    thrown.expect(AssertionError.class);
    assertions = new FileAssert(null);
    assertions.hasContentEqualTo(expected,charSet);
  }

  @Test
  public void should_throw_error_if_expected_is_null() {
    thrown.expect(NullPointerException.class);
    assertions.hasContentEqualTo(null,charSet);
  }

  @Test
  public void should_throw_error_if_expected_is_not_file() {
    thrown.expect(IllegalArgumentException.class);
    assertions.hasContentEqualTo(FakeFile.newDirectory("directory"),charSet);
  }
}
