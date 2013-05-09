/*
 * Created on Dec 14, 2010
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
 * Copyright @2013 the original author or authors.
 */
package org.fest.assertions.error;

/**
 * Creates an error message indicating that an assertion that verifies that a {@code String} contains another
 * {@code String} only once failed.
 *
 * @author Pauline Iogna
 * @author Joel Costigliola
 * @author Yvonne Wang
 */
public class ShouldContainStringOnlyOnce extends BasicErrorMessageFactory {

  /**
   * Creates a new <code>{@link ShouldContainStringOnlyOnce}</code>.
   *
   * @param actual the actual value in the failed assertion.
   * @param sequence the String expected to be in {@code actual} only once.
   * @param occurences the number of occurrences of sequence in actual.
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldContainOnlyOnce(String actual, String sequence, int occurences) {
    return new ShouldContainStringOnlyOnce(actual, sequence, occurences);
  }

  private ShouldContainStringOnlyOnce(String actual, String expected, int occurences) {
    super("expecting:\n<%s>\n to appear only once in:\n<%s>\n but it appeared %s time.", expected, actual, occurences);
  }
}
