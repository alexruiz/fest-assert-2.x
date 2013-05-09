/*
 * Created on Dec 20, 2010
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

import org.fest.assertions.core.ArraySortedAssert;
import org.fest.assertions.core.EnumerableAssert;
import org.fest.assertions.core.IndexedObjectEnumerableAssert;
import org.fest.assertions.data.Index;
import org.fest.assertions.description.Description;
import org.fest.assertions.internal.LongArrays;
import org.fest.util.VisibleForTesting;

/**
 * Assertion methods for arrays of {@code long}s.
 * <p>
 * To create an instance of this class, invoke <code>{@link Assertions#assertThat(long[])}</code>.
 * </p>
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 * @author Joel Costigliola
 * @author Mikhail Mazursky
 * @author Nicolas François
 */
public class LongArrayAssert extends AbstractAssert<LongArrayAssert, long[]> implements
    EnumerableAssert<LongArrayAssert>, IndexedObjectEnumerableAssert<LongArrayAssert, Long>,
    ArraySortedAssert<LongArrayAssert> {

  @VisibleForTesting
  LongArrays arrays = LongArrays.instance();

  protected LongArrayAssert(long[] actual) {
    super(actual, LongArrayAssert.class);
  }

  protected LongArrayAssert(long[] actual, Description description) {
    super(actual, LongArrayAssert.class, description);
  }

  /** {@inheritDoc} */
  @Override
  public LongArrayAssert isNullOrEmpty() {
    arrays.assertNullOrEmpty(description, actual);
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public LongArrayAssert isEmpty() {
    arrays.assertEmpty(description, actual);
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public LongArrayAssert isNotEmpty() {
    arrays.assertNotEmpty(description, actual);
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public LongArrayAssert hasSize(int expected) {
    arrays.assertHasSize(description, actual, expected);
    return this;
  }

  @Override
  public LongArrayAssert isSorted() {
    arrays.assertIsSorted(description, actual);
    return this;
  }

  @Override
  public LongArrayAssert contains(Long value, Index index) {
    arrays.assertContains(description, actual, value, index);
    return this;
  }

  @Override
  public LongArrayAssert doesNotContain(Long value, Index index) {
    arrays.assertDoesNotContain(description, actual, value, index);
    return this;
  }

  /**
   * Verifies that the actual array contains the given values, in any order.
   *
   * @param values the given values.
   * @return {@code this} assertion object.
   * @throws NullPointerException if the given argument is {@code null}.
   * @throws IllegalArgumentException if the given argument is an empty array.
   * @throws AssertionError if the actual array is {@code null}.
   * @throws AssertionError if the actual array does not contain the given values.
   */
  public LongArrayAssert contains(long... values) {
    arrays.assertContains(description, actual, values);
    return this;
  }

  /**
   * Verifies that the actual array contains only the given values and nothing else, in any order.
   *
   * @param values the given values.
   * @return {@code this} assertion object.
   * @throws NullPointerException if the given argument is {@code null}.
   * @throws IllegalArgumentException if the given argument is an empty array.
   * @throws AssertionError if the actual array is {@code null}.
   * @throws AssertionError if the actual array does not contain the given values, i.e. the actual array contains some
   *           or none of the given values, or the actual array contains more values than the given ones.
   */
  public LongArrayAssert containsOnly(long... values) {
    arrays.assertContainsOnly(description, actual, values);
    return this;
  }

  /**
   * Verifies that the actual array contains the given sequence, without any other values between them.
   *
   * @param sequence the sequence of values to look for.
   * @return this assertion object.
   * @throws AssertionError if the actual array is {@code null}.
   * @throws AssertionError if the given array is {@code null}.
   * @throws AssertionError if the actual array does not contain the given sequence.
   */
  public LongArrayAssert containsSequence(long... sequence) {
    arrays.assertContainsSequence(description, actual, sequence);
    return this;
  }

  /**
   * Verifies that the actual array contains the given value at the given index.
   *
   * @param value the value to look for.
   * @param index the index where the value should be stored in the actual array.
   * @return this assertion object.
   * @throws AssertionError if the actual array is {@code null} or empty.
   * @throws NullPointerException if the given {@code Index} is {@code null}.
   * @throws IndexOutOfBoundsException if the value of the given {@code Index} is equal to or greater than the size of
   *           the actual array.
   * @throws AssertionError if the actual array does not contain the given value at the given index.
   */
  public LongArrayAssert contains(long value, Index index) {
    arrays.assertContains(description, actual, value, index);
    return this;
  }

  /**
   * Verifies that the actual array does not contain the given values.
   *
   * @param values the given values.
   * @return {@code this} assertion object.
   * @throws NullPointerException if the given argument is {@code null}.
   * @throws IllegalArgumentException if the given argument is an empty array.
   * @throws AssertionError if the actual array is {@code null}.
   * @throws AssertionError if the actual array contains any of the given values.
   */
  public LongArrayAssert doesNotContain(long... values) {
    arrays.assertDoesNotContain(description, actual, values);
    return this;
  }

  /**
   * Verifies that the actual array does not contain the given value at the given index.
   *
   * @param value the value to look for.
   * @param index the index where the value should be stored in the actual array.
   * @return this assertion object.
   * @throws AssertionError if the actual array is {@code null}.
   * @throws NullPointerException if the given {@code Index} is {@code null}.
   * @throws AssertionError if the actual array contains the given value at the given index.
   */
  public LongArrayAssert doesNotContain(long value, Index index) {
    arrays.assertDoesNotContain(description, actual, value, index);
    return this;
  }

  /**
   * Verifies that the actual array does not contain duplicates.
   *
   * @return {@code this} assertion object.
   * @throws AssertionError if the actual array is {@code null}.
   * @throws AssertionError if the actual array contains duplicates.
   */
  public LongArrayAssert doesNotHaveDuplicates() {
    arrays.assertDoesNotHaveDuplicates(description, actual);
    return this;
  }

  /**
   * Verifies that the actual array starts with the given sequence of values, without any other values between them.
   * Similar to <code>{@link #containsSequence(long...)}</code>, but it also verifies that the first element in the
   * sequence is also first element of the actual array.
   *
   * @param sequence the sequence of values to look for.
   * @return this assertion object.
   * @throws NullPointerException if the given argument is {@code null}.
   * @throws IllegalArgumentException if the given argument is an empty array.
   * @throws AssertionError if the actual array is {@code null}.
   * @throws AssertionError if the actual array does not start with the given sequence.
   */
  public LongArrayAssert startsWith(long... sequence) {
    arrays.assertStartsWith(description, actual, sequence);
    return this;
  }

  /**
   * Verifies that the actual array ends with the given sequence of values, without any other values between them.
   * Similar to <code>{@link #containsSequence(long...)}</code>, but it also verifies that the last element in the
   * sequence is also last element of the actual array.
   *
   * @param sequence the sequence of values to look for.
   * @return this assertion object.
   * @throws NullPointerException if the given argument is {@code null}.
   * @throws IllegalArgumentException if the given argument is an empty array.
   * @throws AssertionError if the actual array is {@code null}.
   * @throws AssertionError if the actual array does not end with the given sequence.
   */
  public LongArrayAssert endsWith(long... sequence) {
    arrays.assertEndsWith(description, actual, sequence);
    return this;
  }
}
