/*
 * Created on Nov 19, 2010
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

import static org.fest.assertions.error.ShouldBeEmpty.shouldBeEmpty;
import static org.fest.assertions.error.ShouldBeNullOrEmpty.shouldBeNullOrEmpty;
import static org.fest.assertions.error.ShouldContain.shouldContain;
import static org.fest.assertions.error.ShouldContainAtIndex.shouldContainAtIndex;
import static org.fest.assertions.error.ShouldContainNull.shouldContainNull;
import static org.fest.assertions.error.ShouldContainOnly.shouldContainOnly;
import static org.fest.assertions.error.ShouldContainSequence.shouldContainSequence;
import static org.fest.assertions.error.ShouldEndWith.shouldEndWith;
import static org.fest.assertions.error.ShouldHaveSize.shouldHaveSize;
import static org.fest.assertions.error.ShouldNotContainAtIndex.shouldNotContainAtIndex;
import static org.fest.assertions.error.ShouldNotContainNull.shouldNotContainNull;
import static org.fest.assertions.error.ShouldNotHaveDuplicates.shouldNotHaveDuplicates;
import static org.fest.assertions.error.ShouldStartWith.shouldStartWith;
import static org.fest.assertions.internal.CommonErrors.arrayOfValuesToLookForIsEmpty;
import static org.fest.assertions.internal.CommonErrors.arrayOfValuesToLookForIsNull;
import static org.fest.assertions.internal.CommonValidations.checkIndexValueIsValid;
import static org.fest.util.Collections.duplicatesFrom;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.fest.assertions.data.Index;
import org.fest.assertions.description.Description;
import org.fest.assertions.error.ShouldNotBeEmpty;
import org.fest.util.VisibleForTesting;

/**
 * Reusable assertions for <code>{@link List}</code>s.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class Lists {

  private static final Lists INSTANCE = new Lists();
  @VisibleForTesting
  Failures failures = Failures.instance();

  /**
   * Returns the singleton instance of this class.
   *
   * @return the singleton instance of this class.
   */
  public static Lists instance() {
    return INSTANCE;
  }

  private static Set<Object> containsOnly(Set<Object> actual, Object[] values) {
    Set<Object> notFound = new LinkedHashSet<Object>();
    for (Object o : org.fest.util.Sets.newLinkedHashSet(values)) {
      if (actual.contains(o)) {
        actual.remove(o);
      } else {
        notFound.add(o);
      }
    }
    return notFound;
  }

  /**
   * Asserts that the given <code>{@link List}</code> is {@code null} or empty.
   *
   * @param description contains information about the assertion.
   * @param actual      the given {@code List}.
   * @throws AssertionError if the given {@code List} is not {@code null} *and* contains one or more elements.
   */
  public void assertNullOrEmpty(Description description, List<?> actual) {
    if (actual == null || actual.isEmpty()) {
      return;
    }
    throw failures.failure(description, shouldBeNullOrEmpty(actual));
  }

  /**
   * Asserts that the given {@code List} is empty.
   *
   * @param description contains information about the assertion.
   * @param actual      the given {@code List}.
   * @throws AssertionError if the given {@code List} is {@code null}.
   * @throws AssertionError if the given {@code List} is not empty.
   */
  public void assertEmpty(Description description, List<?> actual) {
    assertNotNull(description, actual);
    if (!actual.isEmpty()) {
      throw failures.failure(description, shouldBeEmpty(actual));
    }
  }

  /**
   * Asserts that the given {@code List} is not empty.
   *
   * @param description contains information about the assertion.
   * @param actual      the given {@code List}.
   * @throws AssertionError if the given {@code List} is {@code null}.
   * @throws AssertionError if the given {@code List} is not empty.
   */
  public void assertNotEmpty(Description description, List<?> actual) {
    assertNotNull(description, actual);
    checkNotEmpty(description, actual);
  }

  /**
   * Asserts that the number of elements in the given {@code List} is equal to the expected one.
   *
   * @param description  contains information about the assertion.
   * @param actual       the given {@code List}.
   * @param expectedSize the expected size of {@code actual}.
   * @throws AssertionError if the given {@code Collection} is {@code null}.
   * @throws AssertionError if the number of elements in the given {@code List} is different than the expected one.
   */
  public void assertHasSize(Description description, List<?> actual, int expectedSize) {
    assertNotNull(description, actual);
    int sizeOfActual = actual.size();
    if (sizeOfActual != expectedSize) {
      throw failures.failure(description, shouldHaveSize(actual, sizeOfActual, expectedSize));
    }
  }

  /**
   * Asserts that the given {@code List} contains the given object at the given index.
   *
   * @param description contains information about the assertion.
   * @param actual      the given {@code List}.
   * @param value       the object to look for.
   * @param index       the index where the object should be stored in the given {@code List}.
   * @throws AssertionError            if the given {@code List} is {@code null} or empty.
   * @throws NullPointerException      if the given {@code Index} is {@code null}.
   * @throws IndexOutOfBoundsException if the value of the given {@code Index} is equal to or greater than the size of
   *                                   the given {@code List}.
   * @throws AssertionError            if the given {@code List} does not contain the given object at the given index.
   */
  public void assertContains(Description description, List<?> actual, Object value, Index index) {
    assertNotNull(description, actual);
    checkNotEmpty(description, actual);
    checkIndexValueIsValid(index, actual.size() - 1);
    Object actualElement = actual.get(index.value);
    if (!areEqual(actualElement, value)) {
      throw failures.failure(description, shouldContainAtIndex(actual, value, index, actual.get(index.value)));
    }
  }

  /**
   * Asserts that the given {@code List} contains {@code null}.
   *
   * @param description contains information about the assertion.
   * @param actual      the given {@code Collection}.
   * @throws AssertionError if the given {@code List} is {@code null}.
   * @throws AssertionError if the given {@code List} does not contain null.
   */
  public void assertContainsNull(Description description, List<?> actual) {
    assertNotNull(description, actual);
    if (!actual.contains(null)) {
      throw failures.failure(description, shouldContainNull(actual));
    }
  }

  /**
   * Asserts that the given {@code List} contains only the given values and nothing else, in any order. Duplicates are
   * allowed.
   *
   * @param description contains information about the assertion.
   * @param actual      the given {@code List}.
   * @param values      the values that are expected to be in the given {@code List}.
   * @throws NullPointerException     if the array of values is {@code null}.
   * @throws IllegalArgumentException if the array of values is empty.
   * @throws AssertionError           if the given {@code List} is {@code null}.
   * @throws AssertionError           if the given {@code List} does not contain the given values or if the given {@code List}
   *                                  contains values that are not in the given array.
   */
  public void assertContainsOnly(Description description, List<?> actual, Object[] values) {
    checkIsNotNullAndNotEmpty(values);
    assertNotNull(description, actual);
    Set<Object> notExpected = new LinkedHashSet<Object>(actual);
    Set<Object> notFound = containsOnly(notExpected, values);
    if (notExpected.isEmpty() && notFound.isEmpty()) {
      return;
    }
    throw failures.failure(description, shouldContainOnly(actual, values, notFound, notExpected));
  }

  /**
   * Asserts that the given {@code List} contains all of the given values, in any order. Duplicates are allowed.
   *
   * @param description contains information about the assertion.
   * @param actual      the given {@code List}.
   * @param values      the values that are expected to be in the given {@code List}.
   * @throws NullPointerException     if the array of values is {@code null}.
   * @throws IllegalArgumentException if the array of values is empty.
   * @throws AssertionError           if the given {@code List} is {@code null}.
   * @throws AssertionError           if the given {@code List} does not contain all the given values.
   */
  public void assertContains(Description description, List<?> actual, Object[] values) {
    checkIsNotNullAndNotEmpty(values);
    assertNotNull(description, actual);
    Set<Object> copyOfActual = new LinkedHashSet<Object>(actual);
    Set<Object> notFound = containsOnly(copyOfActual, values);
    if (!notFound.isEmpty()) {
      throw failures.failure(description, shouldContain(actual, values, notFound));
    }
  }

  /**
   * Verifies that the given <code>{@link List}</code> contains the given sequence(ordered) of objects, without any
   * other objects between them. Duplicates are allowed.
   *
   * @param description contains information about the assertion.
   * @param actual      the given {@code List}.
   * @param sequence    the sequence of objects to look for.
   * @throws AssertionError           if the given {@code List} is {@code null}.
   * @throws NullPointerException     if the given sequence is {@code null}.
   * @throws IllegalArgumentException if the given sequence is empty.
   * @throws AssertionError           if the given {@code List} does not contain the given sequence of objects.
   */
  public void assertContainsSequence(Description description, List<?> actual, Object[] sequence) {
    checkIsNotNullAndNotEmpty(sequence);
    assertNotNull(description, actual);
    boolean firstAlreadyFound = false;
    int i = 0;
    int sequenceSize = sequence.length;
    for (Object o : actual) {
      if (i >= sequenceSize) {
        break;
      }
      if (!firstAlreadyFound) {
        if (!areEqual(o, sequence[i])) {
          continue;
        }
        firstAlreadyFound = true;
        i++;
        continue;
      }
      if (areEqual(o, sequence[i++])) {
        continue;
      }
      throw actualDoesNotContainSequence(description, actual, sequence);
    }
    if (!firstAlreadyFound || i < sequenceSize) {
      throw actualDoesNotContainSequence(description, actual, sequence);
    }
  }

  private AssertionError actualDoesNotContainSequence(Description description, List<?> actual, Object[] sequence) {
    return failures.failure(description, shouldContainSequence(actual, sequence));
  }

  /**
   * Verifies that the given {@code List} does not contain the given object at the given index.
   *
   * @param description contains information about the assertion.
   * @param actual      the given {@code List}.
   * @param value       the object to look for.
   * @param index       the index where the object should be stored in the given {@code List}.
   * @throws AssertionError       if the given {@code List} is {@code null}.
   * @throws NullPointerException if the given {@code Index} is {@code null}.
   * @throws AssertionError       if the given {@code List} contains the given object at the given index.
   */
  public void assertDoesNotContain(Description description, List<?> actual, Object value, Index index) {
    assertNotNull(description, actual);
    if (actual.isEmpty() || index.value >= actual.size()) {
      return;
    }
    Object actualElement = actual.get(index.value);
    if (areEqual(actualElement, value)) {
      throw failures.failure(description, shouldNotContainAtIndex(actual, value, index));
    }
  }

  /**
   * Asserts that the given {@code List} does not contain {@code null}.
   *
   * @param description contains information about the assertion.
   * @param actual      the given {@code List}.
   * @throws AssertionError if the given {@code List} is {@code null}.
   * @throws AssertionError if the given {@code List} contains any of given values.
   */
  public void assertDoesNotContainNull(Description description, List<?> actual) {
    assertNotNull(description, actual);
    assertNotEmpty(description, actual);
    if (actual.contains(null)) {
      throw failures.failure(description, shouldNotContainNull(actual));
    }
  }

  /**
   * Asserts that the given {@code List} does not have duplicate values.
   *
   * @param description contains information about the assertion.
   * @param actual      the given {@code List}.
   * @throws NullPointerException     if the array of values is {@code null}.
   * @throws IllegalArgumentException if the array of values is empty.
   * @throws AssertionError           if the given {@code List} is {@code null}.
   * @throws AssertionError           if the given {@code List} contains duplicate values.
   */
  public void assertDoesNotHaveDuplicates(Description description, List<?> actual) {
    assertNotNull(description, actual);
    Collection<?> duplicates = duplicatesFrom(actual);
    if (duplicates.isEmpty()) {
      return;
    }
    throw failures.failure(description, shouldNotHaveDuplicates(actual, duplicates));
  }

  /**
   * Asserts that the given {@code List} starts with the given sequence of objects, without any other objects between
   * them. Similar to <code>{@link #assertContainsSequence(Description, List, Object[])}</code>, but it also verifies
   * that the first element in the sequence is also the first element of the given {@code List}.
   *
   * @param description contains information about the assertion.
   * @param actual      the given {@code List}.
   * @param sequence    the sequence of objects to look for.
   * @throws NullPointerException     if the given argument is {@code null}.
   * @throws IllegalArgumentException if the given argument is an empty array.
   * @throws AssertionError           if the given {@code List} is {@code null}.
   * @throws AssertionError           if the given {@code List} does not start with the given sequence of objects.
   */
  public void assertStartsWith(Description description, List<?> actual, Object[] sequence) {
    checkIsNotNullAndNotEmpty(sequence);
    assertNotNull(description, actual);
    int sequenceSize = sequence.length;
    if (actual.size() < sequenceSize) {
      throw actualDoesNotStartWithSequence(description, actual, sequence);
    }
    int i = 0;
    for (Object o : actual) {
      if (i >= sequenceSize) {
        break;
      }
      if (areEqual(o, sequence[i++])) {
        continue;
      }
      throw actualDoesNotStartWithSequence(description, actual, sequence);
    }
  }

  /**
   * Verifies that the given {@code List} ends with the given sequence of objects, without any other objects between
   * them. Similar to <code>{@link #assertContainsSequence(Description, List, Object[])}</code>, but it also verifies
   * that the last element in the sequence is also the last element of the given {@code List}.
   *
   * @param description contains information about the assertion.
   * @param actual      the given {@code List}.
   * @param sequence    the sequence of objects to look for.
   * @throws NullPointerException     if the given argument is {@code null}.
   * @throws IllegalArgumentException if the given argument is an empty array.
   * @throws AssertionError           if the given {@code List} is {@code null}.
   * @throws AssertionError           if the given {@code List} does not end with the given sequence of objects.
   */
  public void assertEndsWith(Description description, List<?> actual, Object[] sequence) {
    checkIsNotNullAndNotEmpty(sequence);
    assertNotNull(description, actual);
    int sequenceSize = sequence.length;
    int sizeOfActual = actual.size();
    if (sizeOfActual < sequenceSize) {
      throw actualDoesNotEndWithSequence(description, actual, sequence);
    }
    int start = actual.size() - sequenceSize;
    int sequenceIndex = 0, indexOfActual = 0;
    for (Object o : actual) {
      if (indexOfActual++ < start) {
        continue;
      }
      if (areEqual(o, sequence[sequenceIndex++])) {
        continue;
      }
      throw actualDoesNotEndWithSequence(description, actual, sequence);
    }
  }

  private AssertionError actualDoesNotStartWithSequence(Description description, List<?> actual, Object[] sequence) {
    return failures.failure(description, shouldStartWith(actual, sequence));
  }

  private AssertionError actualDoesNotEndWithSequence(Description description, List<?> actual, Object[] sequence) {
    return failures.failure(description, shouldEndWith(actual, sequence));
  }

  private void assertNotNull(Description description, List<?> actual) {
    Objects.instance().assertNotNull(description, actual);
  }

  private void checkNotEmpty(Description description, List<?> actual) {
    if (actual.isEmpty()) {
      throw failures.failure(description, ShouldNotBeEmpty.shouldNotBeEmpty());
    }
  }

  private boolean areEqual(Object actual, Object other) {
    return org.fest.util.Objects.areEqual(actual, other);
  }

  private void checkIsNotNullAndNotEmpty(Object[] values) {
    if (values == null) {
      throw arrayOfValuesToLookForIsNull();
    }
    if (values.length == 0) {
      throw arrayOfValuesToLookForIsEmpty();
    }
  }
}
