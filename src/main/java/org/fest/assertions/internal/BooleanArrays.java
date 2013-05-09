/*
 * Created on Dec 15, 2010
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
package org.fest.assertions.internal;

import org.fest.assertions.data.Index;
import org.fest.assertions.description.Description;
import org.fest.util.VisibleForTesting;

/**
 * Reusable assertions for arrays of {@code boolean}s.
 *
 * @author Alex Ruiz
 * @author Joel Costigliola
 * @author Mikhail Mazursky
 * @author Nicolas François
 * @author Yvonne Wang
 */
public class BooleanArrays {

  private static final BooleanArrays INSTANCE = new BooleanArrays();

  /**
   * Returns the singleton instance of this class.
   *
   * @return the singleton instance of this class.
   */
  public static BooleanArrays instance() {
    return INSTANCE;
  }

  private final Arrays arrays = Arrays.instance();

  @VisibleForTesting
  Failures failures = Failures.instance();

  @VisibleForTesting
  BooleanArrays() {
  }

  /**
   * Asserts that the given array is {@code null} or empty.
   *
   * @param description contains information about the assertion.
   * @param actual the given array.
   * @throws AssertionError if the given array is not {@code null} *and* contains one or more elements.
   */
  public void assertNullOrEmpty(Description description, boolean[] actual) {
    arrays.assertNullOrEmpty(description, actual);
  }

  /**
   * Asserts that the given array is empty.
   *
   * @param description contains information about the assertion.
   * @param actual the given array.
   * @throws AssertionError if the given array is {@code null}.
   * @throws AssertionError if the given array is not empty.
   */
  public void assertEmpty(Description description, boolean[] actual) {
    arrays.assertEmpty(description, actual);
  }

  /**
   * Asserts that the given array is not empty.
   *
   * @param description contains information about the assertion.
   * @param actual the given array.
   * @throws AssertionError if the given array is {@code null}.
   * @throws AssertionError if the given array is empty.
   */
  public void assertNotEmpty(Description description, boolean[] actual) {
    arrays.assertNotEmpty(description, actual);
  }

  /**
   * Asserts that the number of elements in the given array is equal to the expected one.
   *
   * @param description contains information about the assertion.
   * @param actual the given array.
   * @param expectedSize the expected size of {@code actual}.
   * @throws AssertionError if the given array is {@code null}.
   * @throws AssertionError if the number of elements in the given array is different than the expected one.
   */
  public void assertHasSize(Description description, boolean[] actual, int expectedSize) {
    arrays.assertHasSize(description, actual, expectedSize);
  }

  /**
   * Asserts that the given array contains the given values, in any order.
   *
   * @param description contains information about the assertion.
   * @param actual the given array.
   * @param values the values that are expected to be in the given array.
   * @throws NullPointerException if the array of values is {@code null}.
   * @throws IllegalArgumentException if the array of values is empty.
   * @throws AssertionError if the given array is {@code null}.
   * @throws AssertionError if the given array does not contain the given values.
   */
  public void assertContains(Description description, boolean[] actual, boolean[] values) {
    arrays.assertContains(description, actual, values);
  }

  /**
   * Verifies that the given array contains the given value at the given index.
   *
   * @param description contains information about the assertion.
   * @param actual the given array.
   * @param value the value to look for.
   * @param index the index where the value should be stored in the given array.
   * @throws AssertionError if the given array is {@code null} or empty.
   * @throws NullPointerException if the given {@code Index} is {@code null}.
   * @throws IndexOutOfBoundsException if the value of the given {@code Index} is equal to or greater than the size of
   *           the given array.
   * @throws AssertionError if the given array does not contain the given value at the given index.
   */
  public void assertContains(Description description, boolean[] actual, boolean value, Index index) {
    arrays.assertContains(description, actual, value, index);
  }

  /**
   * Verifies that the given array does not contain the given value at the given index.
   *
   * @param description contains information about the assertion.
   * @param actual the given array.
   * @param value the value to look for.
   * @param index the index where the value should be stored in the given array.
   * @throws AssertionError if the given array is {@code null}.
   * @throws NullPointerException if the given {@code Index} is {@code null}.
   * @throws AssertionError if the given array contains the given value at the given index.
   */
  public void assertDoesNotContain(Description description, boolean[] actual, boolean value, Index index) {
    arrays.assertDoesNotContain(description, actual, value, index);
  }

  /**
   * Asserts that the given array contains only the given values and nothing else, in any order.
   *
   * @param description contains information about the assertion.
   * @param actual the given array.
   * @param values the values that are expected to be in the given array.
   * @throws NullPointerException if the array of values is {@code null}.
   * @throws IllegalArgumentException if the array of values is empty.
   * @throws AssertionError if the given array is {@code null}.
   * @throws AssertionError if the given array does not contain the given values or if the given array contains values
   *           that are not in the given array.
   */
  public void assertContainsOnly(Description description, boolean[] actual, boolean[] values) {
    arrays.assertContainsOnly(description, actual, values);
  }

  /**
   * Verifies that the given array contains the given sequence of values, without any other values between them.
   *
   * @param description contains information about the assertion.
   * @param actual the given array.
   * @param sequence the sequence of values to look for.
   * @throws AssertionError if the given array is {@code null}.
   * @throws NullPointerException if the given sequence is {@code null}.
   * @throws IllegalArgumentException if the given sequence is empty.
   * @throws AssertionError if the given array does not contain the given sequence of values.
   */
  public void assertContainsSequence(Description description, boolean[] actual, boolean[] sequence) {
    arrays.assertContainsSequence(description, actual, sequence);
  }

  /**
   * Asserts that the given array does not contain the given values.
   *
   * @param description contains information about the assertion.
   * @param actual the given array.
   * @param values the values that are expected not to be in the given array.
   * @throws NullPointerException if the array of values is {@code null}.
   * @throws IllegalArgumentException if the array of values is empty.
   * @throws AssertionError if the given array is {@code null}.
   * @throws AssertionError if the given array contains any of given values.
   */
  public void assertDoesNotContain(Description description, boolean[] actual, boolean[] values) {
    arrays.assertDoesNotContain(description, actual, values);
  }

  /**
   * Asserts that the given array does not have duplicate values.
   *
   * @param description contains information about the assertion.
   * @param actual the given array.
   * @throws NullPointerException if the array of values is {@code null}.
   * @throws IllegalArgumentException if the array of values is empty.
   * @throws AssertionError if the given array is {@code null}.
   * @throws AssertionError if the given array contains duplicate values.
   */
  public void assertDoesNotHaveDuplicates(Description description, boolean[] actual) {
    arrays.assertDoesNotHaveDuplicates(description, actual);
  }

  /**
   * Verifies that the given array starts with the given sequence of values, without any other values between them.
   * Similar to <code>{@link #assertContainsSequence(Description, boolean[], boolean[])}</code>, but it also verifies
   * that the first element in the sequence is also the first element of the given array.
   *
   * @param description contains information about the assertion.
   * @param actual the given array.
   * @param sequence the sequence of values to look for.
   * @throws NullPointerException if the given argument is {@code null}.
   * @throws IllegalArgumentException if the given argument is an empty array.
   * @throws AssertionError if the given array is {@code null}.
   * @throws AssertionError if the given array does not start with the given sequence of values.
   */
  public void assertStartsWith(Description description, boolean[] actual, boolean[] sequence) {
    arrays.assertStartsWith(description, actual, sequence);
  }

  /**
   * Verifies that the given array ends with the given sequence of values, without any other values between them.
   * Similar to <code>{@link #assertContainsSequence(Description, boolean[], boolean[])}</code>, but it also verifies
   * that the last element in the sequence is also the last element of the given array.
   *
   * @param description contains information about the assertion.
   * @param actual the given array.
   * @param sequence the sequence of values to look for.
   * @throws NullPointerException if the given argument is {@code null}.
   * @throws IllegalArgumentException if the given argument is an empty array.
   * @throws AssertionError if the given array is {@code null}.
   * @throws AssertionError if the given array does not end with the given sequence of values.
   */
  public void assertEndsWith(Description description, boolean[] actual, boolean[] sequence) {
    arrays.assertEndsWith(description, actual, sequence);
  }
}