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
package org.fest.assertions.api;

import org.fest.assertions.core.FloatingPointNumberAssert;
import org.fest.assertions.data.Offset;
import org.fest.assertions.description.Description;
import org.fest.assertions.internal.Floats;
import org.fest.util.VisibleForTesting;

/**
 * Assertion methods for floats.
 * <p>
 * To create an instance of this class, invoke {@link Assertions#assertThat(Float)} or
 * {@link Assertions#assertThat(float)}.
 * </p>
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 * @author Ansgar Konermann
 */
public class FloatAssert extends AbstractComparableAssert<FloatAssert, Float> implements
  FloatingPointNumberAssert<FloatAssert, Float> {

  @VisibleForTesting
  Floats floats = Floats.instance();

  protected FloatAssert(Float actual) {
    super(actual, FloatAssert.class);
  }

  protected FloatAssert(Float actual, Description description) {
    super(actual, FloatAssert.class, description);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public FloatAssert isNaN() {
    floats.assertIsNaN(description, actual);
    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public FloatAssert isNotNaN() {
    floats.assertIsNotNaN(description, actual);
    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public FloatAssert isEqualTo(Float expected, Offset<Float> offset) {
    floats.assertEqual(description, actual, expected, offset);
    return this;
  }

  /**
   * Verifies that the actual value is equal to the given one.
   *
   * @param expected the given value to compare the actual value to.
   * @return {@code this} assertion object.
   * @throws AssertionError if the actual value is {@code null}.
   * @throws AssertionError if the actual value is not equal to the given one.
   */
  public FloatAssert isEqualTo(float expected) {
    floats.assertEqual(description, actual, expected);
    return this;
  }

  /**
   * Verifies that the actual value is equal to the given one, within a positive offset.
   *
   * @param expected the given value to compare the actual value to.
   * @param offset   the given positive offset.
   * @return {@code this} assertion object.
   * @throws NullPointerException if the given offset is {@code null}.
   * @throws AssertionError       if the actual value is {@code null}.
   * @throws AssertionError       if the actual value is not equal to the given one.
   */
  public FloatAssert isEqualTo(float expected, Offset<Float> offset) {
    floats.assertEqual(description, actual, expected, offset);
    return this;
  }

  /**
   * Verifies that the actual value is not equal to the given one.
   *
   * @param expected the given value to compare the actual value to.
   * @return {@code this} assertion object.
   * @throws AssertionError if the actual value is {@code null}.
   * @throws AssertionError if the actual value is equal to the given one.
   */
  public FloatAssert isNotEqualTo(float expected) {
    floats.assertNotEqual(description, actual, expected);
    return this;
  }

  /**
   * Verifies that the actual value is less than the given one.
   *
   * @param expected the given value to compare the actual value to.
   * @return {@code this} assertion object.
   * @throws AssertionError if the actual value is {@code null}.
   * @throws AssertionError if the actual value is equal to or greater than the given one.
   */
  public FloatAssert isLessThan(float expected) {
    floats.assertLessThan(description, actual, expected);
    return this;
  }

  /**
   * Verifies that the actual value is less than or equal to the given one.
   *
   * @param expected the given value to compare the actual value to.
   * @return {@code this} assertion object.
   * @throws AssertionError if the actual value is {@code null}.
   * @throws AssertionError if the actual value is greater than the given one.
   */
  public FloatAssert isNotGreaterThan(float expected) {
    floats.assertNotGreaterThan(description, actual, expected);
    return this;
  }

  /**
   * Verifies that the actual value is greater than the given one.
   *
   * @param expected the given value to compare the actual value to.
   * @return {@code this} assertion object.
   * @throws AssertionError if the actual value is {@code null}.
   * @throws AssertionError if the actual value is equal to or less than the given one.
   */
  public FloatAssert isGreaterThan(float expected) {
    floats.assertGreaterThan(description, actual, expected);
    return this;
  }

  /**
   * Verifies that the actual value is greater than or equal to the given one.
   *
   * @param expected the given value to compare the actual value to.
   * @return {@code this} assertion object.
   * @throws AssertionError if the actual value is {@code null}.
   * @throws AssertionError if the actual value is less than the given one.
   */
  public FloatAssert isNotLessThan(float expected) {
    floats.assertNotLessThan(description, actual, expected);
    return this;
  }
}
