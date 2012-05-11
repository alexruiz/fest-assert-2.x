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
 * Copyright @2010-2011 the original author or authors.
 */
package org.fest.assertions.api;

import java.util.Comparator;

import org.fest.assertions.core.ArraySortedAssert;
import org.fest.assertions.core.Condition;
import org.fest.assertions.core.IndexedObjectEnumerableAssert;
import org.fest.assertions.core.ObjectEnumerableAssert;
import org.fest.assertions.data.Index;
import org.fest.assertions.internal.ObjectArrays;
import org.fest.util.ComparatorBasedComparisonStrategy;
import org.fest.util.VisibleForTesting;

/**
 * Assertion methods for arrays of objects.
 * <p>
 * To create an instance of this class, invoke <code>{@link Assertions#assertThat(Object[])}</code>.
 * </p>
 * 
 * @author Yvonne Wang
 * @author Alex Ruiz
 * @author Joel Costigliola
 * @author Nicolas François
 * @author Mikhail Mazursky
 */
public class ObjectArrayAssert<T> extends AbstractAssert<ObjectArrayAssert<T>, T[]> implements
    ObjectEnumerableAssert<ObjectArrayAssert<T>, T>, IndexedObjectEnumerableAssert<T>, ArraySortedAssert<ObjectArrayAssert<T>, T> {

  @VisibleForTesting
  ObjectArrays arrays = ObjectArrays.instance();

  protected ObjectArrayAssert(T[] actual) {
    super(actual, ObjectArrayAssert.class);
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
  public ObjectArrayAssert<T> isNotEmpty() {
    arrays.assertNotEmpty(info, actual);
    return this;
  }

  /** {@inheritDoc} */
  public ObjectArrayAssert<T> hasSize(int expected) {
    arrays.assertHasSize(info, actual, expected);
    return this;
  }
  
  /** {@inheritDoc} */
  public ObjectArrayAssert<T> hasSameSizeAs(Object[] other) {
    arrays.assertHasSameSizeAs(info, actual, other);
    return this;
  }
  
  /** {@inheritDoc} */
  public ObjectArrayAssert<T> hasSameSizeAs(Iterable<?> other) {
    arrays.assertHasSameSizeAs(info, actual, other);
    return this;
  }  

  /** {@inheritDoc} */
  public ObjectArrayAssert<T> contains(Object... values) {
    arrays.assertContains(info, actual, values);
    return this;
  }

  /** {@inheritDoc} */
  public ObjectArrayAssert<T> containsOnly(Object... values) {
    arrays.assertContainsOnly(info, actual, values);
    return this;
  }

  /** {@inheritDoc} */
  public ObjectArrayAssert<T> containsSequence(Object... sequence) {
    arrays.assertContainsSequence(info, actual, sequence);
    return this;
  }

  /** {@inheritDoc} */
  public ObjectArrayAssert<T> contains(Object value, Index index) {
    arrays.assertContains(info, actual, value, index);
    return this;
  }

  /** {@inheritDoc} */
  public ObjectArrayAssert<T> doesNotContain(Object value, Index index) {
    arrays.assertDoesNotContain(info, actual, value, index);
    return this;
  }

  /** {@inheritDoc} */
  public ObjectArrayAssert<T> doesNotContain(Object... values) {
    arrays.assertDoesNotContain(info, actual, values);
    return this;
  }

  /** {@inheritDoc} */
  public ObjectArrayAssert<T> doesNotHaveDuplicates() {
    arrays.assertDoesNotHaveDuplicates(info, actual);
    return this;
  }

  /** {@inheritDoc} */
  public ObjectArrayAssert<T> startsWith(Object... sequence) {
    arrays.assertStartsWith(info, actual, sequence);
    return this;
  }

  /** {@inheritDoc} */
  public ObjectArrayAssert<T> endsWith(Object... sequence) {
    arrays.assertEndsWith(info, actual, sequence);
    return this;
  }

  /** {@inheritDoc} */
  public ObjectArrayAssert<T> containsNull() {
    arrays.assertContainsNull(info, actual);
    return this;
  }

  /** {@inheritDoc} */
  public ObjectArrayAssert<T> doesNotContainNull() {
    arrays.assertDoesNotContainNull(info, actual);
    return this;
  }

  /** {@inheritDoc} */
  public <E> ObjectArrayAssert<T> are(Condition<E> condition) {
	  arrays.assertAre(info, actual, condition);
	  return myself;
  }
  
  /** {@inheritDoc} */
  public <E> ObjectArrayAssert<T> areNot(Condition<E> condition) {
	  arrays.assertAreNot(info, actual, condition);
	  return myself;
  } 
 
  /** {@inheritDoc} */
  public <E> ObjectArrayAssert<T> have(Condition<E> condition) {
	  arrays.assertHave(info, actual, condition);
	  return myself;
  }  

  /** {@inheritDoc} */
  public <E> ObjectArrayAssert<T> doNotHave(Condition<E> condition) {
	  arrays.assertDoNotHave(info, actual, condition);
	  return myself;
  }   
  
  /** {@inheritDoc} */
  public <E> ObjectArrayAssert<T> areAtLeast(int times, Condition<E> condition) {
	  arrays.assertAreAtLeast(info, actual, times, condition);
	  return myself;
  }   
  
  /** {@inheritDoc} */
  public <E> ObjectArrayAssert<T> areNotAtLeast(int times, Condition<E> condition) {
	  arrays.assertAreNotAtLeast(info, actual, times, condition);
	  return myself;
  }     
  
  /** {@inheritDoc} */
  public <E> ObjectArrayAssert<T> areAtMost(int times, Condition<E> condition) {
	  arrays.assertAreAtMost(info, actual, times, condition);
	  return myself;
  }
  
  /** {@inheritDoc} */
  public <E> ObjectArrayAssert<T> areNotAtMost(int times, Condition<E> condition) {
	  arrays.assertAreNotAtMost(info, actual, times, condition);
	  return myself;
  }     
  
  /** {@inheritDoc} */
  public <E> ObjectArrayAssert<T> areExactly(int times, Condition<E> condition) {
	  arrays.assertAreExactly(info, actual, times, condition);
	  return myself;
  }
  
  /** {@inheritDoc} */
  public <E> ObjectArrayAssert<T> areNotExactly(int times, Condition<E> condition) {
	  arrays.assertAreNotExactly(info, actual, times, condition);
	  return myself;
  }  
  
  /** {@inheritDoc} */
  public <E> ObjectArrayAssert<T> haveAtLeast(int times, Condition<E> condition) {
	  arrays.assertHaveAtLeast(info, actual, times, condition);
	  return myself;
  }
  
  /** {@inheritDoc} */
  public <E> ObjectArrayAssert<T> doNotHaveAtLeast(int times, Condition<E> condition) {
	  arrays.assertDoNotHaveAtLeast(info, actual, times, condition);
	  return myself;
  }  
  
  /** {@inheritDoc} */
  public <E> ObjectArrayAssert<T> haveAtMost(int times, Condition<E> condition) {
	  arrays.assertHaveAtMost(info, actual, times, condition);
	  return myself;
  }
  
  /** {@inheritDoc} */
  public <E> ObjectArrayAssert<T> doNotHaveAtMost(int times, Condition<E> condition) {
	  arrays.assertDoNotHaveAtMost(info, actual, times, condition);
	  return myself;
  }  
  
  /** {@inheritDoc} */
  public <E> ObjectArrayAssert<T> haveExactly(int times, Condition<E> condition) {
	  arrays.assertHaveExactly(info, actual, times, condition);
	  return myself;
  }
  
  /** {@inheritDoc} */
  public <E> ObjectArrayAssert<T> doNotHaveExactly(int times, Condition<E> condition) {
	  arrays.assertDoNotHaveExactly(info, actual, times, condition);
	  return myself;
  }  
  
  /** {@inheritDoc} */
  public ObjectArrayAssert<T> isSorted() {
    arrays.assertIsSorted(info, actual);
    return this;
  }

  /** {@inheritDoc} */
  public ObjectArrayAssert<T> isSortedAccordingTo(Comparator<? super T> comparator) {
    arrays.assertIsSortedAccordingToComparator(info, actual, comparator);
    return this;
  }

  public ObjectArrayAssert<T> usingElementComparator(Comparator<? super T> customComparator) {
    this.arrays = new ObjectArrays(new ComparatorBasedComparisonStrategy(customComparator));
    return myself;
  }

  public ObjectArrayAssert<T> usingDefaultElementComparator() {
    this.arrays = ObjectArrays.instance();
    return myself;
  }

}
