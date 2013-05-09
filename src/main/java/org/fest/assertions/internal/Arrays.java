/*
 * Created on Nov 28, 2010
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
import static org.fest.assertions.error.ShouldNotBeEmpty.shouldNotBeEmpty;
import static org.fest.assertions.error.ShouldNotContain.shouldNotContain;
import static org.fest.assertions.error.ShouldNotContainAtIndex.shouldNotContainAtIndex;
import static org.fest.assertions.error.ShouldNotContainNull.shouldNotContainNull;
import static org.fest.assertions.error.ShouldNotHaveDuplicates.shouldNotHaveDuplicates;
import static org.fest.assertions.error.ShouldStartWith.shouldStartWith;
import static org.fest.assertions.internal.CommonErrors.arrayOfValuesToLookForIsEmpty;
import static org.fest.assertions.internal.CommonErrors.arrayOfValuesToLookForIsNull;
import static org.fest.assertions.internal.CommonValidations.checkIndexValueIsValid;
import static org.fest.assertions.util.ArrayWrapperList.wrap;
import static org.fest.util.Collections.duplicatesFrom;
import static org.fest.util.Objects.areEqual;
import static org.fest.util.Sets.newLinkedHashSet;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import org.fest.assertions.data.Index;
import org.fest.assertions.description.Description;
import org.fest.assertions.error.BasicErrorMessageFactory;
import org.fest.assertions.util.ArrayWrapperList;
import org.fest.util.VisibleForTesting;

/**
 * Assertions for object and primitive arrays. It trades off performance for DRY.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
class Arrays {
  private static final Arrays INSTANCE = new Arrays();

  static Arrays instance() {
    return INSTANCE;
  }

  private Arrays() {
  }

  @VisibleForTesting
  Failures failures = Failures.instance();

  void assertNullOrEmpty(Description description, Object array) {
    if (array == null || isArrayEmpty(array)) {
      return;
    }
    throw failures.failure(description, shouldBeNullOrEmpty(array));
  }

  void assertEmpty(Description description, Object array) {
    assertNotNull(description, array);
    if (!isArrayEmpty(array)) {
      throw failures.failure(description, shouldBeEmpty(array));
    }
  }

  void assertHasSize(Description description, Object array, int expectedSize) {
    assertNotNull(description, array);
    if (expectedSize < 0) {
      throw failures.failure(description, new BasicErrorMessageFactory("The expectedSize should not be negative"));
    }
    int sizeOfActual = sizeOf(array);
    if (sizeOfActual != expectedSize) {
      throw failures.failure(description, shouldHaveSize(array, sizeOfActual, expectedSize));
    }
  }

  void assertContains(Description description, Object array, Object values) {
    checkIsNotNullAndNotEmpty(values);
    assertNotNull(description, array);
    assertNotEmpty(description, array);
    int valueCount = sizeOf(values);
    Set<Object> notFound = newLinkedHashSet();
    for (int i = 0; i < valueCount; i++) {
      Object value = Array.get(values, i);
      if (!arrayContains(array, value)) {
        notFound.add(value);
      }
    }
    if (notFound.isEmpty()) {
      return;
    }
    throw failures.failure(description, shouldContain(array, values, notFound));
  }

  void assertContains(Description description, Object array, Object value, Index index) {
    assertNotNull(description, array);
    assertNotEmpty(description, array);
    checkIndexValueIsValid(index, sizeOf(array) - 1);
    Object actualElement = Array.get(array, index.value);
    if (areEqual(actualElement, value)) {
      return;
    }
    throw failures.failure(description, shouldContainAtIndex(array, value, index, Array.get(array, index.value)));
  }

  void assertNotEmpty(Description description, Object array) {
    assertNotNull(description, array);
    if (isArrayEmpty(array)) {
      throw failures.failure(description, shouldNotBeEmpty());
    }
  }

  void assertDoesNotContain(Description description, Object array, Object value, Index index) {
    assertNotNull(description, array);
    checkIndexValueIsValid(index, Integer.MAX_VALUE);
    int indexValue = index.value;
    if (indexValue >= sizeOf(array)) {
      return;
    }
    Object actualElement = Array.get(array, index.value);
    if (!areEqual(actualElement, value)) {
      return;
    }
    throw failures.failure(description, shouldNotContainAtIndex(array, value, index));
  }

  void assertContainsOnly(Description description, Object array, Object values) {
    checkIsNotNullAndNotEmpty(values);
    assertNotNull(description, array);
    Set<Object> notExpected = asSet(array);
    Set<Object> notFound = containsOnly(notExpected, values);
    if (notExpected.isEmpty() && notFound.isEmpty()) {
      return;
    }
    throw failures.failure(description, shouldContainOnly(array, values, notFound, notExpected));
  }

  private Set<Object> containsOnly(Set<Object> actual, Object values) {
    Set<Object> notFound = newLinkedHashSet();
    for (Object o : asSet(values)) {
      if (actual.contains(o)) {
        actual.remove(o);
      } else {
        notFound.add(o);
      }
    }
    return notFound;
  }

  private Set<Object> asSet(Object array) {
    Set<Object> set = newLinkedHashSet();
    int size = sizeOf(array);
    for (int i = 0; i < size; i++) {
      Object element = Array.get(array, i);
      set.add(element);
    }
    return set;
  }

  void assertContainsSequence(Description description, Object array, Object sequence) {
    checkIsNotNullAndNotEmpty(sequence);
    assertNotNull(description, array);
    int sizeOfActual = sizeOf(array);
    int sizeOfSequence = sizeOf(sequence);
    boolean firstFound = false;
    int index = 0;

    if (sizeOfActual < sizeOfSequence) {
      String format = "The size of sequence is greater than the size of array";
      throw failures.failure(description, new BasicErrorMessageFactory(format, array, sequence));
    }
    for (int i = 0; i < sizeOfActual && index < sizeOfSequence; i++) {
      if (areEqual(Array.get(array, i), Array.get(sequence, index))) {
        firstFound = true;
        index++;
        if (i >= sizeOfActual - 1 && index < sizeOfSequence) {
          throw arrayDoesNotContainSequence(description, array, sequence);
        }
        continue;
      }
      if (!firstFound) {
        if (i >= sizeOfActual - 1 && index < sizeOfSequence) {
          throw arrayDoesNotContainSequence(description, array, sequence);
        }
        continue;
      } else {
        throw arrayDoesNotContainSequence(description, array, sequence);
      }
    }
  }

  private AssertionError arrayDoesNotContainSequence(Description description, Object array, Object sequence) {
    return failures.failure(description, shouldContainSequence(array, sequence));
  }

  void assertDoesNotContain(Description description, Object array, Object values) {
    checkIsNotNullAndNotEmpty(values);
    assertNotNull(description, array);
    Set<Object> found = new LinkedHashSet<Object>();
    for (int i = 0; i < sizeOf(values); i++) {
      Object value = Array.get(values, i);
      if (arrayContains(array, value)) {
        found.add(value);
      }
    }
    if (found.isEmpty()) {
      return;
    }
    throw failures.failure(description, shouldNotContain(array, values, found));
  }

  private boolean arrayContains(Object array, Object value) {
    int size = sizeOf(array);
    for (int i = 0; i < size; i++) {
      Object element = Array.get(array, i);
      if (areEqual(element, value)) {
        return true;
      }
    }
    return false;
  }

  void assertDoesNotHaveDuplicates(Description description, Object array) {
    assertNotNull(description, array);
    ArrayWrapperList wrapped = wrap(array);
    Collection<?> duplicates = duplicatesFrom(wrapped);
    if (duplicates == null || duplicates.isEmpty()) {
      return;
    }
    throw failures.failure(description, shouldNotHaveDuplicates(array, duplicates));
  }

  void assertStartsWith(Description description, Object array, Object sequence) {
    checkIsNotNullAndNotEmpty(sequence);
    assertNotNull(description, array);
    int sequenceSize = sizeOf(sequence);
    int arraySize = sizeOf(array);
    if (arraySize < sequenceSize) {
      throw arrayDoesNotStartWithSequence(description, array, sequence);
    }
    for (int i = 0; i < sequenceSize; i++) {
      if (areEqual(Array.get(sequence, i), Array.get(array, i))) {
        continue;
      }
      throw arrayDoesNotStartWithSequence(description, array, sequence);
    }
  }

  private AssertionError arrayDoesNotStartWithSequence(Description description, Object array, Object sequence) {
    return failures.failure(description, shouldStartWith(array, sequence));
  }

  void assertEndsWith(Description description, Object array, Object sequence) {
    checkIsNotNullAndNotEmpty(sequence);
    assertNotNull(description, array);
    int sequenceSize = sizeOf(sequence);
    int arraySize = sizeOf(array);
    if (arraySize < sequenceSize) {
      throw arrayDoesNotEndWithSequence(description, failures, array, sequence);
    }
    for (int i = 0; i < sequenceSize; i++) {
      int sequenceIndex = sequenceSize - (i + 1);
      int arrayIndex = arraySize - (i + 1);
      if (areEqual(Array.get(sequence, sequenceIndex), Array.get(array, arrayIndex))) {
        continue;
      }
      throw arrayDoesNotEndWithSequence(description, failures, array, sequence);
    }
  }

  void assertContainsNull(Description description, Object array) {
    assertNotNull(description, array);
    if (!arrayContains(array, null)) {
      throw failures.failure(description, shouldContainNull(array));
    }
  }

  void assertDoesNotContainNull(Description description, Object array) {
    assertNotNull(description, array);
    if (arrayContains(array, null)) {
      throw failures.failure(description, shouldNotContainNull(array));
    }
  }

  private void checkIsNotNullAndNotEmpty(Object values) {
    if (values == null) {
      throw arrayOfValuesToLookForIsNull();
    }
    if (isArrayEmpty(values)) {
      throw arrayOfValuesToLookForIsEmpty();
    }
  }

  private boolean isArrayEmpty(Object array) {
    return sizeOf(array) == 0;
  }

  private AssertionError arrayDoesNotEndWithSequence(Description description, Failures failures, Object array,
      Object sequence) {
    return failures.failure(description, shouldEndWith(array, sequence));
  }

  private static void assertNotNull(Description description, Object array) {
    Objects.instance().assertNotNull(description, array);
  }

  private int sizeOf(Object array) {
    return Array.getLength(array);
  }
}