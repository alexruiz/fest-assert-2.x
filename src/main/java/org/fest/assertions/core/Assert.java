/*
 * Created on Jul 15, 2010
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
package org.fest.assertions.core;

/**
 * Base contract of all assertion objects: the minimum functionality that any assertion object should provide.
 *
 * @param <S> the "self" type of this assertion class. Please read &quot;<a href="http://bit.ly/anMa4g"
 *            target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 *            for more details.
 * @param <A> the type of the <em>actual</em> value.
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public interface Assert<S, A> {
  /**
   * Verifies that the <em>actual</em> value is equal to the given one.
   *
   * @param expected the given value to compare the <em>actual</em> value to.
   * @return {@code this} assertion object.
   * @throws AssertionError if the <em>actual</em> value is not equal to the given one.
   */
  S isEqualTo(A expected);

  /**
   * Verifies that the <em>actual</em> value is not equal to the given one.
   *
   * @param other the given value to compare the <em>actual</em> value to.
   * @return {@code this} assertion object.
   * @throws AssertionError if the <em>actual</em> value is equal to the given one.
   */
  S isNotEqualTo(A other);

  /**
   * Verifies that the <em>actual</em> value is {@code null}.
   *
   * @throws AssertionError if the <em>actual</em> value is not {@code null}.
   */
  S isNull();

  /**
   * Verifies that the <em>actual</em> value is not {@code null}.
   *
   * @return {@code this} assertion object.
   * @throws AssertionError if the <em>actual</em> value is {@code null}.
   */
  S isNotNull();
}
