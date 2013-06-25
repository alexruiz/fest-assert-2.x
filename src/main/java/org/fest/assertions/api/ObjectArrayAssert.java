/*
 * Created on Jul 26, 2010
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

import javax.annotation.Nonnull;

import org.fest.assertions.core.EnumerableAssert;
import org.fest.assertions.core.IndexedObjectEnumerableAssert;
import org.fest.assertions.data.Index;
import org.fest.assertions.description.Description;
import org.fest.assertions.internal.ObjectArrays;
import org.fest.util.VisibleForTesting;

/**
 * Assertion methods for arrays of objects. To create an instance of this class, invoke
 * {@link Assertions#assertThat(Object[])}.
 * 
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class ObjectArrayAssert extends AbstractAssert<ObjectArrayAssert, Object[]> implements
    EnumerableAssert<ObjectArrayAssert> {

  @VisibleForTesting
  ObjectArrays arrays = ObjectArrays.instance();

  protected ObjectArrayAssert(Object[] actual) {
    super(actual, ObjectArrayAssert.class);
  }

  protected ObjectArrayAssert(Object[] actual, Description description) {
    super(actual, ObjectArrayAssert.class, description);
  }

  /** {@inheritDoc} */
  @Override
  public ObjectArrayAssert isNullOrEmpty() {
    arrays.assertNullOrEmpty(description, actual);
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public ObjectArrayAssert isEmpty() {
    arrays.assertEmpty(description, actual);
    return this;
  }

  /** {@inheritDoc} */
  @Override
  @Nonnull
  public ObjectArrayAssert isNotEmpty() {
    arrays.assertNotEmpty(description, actual);
    return this;
  }

  /** {@inheritDoc} */
  @Override
  @Nonnull
  public ObjectArrayAssert hasSize(int expectedSize) {
    arrays.assertHasSize(description, actual, expectedSize);
    return this;
  }

  /**
   * Verifies that the given array contains the given object at the given index.
   * 
   * @param value the object to look for.
   * @param index the index where the object should be stored in the given array.
   * @return {@code this} assertion object.
   * @throws AssertionError if the given array is {@code null} or empty.
   * @throws NullPointerException if the given {@code Index} is {@code null}.
   * @throws IndexOutOfBoundsException if the value of the given {@code Index} is equal to or greater than the size of
   *           the given array.
   * @throws AssertionError if the given array does not contain the given object at the given index.
   */
  public ObjectArrayAssert contains(Object value, Index index) {
    arrays.assertContains(description, actual, value, index);
    return this;
  }

  /**
   * Verifies that the given array contains the given object at the given index.
   * 
   * @param value the object to look for.
   * @param index the index where the object should be stored in the given array.
   * @return {@code this} assertion object.
   * @throws AssertionError if the given array is {@code null} or empty.
   * @throws NullPointerException if the given {@code Index} is {@code null}.
   * @throws IndexOutOfBoundsException if the value of the given {@code Index} is equal to or greater than the size of
   *           the given array.
   * @throws AssertionError if the given array does not contain the given object at the given index.
   */
  public ObjectArrayAssert doesNotContain(Object value, Index index) {
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
  public ObjectArrayAssert contains(Object... values) {
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
  public ObjectArrayAssert containsOnly(Object... values) {
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
  public ObjectArrayAssert containsSequence(Object... sequence) {
    arrays.assertContainsSequence(description, actual, sequence);
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
  public ObjectArrayAssert doesNotContain(Object... values) {
    arrays.assertDoesNotContain(description, actual, values);
    return this;
  }

  /**
   * Verifies that the actual array does not contain duplicates.
   * 
   * @return {@code this} assertion object.
   * @throws AssertionError if the actual array is {@code null}.
   * @throws AssertionError if the actual array contains duplicates.
   */
  public ObjectArrayAssert doesNotHaveDuplicates() {
    arrays.assertDoesNotHaveDuplicates(description, actual);
    return this;
  }

  /**
   * Verifies that the actual array starts with the given sequence of values, without any other values between them.
   * Similar to <code>{@link #containsSequence(Object...)}</code>, but it also verifies that the first element in the
   * sequence is also first element of the actual array.
   * 
   * @param sequence the sequence of values to look for.
   * @return this assertion object.
   * @throws NullPointerException if the given argument is {@code null}.
   * @throws IllegalArgumentException if the given argument is an empty array.
   * @throws AssertionError if the actual array is {@code null}.
   * @throws AssertionError if the actual array does not start with the given sequence.
   */
  public ObjectArrayAssert startsWith(Object... sequence) {
    arrays.assertStartsWith(description, actual, sequence);
    return this;
  }

  /**
   * Verifies that the actual array ends with the given sequence of values, without any other values between them.
   * Similar to <code>{@link #containsSequence(Object...)}</code>, but it also verifies that the last element in the
   * sequence is also last element of the actual array.
   * 
   * @param sequence the sequence of values to look for.
   * @return this assertion object.
   * @throws NullPointerException if the given argument is {@code null}.
   * @throws IllegalArgumentException if the given argument is an empty array.
   * @throws AssertionError if the actual array is {@code null}.
   * @throws AssertionError if the actual array does not end with the given sequence.
   */
  public ObjectArrayAssert endsWith(Object... sequence) {
    arrays.assertEndsWith(description, actual, sequence);
    return this;
  }

  /**
   * Verifies that the actual array contains the {@code null} value(s), in any order.
   * 
   * @return {@code this} assertion object.
   * @throws NullPointerException if the given argument is {@code null}.
   * @throws IllegalArgumentException if the given argument is an empty array.
   * @throws AssertionError if the actual array is {@code null}.
   * @throws AssertionError if the actual array does not contain the given values.
   */
  public ObjectArrayAssert containsNull() {
    arrays.assertContainsNull(description, actual);
    return this;
  }

  /**
   * Verifies that the actual array does not contain the {@code null} value(s), in any order.
   * 
   * @return {@code this} assertion object.
   * @throws NullPointerException if the given argument is {@code null}.
   * @throws IllegalArgumentException if the given argument is an empty array.
   * @throws AssertionError if the actual array is {@code null}.
   * @throws AssertionError if the actual array does not contain the given values.
   */
  public ObjectArrayAssert doesNotContainNull() {
    arrays.assertDoesNotContainNull(description, actual);
    return this;
  }
}
