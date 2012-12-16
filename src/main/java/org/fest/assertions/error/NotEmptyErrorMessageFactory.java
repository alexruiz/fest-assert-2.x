/*
 * Created on Sep 22, 2010
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
 * Copyright @2010-2012 the original author or authors.
 */
package org.fest.assertions.error;

/**
 * Creates an error message indicating that an assertion that verifies an enumeration is empty, failed. Enumerations
 * include collections, maps, arrays and {@code String}s.
 *
 * @author Alex Ruiz
 */
public class NotEmptyErrorMessageFactory extends BasicErrorMessageFactory {

  /**
   * Creates a new <code>{@link NotEmptyErrorMessageFactory}</code>.
   *
   * @param actual the actual value in the failed assertion.
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldBeEmpty(Object actual) {
    return new NotEmptyErrorMessageFactory(actual);
  }

  private NotEmptyErrorMessageFactory(Object actual) {
    super("expecting empty but was:<%s>", actual);
  }
}
