/*
 * Created on Oct 25, 2010
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

import static java.lang.Double.NaN;
import static java.lang.Math.abs;

import static org.fest.assertions.error.ShouldBeEqualWithinOffset.shouldBeEqual;
import static org.fest.assertions.error.ShouldBeGreaterThan.shouldBeGreaterThan;
import static org.fest.assertions.error.ShouldBeLessThan.shouldBeLessThan;
import static org.fest.assertions.error.ShouldNotBeEqual.shouldNotBeEqual;
import static org.fest.assertions.error.ShouldNotBeGreaterThan.shouldNotBeGreaterThan;
import static org.fest.assertions.error.ShouldNotBeLessThan.shouldNotBeLessThan;
import static org.fest.assertions.internal.CommonValidations.checkNumberIsNotNull;
import static org.fest.assertions.internal.CommonValidations.checkOffsetIsNotNull;
import static org.fest.assertions.internal.Comparables.assertNotNull;

import org.fest.assertions.data.Offset;
import org.fest.assertions.description.Description;
import org.fest.assertions.error.ShouldBeEqual;
import org.fest.util.VisibleForTesting;

/**
 * Reusable assertions for <code>{@link Double}</code>s.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class Doubles {

  private static final Doubles INSTANCE = new Doubles();

  /**
   * Returns the singleton instance of this class.
   *
   * @return the singleton instance of this class.
   */
  public static Doubles instance() {
    return INSTANCE;
  }

  @VisibleForTesting
  Comparables comparables = Comparables.instance();
  @VisibleForTesting
  Failures failures = Failures.instance();

  @VisibleForTesting
  Doubles() {
  }

  /**
   * Verifies that the actual value is equal to {@code NaN}.
   *
   * @param description the description of the <em>actual</em> value.
   * @param actual the <em>actual</em> value.
   */
  public void assertIsNaN(Description description, Double actual) {
    comparables.assertEqual(description, actual, NaN);
  }

  /**
   * Verifies that the actual value is not equal to {@code NaN}.
   *
   * @param description the description of the <em>actual</em> value.
   * @param actual the <em>actual</em> value.
   */
  public void assertIsNotNaN(Description description, Double actual) {
    comparables.assertNotEqual(description, actual, NaN);
  }

  /**
   * Verifies that two doubles are equal within a positive offset.<br>
   * It does not rely on the custom comparisonStrategy (if one is set) because using an offset is already a specific
   * comparison strategy.
   *
   * @param description describes the information about the assertion.
   * @param actual the <em>actual</em> value.
   * @param expected the <em>expected</em> value.
   * @param offset the given positive <em>offset</em>.
   * @throws NullPointerException if the given <em>offset</em> is {@code null}.
   * @throws AssertionError if the <em>actual</em> value is {@code null}.
   * @throws AssertionError if the <em>actual</em> value is not equal to the <em>expected</em> one.
   */
  public void assertEqual(Description description, Double actual, Double expected, Offset<Double> offset) {
    checkOffsetIsNotNull(offset);
    checkNumberIsNotNull(expected);
    assertNotNull(description, actual);
    if (!isEqualTo(actual, expected, offset)) {
      throw failures.failure(description,
          shouldBeEqual(actual, expected, offset, abs(expected.doubleValue() - actual.doubleValue())));
    }
  }

  public void assertEqual(Description description, Double actual, double expected) {
    assertNotNull(description, actual);
    if (!(actual.doubleValue() == expected)) {
      throw failures.failure(description, ShouldBeEqual.shouldBeEqual(actual, expected));
    }
  }

  /**
   * Verifies that two doubles are not equal.
   *
   * @param description the description of the <em>actual</em> value.
   * @param actual the <em>actual</em> value.
   * @param expected the <em>expected</em> value.
   * @param offset the given positive <em>offset</em>.
   */
  public void assertNotEqual(Description description, Double actual, Double expected, Offset<Double> offset) {
    checkOffsetIsNotNull(offset);
    checkNumberIsNotNull(expected);
    assertNotNull(description, actual);
    if (isEqualTo(actual, expected, offset)) {
      throw failures.failure(description, shouldNotBeEqual(actual, expected));
    }
  }

  public void assertNotEqual(Description description, Double actual, double expected) {
    assertNotNull(description, actual);
    if (actual.doubleValue() == expected) {
      throw failures.failure(description, shouldNotBeEqual(actual, expected));
    }
  }

  /**
   * Verifies that the actual value is greater than the expected.
   *
   * @param description the description of the <em>actual</em> value.
   * @param actual the <em>actual</em> value.
   * @param expected the <em>expected</em> value.
   */
  public void assertGreaterThan(Description description, Double actual, double expected) {
    assertNotNull(description, actual);
    if (!isGreaterThan(actual, expected)) {
      throw failures.failure(description, shouldBeGreaterThan(actual, expected));
    }
  }

  /**
   * Verifies that the actual value is not greater than the expected.
   *
   * @param description the description of the <em>actual</em> value.
   * @param actual the <em>actual</em> value.
   * @param expected the <em>expected</em> value.
   */
  public void assertNotGreaterThan(Description description, Double actual, double expected) {
    assertNotNull(description, actual);
    if (isGreaterThan(actual, expected)) {
      throw failures.failure(description, shouldNotBeGreaterThan(actual, expected));
    }
  }

  /**
   * Verifies that the actual value is less than the expected.
   *
   * @param description the description of the <em>actual</em> value.
   * @param actual the <em>actual</em> value.
   * @param expected the <em>expected</em> value.
   */
  public void assertLessThan(Description description, Double actual, double expected) {
    assertNotNull(description, actual);
    if (!isLessThan(actual, expected)) {
      throw failures.failure(description, shouldBeLessThan(actual, expected));
    }
  }

  /**
   * Verifies that the actual value is not less than the expected.
   *
   * @param description the description of the <em>actual</em> value.
   * @param actual the <em>actual</em> value.
   * @param expected the <em>expected</em> value.
   */
  public void assertNotLessThan(Description description, Double actual, double expected) {
    assertNotNull(description, actual);
    if (isLessThan(actual, expected)) {
      throw failures.failure(description, shouldNotBeLessThan(actual, expected));
    }
  }

  private boolean isEqualTo(Double actual, Double expected, Offset<?> offset) {
    return abs(expected.doubleValue() - actual.doubleValue()) <= offset.value.doubleValue();
  }

  private boolean isGreaterThan(Double actual, double expected) {
    return actual.doubleValue() > expected;
  }

  private boolean isLessThan(Double actual, double expected) {
    return actual.doubleValue() < expected;
  }
}
