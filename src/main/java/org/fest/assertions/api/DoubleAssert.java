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
package org.fest.assertions.api;

import org.fest.assertions.core.FloatingPointNumberAssert;
import org.fest.assertions.data.Offset;
import org.fest.assertions.description.Description;
import org.fest.assertions.internal.Doubles;
import org.fest.util.VisibleForTesting;

/**
 * Assertion methods for doubles.
 * <p>
 * To create an instance of this class, invoke {@link Assertions#assertThat(Double)} or
 * {@link Assertions#assertThat(double)}.
 * </p>
 *
 * @author Yvonne Wang
 * @author David DIDIER
 * @author Alex Ruiz
 * @author Ansgar Konermann
 */
public class DoubleAssert extends AbstractComparableAssert<DoubleAssert, Double> implements
  FloatingPointNumberAssert<DoubleAssert, Double> {

  @VisibleForTesting
  Doubles doubles = Doubles.instance();

  protected DoubleAssert(Double actual) {
    super(actual, DoubleAssert.class);
  }

  protected DoubleAssert(Double actual, Description description) {
    super(actual, DoubleAssert.class, description);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public DoubleAssert isNaN() {
    doubles.assertIsNaN(description, actual);
    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public DoubleAssert isNotNaN() {
    doubles.assertIsNotNaN(description, actual);
    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public DoubleAssert isEqualTo(Double expected, Offset<Double> offset) {
    doubles.assertEqual(description, actual, expected, offset);
    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public DoubleAssert isNotEqualTo(Double expected) {
    doubles.assertNotEqual(description, actual, expected);
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
  public DoubleAssert isEqualTo(double expected) {
    doubles.assertEqual(description, actual, expected);
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
  public DoubleAssert isEqualTo(double expected, Offset<Double> offset) {
    doubles.assertEqual(description, actual, expected, offset);
    return this;
  }

  /**
   * Verifies that the actual value is not equal to the given one.
   *
   * @param other the given value to compare the actual value to.
   * @return {@code this} assertion object.
   * @throws AssertionError if the actual value is {@code null}.
   * @throws AssertionError if the actual value is equal to the given one.
   */
  public DoubleAssert isNotEqualTo(double other) {
    doubles.assertNotEqual(description, actual, other);
    return this;
  }

  /**
   * Verifies that the actual value is less than the given one.
   *
   * @param other the given value to compare the actual value to.
   * @return {@code this} assertion object.
   * @throws AssertionError if the actual value is {@code null}.
   * @throws AssertionError if the actual value is equal to or greater than the given one.
   */
  public DoubleAssert isLessThan(double other) {
    doubles.assertLessThan(description, actual, other);
    return this;
  }

  /**
   * Verifies that the actual value is less than or equal to the given one.
   *
   * @param other the given value to compare the actual value to.
   * @return {@code this} assertion object.
   * @throws AssertionError if the actual value is {@code null}.
   * @throws AssertionError if the actual value is greater than the given one.
   */
  public DoubleAssert isNotGreaterThan(double other) {
    doubles.assertNotGreaterThan(description, actual, other);
    return this;
  }

  /**
   * Verifies that the actual value is greater than the given one.
   *
   * @param other the given value to compare the actual value to.
   * @return {@code this} assertion object.
   * @throws AssertionError if the actual value is {@code null}.
   * @throws AssertionError if the actual value is equal to or less than the given one.
   */
  public DoubleAssert isGreaterThan(double other) {
    doubles.assertGreaterThan(description, actual, other);
    return this;
  }

  /**
   * Verifies that the actual value is greater than or equal to the given one.
   *
   * @param other the given value to compare the actual value to.
   * @return {@code this} assertion object.
   * @throws AssertionError if the actual value is {@code null}.
   * @throws AssertionError if the actual value is less than the given one.
   */
  public DoubleAssert isNotLessThan(double other) {
    doubles.assertNotLessThan(description, actual, other);
    return this;
  }
}
