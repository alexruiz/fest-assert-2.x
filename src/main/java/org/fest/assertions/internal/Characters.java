/*
 * Created on Oct 23, 2010
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
 * Copyright @2010-2013 the original author or authors.
 */
package org.fest.assertions.internal;

import static java.lang.Character.isLowerCase;
import static java.lang.Character.isUpperCase;

import static org.fest.assertions.error.ShouldBeEqual.shouldBeEqual;
import static org.fest.assertions.error.ShouldBeGreaterThan.shouldBeGreaterThan;
import static org.fest.assertions.error.ShouldBeLessThan.shouldBeLessThan;
import static org.fest.assertions.error.ShouldBeLowerCase.shouldBeLowerCase;
import static org.fest.assertions.error.ShouldBeUpperCase.shouldBeUpperCase;
import static org.fest.assertions.error.ShouldNotBeEqual.shouldNotBeEqual;
import static org.fest.assertions.error.ShouldNotBeGreaterThan.shouldNotBeGreaterThan;

import org.fest.assertions.description.Description;
import org.fest.assertions.error.ShouldNotBeLessThan;
import org.fest.util.VisibleForTesting;

/**
 * Reusable assertions for {@link Character}.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class Characters {

  private static final Characters INSTANCE = new Characters();

  /**
   * Returns the singleton instance of this class.
   *
   * @return the singleton instance of this class.
   */
  public static Characters instance() {
    return INSTANCE;
  }

  @VisibleForTesting
  Failures failures = Failures.instance();

  @VisibleForTesting
  Characters() {
  }

  /**
   * Asserts that two characters are equal.
   *
   * @param description contains information about the assertion.
   * @param actual the actual value.
   * @param expected the expected value.
   * @throws AssertionError if the actual value is {@code null}.
   * @throws AssertionError if the actual value is not equal to the expected one. This method will throw a
   *           {@code org.junit.ComparisonFailure} instead if JUnit is in the classpath and the expected and actual
   *           values are not equal.
   */
  public void assertEqual(Description description, Character actual, char expected) {
    assertNotNull(description, actual);
    if (actual.charValue() != expected) {
      throw failures.failure(description, shouldBeEqual(actual, expected));
    }
  }

  /**
   * Asserts that two characters are not equal.
   *
   * @param description contains information about the assertion.
   * @param actual the actual value.
   * @param other the value to compare the actual value to.
   * @throws AssertionError if the actual value is {@code null}.
   * @throws AssertionError if the actual value is equal to the other one.
   */
  public void assertNotEqual(Description description, Character actual, char other) {
    assertNotNull(description, actual);
    if (actual.charValue() == other) {
      throw failures.failure(description, shouldNotBeEqual(actual, other));
    }
  }

  /**
   * Asserts that the actual value is less than the other one.
   *
   * @param description contains information about the assertion.
   * @param actual the actual value.
   * @param other the value to compare the actual value to.
   * @throws AssertionError if the actual value is {@code null}.
   * @throws AssertionError if the actual value is not less than the other one: this assertion will fail if the actual
   *           value is equal to or greater than the other value.
   */
  public void assertLessThan(Description description, Character actual, char other) {
    assertNotNull(description, actual);
    if (!isLessThan(actual, other)) {
      throw failures.failure(description, shouldBeLessThan(actual, other));
    }
  }

  /**
   * Asserts that the actual value is less than or equal to the other one.
   *
   * @param description contains information about the assertion.
   * @param actual the actual value.
   * @param other the value to compare the actual value to.
   * @throws AssertionError if the actual value is {@code null}.
   * @throws AssertionError if the actual value is greater than the other one.
   */
  public void assertNotGreaterThan(Description description, Character actual, char other) {
    assertNotNull(description, actual);
    if (isGreaterThan(actual, other)) {
      throw failures.failure(description, shouldNotBeGreaterThan(actual, other));
    }
  }

  /**
   * Asserts that the actual value is greater than the other one.
   *
   * @param description contains information about the assertion.
   * @param actual the actual value.
   * @param other the value to compare the actual value to.
   * @throws AssertionError if the actual value is {@code null}.
   * @throws AssertionError if the actual value is not greater than the other one: this assertion will fail if the
   *           actual value is equal to or less than the other value.
   */
  public void assertGreaterThan(Description description, Character actual, char other) {
    assertNotNull(description, actual);
    if (!isGreaterThan(actual, other)) {
      throw failures.failure(description, shouldBeGreaterThan(actual, other));
    }
  }

  /**
   * Asserts that the actual value is greater than or equal to the other one.
   *
   * @param description contains information about the assertion.
   * @param actual the actual value.
   * @param other the value to compare the actual value to.
   * @throws AssertionError if the actual value is {@code null}.
   * @throws AssertionError if the actual value is less than the other one.
   */
  public void assertNotLessThan(Description description, Character actual, char other) {
    assertNotNull(description, actual);
    if (isLessThan(actual, other)) {
      throw failures.failure(description, ShouldNotBeLessThan.shouldNotBeLessThan(actual, other));
    }
  }

  /**
   * Asserts that the actual value is a lowercase character.
   *
   * @param description contains information about the assertion.
   * @param actual the actual value.
   * @throws AssertionError if the actual value is {@code null}.
   * @throws AssertionError if the actual value is not a lowercase character.
   */
  public void assertLowerCase(Description description, Character actual) {
    assertNotNull(description, actual);
    if (!isLowerCase(actual)) {
      throw failures.failure(description, shouldBeLowerCase(actual));
    }
  }

  /**
   * Asserts that the actual value is a uppercase character.
   *
   * @param description contains information about the assertion.
   * @param actual the actual value.
   * @throws AssertionError if the actual value is {@code null}.
   * @throws AssertionError if the actual value is not a uppercase character.
   */
  public void assertUpperCase(Description description, Character actual) {
    assertNotNull(description, actual);
    if (!isUpperCase(actual)) {
      throw failures.failure(description, shouldBeUpperCase(actual));
    }
  }

  private static boolean isGreaterThan(Character actual, char other) {
    return actual.charValue() > other;
  }

  private static boolean isLessThan(Character actual, char other) {
    return actual.charValue() < other;
  }

  private static void assertNotNull(Description description, Character actual) {
    Objects.instance().assertNotNull(description, actual);
  }
}
