/*
 * Created on May 8, 2013
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
 * Copyright @2013 the original author or authors.
 */
package org.fest.assertions.api;

import java.util.Map;

import javax.annotation.Nonnull;

import org.fest.assertions.core.EnumerableAssert;
import org.fest.assertions.data.MapEntry;
import org.fest.assertions.description.Description;
import org.fest.assertions.internal.Maps;
import org.fest.util.VisibleForTesting;

/**
 * An implementation class of <code>{@link EnumerableAssert}</code> whose actual type is
 * <code>{@link Map}</code>.
 *
 * @param <S> the "self" type of this assertion class.
 *
 * @author Yvonne Wang
 */
public abstract class AbstractMapAssert<S> implements EnumerableAssert<S> {

  private final Map<?, ?> actual;
  private final S myself;
  private final Description description;

  @VisibleForTesting
  Maps maps = Maps.instance();

  protected AbstractMapAssert(Map<?, ?> actual, Class<S> selfType) {
    this(actual, selfType, null);
  }

  protected AbstractMapAssert(Map<?, ?> actual, Class<S> selfType, Description description) {
    myself = selfType.cast(this);
    this.actual = actual;
    this.description = description;
  }

  @Override
  public S isNullOrEmpty() {
    maps.assertNullOrEmpty(description, actual);
    return myself;
  }

  @Override
  public S isEmpty() {
    maps.assertEmpty(description, actual);
    return myself;
  }

  @Override
  @Nonnull
  public S isNotEmpty() {
    maps.assertNotEmpty(description, actual);
    return myself;
  }

  @Override
  @Nonnull
  public S hasSize(int expectedSize) {
    maps.assertHasSize(description, actual, expectedSize);
    return myself;
  }

  public S contains(MapEntry... entries) {
    maps.assertContains(description, actual, entries);
    return myself;
  }

  public S containsOnly(MapEntry... entries) {
    maps.assertContainsOnly(description, actual, entries);
    return myself;
  }

  public S doesNotContain(MapEntry... entries) {
    maps.assertDoesNotContain(description, actual, entries);
    return myself;
  }

  public <K> S containsKey(K key) {
    maps.assertContainsKey(description, actual, key);
    return myself;
  }

  public <K> S doesNotcontainKey(K key) {
    maps.assertDoesNotContainKey(description, actual, key);
    return myself;
  }

  public <V> S containsValue(V value) {
    maps.assertContainsValue(description, actual, value);
    return myself;
  }

  public <V> S doesNotContainValue(V value) {
    maps.assertDoesNotContainValue(description, actual, value);
    return myself;
  }

  public S doesNotHaveDuplicateValues() {
    maps.assertDoesNotContainDuplicateValues(description, actual);
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
    throw new UnsupportedOperationException("'equals' is not supported...");
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
