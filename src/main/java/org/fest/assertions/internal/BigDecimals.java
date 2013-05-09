/*
 * Created on Feb 8, 2011
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
 * Copyright @2011 the original author or authors.
 */
package org.fest.assertions.internal;

import static org.fest.assertions.internal.CommonValidations.checkNumberIsNotNull;
import static org.fest.assertions.internal.Comparables.assertNotNull;

import java.math.BigDecimal;

import org.fest.assertions.description.Description;
import org.fest.util.VisibleForTesting;

/**
 * Reusable assertions for <code>{@link BigDecimal}</code>s.
 *
 * @author Yvonne Wang
 */
public class BigDecimals {

  private static final BigDecimal ZERO = BigDecimal.ZERO;
  private static final BigDecimals INSTANCE = new BigDecimals();

  /**
   * Returns the singleton instance of this class.
   *
   * @return the singleton instance of this class.
   */
  public static BigDecimals instance() {
    return INSTANCE;
  }

  @VisibleForTesting
  Comparables comparables = Comparables.instance();

  @VisibleForTesting
  Failures failures = Failures.instance();

  @VisibleForTesting
  BigDecimals() {
  }

  /**
   * Verifies that two BigDecimals are equal.
   *
   * @param description the description of the <em>actual</em> value.
   * @param actual the <em>actual</em> value.
   * @param expected the <em>expected</em> value.
   */
  public void assertEqual(Description description, BigDecimal actual, BigDecimal expected) {
    checkNumberIsNotNull(expected);
    assertNotNull(description, actual);
    comparables.assertEqual(description, actual, expected);
  }

  /**
   * Verifies that two BigDecimals are not equal.
   *
   * @param description the description of the <em>actual</em> value.
   * @param actual the <em>actual</em> value.
   * @param expected the <em>expected</em> value.
   */
  public void assertNotEqual(Description description, BigDecimal actual, BigDecimal expected) {
    checkNumberIsNotNull(expected);
    assertNotNull(description, actual);
    comparables.assertNotEqual(description, actual, expected);
  }

  /**
   * Verifies that the actual value is greater than the expected.
   *
   * @param description the description of the <em>actual</em> value.
   * @param actual the <em>actual</em> value.
   * @param expected the <em>expected</em> value.
   */
  public void assertGreaterThan(Description description, BigDecimal actual, BigDecimal expected) {
    checkNumberIsNotNull(expected);
    assertNotNull(description, actual);
    comparables.assertGreaterThan(description, actual, expected);
  }

  /**
   * Verifies that the actual value is not greater than the expected.
   *
   * @param description the description of the <em>actual</em> value.
   * @param actual the <em>actual</em> value.
   * @param expected the <em>expected</em> value.
   */
  public void assertNotGreaterThan(Description description, BigDecimal actual, BigDecimal expected) {
    checkNumberIsNotNull(expected);
    assertNotNull(description, actual);
    comparables.assertNotGreaterThan(description, actual, expected);
  }

  /**
   * Verifies that the actual value is less than the expected.
   *
   * @param description the description of the <em>actual</em> value.
   * @param actual the <em>actual</em> value.
   * @param expected the <em>expected</em> value.
   */
  public void assertLessThan(Description description, BigDecimal actual, BigDecimal expected) {
    checkNumberIsNotNull(expected);
    assertNotNull(description, actual);
    comparables.assertLessThan(description, actual, expected);
  }

  /**
   * Verifies that the actual value is not less than the expected.
   *
   * @param description the description of the <em>actual</em> value.
   * @param actual the <em>actual</em> value.
   * @param expected the <em>expected</em> value.
   */
  public void assertNotLessThan(Description description, BigDecimal actual, BigDecimal expected) {
    checkNumberIsNotNull(expected);
    assertNotNull(description, actual);
    comparables.assertNotLessThan(description, actual, expected);
  }

  /**
   * Verifies that the actual value is zero.
   *
   * @param description the description of the <em>actual</em> value.
   * @param actual the <em>actual</em> value.
   */
  public void assertIsZero(Description description, BigDecimal actual) {
    comparables.assertEqual(description, actual, ZERO);
  }

  /**
   * Verifies that the actual value is not zero.
   *
   * @param description the description of the <em>actual</em> value.
   * @param actual the <em>actual</em> value.
   */
  public void assertIsNotZero(Description description, BigDecimal actual) {
    comparables.assertNotEqual(description, actual, ZERO);
  }

  /**
   * Verifies that the actual value is positive.
   *
   * @param description the description of the <em>actual</em> value.
   * @param actual the <em>actual</em> value.
   */
  public void assertIsPositive(Description description, BigDecimal actual) {
    comparables.assertGreaterThan(description, actual, ZERO);
  }

  /**
   * Verifies that the actual value is negative.
   *
   * @param description the description of the <em>actual</em> value.
   * @param actual the <em>actual</em> value.
   */
  public void assertIsNegative(Description description, BigDecimal actual) {
    comparables.assertLessThan(description, actual, ZERO);
  }
}
