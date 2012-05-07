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

import java.util.Collection;
import java.util.Comparator;

import org.fest.assertions.core.Condition;
import org.fest.assertions.core.ObjectEnumerableAssert;
import org.fest.assertions.internal.Iterables;
import org.fest.util.ComparatorBasedComparisonStrategy;
import org.fest.util.VisibleForTesting;

/**
 * Base class for implementations of <code>{@link ObjectEnumerableAssert}</code> whose actual value type is
 * <code>{@link Collection}</code>.
 * @param <S> the "self" type of this assertion class. Please read &quot;<a href="http://bit.ly/anMa4g"
 *          target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 *          for more details.
 * @param <A> the type of the "actual" value.
 * @param <T> the type of elements of the "actual" value.
 * 
 * @author Yvonne Wang
 * @author Alex Ruiz
 * @author Mathieu Baechler
 * @author Joel Costigliola
 * @author Maciej Jaskowski
 * @author Nicolas François
 * @author Mikhail Mazursky
 */
public abstract class AbstractIterableAssert<S extends AbstractIterableAssert<S, A, T>, A extends Iterable<T>, T> extends
    AbstractAssert<S, A> implements ObjectEnumerableAssert<S, T> {

  @VisibleForTesting
  Iterables iterables = Iterables.instance();

  protected AbstractIterableAssert(A actual, Class<?> selfType) {
    super(actual, selfType);
  }

  /** {@inheritDoc} */
  public final void isNullOrEmpty() {
    iterables.assertNullOrEmpty(info, actual);
  }

  /** {@inheritDoc} */
  public final void isEmpty() {
    iterables.assertEmpty(info, actual);
  }

  /** {@inheritDoc} */
  public final S isNotEmpty() {
    iterables.assertNotEmpty(info, actual);
    return myself;
  }

  /** {@inheritDoc} */
  public final S hasSize(int expected) {
    iterables.assertHasSize(info, actual, expected);
    return myself;
  }

  /** {@inheritDoc} */
  public S hasSameSizeAs(Object[] other) {
    iterables.assertHasSameSizeAs(info, actual, other);
    return myself;
  }

  /** {@inheritDoc} */
  public S hasSameSizeAs(Iterable<?> other) {
    iterables.assertHasSameSizeAs(info, actual, other);
    return myself;
  }

  /** {@inheritDoc} */
  public final S contains(T... values) {
    iterables.assertContains(info, actual, values);
    return myself;
  }

  /** {@inheritDoc} */
  public final S containsOnly(T... values) {
    iterables.assertContainsOnly(info, actual, values);
    return myself;
  }

  /**
   * Verifies that all the elements of the actual {@code Iterable} are present in the given {@code Iterable}.
   * @param values the {@code Iterable} that should contain all actual elements.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Iterable} is {@code null}.
   * @throws NullPointerException if the given {@code Iterable} is {@code null}.
   * @throws AssertionError if the actual {@code Iterable} is not subset of set {@code Iterable}.
   */
  public final S isSubsetOf(Iterable<? extends T> values) {
    iterables.assertIsSubsetOf(info, actual, values);
    return myself;
  }

  /** {@inheritDoc} */
  public final S containsSequence(T... sequence) {
    iterables.assertContainsSequence(info, actual, sequence);
    return myself;
  }

  /** {@inheritDoc} */
  public final S doesNotContain(T... values) {
    iterables.assertDoesNotContain(info, actual, values);
    return myself;
  }

  /** {@inheritDoc} */
  public final S doesNotHaveDuplicates() {
    iterables.assertDoesNotHaveDuplicates(info, actual);
    return myself;
  }

  /** {@inheritDoc} */
  public final S startsWith(T... sequence) {
    iterables.assertStartsWith(info, actual, sequence);
    return myself;
  }

  /** {@inheritDoc} */
  public final S endsWith(T... sequence) {
    iterables.assertEndsWith(info, actual, sequence);
    return myself;
  }

  /** {@inheritDoc} */
  public S containsNull() {
    iterables.assertContainsNull(info, actual);
    return myself;
  }

  /** {@inheritDoc} */
  public S doesNotContainNull() {
    iterables.assertDoesNotContainNull(info, actual);
    return myself;
  }

  /** {@inheritDoc} */
  public <E> S are(Condition<E> condition) {
    iterables.assertAre(info, actual, condition);
    return myself;
  }

  /** {@inheritDoc} */
  public <E> S areNot(Condition<E> condition) {
    iterables.assertAreNot(info, actual, condition);
    return myself;
  }

  /** {@inheritDoc} */
  public <E> S have(Condition<E> condition) {
    iterables.assertHave(info, actual, condition);
    return myself;
  }

  /** {@inheritDoc} */
  public <E> S doNotHave(Condition<E> condition) {
    iterables.assertDoNotHave(info, actual, condition);
    return myself;
  }

  /** {@inheritDoc} */
  public <E> S areAtLeast(int times, Condition<E> condition) {
    iterables.assertAreAtLeast(info, actual, times, condition);
    return myself;
  }

  /** {@inheritDoc} */
  public <E> S areNotAtLeast(int times, Condition<E> condition) {
    iterables.assertAreNotAtLeast(info, actual, times, condition);
    return myself;
  }

  /** {@inheritDoc} */
  public <E> S areAtMost(int times, Condition<E> condition) {
    iterables.assertAreAtMost(info, actual, times, condition);
    return myself;
  }

  /** {@inheritDoc} */
  public <E> S areNotAtMost(int times, Condition<E> condition) {
    iterables.assertAreNotAtMost(info, actual, times, condition);
    return myself;
  }

  /** {@inheritDoc} */
  public <E> S areExactly(int times, Condition<E> condition) {
    iterables.assertAreExactly(info, actual, times, condition);
    return myself;
  }

  /** {@inheritDoc} */
  public <E> S areNotExactly(int times, Condition<E> condition) {
    iterables.assertAreNotExactly(info, actual, times, condition);
    return myself;
  }

  /** {@inheritDoc} */
  public <E> S haveAtLeast(int times, Condition<E> condition) {
    iterables.assertHaveAtLeast(info, actual, times, condition);
    return myself;
  }

  /** {@inheritDoc} */
  public <E> S doNotHaveAtLeast(int times, Condition<E> condition) {
    iterables.assertDoNotHaveAtLeast(info, actual, times, condition);
    return myself;
  }

  /** {@inheritDoc} */
  public <E> S haveAtMost(int times, Condition<E> condition) {
    iterables.assertHaveAtMost(info, actual, times, condition);
    return myself;
  }

  /** {@inheritDoc} */
  public <E> S doNotHaveAtMost(int times, Condition<E> condition) {
    iterables.assertDoNotHaveAtMost(info, actual, times, condition);
    return myself;
  }

  /** {@inheritDoc} */
  public <E> S haveExactly(int times, Condition<E> condition) {
    iterables.assertHaveExactly(info, actual, times, condition);
    return myself;
  }

  /** {@inheritDoc} */
  public <E> S doNotHaveExactly(int times, Condition<E> condition) {
    iterables.assertDoNotHaveExactly(info, actual, times, condition);
    return myself;
  }

  public S usingElementComparator(Comparator<? super T> customComparator) {
    this.iterables = new Iterables(new ComparatorBasedComparisonStrategy(customComparator));
    return myself;
  }

  public S usingDefaultElementComparator() {
    this.iterables = Iterables.instance();
    return myself;
  }
}
