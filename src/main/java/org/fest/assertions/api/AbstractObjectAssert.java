/*
 * Created on Sep 24, 2012
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
 * Copyright @2012-2013 the original author or authors.
 */
package org.fest.assertions.api;

import org.fest.assertions.description.Description;

/**
 * Base class for assertions for {@code Object}s.
 *
 * @param <S> the "self" type of this assertion class. Please read &quot;<a href="http://bit.ly/anMa4g"
 *          target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 *          for more details.
 * @param <A> the type of the <em>actual</em> value.
 *
 * @author Alex Ruiz
 * @author Joel Costigliola
 * @author Yvonne Wang
 */
public abstract class AbstractObjectAssert<S, A> extends AbstractAssert<S, A> {
  protected AbstractObjectAssert(A actual, Class<S> selfType) {
    super(actual, selfType);
  }

  protected AbstractObjectAssert(A actual, Class<S> selfType, Description description) {
    super(actual, selfType, description);
  }

  /**
   * Verifies that the <em>actual</em> value is the same as the given one.
   *
   * @param expected the given value to compare the <em>actual</em> value to.
   * @return {@code this} assertion object.
   * @throws AssertionError if the <em>actual</em> value is not the same as the given one.
   */
  public S isSameAs(A expected) {
    objects.assertSame(description, actual, expected);
    return myself;
  }

  /**
   * Verifies that the <em>actual</em> value is not the same as the given one.
   *
   * @param other the given value to compare the <em>actual</em> value to.
   * @return {@code this} assertion object.
   * @throws AssertionError if the <em>actual</em> value is the same as the given one.
   */
  public S isNotSameAs(A other) {
    objects.assertNotSame(description, actual, other);
    return myself;
  }

  /**
   * Verifies that the <em>actual</em> value is an instance of the given type.
   *
   * @param type the type to check the <em>actual</em> value against.
   * @return this assertion object.
   * @throws NullPointerException if the given type is {@code null}.
   * @throws AssertionError if the <em>actual</em> value is {@code null}.
   * @throws AssertionError if the <em>actual</em> value is not an instance of the given type.
   */
  public S isInstanceOf(Class<?> type) {
    objects.assertIsInstanceOf(description, actual, type);
    return myself;
  }

  /**
   * Verifies that the <em>actual</em> value is not an instance of the given type.
   *
   * @param type the type to check the <em>actual</em> value against.
   * @return this assertion object.
   * @throws NullPointerException if the given type is {@code null}.
   * @throws AssertionError if the <em>actual</em> value is an instance of the given type.
   */
  public S isNotInstanceOf(Class<?> type) {
    objects.assertIsNotInstanceOf(description, actual, type);
    return myself;
  }

  /**
   * Verifies that the <em>actual</em> value is an instance of any of the given types.
   *
   * @param types the types to check the <em>actual</em> value against.
   * @return this assertion object.
   * @throws NullPointerException if the given array is {@code null}.
   * @throws IllegalArgumentException if the given array is empty.
   * @throws NullPointerException if the given array has {@code null} elements.
   * @throws AssertionError if the actual <em>actual</em> value is {@code null}.
   * @throws AssertionError if the actual <em>actual</em> value is not an instance of any of the given types.
   */
  public S isInstanceOfAny(Class<?>... types) {
    objects.assertIsInstanceOfAny(description, actual, types);
    return myself;
  }

  /**
   * Verifies that the <em>actual</em> value is not an instance of any of the given types.
   *
   * @param types the types to check the <em>actual</em> value against.
   * @return this assertion object.
   * @throws NullPointerException if the given array is {@code null}.
   * @throws IllegalArgumentException if the given array is empty.
   * @throws NullPointerException if the given array has {@code null} elements.
   * @throws AssertionError if the actual <em>actual</em> value is an instance of any of the given types.
   */
  public S isNotInstanceOfAny(Class<?>... types) {
    objects.assertIsNotInstanceOfAny(description, actual, types);
    return myself;
  }
}
