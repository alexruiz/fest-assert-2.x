/*
 * Created on Nov 18, 2010
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
package org.fest.assertions.api;

import org.fest.assertions.core.Assert;
import org.fest.assertions.core.ExtensionPoints;
import org.fest.assertions.core.Matcher;
import org.fest.assertions.description.Description;
import org.fest.assertions.internal.Matchers;
import org.fest.assertions.internal.Objects;
import org.fest.util.VisibleForTesting;

/**
 * Base class for all assertions.
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
public abstract class AbstractAssert<S, A> implements Assert<S, A>, ExtensionPoints<S, A> {
  @VisibleForTesting
  Objects objects = Objects.instance();

  @VisibleForTesting
  Matchers matchers = Matchers.instance();

  protected final A actual;
  protected final S myself;
  protected final Description description;

  protected AbstractAssert(A actual, Class<S> selfType) {
    this(actual, selfType, null);
  }

  protected AbstractAssert(A actual, Class<S> selfType, Description description) {
    myself = selfType.cast(this);
    this.actual = actual;
    this.description = description;
  }

  /** {@inheritDoc} */
  @Override
  public S isEqualTo(A expected) {
    objects.assertEqual(description, actual, expected);
    return myself;
  }

  /** {@inheritDoc} */
  @Override
  public S isNotEqualTo(A other) {
    objects.assertNotEqual(description, actual, other);
    return myself;
  }

  /** {@inheritDoc} */
  @Override
  public final void isNull() {
    objects.assertNull(description, actual);
  }

  /** {@inheritDoc} */
  @Override
  public S isNotNull() {
    objects.assertNotNull(description, actual);
    return myself;
  }

  /** {@inheritDoc} */
  @Override
  public S is(Matcher<? super A> matcher) {
    matchers.assertIs(description, actual, matcher);
    return myself;
  }

  /** {@inheritDoc} */
  @Override
  public S isNot(Matcher<? super A> matcher) {
    matchers.assertIsNot(description, actual, matcher);
    return myself;
  }

  /** {@inheritDoc} */
  @Override
  public S has(Matcher<? super A> matcher) {
    matchers.assertHas(description, actual, matcher);
    return myself;
  }

  /** {@inheritDoc} */
  @Override
  public S doesNotHave(Matcher<? super A> matcher) {
    matchers.assertDoesNotHave(description, actual, matcher);
    return myself;
  }

  /**
   * Returns the description of the <em>actual</em> value.
   *
   * @return the description of the <em>actual</em> value, or {@code null} if such description was not specified during
   *         construction.
   */
  public String descriptionText() {
    return description != null ? description.value() : null;
  }

  /** {@inheritDoc} */
  @Override
  public final boolean equals(Object obj) {
    throw new UnsupportedOperationException("'equals' is not supported...maybe you intended to call 'isEqualTo'");
  }

  /**
   * Always returns 1.
   *
   * @return 1.
   */
  @Override
  public final int hashCode() {
    return 1;
  }
}
