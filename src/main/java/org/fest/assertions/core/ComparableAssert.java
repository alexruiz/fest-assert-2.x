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
 * Copyright @2010-2011 the original author or authors.
 */
package org.fest.assertions.core;

/**
 * Assertion methods applicable to <code>{@link Comparable}</code>s.
 *
 * @param <S> the "self" type of this assertion class. Please read &quot;<a href="http://bit.ly/anMa4g"
 *          target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 *          for more details.
 * @param <A> the type of the <em>actual</em> value.
 *
 * @author Alex Ruiz
 * @author Ted M. Young
 */
public interface ComparableAssert<S, A extends Comparable<? super A>> {
  /**
   * Verifies that the <em>actual</em> value is less than the given one.
   *
   * @param other the given value to compare the <em>actual</em> value to.
   * @return {@code this} assertion object.
   * @throws AssertionError if the <em>actual</em> value is {@code null}.
   * @throws AssertionError if the <em>actual</em> value is equal to or greater than the given one.
   */
  S isLessThan(A other);

  /**
   * Verifies that the <em>actual</em> value is greater than or equal to the given one.
   *
   * @param other the given value to compare the <em>actual</em> value to.
   * @return {@code this} assertion object.
   * @throws AssertionError if the <em>actual</em> value is {@code null}.
   * @throws AssertionError if the <em>actual</em> value is less than the given one.
   */
  S isNotLessThan(A other);

  /**
   * Verifies that the <em>actual</em> value is greater than the given one.
   *
   * @param other the given value to compare the <em>actual</em> value to.
   * @return {@code this} assertion object.
   * @throws AssertionError if the <em>actual</em> value is {@code null}.
   * @throws AssertionError if the <em>actual</em> value is equal to or less than the given one.
   */
  S isGreaterThan(A other);

  /**
   * Verifies that the <em>actual</em> value is not greater than the given one.
   *
   * @param other the given value to compare the <em>actual</em> value to.
   * @return {@code this} assertion object.
   * @throws AssertionError if the <em>actual</em> value is {@code null}.
   * @throws AssertionError if the <em>actual</em> value is greater than the given one.
   */
  S isNotGreaterThan(A other);
}
