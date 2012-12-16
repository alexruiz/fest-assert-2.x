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
package org.fest.assertions.core;

/**
 * Assertion methods applicable to {@link Number}s.
 *
 * @param <T> the type of the <em>actual</em> value.
 *
 * @author Alex Ruiz
 */
public interface NumberAssert<T extends Number> {
  /**
   * Verifies that the <em>actual</em> value is equal to zero.
   *
   * @return this assertion object.
   * @throws AssertionError if the <em>actual</em> value is {@code null}.
   * @throws AssertionError if the <em>actual</em> value is not equal to zero.
   */
  NumberAssert<T> isZero();

  /**
   * Verifies that the <em>actual</em> value is not equal to zero.
   *
   * @return this assertion object.
   * @throws AssertionError if the <em>actual</em> value is {@code null}.
   * @throws AssertionError if the <em>actual</em> value is equal to zero.
   */
  NumberAssert<T> isNotZero();

  /**
   * Verifies that the <em>actual</em> value is positive.
   *
   * @return this assertion object.
   * @throws AssertionError if the <em>actual</em> value is {@code null}.
   * @throws AssertionError if the <em>actual</em> value is not positive.
   */
  NumberAssert<T> isPositive();

  /**
   * Verifies that the <em>actual</em> value is negative.
   *
   * @return this assertion object.
   * @throws AssertionError if the <em>actual</em> value is {@code null}.
   * @throws AssertionError if the <em>actual</em> value is not negative.
   */
  NumberAssert<T> isNegative();
}
