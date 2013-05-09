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

import java.io.File;
import java.nio.charset.Charset;

import org.fest.assertions.description.Description;
import org.fest.assertions.internal.Files;
import org.fest.util.IORuntimeException;
import org.fest.util.VisibleForTesting;

/**
 * Assertion methods for {@link File}s.
 * <p>
 * To create a new instance of this class, invoke {@link Assertions#assertThat(File)}.
 * </p>
 *
 * @author David DIDIER
 * @author Yvonne Wang
 * @author Alex Ruiz
 * @author Olivier Michallat
 * @author Olivier Demeijer
 */
public class FileAssert extends AbstractAssert<FileAssert, File> {
  @VisibleForTesting
  Files files = Files.instance();

  protected FileAssert(File actual) {
    super(actual, FileAssert.class);
  }

  protected FileAssert(File actual, Description description) {
    super(actual, FileAssert.class, description);
  }

  /**
   * Verifies that the <em>actual</em> {@link File} represents an existing file or directory.
   *
   * @return {@code this} assertion object.
   * @throws AssertionError if the <em>actual</em> {@code File} is {@code null}.
   * @throws AssertionError if the <em>actual</em> {@link File} represents a file or directory that does not exist.
   */
  public FileAssert exists() {
    files.assertExists(description, actual);
    return this;
  }

  /**
   * Verifies that the <em>actual</em> {@link File} represents a file or directory that does not exist.
   *
   * @return {@code this} assertion object.
   * @throws AssertionError if the <em>actual</em> {@code File} is {@code null}.
   * @throws AssertionError if the <em>actual</em> {@link File} represents an existing file or directory.
   */
  public FileAssert doesNotExist() {
    files.assertDoesNotExist(description, actual);
    return this;
  }

  /**
   * Verifies that the <em>actual</em> {@code File} represents an existing file.
   *
   * @return {@code this} assertion object.
   * @throws AssertionError if the <em>actual</em> {@code File} is {@code null}.
   * @throws AssertionError if the <em>actual</em> {@code File} does not represent an existing file.
   */
  public FileAssert isFile() {
    files.assertIsFile(description, actual);
    return this;
  }

  /**
   * Verifies that the <em>actual</em> {@code File} represents an existing directory.
   *
   * @return {@code this} assertion object.
   * @throws AssertionError if the <em>actual</em> {@code File} is {@code null}.
   * @throws AssertionError if the <em>actual</em> {@code File} does not represent an existing directory.
   */
  public FileAssert isDirectory() {
    files.assertIsDirectory(description, actual);
    return this;
  }

  /**
   * Verifies that the content of the file represented by the <em>actual</em> {@code File} is equal to the content of
   * the file represented by the given {@code File}. It is assumed that the content of the files is text. This method
   * uses the charset in {@link Charset#defaultCharset()} to read the contents of the files.
   *
   * @param expected the <em>expected</em> {@code File}.
   * @return {@code this} assertion object.
   * @throws NullPointerException if the <em>expected</em> {@code File} is {@code null}.
   * @throws IllegalArgumentException if the <em>expected</em> {@code File} does not represent an existing file.
   * @throws AssertionError if the <em>actual</em> {@code File} is {@code null}.
   * @throws AssertionError if the <em>actual</em> {@code File} does not represent an existing file.
   * @throws IORuntimeException if an I/O error occurs.
   * @throws AssertionError if the content of the file represented by the <em>actual</em> {@code File} is not equal to
   *           the content of the file represented by the given {@code File}.
   */
  public FileAssert hasContentEqualTo(File expected) {
    return hasContentEqualTo(expected, defaultCharset());
  }

  /**
   * Verifies that the content of the file represented by the <em>actual</em> {@code File} is equal to the content of
   * the file represented by the given {@code File}. It is assumed that the content of the files is text.
   *
   * @param expected the <em>expected</em> {@code File}.
   * @param charset the charset to use when reading the contents of the {@code File}s.
   * @return {@code this} assertion object.
   * @throws NullPointerException if the <em>expected</em> {@code File} is {@code null}.
   * @throws IllegalArgumentException if the <em>expected</em> {@code File} does not represent an existing file.
   * @throws NullPointerException if the given charset is {@code null}.
   * @throws AssertionError if the <em>actual</em> {@code File} is {@code null}.
   * @throws AssertionError if the <em>actual</em> {@code File} does not represent an existing file.
   * @throws IORuntimeException if an I/O error occurs.
   * @throws AssertionError if the content of the file represented by the <em>actual</em> {@code File} is not equal to
   *           the content of the file represented by the given {@code File}.
   */
  public FileAssert hasContentEqualTo(File expected, Charset charset) {
    files.assertEqualContent(description, actual, expected, charset);
    return this;
  }
}
