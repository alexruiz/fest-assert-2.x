/*
 * Created on Oct 17, 2010
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
package org.fest.assertions.internal;

import static org.fest.assertions.error.NotEqualErrorFactory.shouldBeEqual;
import static org.fest.assertions.error.ShouldBeGreaterThan.shouldBeGreaterThan;
import static org.fest.assertions.error.ShouldBeLessThan.shouldBeLessThan;
import static org.fest.assertions.error.ShouldNotBeEqual.shouldNotBeEqual;
import static org.fest.assertions.error.ShouldNotBeGreaterThan.shouldNotBeGreaterThan;
import static org.fest.assertions.error.ShouldNotBeLessThan.shouldNotBeLessThan;

import org.fest.assertions.description.Description;
import org.fest.util.VisibleForTesting;

/**
 * Reusable assertions for {@link Comparable}s.
 *
 * @author Alex Ruiz
 */
public class Comparables {
  private static final Comparables INSTANCE = new Comparables();

  public static Comparables instance() {
    return INSTANCE;
  }

  @VisibleForTesting
  Failures failures = Failures.instance();

  @VisibleForTesting
  Comparables() {}

  @VisibleForTesting
  void setFailures(Failures failures) {
    this.failures = failures;
  }

  /**
   * Asserts that two values are equal by invoking {@link Comparable#compareTo(Object)}.
   *
   * @param description the description of the <em>actual</em> value.
   * @param actual the <em>actual</em> value.
   * @param expected the <em>expected</em> value.
   * @throws AssertionError if the <em>actual</em> value is {@code null}.
   * @throws AssertionError if the <em>actual</em> value is not equal to the expected one. This method will throw a
   *           {@code org.junit.ComparisonFailure} instead if JUnit is in the classpath and the expected and actual
   *           values are not equal.
   */
  public <T extends Comparable<T>> void assertEqual(Description description, T actual, T expected) {
    assertNotNull(description, actual);
    if (!areEqual(actual, expected)) {
      throw shouldBeEqual(actual, expected).newAssertionError(description);
    }
  }

  /**
   * Asserts that two values are not equal by invoking {@link Comparable#compareTo(Object)}.
   *
   * @param description the description of the <em>actual</em> value.
   * @param actual the <em>actual</em> value.
   * @param other the value to compare the <em>actual</em> value to.
   * @throws AssertionError if the <em>actual</em> value is {@code null}.
   * @throws AssertionError if the <em>actual</em> value is equal to the other one.
   */
  public <T extends Comparable<T>> void assertNotEqual(Description description, T actual, T other) {
    assertNotNull(description, actual);
    if (areEqual(actual, other)) {
      throw failures.failure(description, shouldNotBeEqual(actual, other));
    }
  }

  private <T extends Comparable<T>> boolean areEqual(T actual, T expected) {
    return actual.compareTo(expected) == 0;
  }

  /**
   * Asserts that the <em>actual</em> value is less than the other one.
   *
   * @param description the description of the <em>actual</em> value.
   * @param actual the <em>actual</em> value.
   * @param other the value to compare the <em>actual</em> value to.
   * @throws AssertionError if the <em>actual</em> value is {@code null}.
   * @throws AssertionError if the <em>actual</em> value is not less than the other one.
   */
  public <T extends Comparable<T>> void assertLessThan(Description description, T actual, T other) {
    assertNotNull(description, actual);
    if (!isLessThan(actual, other)) {
      throw failures.failure(description, shouldBeLessThan(actual, other));
    }
  }

  /**
   * Asserts that the <em>actual</em> value is greater than or equal to the other one.
   *
   * @param description the description of the <em>actual</em> value.
   * @param actual the <em>actual</em> value.
   * @param other the value to compare the actual value to.
   * @throws AssertionError if the <em>actual</em> value is {@code null}.
   * @throws AssertionError if the <em>actual</em> value is less than the other one.
   */
  public <T extends Comparable<T>> void assertNotLessThan(Description description, T actual, T other) {
    assertNotNull(description, actual);
    if (isLessThan(actual, other)) {
      throw failures.failure(description, shouldNotBeLessThan(actual, other));
    }
  }

  private static <T extends Comparable<T>> boolean isLessThan(T actual, T other) {
    return actual.compareTo(other) < 0;
  }

  /**
   * Asserts that the <em>actual</em> value is greater than the other one.
   *
   * @param description the description of the <em>actual</em> value.
   * @param actual the <em>actual</em> value.
   * @param other the value to compare the <em>actual</em> value to.
   * @throws AssertionError if the <em>actual</em> value is {@code null}.
   * @throws AssertionError if the <em>actual</em> value is not greater than the other one.
   */
  public <T extends Comparable<T>> void assertGreaterThan(Description description, T actual, T other) {
    assertNotNull(description, actual);
    if (!isGreaterThan(actual, other)) {
      throw failures.failure(description, shouldBeGreaterThan(actual, other));
    }
  }

  /**
   * Asserts that the <em>actual</em> value is less than or equal to the other one.
   *
   * @param description the description of the <em>actual</em> value.
   * @param actual the <em>actual</em> value.
   * @param other the value to compare the <em>actual</em> value to.
   * @throws AssertionError if the <em>actual</em> value is {@code null}.
   * @throws AssertionError if the <em>actual</em> value is greater than the other one.
   */
  public <T extends Comparable<T>> void assertNotGreaterThan(Description description, T actual, T other) {
    assertNotNull(description, actual);
    if (isGreaterThan(actual, other)) {
      throw failures.failure(description, shouldNotBeGreaterThan(actual, other));
    }
  }

  private static <T extends Comparable<T>> boolean isGreaterThan(T actual, T other) {
    return actual.compareTo(other) > 0;
  }

  protected static <T> void assertNotNull(Description description, T actual) {
    Objects.instance().assertNotNull(description, actual);
  }
}
