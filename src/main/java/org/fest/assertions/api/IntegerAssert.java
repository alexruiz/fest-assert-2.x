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
 * Copyright @2010-2013 the original author or authors.
 */
package org.fest.assertions.api;

import org.fest.assertions.description.Description;
import org.fest.assertions.internal.Integers;
import org.fest.util.VisibleForTesting;

/**
 * Assertion methods for integers.
 * <p>
 * To create an instance of this class, invoke <code>{@link Assertions#assertThat(Integer)}</code> or
 * <code>{@link Assertions#assertThat(int)}</code>.
 * </p>
 *
 * @author Yvonne Wang
 * @author David DIDIER
 * @author Ansgar Konermann
 * @author Alex Ruiz
 */
public class IntegerAssert extends AbstractComparableAssert<IntegerAssert, Integer> {

  @VisibleForTesting
  Integers integers = Integers.instance();

  protected IntegerAssert(Integer actual) {
    super(actual, IntegerAssert.class);
  }

  protected IntegerAssert(Integer actual, Description description) {
    super(actual, IntegerAssert.class, description);
  }

  /**
   * Verifies that the actual value is equal to the given one.
   *
   * @param expected the given value to compare the actual value to.
   * @return {@code this} assertion object.
   * @throws AssertionError if the actual value is {@code null}.
   * @throws AssertionError if the actual value is not equal to the given one.
   */
  public IntegerAssert isEqualTo(int expected) {
    integers.assertEqualTo(description, actual, expected);
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
  public IntegerAssert isNotEqualTo(int expected) {
    integers.assertNotEqualTo(description, actual, expected);
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
  public IntegerAssert isLessThan(int expected) {
    integers.assertLessThan(description, actual, expected);
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
  public IntegerAssert isNotLessThan(int expected) {
    integers.assertNotLessThan(description, actual, expected);
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
  public IntegerAssert isGreaterThan(int expected) {
    integers.assertGreaterThan(description, actual, expected);
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
  public IntegerAssert isNotGreaterThan(int expected) {
    integers.assertNotGreaterThan(description, actual, expected);
    return this;
  }
}
