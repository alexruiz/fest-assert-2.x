/*
 * Created on May 1, 2013
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
package org.fest.assertions.internal;

import static org.fest.assertions.error.ShouldBeEmpty.shouldBeEmpty;
import static org.fest.assertions.error.ShouldBeNullOrEmpty.shouldBeNullOrEmpty;
import static org.fest.assertions.error.ShouldContainOnly.shouldContainOnly;
import static org.fest.assertions.error.ShouldHaveSize.shouldHaveSize;
import static org.fest.assertions.error.ShouldNotBeLessThan.shouldNotBeLessThan;
import static org.fest.assertions.error.ShouldNotContain.shouldNotContain;
import static org.fest.assertions.internal.CommonErrors.arrayOfValuesToLookForIsEmpty;
import static org.fest.assertions.internal.CommonErrors.arrayOfValuesToLookForIsNull;

import java.util.LinkedHashSet;
import java.util.Set;

import org.fest.assertions.description.Description;
import org.fest.assertions.error.ShouldContain;
import org.fest.assertions.error.ShouldNotBeEmpty;
import org.fest.util.VisibleForTesting;

/**
 * Reusable assertions for <code>{@link Set}</code>.
 *
 * @author Yvonne Wang
 */
public class Sets {

  private static final Sets INSTANCE = new Sets();

  /**
   * Returns the singleton instance of this class.
   *
   * @return the singleton instance of this class.
   */
  public static Sets instance() {
    return INSTANCE;
  }

  @VisibleForTesting
  Failures failures = Failures.instance();

  /**
   * Asserts that the given <code>{@link Set}</code> is {@code null} or empty.
   *
   * @param description contains information about the assertion.
   * @param actual the given {@code Set}.
   * @throws AssertionError if the given {@code Set} is not {@code null} *and* contains one or more elements.
   */
  public void assertNullOrEmpty(Description description, Set<?> actual) {
    if (actual == null || actual.isEmpty()) {
      return;
    }
    throw failures.failure(description, shouldBeNullOrEmpty(actual));
  }

  /**
   * Asserts that the given {@code Set} is empty.
   *
   * @param description contains information about the assertion.
   * @param actual the given {@code Set}.
   * @throws AssertionError if the given {@code Set} is {@code null}.
   * @throws AssertionError if the given {@code Set} is not empty.
   */
  public void assertEmpty(Description description, Set<?> actual) {
    assertNotNull(description, actual);
    if (!actual.isEmpty()) {
      throw failures.failure(description, shouldBeEmpty(actual));
    }
  }

  /**
   * Asserts that the given {@code Set} is not empty.
   *
   * @param description contains information about the assertion.
   * @param actual the given {@code List}.
   * @throws AssertionError if the given {@code Set} is {@code null}.
   * @throws AssertionError if the given {@code Set} is not empty.
   */
  public void assertNotEmpty(Description description, Set<?> actual) {
    assertNotNull(description, actual);
    checkNotEmpty(description, actual);
  }

  private void checkNotEmpty(Description description, Set<?> actual) {
    if (actual.isEmpty()) {
      throw failures.failure(description, ShouldNotBeEmpty.shouldNotBeEmpty());
    }
  }

  /**
   * Asserts that the given {@code Set} contains the given object.
   *
   * @param description contains information about the assertion.
   * @param actual the given {@code Set}.
   * @param values the object to look for.
   * @throws AssertionError if the given {@code Set} is {@code null} or empty.
   * @throws AssertionError if the given {@code Set} does not contain the given object.
   */
  public void assertContains(Description description, Set<?> actual, Object[] values) {
    assertNotNull(description, actual);
    checkNotEmpty(description, actual);
    checkIsNotNullAndNotEmpty(values);
    Set<Object> notFound = new LinkedHashSet<Object>();
    for (Object value : values) {
      if (!actual.contains(value)) {
        notFound.add(value);
      }
    }
    if (!notFound.isEmpty()) {
      throw failures.failure(description, ShouldContain.shouldContain(actual, values, notFound));
    }
  }

  /**
   * Asserts that the given {@code Set} does not contain the given values.
   *
   * @param description contains information about the assertion.
   * @param actual the given {@code Set}.
   * @param values the entries that are expected to be in the given {@code Set}.
   * @throws NullPointerException if the array of values is {@code null}.
   * @throws IllegalArgumentException if the array of values is empty.
   * @throws NullPointerException if any of the values in the given array is {@code null}.
   * @throws AssertionError if the given {@code Set} is {@code null}.
   * @throws AssertionError if the given {@code Set} contains any of the given values.
   */
  public void assertDoesNotContain(Description description, Set<?> actual, Object[] values) {
    checkIsNotNullAndNotEmpty(values);
    assertNotNull(description, actual);
    checkNotEmpty(description, actual);
    Set<Object> found = new LinkedHashSet<Object>();
    for (Object value : values) {
      if (actual.contains(value)) {
        found.add(value);
      }
    }
    if (!found.isEmpty()) {
      throw failures.failure(description, shouldNotContain(actual, values, found));
    }
  }

  /**
   * Asserts that the given {@code Set} contains only the given entries, nothing else.
   *
   * @param description contains information about the assertion.
   * @param actual the given {@code Set}.
   * @param values the values that are expected to be in the given {@code Set}.
   * @throws NullPointerException if the array of values is {@code null}.
   * @throws IllegalArgumentException if the array of values is empty.
   * @throws NullPointerException if any of the values in the given array is {@code null}.
   * @throws AssertionError if the given {@code Set} is {@code null}.
   * @throws AssertionError if the given {@code Set} does not contain the given values.
   */
  public void assertContainsOnly(Description description, Set<?> actual, Object[] values) {
    assertNotNull(description, actual);
    checkNotEmpty(description, actual);
    checkIsNotNullAndNotEmpty(values);
    Set<Object> notExpected = new LinkedHashSet<Object>(actual);
    Set<Object> notFound = containsOnly(actual, notExpected, values);
    if (!notFound.isEmpty() || !notExpected.isEmpty()) {
      throw failures.failure(description, shouldContainOnly(actual, values, notFound, notExpected));
    }
  }

  /**
   * Asserts that the number of values in the given {@code Set} is equal to the expected one.
   *
   * @param description contains information about the assertion.
   * @param actual the given {@code Set}.
   * @param expectedSize the expected size of {@code actual}.
   * @throws AssertionError if the given {@code Set} is {@code null}.
   * @throws AssertionError if the number of values in the given {@code Set} is different than the expected one.
   */
  public void assertHasSize(Description description, Set<?> actual, int expectedSize) {
    assertNotNull(description, actual);
    if (expectedSize < 0) {
      throw failures.failure(description, shouldNotBeLessThan(expectedSize, 0));
    }
    int sizeOfActual = actual.size();
    if (sizeOfActual != expectedSize) {
      throw failures.failure(description, shouldHaveSize(actual, sizeOfActual, expectedSize));
    }
  }

  private void checkIsNotNullAndNotEmpty(Object[] values) {
    if (values == null) {
      throw arrayOfValuesToLookForIsNull();
    }
    if (values.length == 0) {
      throw arrayOfValuesToLookForIsEmpty();
    }
  }

  private Set<Object> containsOnly(Set<?> actual, Set<?> notExpected, Object[] values) {
    Set<Object> notFound = new LinkedHashSet<Object>();
    for (Object value : values) {
      if (!actual.contains(value)) {
        notFound.add(value);
      } else {
        notExpected.remove(value);
      }
    }
    return notFound;
  }

  private void assertNotNull(Description description, Set<?> actual) {
    Objects.instance().assertNotNull(description, actual);
  }
}
