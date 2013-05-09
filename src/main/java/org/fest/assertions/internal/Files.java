/*
 * Created on Jan 26, 2011
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
package org.fest.assertions.internal;

import static org.fest.util.Preconditions.checkNotNull;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import org.fest.assertions.description.Description;
import org.fest.assertions.error.BasicErrorMessageFactory;
import org.fest.util.IORuntimeException;
import org.fest.util.VisibleForTesting;

/**
 * Reusable assertions for {@link File}s.
 *
 * @author David DIDIER
 * @author Yvonne Wang
 * @author Alex Ruiz
 * @author Olivier Demeijer
 * @author Olivier Michallat
 */
public class Files {
  private static final Files INSTANCE = new Files();

  public static Files instance() {
    return INSTANCE;
  }

  @VisibleForTesting
  FileContentComparator comparator = new FileContentComparator();

  @VisibleForTesting
  Failures failures = Failures.instance();

  @VisibleForTesting
  Files() {
  }

  /**
   * Asserts that the <em>actual</em> {@link File} represents an existing file or directory.
   *
   * @param description the description of the <em>actual</em> {@code File}.
   * @param actual the <em>actual</em> {@code File}.
   * @throws AssertionError if the <em>actual</em> {@code File} is {@code null}.
   * @throws AssertionError if the <em>actual</em> {@link File} represents a file or directory that does not exist.
   */
  public void assertExists(Description description, File actual) {
    assertNotNull(description, actual);
    if (!actual.exists()) {
      String format = "expecting resource in path:<%s> to exist";
      throw failures.failure(description, new BasicErrorMessageFactory(format, actual));
    }
  }

  /**
   * Asserts that the <em>actual</em> {@link File} represents a file or directory that does not exist.
   *
   * @param description the description of the <em>actual</em> {@code File}.
   * @param actual the <em>actual</em> {@code File}.
   * @throws AssertionError if the <em>actual</em> {@code File} is {@code null}.
   * @throws AssertionError if the <em>actual</em> {@link File} represents an existing file or directory.
   */
  public void assertDoesNotExist(Description description, File actual) {
    assertNotNull(description, actual);
    if (actual.exists()) {
      String format = "expecting resource in path:<%s> not to exist";
      throw failures.failure(description, new BasicErrorMessageFactory(format, actual));
    }
  }

  /**
   * Asserts that the <em>actual</em> {@code File} represents an existing file.
   *
   * @param description the description of the <em>actual</em> {@code File}.
   * @param actual the <em>actual</em> {@code File}.
   * @throws AssertionError if the <em>actual</em> {@code File} is {@code null}.
   * @throws AssertionError if the <em>actual</em> {@code File} does not represent an existing file.
   */
  public void assertIsFile(Description description, File actual) {
    assertNotNull(description, actual);
    if (!actual.isFile()) {
      String format = "expecting path:<%s> to represent an existing file";
      throw failures.failure(description, new BasicErrorMessageFactory(format, actual));
    }
  }

  /**
   * Asserts that the <em>actual</em> {@code File} represents an existing directory.
   *
   * @param description the description of the <em>actual</em> {@code File}.
   * @param actual the <em>actual</em> {@code File}.
   * @throws AssertionError if the <em>actual</em> {@code File} is {@code null}.
   * @throws AssertionError if the <em>actual</em> {@code File} does not represent an existing directory.
   */
  public void assertIsDirectory(Description description, File actual) {
    assertNotNull(description, actual);
    if (!actual.isDirectory()) {
      String format = "expecting path:<%s> to represent an existing directory";
      throw failures.failure(description, new BasicErrorMessageFactory(format, actual));
    }
  }

  /**
   * Asserts that the given {@code File}s represent existing files with equal content. It is assumed that the content of
   * the files is text.
   *
   * @param description the description of the <em>actual</em> {@code File}.
   * @param actual the <em>actual</em> {@code File}.
   * @param expected the <em>expected</em> {@code File}.
   * @param charset the charset to use when reading the contents of the given {@code File}s.
   * @throws NullPointerException if the <em>expected</em> {@code File} is {@code null}.
   * @throws IllegalArgumentException if the <em>expected</em> {@code File} does not represent an existing file.
   * @throws NullPointerException if the given charset is {@code null}.
   * @throws AssertionError if the <em>actual</em> {@code File} is {@code null}.
   * @throws AssertionError if the <em>actual</em> {@code File} does not represent an existing file.
   * @throws IORuntimeException if an I/O error occurs.
   * @throws AssertionError if the given files do not have equal content.
   */
  public void assertEqualContent(Description description, File actual, File expected, Charset charset) {
    checkIsFile(expected);
    checkNotNull(charset);
    assertIsFile(description, actual);
    try {
      String lineSeparator = System.getProperty("line.separator");
      FileDiffs diffs = comparator.compareContents(actual, expected, charset);
      if (!diffs.isEmpty()) {
        String format = "files expected:<%s> and actual:<%s> do not have equal content:" + lineSeparator + "%s"
            + lineSeparator + "using charset:<%s>";
        throw failures.failure(description, new BasicErrorMessageFactory(format, expected, actual, diffs, charset));
      }
    } catch (IOException e) {
      String format = "Failed to compare contents of files:<%s> and:<%s> using charset:<%s>";
      throw new IORuntimeException(String.format(format, actual, expected, charset), e);
    }
  }

  private void checkIsFile(File expected) {
    checkNotNull(expected);
    if (!expected.isFile()) {
      throw new IllegalArgumentException();
    }
  }

  private static void assertNotNull(Description description, File actual) {
    Objects.instance().assertNotNull(description, actual);
  }
}
