/*
 * Created on Sep 17, 2010
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
package org.fest.assertions.internal;

import static org.fest.assertions.error.ShouldBeEmpty.shouldBeEmpty;
import static org.fest.assertions.error.ShouldBeNullOrEmpty.shouldBeNullOrEmpty;
import static org.fest.assertions.error.ShouldContain.shouldContain;
import static org.fest.assertions.error.ShouldContainNull.shouldContainNull;
import static org.fest.assertions.error.ShouldContainOnly.shouldContainOnly;
import static org.fest.assertions.error.ShouldContainSequence.shouldContainSequence;
import static org.fest.assertions.error.ShouldEndWith.shouldEndWith;
import static org.fest.assertions.error.ShouldHaveSize.shouldHaveSize;
import static org.fest.assertions.error.ShouldNotBeEmpty.shouldNotBeEmpty;
import static org.fest.assertions.error.ShouldNotContain.shouldNotContain;
import static org.fest.assertions.error.ShouldNotContainNull.shouldNotContainNull;
import static org.fest.assertions.error.ShouldNotHaveDuplicates.shouldNotHaveDuplicates;
import static org.fest.assertions.error.ShouldStartWith.shouldStartWith;
import static org.fest.assertions.internal.CommonErrors.*;
import static org.fest.util.Collections.*;
import static org.fest.util.Objects.areEqual;

import java.util.*;

import org.fest.assertions.core.AssertionInfo;
import org.fest.util.VisibleForTesting;

/**
 * Reusable assertions for <code>{@link Iterable}</code>s.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class Iterables {

  private static final Iterables INSTANCE = new Iterables();

  /**
   * Returns the singleton instance of this class.
   * @return the singleton instance of this class.
   */
  public static Iterables instance() {
    return INSTANCE;
  }

  @VisibleForTesting Failures failures = Failures.instance();

  @VisibleForTesting Iterables() {}

  private <T> Collection<T> asCollection(Iterable<T> iterable) {
	  ArrayList<T> list = new ArrayList<T>();
	  for (T element: iterable) {
		  list.add(element);
	  }
	  return list;
  }
  
  /**
   * Asserts that the given <code>{@link Collection}</code> is {@code null} or empty.
   * @param info contains information about the assertion.
   * @param actual the given {@code Iterable}.
   * @throws AssertionError if the given {@code Iterable} is not {@code null} *and* contains one or more elements.
   */
  public void assertNullOrEmpty(AssertionInfo info, Iterable<?> actual) {
    if (actual == null || asCollection(actual).isEmpty()) return;
    throw failures.failure(info, shouldBeNullOrEmpty(actual));
  }

  /**
   * Asserts that the given {@code Iterable} is empty.
   * @param info contains information about the assertion.
   * @param actual the given {@code Iterable}.
   * @throws AssertionError if the given {@code Iterable} is {@code null}.
   * @throws AssertionError if the given {@code Iterable} is not empty.
   */
  public void assertEmpty(AssertionInfo info, Iterable<?> actual) {
    assertNotNull(info, actual);
    if (asCollection(actual).isEmpty()) return;
    throw failures.failure(info, shouldBeEmpty(actual));
  }

  /**
   * Asserts that the given {@code Iterable} is not empty.
   * @param info contains information about the assertion.
   * @param actual the given {@code Iterable}.
   * @throws AssertionError if the given {@code Iterable} is {@code null}.
   * @throws AssertionError if the given {@code Iterable} is empty.
   */
  public void assertNotEmpty(AssertionInfo info, Iterable<?> actual) {
    assertNotNull(info, actual);
    if (!asCollection(actual).isEmpty()) return;
    throw failures.failure(info, shouldNotBeEmpty());
  }

  /**
   * Asserts that the number of elements in the given {@code Iterable} is equal to the expected one.
   * @param info contains information about the assertion.
   * @param actual the given {@code Iterable}.
   * @param expectedSize the expected size of {@code actual}.
   * @throws AssertionError if the given {@code Iterable} is {@code null}.
   * @throws AssertionError if the number of elements in the given {@code Iterable} is different than the expected
   * one.
   */
  public void assertHasSize(AssertionInfo info, Iterable<?> actual, int expectedSize) {
    assertNotNull(info, actual);
    int sizeOfActual = asCollection(actual).size();
    if (sizeOfActual == expectedSize) return;
    throw failures.failure(info, shouldHaveSize(actual, sizeOfActual, expectedSize));
  }

  /**
   * Asserts that the given {@code Iterable} contains the given values, in any order.
   * @param info contains information about the assertion.
   * @param actual the given {@code Iterable}.
   * @param values the values that are expected to be in the given {@code Iterable}.
   * @throws NullPointerException if the array of values is {@code null}.
   * @throws AssertionError if the given {@code Iterable} is {@code null}.
   * @throws AssertionError if the given {@code Iterable} does not contain the given values.
   */
  public void assertContains(AssertionInfo info, Iterable<?> actual, Object[] values) {
    checkIsNotNull(values);
    assertNotNull(info, actual);
    Set<Object> notFound = new LinkedHashSet<Object>();
    for (Object value : values) if (!asCollection(actual).contains(value)) notFound.add(value);
    if (notFound.isEmpty()) return;
    throw failures.failure(info, shouldContain(actual, values, notFound));
  }

  /**
   * Asserts that the given {@code Iterable} contains only the given values and nothing else, in any order.
   * @param info contains information about the assertion.
   * @param actual the given {@code Iterable}.
   * @param values the values that are expected to be in the given {@code Iterable}.
   * @throws NullPointerException if the array of values is {@code null}.
   * @throws AssertionError if the given {@code Iterable} is {@code null}.
   * @throws AssertionError if the given {@code Iterable} does not contain the given values or if the given
   * {@code Iterable} contains values that are not in the given array.
   */
  public void assertContainsOnly(AssertionInfo info, Iterable<?> actual, Object[] values) {
    checkIsNotNull(values);
    assertNotNull(info, actual);
    Set<Object> notExpected = new LinkedHashSet<Object>(asCollection(actual));
    Set<Object> notFound = containsOnly(notExpected, values);
    if (notExpected.isEmpty() && notFound.isEmpty()) return;
    throw failures.failure(info, shouldContainOnly(actual, values, notFound, notExpected));
  }

  private static Set<Object> containsOnly(Set<Object> actual, Object[] values) {
    Set<Object> notFound = new LinkedHashSet<Object>();
    for (Object o : set(values)) {
      if (actual.contains(o)) actual.remove(o);
      else notFound.add(o);
    }
    return notFound;
  }

  /**
   * Verifies that the given <code>{@link Iterable}</code> contains the given sequence of objects, without any other
   * objects between them.
   * @param info contains information about the assertion.
   * @param actual the given {@code Iterable}.
   * @param sequence the sequence of objects to look for.
   * @throws AssertionError if the given {@code Iterable} is {@code null}.
   * @throws NullPointerException if the given sequence is {@code null}.
   * @throws AssertionError if the given {@code Iterable} does not contain the given sequence of objects.
   */
  public void assertContainsSequence(AssertionInfo info, Iterable<?> actual, Object[] sequence) {
    checkIsNotNull(sequence);
    if (sequence.length == 0) {
    	return;
    }
    assertNotNull(info, actual);
    boolean firstAlreadyFound = false;
    int i = 0;
    int sequenceSize = sequence.length;
    for (Object o : actual) {
      if (i >= sequenceSize) break;
      if (!firstAlreadyFound) {
        if (!areEqual(o, sequence[i])) continue;
        firstAlreadyFound = true;
        i++;
        continue;
      }
      if (areEqual(o, sequence[i++])) continue;
      throw actualDoesNotContainSequence(info, actual, sequence);
    }
    if (!firstAlreadyFound || i < sequenceSize) throw actualDoesNotContainSequence(info, actual, sequence);
  }

  private AssertionError actualDoesNotContainSequence(AssertionInfo info, Iterable<?> actual, Object[] sequence) {
    return failures.failure(info, shouldContainSequence(actual, sequence));
  }

  /**
   * Asserts that the given {@code Iterable} does not contain the given values.
   * @param info contains information about the assertion.
   * @param actual the given {@code Iterable}.
   * @param values the values that are expected not to be in the given {@code Iterable}.
   * @throws NullPointerException if the array of values is {@code null}.
   * @throws AssertionError if the given {@code Iterable} is {@code null}.
   * @throws AssertionError if the given {@code Iterable} contains any of given values.
   */
  public void assertDoesNotContain(AssertionInfo info, Iterable<?> actual, Object[] values) {
    checkIsNotNull(values);
    assertNotNull(info, actual);
    Set<Object> found = new LinkedHashSet<Object>();
    for (Object o: values) if (asCollection(actual).contains(o)) found.add(o);
    if (found.isEmpty()) return;
    throw failures.failure(info, shouldNotContain(actual, values, found));
  }

  /**
   * Asserts that the given {@code Iterable} does not have duplicate values.
   * @param info contains information about the assertion.
   * @param actual the given {@code Iterable}.
   * @throws NullPointerException if the array of values is {@code null}.
   * @throws IllegalArgumentException if the array of values is empty.
   * @throws AssertionError if the given {@code Iterable} is {@code null}.
   * @throws AssertionError if the given {@code Iterable} contains duplicate values.
   */
  public void assertDoesNotHaveDuplicates(AssertionInfo info, Iterable<?> actual) {
    assertNotNull(info, actual);
    Collection<?> duplicates = duplicatesFrom(asCollection(actual));
    if (duplicates.isEmpty()) return;
    throw failures.failure(info, shouldNotHaveDuplicates(actual, duplicates));
  }

  /**
   * Verifies that the given {@code Iterable} starts with the given sequence of objects, without any other objects
   * between them. Similar to <code>{@link #assertContainsSequence(AssertionInfo, Iterable, Object[])}</code>, but
   * it also verifies that the first element in the sequence is also the first element of the given {@code Iterable}.
   * @param info contains information about the assertion.
   * @param actual the given {@code Iterable}.
   * @param sequence the sequence of objects to look for.
   * @throws NullPointerException if the given argument is {@code null}.
   * @throws AssertionError if the given {@code Iterable} is {@code null}.
   * @throws AssertionError if the given {@code Iterable} does not start with the given sequence of objects.
   */
  public void assertStartsWith(AssertionInfo info, Iterable<?> actual, Object[] sequence) {
    checkIsNotNull(sequence);
    assertNotNull(info, actual);
    int sequenceSize = sequence.length;
    if (asCollection(actual).size() < sequenceSize) throw actualDoesNotStartWithSequence(info, actual, sequence);
    int i = 0;
    for (Object o: actual) {
      if (i >= sequenceSize) break;
      if (areEqual(o, sequence[i++])) continue;
      throw actualDoesNotStartWithSequence(info, actual, sequence);
    }
  }

  private AssertionError actualDoesNotStartWithSequence(AssertionInfo info, Iterable<?> actual, Object[] sequence) {
    return failures.failure(info, shouldStartWith(actual, sequence));
  }

  /**
   * Verifies that the given {@code Iterable} ends with the given sequence of objects, without any other objects
   * between them. Similar to <code>{@link #assertContainsSequence(AssertionInfo, Iterable, Object[])}</code>, but
   * it also verifies that the last element in the sequence is also the last element of the given {@code Iterable}.
   * @param info contains information about the assertion.
   * @param actual the given {@code Iterable}.
   * @param sequence the sequence of objects to look for.
   * @throws NullPointerException if the given argument is {@code null}.
   * @throws AssertionError if the given {@code Iterable} is {@code null}.
   * @throws AssertionError if the given {@code Iterable} does not end with the given sequence of objects.
   */
  public void assertEndsWith(AssertionInfo info, Iterable<?> actual, Object[] sequence) {
    checkIsNotNull(sequence);
    assertNotNull(info, actual);
    int sequenceSize = sequence.length;
    int sizeOfActual = asCollection(actual).size();
    if (sizeOfActual < sequenceSize) throw actualDoesNotEndWithSequence(info, actual, sequence);
    int start = asCollection(actual).size() - sequenceSize;
    int sequenceIndex = 0, indexOfActual = 0;
    for (Object o: actual) {
      if (indexOfActual++ < start) continue;
      if (areEqual(o, sequence[sequenceIndex++])) continue;
      throw actualDoesNotEndWithSequence(info, actual, sequence);
    }
  }

  /**
   * Asserts that the given {@code Iterable} contains at least a null element.
   * @param info contains information about the assertion.
   * @param actual the given {@code Iterable}.
   * @throws AssertionError if the given {@code Iterable} is {@code null}.
   * @throws AssertionError if the given {@code Iterable} does not contain at least a null element.
   */
  public void assertContainsNull(AssertionInfo info, Iterable<?> actual) {
    assertNotNull(info, actual);
    if (!asCollection(actual).contains(null)) throw failures.failure(info, shouldContainNull(actual));
  }

  /**
   * Asserts that the given {@code Iterable} does not contain null elements.
   * @param info contains information about the assertion.
   * @param actual the given {@code Iterable}.
   * @throws AssertionError if the given {@code Iterable} is {@code null}.
   * @throws AssertionError if the given {@code Iterable} contains a null element.
   */
  public void assertDoesNotContainNull(AssertionInfo info, Iterable<?> actual) {
    assertNotNull(info, actual);
    if (asCollection(actual).contains(null)) throw failures.failure(info, shouldNotContainNull(actual));
  }

  private void checkIsNotNull(Object[] values) {
	 if (values == null) throw arrayOfValuesToLookForIsNull();
  }

  
  private void assertNotNull(AssertionInfo info, Iterable<?> actual) {
    Objects.instance().assertNotNull(info, actual);
  }

  private AssertionError actualDoesNotEndWithSequence(AssertionInfo info, Iterable<?> actual, Object[] sequence) {
    return failures.failure(info, shouldEndWith(actual, sequence));
  }
}
