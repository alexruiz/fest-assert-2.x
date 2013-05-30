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
 * Copyright @2010-2013 the original author or authors.
 */
package org.fest.assertions.api;

import org.fest.assertions.description.Description;
import org.fest.assertions.internal.BigDecimals;
import org.fest.util.VisibleForTesting;

import java.math.BigDecimal;

/**
 * Assertion methods for <code>{@link BigDecimal}</code>s.
 * <p>
 * To create an instance of this class, invoke <code>{@link Assertions#assertThat(BigDecimal)}</code>.
 * </p>
 *
 * @author David DIDIER
 * @author Ted M. Young
 * @author Yvonne Wang
 * @author Alex Ruiz
 * @author Joel Costigliola
 * @author Mikhail Mazursky
 */
public class BigDecimalAssert extends AbstractUnevenComparableAssert<BigDecimalAssert, BigDecimal> {

  @VisibleForTesting
  BigDecimals bigDecimals = BigDecimals.instance();

  protected BigDecimalAssert(String actual) {
    super(new BigDecimal(actual), BigDecimalAssert.class);
  }

  protected BigDecimalAssert(BigDecimal actual) {
    super(actual, BigDecimalAssert.class);
  }

  protected BigDecimalAssert(BigDecimal actual, Description description) {
    super(actual, BigDecimalAssert.class, description);
  }

  public BigDecimalAssert isEqualTo(String expected) {
    bigDecimals.assertEqual(description, actual, new BigDecimal(expected));
    return this;
  }

  public BigDecimalAssert isNotEqualTo(String expected) {
    bigDecimals.assertNotEqual(description, actual, new BigDecimal(expected));
    return this;
  }

  public BigDecimalAssert isGreaterThan(String expected) {
    bigDecimals.assertGreaterThan(description, actual, new BigDecimal(expected));
    return this;
  }

  public BigDecimalAssert isNotGreaterThan(String expected) {
    bigDecimals.assertNotGreaterThan(description, actual, new BigDecimal(expected));
    return this;
  }

  public BigDecimalAssert isLessThan(String expected) {
    bigDecimals.assertLessThan(description, actual, new BigDecimal(expected));
    return this;
  }

  public BigDecimalAssert isNotLessThan(String expected) {
    bigDecimals.assertNotLessThan(description, actual, new BigDecimal(expected));
    return this;
  }

  public BigDecimalAssert isEqualByComparingTo(String expected) {
    return super.isEqualByComparingTo(new BigDecimal(expected));
  }

  public BigDecimalAssert isNotEqualByComparingTo(String expected) {
    return super.isNotEqualByComparingTo(new BigDecimal(expected));
  }
}
