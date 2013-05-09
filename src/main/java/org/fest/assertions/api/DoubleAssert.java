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
 * To create an instance of this class, invoke <code>{@link Assertions#assertThat(Double)}</code> or
 * <code>{@link Assertions#assertThat(double)}</code>.
 * </p>
 *
 * @author Yvonne Wang
 * @author David DIDIER
 * @author Alex Ruiz
 * @author Ansgar Konermann
 * @author Joel Costigliola
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

  /** {@inheritDoc} */
  @Override
  public DoubleAssert isNaN() {
    doubles.assertIsNaN(description, actual);
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public DoubleAssert isNotNaN() {
    doubles.assertIsNotNaN(description, actual);
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public DoubleAssert isEqualTo(Double expected, Offset<Double> offset) {
    doubles.assertEqual(description, actual, expected, offset);
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public DoubleAssert isNotEqualTo(Double expected) {
    doubles.assertNotEqual(description, actual, expected);
    return this;
  }

  public DoubleAssert isEqualTo(double expected) {
    doubles.assertEqual(description, actual, expected);
    return this;
  }

  public DoubleAssert isGreaterThan(double expected) {
    doubles.assertGreaterThan(description, actual, expected);
    return this;
  }

  public DoubleAssert isNotGreaterThan(double expected) {
    doubles.assertNotGreaterThan(description, actual, expected);
    return this;
  }

  public DoubleAssert isLessThan(double expected) {
    doubles.assertLessThan(description, actual, expected);
    return this;
  }

  public DoubleAssert isNotLessThan(double expected) {
    doubles.assertNotLessThan(description, actual, expected);
    return this;
  }
}
