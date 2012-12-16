/*
 * Created on Sep 26, 2010
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
 * Mechanism for extending assertion classes.
 *
 * @param <S> the "self" type of this assertion class. Please read &quot;<a href="http://bit.ly/anMa4g"
 *          target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 *          for more details.
 * @param <A> the type of the <em>actual</em> value.
 *
 * @author Alex Ruiz
 * @author Mikhail Mazursky
 */
public interface ExtensionPoints<S, A> {

  /**
   * Verifies that the <em>actual</em> value satisfies the given matcher. This method is an alias for
   * {@link #has(Matcher)}.
   *
   * @param matcher the given matcher.
   * @return {@code this ExtensionPoints} object.
   * @throws NullPointerException if the given matcher is {@code null}.
   * @throws AssertionError if the <em>actual</em> value does not satisfy the given matcher.
   * @see #is(Matcher)
   */
  S is(Matcher<? super A> matcher);

  /**
   * Verifies that the <em>actual</em> value does not satisfy the given matcher. This method is an alias for
   * {@link #doesNotHave(Matcher)}.
   *
   * @param matcher the given matcher.
   * @return {@code this ExtensionPoints} object.
   * @throws NullPointerException if the given matcher is {@code null}.
   * @throws AssertionError if the <em>actual</em> value satisfies the given matcher.
   * @see #isNot(Matcher)
   */
  S isNot(Matcher<? super A> matcher);

  /**
   * Verifies that the <em>actual</em> value satisfies the given matcher. This method is an alias for
   * {@link #is(Matcher)}.
   *
   * @param matcher the given matcher.
   * @return {@code this ExtensionPoints} object.
   * @throws NullPointerException if the given matcher is {@code null}.
   * @throws AssertionError if the <em>actual</em> value does not satisfy the given matcher.
   * @see #is(Matcher)
   */
  S has(Matcher<? super A> matcher);

  /**
   * Verifies that the <em>actual</em> value does not satisfy the given matcher. This method is an alias for
   * {@link #isNot(Matcher)}.
   *
   * @param matcher the given matcher.
   * @return {@code this ExtensionPoints} object.
   * @throws NullPointerException if the given matcher is {@code null}.
   * @throws AssertionError if the <em>actual</em> value satisfies the given matcher.
   * @see #isNot(Matcher)
   */
  S doesNotHave(Matcher<? super A> matcher);
}