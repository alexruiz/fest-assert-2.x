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
 * Copyright @2011-2012 the original author or authors.
 */
package org.fest.assertions.internal;

import static junit.framework.Assert.assertEquals;
import static org.fest.assertions.test.TextFileWriter.write;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

/**
 * Tests for {@link FileContentComparator#compareContents(File, File, Charset)}.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class FileContentComparator_compareContents_Test {
  @Rule
  public TemporaryFolder folder = new TemporaryFolder();

  private Charset charset;
  private File actual;
  private File expected;
  private FileContentComparator comparator;

  @Before
  public void setUp() throws IOException {
    charset = Charset.forName("UTF-8");
    actual = folder.newFile("actual.txt");
    expected = folder.newFile("expected.txt");
    comparator = new FileContentComparator();
  }

  @Test
  public void should_return_empty_diff_list_if_files_have_equal_content() throws IOException {
    write("line0", "line1").using(charset).to(actual);
    write("line0", "line1").using(charset).to(expected);
    FileDiffs diffs = comparator.compareContents(actual, expected, charset);
    assertEquals(0, diffs.size());
  }

  @Test
  public void should_return_diffs_if_files_do_not_have_equal_content() throws IOException {
    write("line_0", "line_1").using(charset).to(actual);
    write("line0", "line1").using(charset).to(expected);
    FileDiffs diffs = comparator.compareContents(actual, expected, charset);
    assertEquals(2, diffs.size());
    assertEquals("line:<0>, expected:<'line0'> but was:<'line_0'>", diffs.get(0));
    assertEquals("line:<1>, expected:<'line1'> but was:<'line_1'>", diffs.get(1));
  }

  @Test
  public void should_return_diffs_if_content_of_actual_is_shorter_than_content_of_expected() throws IOException {
    write("line_0").using(charset).to(actual);
    write("line_0", "line_1").using(charset).to(expected);
    FileDiffs diffs = comparator.compareContents(actual, expected, charset);
    assertEquals(1, diffs.size());
    assertEquals("line:<1>, expected:<'line_1'> but was:<EOF>", diffs.get(0));
  }

  @Test
  public void should_return_diffs_if_content_of_actual_is_longer_than_content_of_expected() throws IOException {
    write("line_0", "line_1").using(charset).to(actual);
    write("line_0").using(charset).to(expected);
    FileDiffs diffs = comparator.compareContents(actual, expected, charset);
    assertEquals(1, diffs.size());
    assertEquals("line:<1>, expected:<EOF> but was:<'line_1'>", diffs.get(0));
  }
}
