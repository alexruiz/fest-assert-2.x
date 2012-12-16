/*
 * Created on Nov 28, 2010
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 *
 * Copyright @2010-2011 the original author or authors.
 */
package org.fest.assertions.internal;

import static org.fest.assertions.error.ConditionAndGroupGenericParameterTypeShouldBeTheSame.shouldBeSameGenericBetweenIterableAndCondition;
import static org.fest.assertions.error.ElementsShouldBe.elementsShouldBe;
import static org.fest.assertions.error.ElementsShouldBeAtLeast.elementsShouldBeAtLeast;
import static org.fest.assertions.error.ElementsShouldBeAtMost.elementsShouldBeAtMost;
import static org.fest.assertions.error.ElementsShouldBeExactly.elementsShouldBeExactly;
import static org.fest.assertions.error.ElementsShouldHave.elementsShouldHave;
import static org.fest.assertions.error.ElementsShouldHaveAtLeast.elementsShouldHaveAtLeast;
import static org.fest.assertions.error.ElementsShouldHaveAtMost.elementsShouldHaveAtMost;
import static org.fest.assertions.error.ElementsShouldHaveExactly.elementsShouldHaveExactly;
import static org.fest.assertions.error.ElementsShouldNotBe.elementsShouldNotBe;
import static org.fest.assertions.error.ElementsShouldNotBeAtLeast.elementsShouldNotBeAtLeast;
import static org.fest.assertions.error.ElementsShouldNotBeAtMost.elementsShouldNotBeAtMost;
import static org.fest.assertions.error.ElementsShouldNotHave.elementsShouldNotHave;
import static org.fest.assertions.error.ElementsShouldNotHaveAtLeast.elementsShouldNotHaveAtLeast;
import static org.fest.assertions.error.ElementsShouldNotHaveAtMost.elementsShouldNotHaveAtMost;
import static org.fest.assertions.error.ElementsShouldNotHaveExactly.elementsShouldNotHaveExactly;
import static org.fest.assertions.error.NotEmptyErrorMessageFactory.shouldBeEmpty;
import static org.fest.assertions.error.NotNullOrEmptyErrorMessageFactory.shouldBeNullOrEmpty;
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
import static org.fest.assertions.internal.CommonErrors.*;
import static org.fest.assertions.internal.CommonValidations.checkIndexValueIsValid;
import static org.fest.assertions.util.ArrayWrapperList.wrap;
import static org.fest.util.Collections.duplicatesFrom;
import static org.fest.util.Iterables.isNullOrEmpty;
import static org.fest.util.Lists.newArrayList;
import static org.fest.util.Objects.areEqual;
import static org.fest.util.Sets.newLinkedHashSet;

import java.lang.reflect.Array;
import java.util.*;

import org.fest.assertions.core.*;
import org.fest.assertions.data.Index;
import org.fest.assertions.error.ElementsShouldNotBeExactly;
import org.fest.assertions.util.ArrayWrapperList;

/**
 * Assertions for object and primitive arrays. It trades off performance for DRY.
 *
 * @author Alex Ruiz
 * @author Joel Costigliola
 * @author Nicolas François
 */
class Arrays {
  private static final Arrays INSTANCE = new Arrays();

  static Arrays instance() {
    return INSTANCE;
  }

  private Arrays() {}

  void assertNullOrEmpty(AssertionInfo info, Failures failures, Object array) {
    if (array == null || isArrayEmpty(array)) {
      return;
    }
    throw failures.failure(info, shouldBeNullOrEmpty(array));
  }

  void assertEmpty(AssertionInfo info, Failures failures, Object array) {
    assertNotNull(info, array);
    if (isArrayEmpty(array)) {
      return;
    }
    throw failures.failure(info, shouldBeEmpty(array));
  }

  void assertHasSize(AssertionInfo info, Failures failures, Object array, int expectedSize) {
    assertNotNull(info, array);
    int sizeOfActual = sizeOf(array);
    if (sizeOfActual == expectedSize) {
      return;
    }
    throw failures.failure(info, shouldHaveSize(array, sizeOfActual, expectedSize));
  }

  void assertContains(AssertionInfo info, Failures failures, Object array, Object values) {
    checkIsNotNullAndNotEmpty(values);
    assertNotNull(info, array);
    Set<Object> notFound = newLinkedHashSet();
    int valueCount = sizeOf(values);
    for (int i = 0; i < valueCount; i++) {
      Object value = Array.get(values, i);
      if (!arrayContains(array, value)) {
        notFound.add(value);
      }
    }
    if (notFound.isEmpty()) {
      return;
    }
    throw failures.failure(info, shouldContain(array, values, notFound));
  }

  // TODO do we need this?
  void assertcontainsAll(AssertionInfo info, Failures failures, Object array, Iterable<?> iterable) {
    if (iterable == null) {
      throw iterableToLookForIsNull();
    }
    assertNotNull(info, array);
    Object[] values = newArrayList(iterable).toArray();
    Set<Object> notFound = new LinkedHashSet<Object>();
    for (Object value : values) {
      if (!arrayContains(array, value)) {
        notFound.add(value);
      }
    }
    if (notFound.isEmpty()) {
      return;
    }
    throw failures.failure(info, shouldContain(array, values, notFound));
  }

  void assertContains(AssertionInfo info, Failures failures, Object array, Object value, Index index) {
    assertNotNull(info, array);
    assertNotEmpty(info, failures, array);
    checkIndexValueIsValid(index, sizeOf(array) - 1);
    Object actualElement = Array.get(array, index.value);
    if (areEqual(actualElement, value)) {
      return;
    }
    throw failures.failure(info, shouldContainAtIndex(array, value, index, Array.get(array, index.value)));
  }

  void assertNotEmpty(AssertionInfo info, Failures failures, Object array) {
    assertNotNull(info, array);
    if (!isArrayEmpty(array)) {
      return;
    }
    throw failures.failure(info, shouldNotBeEmpty());
  }

  void assertDoesNotContain(AssertionInfo info, Failures failures, Object array, Object value, Index index) {
    assertNotNull(info, array);
    checkIndexValueIsValid(index, Integer.MAX_VALUE);
    int indexValue = index.value;
    if (indexValue >= sizeOf(array)) {
      return;
    }
    Object actualElement = Array.get(array, index.value);
    if (!areEqual(actualElement, value)) {
      return;
    }
    throw failures.failure(info, shouldNotContainAtIndex(array, value, index));
  }

  void assertContainsOnly(AssertionInfo info, Failures failures, Object array, Object values) {
    checkIsNotNullAndNotEmpty(values);
    assertNotNull(info, array);
    Set<Object> notExpected = asSet(array);
    Set<Object> notFound = containsOnly(notExpected, values);
    if (notExpected.isEmpty() && notFound.isEmpty()) {
      return;
    }
    throw failures.failure(info, shouldContainOnly(array, values, notFound, notExpected));
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

  void assertContainsSequence(AssertionInfo info, Failures failures, Object array, Object sequence) {
    checkIsNotNullAndNotEmpty(sequence);
    assertNotNull(info, array);
    boolean firstAlreadyFound = false;
    int i = 0;
    int sequenceSize = sizeOf(sequence);
    int sizeOfActual = sizeOf(array);
    for (int j = 0; j < sizeOfActual; j++) {
      Object o = Array.get(array, j);
      if (i >= sequenceSize) {
        break;
      }
      if (!firstAlreadyFound) {
        if (!areEqual(o, Array.get(sequence, i))) {
          continue;
        }
        firstAlreadyFound = true;
        i++;
        continue;
      }
      if (areEqual(o, Array.get(sequence, i++))) {
        continue;
      }
      throw arrayDoesNotContainSequence(info, failures, array, sequence);
    }
    if (!firstAlreadyFound || i < sequenceSize) {
      throw arrayDoesNotContainSequence(info, failures, array, sequence);
    }
  }

  private AssertionError arrayDoesNotContainSequence(
      AssertionInfo info, Failures failures, Object array, Object sequence) {
    return failures.failure(info, shouldContainSequence(array, sequence));
  }

  void assertDoesNotContain(AssertionInfo info, Failures failures, Object array, Object values) {
    checkIsNotNullAndNotEmpty(values);
    assertNotNull(info, array);
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
    throw failures.failure(info, shouldNotContain(array, values, found));
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

  void assertDoesNotHaveDuplicates(AssertionInfo info, Failures failures, Object array) {
    assertNotNull(info, array);
    ArrayWrapperList wrapped = wrap(array);
    Collection<?> duplicates = duplicatesFrom(wrapped);
    if (isNullOrEmpty(duplicates)) {
      return;
    }
    throw failures.failure(info, shouldNotHaveDuplicates(array, duplicates));
  }

  void assertStartsWith(AssertionInfo info, Failures failures, Object array, Object sequence) {
    checkIsNotNullAndNotEmpty(sequence);
    assertNotNull(info, array);
    int sequenceSize = sizeOf(sequence);
    int arraySize = sizeOf(array);
    if (arraySize < sequenceSize) {
      throw arrayDoesNotStartWithSequence(info, failures, array, sequence);
    }
    for (int i = 0; i < sequenceSize; i++) {
      if (areEqual(Array.get(sequence, i), Array.get(array, i))) {
        continue;
      }
      throw arrayDoesNotStartWithSequence(info, failures, array, sequence);
    }
  }

  private AssertionError arrayDoesNotStartWithSequence(
      AssertionInfo info, Failures failures, Object array, Object sequence) {
    return failures.failure(info, shouldStartWith(array, sequence));
  }

  void assertEndsWith(AssertionInfo info, Failures failures, Object array, Object sequence) {
    checkIsNotNullAndNotEmpty(sequence);
    assertNotNull(info, array);
    int sequenceSize = sizeOf(sequence);
    int arraySize = sizeOf(array);
    if (arraySize < sequenceSize) {
      throw arrayDoesNotEndWithSequence(info, failures, array, sequence);
    }
    for (int i = 0; i < sequenceSize; i++) {
      int sequenceIndex = sequenceSize - (i + 1);
      int arrayIndex = arraySize - (i + 1);
      if (areEqual(Array.get(sequence, sequenceIndex), Array.get(array, arrayIndex))) {
        continue;
      }
      throw arrayDoesNotEndWithSequence(info, failures, array, sequence);
    }
  }

  void assertContainsNull(AssertionInfo info, Failures failures, Object array) {
    assertNotNull(info, array);
    if (!arrayContains(array, null)) {
      throw failures.failure(info, shouldContainNull(array));
    }
  }

  void assertDoesNotContainNull(AssertionInfo info, Failures failures, Object array) {
    assertNotNull(info, array);
    if (arrayContains(array, null)) {
      throw failures.failure(info, shouldNotContainNull(array));
    }
  }

  public <E> void assertAre(AssertionInfo info, Failures failures, Matchers conditions, Object array, Matcher<E> condition) {
    assertNotNull(info, array);
    conditions.assertIsNotNull(condition);
    try {
      List<E> notSatisfiesCondition = notSatisfiesCondition(array, condition);
      if (notSatisfiesCondition.isEmpty()) {
        return;
      }
      throw failures.failure(info, elementsShouldBe(array, notSatisfiesCondition, condition));
    } catch (ClassCastException e) {
      throw failures.failure(info, shouldBeSameGenericBetweenIterableAndCondition(array, condition));
    }
  }

  public <E> void assertAreNot(AssertionInfo info, Failures failures, Matchers conditions, Object array, Matcher<E> condition) {
    assertNotNull(info, array);
    conditions.assertIsNotNull(condition);
    try {
      List<E> satisfiesCondition = satisfiesCondition(array, condition);
      if (satisfiesCondition.isEmpty()) {
        return;
      }
      throw failures.failure(info, elementsShouldNotBe(array, satisfiesCondition, condition));
    } catch (ClassCastException e) {
      throw failures.failure(info, shouldBeSameGenericBetweenIterableAndCondition(array, condition));
    }
  }

  public <E> void assertHave(AssertionInfo info, Failures failures, Matchers conditions, Object array, Matcher<E> condition) {
    assertNotNull(info, array);
    conditions.assertIsNotNull(condition);
    try {
      List<E> notSatisfiesCondition = notSatisfiesCondition(array, condition);
      if (notSatisfiesCondition.isEmpty()) {
        return;
      }
      throw failures.failure(info, elementsShouldHave(array, notSatisfiesCondition, condition));
    } catch (ClassCastException e) {
      throw failures.failure(info, shouldBeSameGenericBetweenIterableAndCondition(array, condition));
    }
  }

  public <E> void assertHaveNot(AssertionInfo info, Failures failures, Matchers conditions, Object array, Matcher<E> condition) {
    assertNotNull(info, array);
    conditions.assertIsNotNull(condition);
    try {
      List<E> satisfiesCondition = satisfiesCondition(array, condition);
      if (satisfiesCondition.isEmpty()) {
        return;
      }
      throw failures.failure(info, elementsShouldNotHave(array, satisfiesCondition, condition));
    } catch (ClassCastException e) {
      throw failures.failure(info, shouldBeSameGenericBetweenIterableAndCondition(array, condition));
    }
  }

  public <E> void assertAreAtLeast(AssertionInfo info, Failures failures, Matchers conditions, Object array, int times,
      Matcher<E> condition) {
    assertNotNull(info, array);
    conditions.assertIsNotNull(condition);
    try {
      List<E> satisfiesCondition = satisfiesCondition(array, condition);
      if (satisfiesCondition.size() >= times) {
        return;
      }
      throw failures.failure(info, elementsShouldBeAtLeast(array, times, condition));
    } catch (ClassCastException e) {
      throw failures.failure(info, shouldBeSameGenericBetweenIterableAndCondition(array, condition));
    }
  }

  public <E> void assertAreNotAtLeast(AssertionInfo info, Failures failures, Matchers conditions, Object array, int times,
      Matcher<E> condition) {
    assertNotNull(info, array);
    conditions.assertIsNotNull(condition);
    try {
      List<E> notSatisfiesCondition = notSatisfiesCondition(array, condition);
      if (notSatisfiesCondition.size() >= times) {
        return;
      }
      throw failures.failure(info, elementsShouldNotBeAtLeast(array, times, condition));
    } catch (ClassCastException e) {
      throw failures.failure(info, shouldBeSameGenericBetweenIterableAndCondition(array, condition));
    }
  }

  public <E> void assertAreAtMost(AssertionInfo info, Failures failures, Matchers conditions, Object array, int times,
      Matcher<E> condition) {
    assertNotNull(info, array);
    conditions.assertIsNotNull(condition);
    try {
      List<E> satisfiesCondition = satisfiesCondition(array, condition);
      if (satisfiesCondition.size() <= times) {
        return;
      }
      throw failures.failure(info, elementsShouldBeAtMost(array, times, condition));
    } catch (ClassCastException e) {
      throw failures.failure(info, shouldBeSameGenericBetweenIterableAndCondition(array, condition));
    }
  }

  public <E> void assertAreNotAtMost(AssertionInfo info, Failures failures, Matchers conditions, Object array, int times,
      Matcher<E> condition) {
    assertNotNull(info, array);
    conditions.assertIsNotNull(condition);
    try {
      List<E> notSatisfiesCondition = notSatisfiesCondition(array, condition);
      if (notSatisfiesCondition.size() <= times) {
        return;
      }
      throw failures.failure(info, elementsShouldNotBeAtMost(array, times, condition));
    } catch (ClassCastException e) {
      throw failures.failure(info, shouldBeSameGenericBetweenIterableAndCondition(array, condition));
    }
  }

  public <E> void assertAreExactly(AssertionInfo info, Failures failures, Matchers conditions, Object array, int times,
      Matcher<E> condition) {
    assertNotNull(info, array);
    conditions.assertIsNotNull(condition);
    try {
      List<E> satisfiesCondition = satisfiesCondition(array, condition);
      if (satisfiesCondition.size() == times) {
        return;
      }
      throw failures.failure(info, elementsShouldBeExactly(array, times, condition));
    } catch (ClassCastException e) {
      throw failures.failure(info, shouldBeSameGenericBetweenIterableAndCondition(array, condition));
    }
  }

  public <E> void assertAreNotExactly(AssertionInfo info, Failures failures, Matchers conditions, Object array, int times,
      Matcher<E> condition) {
    assertNotNull(info, array);
    conditions.assertIsNotNull(condition);
    try {
      List<E> notSatisfiesCondition = notSatisfiesCondition(array, condition);
      if (notSatisfiesCondition.size() == times) {
        return;
      }
      throw failures.failure(info, ElementsShouldNotBeExactly.elementsShouldNotBeExactly(array, times, condition));
    } catch (ClassCastException e) {
      throw failures.failure(info, shouldBeSameGenericBetweenIterableAndCondition(array, condition));
    }
  }

  public <E> void assertHaveAtLeast(AssertionInfo info, Failures failures, Matchers conditions, Object array, int times,
      Matcher<E> condition) {
    assertNotNull(info, array);
    conditions.assertIsNotNull(condition);
    try {
      List<E> satisfiesCondition = satisfiesCondition(array, condition);
      if (satisfiesCondition.size() >= times) {
        return;
      }
      throw failures.failure(info, elementsShouldHaveAtLeast(array, times, condition));
    } catch (ClassCastException e) {
      throw failures.failure(info, shouldBeSameGenericBetweenIterableAndCondition(array, condition));
    }
  }

  public <E> void assertDoNotHaveAtLeast(AssertionInfo info, Failures failures, Matchers conditions, Object array, int times,
      Matcher<E> condition) {
    assertNotNull(info, array);
    conditions.assertIsNotNull(condition);
    try {
      List<E> notSatisfiesCondition = notSatisfiesCondition(array, condition);
      if (notSatisfiesCondition.size() >= times) {
        return;
      }
      throw failures.failure(info, elementsShouldNotHaveAtLeast(array, times, condition));
    } catch (ClassCastException e) {
      throw failures.failure(info, shouldBeSameGenericBetweenIterableAndCondition(array, condition));
    }
  }

  public <E> void assertHaveAtMost(AssertionInfo info, Failures failures, Matchers conditions, Object array, int times,
      Matcher<E> condition) {
    assertNotNull(info, array);
    conditions.assertIsNotNull(condition);
    try {
      List<E> satisfiesCondition = satisfiesCondition(array, condition);
      if (satisfiesCondition.size() <= times) {
        return;
      }
      throw failures.failure(info, elementsShouldHaveAtMost(array, times, condition));
    } catch (ClassCastException e) {
      throw failures.failure(info, shouldBeSameGenericBetweenIterableAndCondition(array, condition));
    }
  }

  public <E> void assertDoNotHaveAtMost(AssertionInfo info, Failures failures, Matchers conditions, Object array, int times,
      Matcher<E> condition) {
    assertNotNull(info, array);
    conditions.assertIsNotNull(condition);
    try {
      List<E> notSatisfiesCondition = notSatisfiesCondition(array, condition);
      if (notSatisfiesCondition.size() <= times) {
        return;
      }
      throw failures.failure(info, elementsShouldNotHaveAtMost(array, times, condition));
    } catch (ClassCastException e) {
      throw failures.failure(info, shouldBeSameGenericBetweenIterableAndCondition(array, condition));
    }
  }

  public <E> void assertHaveExactly(AssertionInfo info, Failures failures, Matchers conditions, Object array, int times,
      Matcher<E> condition) {
    assertNotNull(info, array);
    conditions.assertIsNotNull(condition);
    try {
      List<E> satisfiesCondition = satisfiesCondition(array, condition);
      if (satisfiesCondition.size() == times) {
        return;
      }
      throw failures.failure(info, elementsShouldHaveExactly(array, times, condition));
    } catch (ClassCastException e) {
      throw failures.failure(info, shouldBeSameGenericBetweenIterableAndCondition(array, condition));
    }
  }

  public <E> void assertDoNotHaveExactly(AssertionInfo info, Failures failures, Matchers conditions, Object array, int times,
      Matcher<E> condition) {
    assertNotNull(info, array);
    conditions.assertIsNotNull(condition);
    try {
      List<E> notSatisfiesCondition = notSatisfiesCondition(array, condition);
      if (notSatisfiesCondition.size() == times) {
        return;
      }
      throw failures.failure(info, elementsShouldNotHaveExactly(array, times, condition));
    } catch (ClassCastException e) {
      throw failures.failure(info, shouldBeSameGenericBetweenIterableAndCondition(array, condition));
    }
  }

  @SuppressWarnings("unchecked")
  private <E> List<E> notSatisfiesCondition(Object array, Matcher<E> condition) {
    List<E> notSatisfiesCondition = new LinkedList<E>();
    int arraySize = sizeOf(array);
    for (int i = 0; i < arraySize; i++) {
      Object o = Array.get(array, i);
      if (!condition.matches((E) o)) {
        notSatisfiesCondition.add((E) o);
      }
    }
    return notSatisfiesCondition;
  }

  @SuppressWarnings("unchecked")
  private <E> List<E> satisfiesCondition(Object array, Matcher<E> condition) {
    List<E> notSatisfiesCondition = new LinkedList<E>();
    int arraySize = sizeOf(array);
    for (int i = 0; i < arraySize; i++) {
      Object o = Array.get(array, i);
      if (condition.matches((E) o)) {
        notSatisfiesCondition.add((E) o);
      }
    }
    return notSatisfiesCondition;
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

  private AssertionError arrayDoesNotEndWithSequence(AssertionInfo info, Failures failures, Object array, Object sequence) {
    return failures.failure(info, shouldEndWith(array, sequence, comparison));
  }

  private static void assertNotNull(AssertionInfo info, Object array) {
    Objects.instance().assertNotNull(info, array);
  }

  private int sizeOf(Object array) {
    return Array.getLength(array);
  }

}