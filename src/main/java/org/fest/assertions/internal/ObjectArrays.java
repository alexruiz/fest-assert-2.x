/*
 * Created on Nov 3, 2010
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

import java.util.Comparator;

import org.fest.assertions.core.ArraySortedAssert;
import org.fest.assertions.core.AssertionInfo;
import org.fest.assertions.core.Condition;
import org.fest.assertions.data.Index;
import org.fest.util.ComparisonStrategy;
import org.fest.util.StandardComparisonStrategy;
import org.fest.util.VisibleForTesting;

/**
 * Reusable assertions for arrays of objects.
 * 
 * @author Alex Ruiz
 * @author Joel Costigliola
 * @author Nicolas François
 */
public class ObjectArrays {

  private static final ObjectArrays INSTANCE = new ObjectArrays();

  /**
   * Returns the singleton instance of this class.
   * @return the singleton instance of this class.
   */
  public static ObjectArrays instance() {
    return INSTANCE;
  }

  private Arrays arrays = Arrays.instance();

  @VisibleForTesting
  ObjectArrays() {
    this(StandardComparisonStrategy.instance());
  }

  public ObjectArrays(ComparisonStrategy comparisonStrategy) {
    this.arrays = new Arrays(comparisonStrategy);
  }

  @VisibleForTesting
  public Comparator<?> getComparator() {
    return arrays.getComparator();
  }

  @VisibleForTesting
  Failures failures = Failures.instance();
  
  @VisibleForTesting
  Conditions conditions = Conditions.instance();  

  /**
   * Asserts that the given array is {@code null} or empty.
   * @param info contains information about the assertion.
   * @param actual the given array.
   * @throws AssertionError if the given array is not {@code null} *and* contains one or more elements.
   */
  public void assertNullOrEmpty(AssertionInfo info, Object[] actual) {
    arrays.assertNullOrEmpty(info, failures, actual);
  }

  /**
   * Asserts that the given array is empty.
   * @param info contains information about the assertion.
   * @param actual the given array.
   * @throws AssertionError if the given array is {@code null}.
   * @throws AssertionError if the given array is not empty.
   */
  public void assertEmpty(AssertionInfo info, Object[] actual) {
    arrays.assertEmpty(info, failures, actual);
  }

  /**
   * Asserts that the given array is not empty.
   * @param info contains information about the assertion.
   * @param actual the given array.
   * @throws AssertionError if the given array is {@code null}.
   * @throws AssertionError if the given array is empty.
   */
  public void assertNotEmpty(AssertionInfo info, Object[] actual) {
    arrays.assertNotEmpty(info, failures, actual);
  }

  /**
   * Asserts that the number of elements in the given array is equal to the expected one.
   * @param info contains information about the assertion.
   * @param actual the given array.
   * @param expectedSize the expected size of {@code actual}.
   * @throws AssertionError if the given array is {@code null}.
   * @throws AssertionError if the number of elements in the given array is different than the expected one.
   */
  public void assertHasSize(AssertionInfo info, Object[] actual, int expectedSize) {
    arrays.assertHasSize(info, failures, actual, expectedSize);
  }

  /**
   * Asserts that the given array contains the given values, in any order.
   * @param info contains information about the assertion.
   * @param actual the given array.
   * @param values the values that are expected to be in the given array.
   * @throws NullPointerException if the array of values is {@code null}.
   * @throws IllegalArgumentException if the array of values is empty.
   * @throws AssertionError if the given array is {@code null}.
   * @throws AssertionError if the given array does not contain the given values.
   */
  public void assertContains(AssertionInfo info, Object[] actual, Object[] values) {
    arrays.assertContains(info, failures, actual, values);
  }

  /**
   * Verifies that the given array contains the given object at the given index.
   * @param info contains information about the assertion.
   * @param actual the given array.
   * @param value the object to look for.
   * @param index the index where the object should be stored in the given array.
   * @throws AssertionError if the given array is {@code null} or empty.
   * @throws NullPointerException if the given {@code Index} is {@code null}.
   * @throws IndexOutOfBoundsException if the value of the given {@code Index} is equal to or greater than the size of
   *           the given array.
   * @throws AssertionError if the given array does not contain the given object at the given index.
   */
  public void assertContains(AssertionInfo info, Object[] actual, Object value, Index index) {
    arrays.assertContains(info, failures, actual, value, index);
  }

  /**
   * Verifies that the given array does not contain the given object at the given index.
   * @param info contains information about the assertion.
   * @param actual the given array.
   * @param value the object to look for.
   * @param index the index where the object should be stored in the given array.
   * @throws AssertionError if the given array is {@code null}.
   * @throws NullPointerException if the given {@code Index} is {@code null}.
   * @throws AssertionError if the given array contains the given object at the given index.
   */
  public void assertDoesNotContain(AssertionInfo info, Object[] actual, Object value, Index index) {
    arrays.assertDoesNotContain(info, failures, actual, value, index);
  }

  /**
   * Asserts that the given array contains only the given values and nothing else, in any order.
   * @param info contains information about the assertion.
   * @param actual the given array.
   * @param values the values that are expected to be in the given array.
   * @throws NullPointerException if the array of values is {@code null}.
   * @throws IllegalArgumentException if the array of values is empty.
   * @throws AssertionError if the given array is {@code null}.
   * @throws AssertionError if the given array does not contain the given values or if the given array contains values
   *           that are not in the given array.
   */
  public void assertContainsOnly(AssertionInfo info, Object[] actual, Object[] values) {
    arrays.assertContainsOnly(info, failures, actual, values);
  }

  /**
   * Verifies that the given array contains the given sequence of objects, without any other objects between them.
   * @param info contains information about the assertion.
   * @param actual the given array.
   * @param sequence the sequence of objects to look for.
   * @throws AssertionError if the given array is {@code null}.
   * @throws NullPointerException if the given sequence is {@code null}.
   * @throws IllegalArgumentException if the given sequence is empty.
   * @throws AssertionError if the given array does not contain the given sequence of objects.
   */
  public void assertContainsSequence(AssertionInfo info, Object[] actual, Object[] sequence) {
    arrays.assertContainsSequence(info, failures, actual, sequence);
  }

  /**
   * Asserts that the given array does not contain the given values.
   * @param info contains information about the assertion.
   * @param actual the given array.
   * @param values the values that are expected not to be in the given array.
   * @throws NullPointerException if the array of values is {@code null}.
   * @throws IllegalArgumentException if the array of values is empty.
   * @throws AssertionError if the given array is {@code null}.
   * @throws AssertionError if the given array contains any of given values.
   */
  public void assertDoesNotContain(AssertionInfo info, Object[] actual, Object[] values) {
    arrays.assertDoesNotContain(info, failures, actual, values);
  }

  /**
   * Asserts that the given array does not have duplicate values.
   * @param info contains information about the assertion.
   * @param actual the given array.
   * @throws NullPointerException if the array of values is {@code null}.
   * @throws IllegalArgumentException if the array of values is empty.
   * @throws AssertionError if the given array is {@code null}.
   * @throws AssertionError if the given array contains duplicate values.
   */
  public void assertDoesNotHaveDuplicates(AssertionInfo info, Object[] actual) {
    arrays.assertDoesNotHaveDuplicates(info, failures, actual);
  }

  /**
   * Verifies that the given array starts with the given sequence of objects, without any other objects between them.
   * Similar to <code>{@link #assertContainsSequence(AssertionInfo, Object[], Object[])}</code>, but it also verifies
   * that the first element in the sequence is also the first element of the given array.
   * @param info contains information about the assertion.
   * @param actual the given array.
   * @param sequence the sequence of objects to look for.
   * @throws NullPointerException if the given argument is {@code null}.
   * @throws IllegalArgumentException if the given argument is an empty array.
   * @throws AssertionError if the given array is {@code null}.
   * @throws AssertionError if the given array does not start with the given sequence of objects.
   */
  public void assertStartsWith(AssertionInfo info, Object[] actual, Object[] sequence) {
    arrays.assertStartsWith(info, failures, actual, sequence);
  }

  /**
   * Verifies that the given array ends with the given sequence of objects, without any other objects between them.
   * Similar to <code>{@link #assertContainsSequence(AssertionInfo, Object[], Object[])}</code>, but it also verifies
   * that the last element in the sequence is also the last element of the given array.
   * @param info contains information about the assertion.
   * @param actual the given array.
   * @param sequence the sequence of objects to look for.
   * @throws NullPointerException if the given argument is {@code null}.
   * @throws IllegalArgumentException if the given argument is an empty array.
   * @throws AssertionError if the given array is {@code null}.
   * @throws AssertionError if the given array does not end with the given sequence of objects.
   */
  public void assertEndsWith(AssertionInfo info, Object[] actual, Object[] sequence) {
    arrays.assertEndsWith(info, failures, actual, sequence);
  }

  /**
   * Asserts that the given array contains at least a null element.
   * @param info contains information about the assertion.
   * @param actual the given array.
   * @throws AssertionError if the given array is {@code null}.
   * @throws AssertionError if the given array does not contain a null element.
   */
  public void assertContainsNull(AssertionInfo info, Object[] actual) {
    arrays.assertContainsNull(info, failures, actual);
  }

  /**
   * Asserts that the given array does not contain null elements.
   * @param info contains information about the assertion.
   * @param actual the given array.
   * @throws AssertionError if the given array is {@code null}.
   * @throws AssertionError if the given array contains a null element.
   */
  public void assertDoesNotContainNull(AssertionInfo info, Object[] actual) {
    arrays.assertDoesNotContainNull(info, failures, actual);
  }
  
  /**
   * Assert that each element of given array satisfies the given condition.
   * @param info contains information about the assertion.
   * @param actual the given array.
   * @param condition the given {@code Condition}.
   * @throws NullPointerException if the given condition is {@code null}.
   * @throws AssertionError if a element cannot be cast to E.
   * @throws AssertionError if one or more element not satisfy the given condition.
   */
  public <E> void assertAre(AssertionInfo info, Object[] actual, Condition<E> condition){
	  arrays.assertAre(info, failures, conditions, actual, condition);
  }  
  
  /**
   * Assert that each element of given array not satisfies the given condition.
   * @param info contains information about the assertion.
   * @param actual the given array.
   * @param condition the given {@code Condition}.
   * @throws NullPointerException if the given condition is {@code null}.
   * @throws AssertionError if a element cannot be cast to E.
   * @throws AssertionError if one or more element satisfy the given condition.
   */
  public <E> void assertAreNot(AssertionInfo info, Object[] actual, Condition<E> condition){
	  arrays.assertAreNot(info, failures, conditions, actual, condition);
  }   
  
  /**
   * Assert that each element of given array satisfies the given condition.
   * @param info contains information about the assertion.
   * @param actual the given array.
   * @param condition the given {@code Condition}.
   * @throws NullPointerException if the given condition is {@code null}.
   * @throws AssertionError if a element cannot be cast to E.
   * @throws AssertionError if one or more element not satisfy the given condition.
   */
  public <E> void assertHave(AssertionInfo info, Object[] actual, Condition<E> condition){
	  arrays.assertHave(info, failures, conditions, actual, condition);
  }    

  /**
   * Assert that each element of given array not satisfies the given condition.
   * @param info contains information about the assertion.
   * @param actual the given array.
   * @param condition the given {@code Condition}.
   * @throws NullPointerException if the given condition is {@code null}.
   * @throws AssertionError if a element cannot be cast to E.
   * @throws AssertionError if one or more element satisfy the given condition.
   */
  public <E> void assertDoNotHave(AssertionInfo info, Object[] actual, Condition<E> condition){
	  arrays.assertHaveNot(info, failures, conditions, actual, condition);
  }
  
  /**
   * Assert that elements of given array satisfies at least n times the given condition.
   * @param info contains information about the assertion.
   * @param actual the given array.
   * @param times least times the condition should be verify.
   * @param condition the given {@code Condition}.
   * @throws NullPointerException if the given condition is {@code null}.
   * @throws AssertionError if a element cannot be cast to E.
   * @throws AssertionError if one or more element satisfy the given condition.
   */  
  public <E> void assertAreAtLeast(AssertionInfo info, Object[] actual, int times, Condition<E> condition){
	  arrays.assertAreAtLeast(info, failures, conditions, actual, times, condition);
  }
  
  /**
   * Assert that elements of given array not satisfies at least n times the given condition.
   * @param info contains information about the assertion.
   * @param actual the given array.
   * @param times least times the condition should not be verify.
   * @param condition the given {@code Condition}.
   * @throws NullPointerException if the given condition is {@code null}.
   * @throws AssertionError if a element cannot be cast to E.
   * @throws AssertionError if one or more element satisfy the given condition.
   */  
  public <E> void assertAreNotAtLeast(AssertionInfo info, Object[] actual, int times, Condition<E> condition){
	  arrays.assertAreNotAtLeast(info, failures, conditions, actual, times, condition);
  }  
  
  /**
   * Assert that elements of given array satisfies at most n times the given condition.
   * @param info contains information about the assertion.
   * @param actual the given array.
   * @param times most times the condition should be verify.
   * @param condition the given {@code Condition}.
   * @throws NullPointerException if the given condition is {@code null}.
   * @throws AssertionError if a element cannot be cast to E.
   * @throws AssertionError if one or more element satisfy the given condition.
   */  
  public <E> void assertAreAtMost(AssertionInfo info, Object[] actual, int times, Condition<E> condition){
	  arrays.assertAreAtMost(info, failures, conditions, actual, times, condition);
  }
  
  /**
   * Assert that elements of given array not satisfies at most n times the given condition.
   * @param info contains information about the assertion.
   * @param actual the given array.
   * @param times most times the condition should not be verify.
   * @param condition the given {@code Condition}.
   * @throws NullPointerException if the given condition is {@code null}.
   * @throws AssertionError if a element cannot be cast to E.
   * @throws AssertionError if one or more element satisfy the given condition.
   */  
  public <E> void assertAreNotAtMost(AssertionInfo info, Object[] actual, int times, Condition<E> condition){
	  arrays.assertAreNotAtMost(info, failures, conditions, actual, times, condition);
  }   
  
  /**
   * Assert that elements of given array satisfies exactly n times the given condition.
   * @param info contains information about the assertion.
   * @param actual the given array.
   * @param times most times the condition should be verify.
   * @param condition the given {@code Condition}.
   * @throws NullPointerException if the given condition is {@code null}.
   * @throws AssertionError if a element cannot be cast to E.
   * @throws AssertionError if one or more element satisfy the given condition.
   */  
  public <E> void assertAreExactly(AssertionInfo info, Object[] actual, int times, Condition<E> condition){
	  arrays.assertAreExactly(info, failures, conditions, actual, times, condition);
  }
  
  /**
   * Assert that elements of given array not satisfies exactly n times the given condition.
   * @param info contains information about the assertion.
   * @param actual the given array.
   * @param times most times the condition should not be verify.
   * @param condition the given {@code Condition}.
   * @throws NullPointerException if the given condition is {@code null}.
   * @throws AssertionError if a element cannot be cast to E.
   * @throws AssertionError if one or more element satisfy the given condition.
   */  
  public <E> void assertAreNotExactly(AssertionInfo info, Object[] actual, int times, Condition<E> condition){
	  arrays.assertAreNotExactly(info, failures, conditions, actual, times, condition);
  }      
  
  /**
   * Assert that elements of given array satisfies at least n times the given condition.
   * @param info contains information about the assertion.
   * @param actual the given array.
   * @param times least times the condition should be verify.
   * @param condition the given {@code Condition}.
   * @throws NullPointerException if the given condition is {@code null}.
   * @throws AssertionError if a element cannot be cast to E.
   * @throws AssertionError if one or more element satisfy the given condition.
   */  
  public <E> void assertHaveAtLeast(AssertionInfo info, Object[] actual, int times, Condition<E> condition){
	  arrays.assertHaveAtLeast(info, failures, conditions, actual, times, condition);
  }
  
  /**
   * Assert that elements of given array not satisfies at least n times the given condition.
   * @param info contains information about the assertion.
   * @param actual the given array.
   * @param times least times the condition should not be verify.
   * @param condition the given {@code Condition}.
   * @throws NullPointerException if the given condition is {@code null}.
   * @throws AssertionError if a element cannot be cast to E.
   * @throws AssertionError if one or more element satisfy the given condition.
   */  
  public <E> void assertDoNotHaveAtLeast(AssertionInfo info, Object[] actual, int times, Condition<E> condition){
	  arrays.assertDoNotHaveAtLeast(info, failures, conditions, actual, times, condition);
  }  
  
  /**
   * Assert that elements of given array satisfies at most n times the given condition.
   * @param info contains information about the assertion.
   * @param actual the given array.
   * @param times most times the condition should be verify.
   * @param condition the given {@code Condition}.
   * @throws NullPointerException if the given condition is {@code null}.
   * @throws AssertionError if a element cannot be cast to E.
   * @throws AssertionError if one or more element satisfy the given condition.
   */  
  public <E> void assertHaveAtMost(AssertionInfo info, Object[] actual, int times, Condition<E> condition){
	  arrays.assertHaveAtMost(info, failures, conditions, actual, times, condition);
  }   
  
  /**
   * Assert that elements of given array not satisfies at most n times the given condition.
   * @param info contains information about the assertion.
   * @param actual the given array.
   * @param times most times the condition should not be verify.
   * @param condition the given {@code Condition}.
   * @throws NullPointerException if the given condition is {@code null}.
   * @throws AssertionError if a element cannot be cast to E.
   * @throws AssertionError if one or more element satisfy the given condition.
   */  
  public <E> void assertDoNotHaveAtMost(AssertionInfo info, Object[] actual, int times, Condition<E> condition){
	  arrays.assertDoNotHaveAtMost(info, failures, conditions, actual, times, condition);
  }    
  
  /**
   * Assert that elements of given array satisfies exactly n times the given condition.
   * @param info contains information about the assertion.
   * @param actual the given array.
   * @param times most times the condition should be verify.
   * @param condition the given {@code Condition}.
   * @throws NullPointerException if the given condition is {@code null}.
   * @throws AssertionError if a element cannot be cast to E.
   * @throws AssertionError if one or more element satisfy the given condition.
   */  
  public <E> void assertHaveExactly(AssertionInfo info, Object[] actual, int times, Condition<E> condition){
	  arrays.assertHaveExactly(info, failures, conditions, actual, times, condition);
  }    
  
  /**
   * Assert that elements of given array not satisfies exactly n times the given condition.
   * @param info contains information about the assertion.
   * @param actual the given array.
   * @param times most times the condition should not be verify.
   * @param condition the given {@code Condition}.
   * @throws NullPointerException if the given condition is {@code null}.
   * @throws AssertionError if a element cannot be cast to E.
   * @throws AssertionError if one or more element satisfy the given condition.
   */  
  public <E> void assertDoNotHaveExactly(AssertionInfo info, Object[] actual, int times, Condition<E> condition){
	  arrays.assertDoNotHaveExactly(info, failures, conditions, actual, times, condition);
  }   
  
  /**
   * Concrete implementation of {@link ArraySortedAssert#isSorted()}.
   * 
   * @param info contains information about the assertion.
   * @param actual the given array.
   */
  public void assertIsSorted(AssertionInfo info, Object[] actual) {
    arrays.assertIsSorted(info, failures, actual);
  }

  /**
   * Concrete implementation of {@link ArraySortedAssert#isSortedAccordingTo(Comparator)}.
   * 
   * @param info contains information about the assertion.
   * @param actual the given array.
   * @param comparator the {@link Comparator} used to compare array elements
   */
  public void assertIsSortedAccordingToComparator(AssertionInfo info, Object[] actual,
      Comparator<? extends Object> comparator) {
    Arrays.assertIsSortedAccordingToComparator(info, failures, actual, comparator);
  }
}
