/*
 * Created on Oct 24, 2010
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

import static java.lang.Math.abs;

import static org.fest.assertions.error.ShouldBeEqual.shouldBeEqual;
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
import org.fest.util.VisibleForTesting;

/**
 * Reusable assertions for <code>{@link Float}</code>s.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class Floats {

  private static final Floats INSTANCE = new Floats();

  /**
   * Returns the singleton instance of this class.
   *
   * @return the singleton instance of this class.
   */
  public static Floats instance() {
    return INSTANCE;
  }

  @VisibleForTesting
  Floats() {
  }

  @VisibleForTesting
  Comparables comparables = Comparables.instance();
  @VisibleForTesting
  Failures failures = Failures.instance();

  /**
   * Verifies that the actual value is equal to {@code NaN}.
   *
   * @param description the description of the <em>actual</em> value.
   * @param actual the <em>actual</em> value.
   */
  public void assertIsNaN(Description description, Float actual) {
    comparables.assertEqual(description, actual, Float.NaN);
  }

  /**
   * Verifies that the actual value is not equal to {@code NaN}.
   *
   * @param description the description of the <em>actual</em> value.
   * @param actual the <em>actual</em> value.
   */
  public void assertIsNotNaN(Description description, Float actual) {
    comparables.assertNotEqual(description, actual, Float.NaN);
  }

  /**
   * Verifies that two floats are equal within a positive offset.<br>
   * It does not rely on the custom comparisionStrategy (if one is set) because using an offset is already a specific
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
  public void assertEqual(Description description, Float actual, Float expected, Offset<Float> offset) {
    checkOffsetIsNotNull(offset);
    checkNumberIsNotNull(expected);
    assertNotNull(description, actual);
    if (!isEqualTo(actual, expected, offset)) {
      throw failures.failure(description,
          shouldBeEqual(actual, expected, offset, abs(expected.floatValue() - actual.floatValue())));
    }
  }

  public void assertEqual(Description description, Float actual, float expected) {
    assertNotNull(description, actual);
    if (actual.floatValue() != expected) {
      throw failures.failure(description, shouldBeEqual(actual, expected));
    }
  }

  public void assertNotEqual(Description description, Float actual, float expected) {
    assertNotNull(description, actual);
    if (actual.floatValue() == expected) {
      throw failures.failure(description, shouldNotBeEqual(actual, expected));
    }
  }

  /**
   * Verifies that the actual value is great than the expected one.
   *
   * @param description the description of the <em>actual</em> value.
   * @param actual the <em>actual</em> value.
   * @param expected the <em>expected</em> value.
   */
  public void assertGreaterThan(Description description, Float actual, float expected) {
    assertNotNull(description, actual);
    if (!isGreaterThan(actual, expected)) {
      throw failures.failure(description, shouldBeGreaterThan(actual, expected));
    }
  }

  /**
   * Verifies that the actual value is less than or equal to the expected.
   *
   * @param description the description of the <em>actual</em> value.
   * @param actual the <em>actual</em> value.
   * @param expected the <em>expected</em> valu.e
   */
  public void assertNotGreaterThan(Description description, Float actual, float expected) {
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
  public void assertLessThan(Description description, Float actual, float expected) {
    assertNotNull(description, actual);
    if (!isLessThan(actual, expected)) {
      throw failures.failure(description, shouldBeLessThan(actual, expected));
    }
  }

  /**
   * Verifies that the actual value is greater than or equal to the expected one.
   *
   * @param description the description of the <em>actual</em> value.
   * @param actual the <em>actual</em> value.
   * @param expected the <em>expected</em> valu.e
   */
  public void assertNotLessThan(Description description, Float actual, float expected) {
    assertNotNull(description, actual);
    if (isLessThan(actual, expected)) {
      throw failures.failure(description, shouldNotBeLessThan(actual, expected));
    }
  }

  private boolean isEqualTo(Float actual, Float expected, Offset<?> offset) {
    return abs(expected.floatValue() - actual.floatValue()) <= offset.value.floatValue();
  }

  private boolean isGreaterThan(Float actual, float expected) {
    return actual.floatValue() > expected;
  }

  private boolean isLessThan(Float actual, float expected) {
    return actual.floatValue() < expected;
  }
}
