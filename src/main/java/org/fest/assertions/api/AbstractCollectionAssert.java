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
 * Copyright @2010-2013 the original author or authors.
 */
package org.fest.assertions.api;

import java.util.Collection;

import javax.annotation.Nonnull;

import org.fest.assertions.core.ObjectEnumerableAssert;
import org.fest.assertions.description.Description;
import org.fest.assertions.internal.Collections;
import org.fest.util.VisibleForTesting;

/**
 * Base class for implementations of {@link ObjectEnumerableAssert} whose actual value type is {@link Collection}.
 *
 * @param <S> the "self" type of this assertion class. Please read &quot;<a href="http://bit.ly/anMa4g"
 *          target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 *          for more details.
 * @param <T> the type of the "actual" value.
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public abstract class AbstractCollectionAssert<S, T extends Collection<?>> extends AbstractAssert<S, T> implements
    ObjectEnumerableAssert<S> {

  @VisibleForTesting
  Collections collections = Collections.instance();

  protected AbstractCollectionAssert(T actual, Class<S> selfType) {
    super(actual, selfType);
  }

  protected AbstractCollectionAssert(T actual, Class<S> selfType, Description description) {
    super(actual, selfType, description);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final S isNullOrEmpty() {
    collections.assertNullOrEmpty(description, actual);
    return myself;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final S isEmpty() {
    collections.assertEmpty(description, actual);
    return myself;
  }

  /**
   * {@inheritDoc}
   */
  @Nonnull
  @Override
  public final S isNotEmpty() {
    collections.assertNotEmpty(description, actual);
    return myself;
  }

  /**
   * {@inheritDoc}
   */
  @Nonnull
  @Override
  public final S hasSize(int expected) {
    collections.assertHasSize(description, actual, expected);
    return myself;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final S contains(Object... values) {
    collections.assertContains(description, actual, values);
    return myself;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final S containsOnly(Object... values) {
    collections.assertContainsOnly(description, actual, values);
    return myself;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final S containsSequence(Object... sequence) {
    collections.assertContainsSequence(description, actual, sequence);
    return myself;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final S doesNotContain(Object... values) {
    collections.assertDoesNotContain(description, actual, values);
    return myself;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final S doesNotHaveDuplicates() {
    collections.assertDoesNotHaveDuplicates(description, actual);
    return myself;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final S startsWith(Object... sequence) {
    collections.assertStartsWith(description, actual, sequence);
    return myself;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final S endsWith(Object... sequence) {
    collections.assertEndsWith(description, actual, sequence);
    return myself;
  }

  @Override
  public S containsNull() {
    collections.assertContainsNull(description, actual);
    return myself;
  }

  @Override
  public S doesNotContainNull() {
    collections.assertDoesNotContainNull(description, actual);
    return myself;
  }
}
