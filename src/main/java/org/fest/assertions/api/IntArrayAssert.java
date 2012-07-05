/*
 * Created on Dec 14, 2010
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

import java.util.Comparator;

import org.fest.assertions.core.ArraySortedAssert;
import org.fest.assertions.core.EnumerableAssert;
import org.fest.assertions.data.Index;
import org.fest.assertions.internal.IntArrays;
import org.fest.util.ComparatorBasedComparisonStrategy;
import org.fest.util.VisibleForTesting;

/**
 * Assertion methods for arrays of {@code int}s.
 * <p>
 * To create an instance of this class, invoke <code>{@link Assertions#assertThat(int[])}</code>.
 * </p>
 * 
 * @author Yvonne Wang
 * @author Alex Ruiz
 * @author Joel Costigliola
 * @author Mikhail Mazursky
 * @author Nicolas François
 */
public class IntArrayAssert extends AbstractAssert<IntArrayAssert, int[]> implements EnumerableAssert<IntArrayAssert, Integer>,
    ArraySortedAssert<IntArrayAssert, Integer> {

  @VisibleForTesting
  IntArrays arrays = IntArrays.instance();

  protected IntArrayAssert(int[] actual) {
    super(actual, IntArrayAssert.class);
  }

  /** {@inheritDoc} */
  public void isNullOrEmpty() {
    arrays.assertNullOrEmpty(info, actual);
  }

  /** {@inheritDoc} */
  public void isEmpty() {
    arrays.assertEmpty(info, actual);
  }

  /** {@inheritDoc} */
  public IntArrayAssert isNotEmpty() {
    arrays.assertNotEmpty(info, actual);
    return this;
  }

  /** {@inheritDoc} */
  public IntArrayAssert hasSize(int expected) {
    arrays.assertHasSize(info, actual, expected);
    return this;
  }
  
  /** {@inheritDoc} */
  public IntArrayAssert hasSameSizeAs(Object[] other) {
	arrays.assertHasSameSizeAs(info, actual, other);
    return this;
  }
  
  /** {@inheritDoc} */
  public IntArrayAssert hasSameSizeAs(Iterable<?> other) {
	arrays.assertHasSameSizeAs(info, actual, other);
    return this;
  }   

  /**
   * Verifies that the actual array contains the given values, in any order.
   * @param values the given values.
   * @return {@code this} assertion object.
   * @throws NullPointerException if the given argument is {@code null}.
   * @throws IllegalArgumentException if the given argument is an empty array.
   * @throws AssertionError if the actual array is {@code null}.
   * @throws AssertionError if the actual array does not contain the given values.
   */
  public IntArrayAssert contains(int... values) {
    arrays.assertContains(info, actual, values);
    return this;
  }

  /**
   * Verifies that the actual array contains only the given values and nothing else, in any order.
   * @param values the given values.
   * @return {@code this} assertion object.
   * @throws NullPointerException if the given argument is {@code null}.
   * @throws IllegalArgumentException if the given argument is an empty array.
   * @throws AssertionError if the actual array is {@code null}.
   * @throws AssertionError if the actual array does not contain the given values, i.e. the actual array contains some
   *           or none of the given values, or the actual array contains more values than the given ones.
   */
  public IntArrayAssert containsOnly(int... values) {
    arrays.assertContainsOnly(info, actual, values);
    return this;
  }

  /**
   * Verifies that the actual array contains the given sequence, without any other values between them.
   * @param sequence the sequence of values to look for.
   * @return this assertion object.
   * @throws AssertionError if the actual array is {@code null}.
   * @throws AssertionError if the given array is {@code null}.
   * @throws AssertionError if the actual array does not contain the given sequence.
   */
  public IntArrayAssert containsSequence(int... sequence) {
    arrays.assertContainsSequence(info, actual, sequence);
    return this;
  }

  /**
   * Verifies that the actual array contains the given value at the given index.
   * @param value the value to look for.
   * @param index the index where the value should be stored in the actual array.
   * @return this assertion object.
   * @throws AssertionError if the actual array is {@code null} or empty.
   * @throws NullPointerException if the given {@code Index} is {@code null}.
   * @throws IndexOutOfBoundsException if the value of the given {@code Index} is equal to or greater than the size of
   *           the actual array.
   * @throws AssertionError if the actual array does not contain the given value at the given index.
   */
  public IntArrayAssert contains(int value, Index index) {
    arrays.assertContains(info, actual, value, index);
    return this;
  }

  /**
   * Verifies that the actual array does not contain the given values.
   * @param values the given values.
   * @return {@code this} assertion object.
   * @throws NullPointerException if the given argument is {@code null}.
   * @throws IllegalArgumentException if the given argument is an empty array.
   * @throws AssertionError if the actual array is {@code null}.
   * @throws AssertionError if the actual array contains any of the given values.
   */
  public IntArrayAssert doesNotContain(int... values) {
    arrays.assertDoesNotContain(info, actual, values);
    return this;
  }

  /**
   * Verifies that the actual array does not contain the given value at the given index.
   * @param value the value to look for.
   * @param index the index where the value should be stored in the actual array.
   * @return this assertion object.
   * @throws AssertionError if the actual array is {@code null}.
   * @throws NullPointerException if the given {@code Index} is {@code null}.
   * @throws AssertionError if the actual array contains the given value at the given index.
   */
  public IntArrayAssert doesNotContain(int value, Index index) {
    arrays.assertDoesNotContain(info, actual, value, index);
    return this;
  }

  /**
   * Verifies that the actual array does not contain duplicates.
   * @return {@code this} assertion object.
   * @throws AssertionError if the actual array is {@code null}.
   * @throws AssertionError if the actual array contains duplicates.
   */
  public IntArrayAssert doesNotHaveDuplicates() {
    arrays.assertDoesNotHaveDuplicates(info, actual);
    return this;
  }

  /**
   * Verifies that the actual array starts with the given sequence of values, without any other values between them.
   * Similar to <code>{@link #containsSequence(int...)}</code>, but it also verifies that the first element in the
   * sequence is also first element of the actual array.
   * @param sequence the sequence of values to look for.
   * @return this assertion object.
   * @throws NullPointerException if the given argument is {@code null}.
   * @throws IllegalArgumentException if the given argument is an empty array.
   * @throws AssertionError if the actual array is {@code null}.
   * @throws AssertionError if the actual array does not start with the given sequence.
   */
  public IntArrayAssert startsWith(int... sequence) {
    arrays.assertStartsWith(info, actual, sequence);
    return this;
  }

  /**
   * Verifies that the actual array ends with the given sequence of values, without any other values between them.
   * Similar to <code>{@link #containsSequence(int...)}</code>, but it also verifies that the last element in the
   * sequence is also last element of the actual array.
   * @param sequence the sequence of values to look for.
   * @return this assertion object.
   * @throws NullPointerException if the given argument is {@code null}.
   * @throws IllegalArgumentException if the given argument is an empty array.
   * @throws AssertionError if the actual array is {@code null}.
   * @throws AssertionError if the actual array does not end with the given sequence.
   */
  public IntArrayAssert endsWith(int... sequence) {
    arrays.assertEndsWith(info, actual, sequence);
    return this;
  }

  /** {@inheritDoc} */
  public IntArrayAssert isSorted() {
    arrays.assertIsSorted(info, actual);
    return this;
  }

  /** {@inheritDoc} */
  public IntArrayAssert isSortedAccordingTo(Comparator<? super Integer> comparator) {
    arrays.assertIsSortedAccordingToComparator(info, actual, comparator);
    return this;
  }

  /** {@inheritDoc} */
  public IntArrayAssert usingElementComparator(Comparator<? super Integer> customComparator) {
    this.arrays = new IntArrays(new ComparatorBasedComparisonStrategy(customComparator));
    return myself;
  }

  /** {@inheritDoc} */
  public IntArrayAssert usingDefaultElementComparator() {
    this.arrays = IntArrays.instance();
    return myself;
  }
}
