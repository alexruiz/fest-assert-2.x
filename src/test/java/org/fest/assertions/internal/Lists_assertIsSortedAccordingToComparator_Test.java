/*
 * Created on Sep 30, 2010
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

import static org.fest.assertions.error.ShouldBeSorted.*;
import static org.fest.assertions.test.ExpectedException.none;
import static org.fest.assertions.test.FailureMessages.actualIsNull;
import static org.fest.assertions.test.TestData.someInfo;
import static org.fest.assertions.test.TestFailures.failBecauseExpectedAssertionErrorWasNotThrown;
import static org.fest.util.Collections.list;

import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.*;

import org.fest.assertions.core.AssertionInfo;
import org.fest.assertions.test.ExpectedException;

/**
 * Tests for <code>{@link Iterables#assertDoesNotContainNull(AssertionInfo, Collection)}</code>.
 * 
 * @author Joel Costigliola
 */
public class Lists_assertIsSortedAccordingToComparator_Test {

  @Rule
  public ExpectedException thrown = none();

  private Failures failures;
  private Lists lists;
  private Comparator<String> stringDescendingOrderComparator;
  private Comparator<Object> comparator;

  @Before
  public void setUp() {
    failures = spy(new Failures());
    lists = new Lists();
    lists.failures = failures;
    stringDescendingOrderComparator = new Comparator<String>() {
      public int compare(String s1, String s2) {
        return -s1.compareTo(s2);
      }
    };
    comparator = new Comparator<Object>() {
      public int compare(Object o1, Object o2) {
        return o1.toString().compareTo(o2.toString());
      }
    };
  }

  @Test
  public void should_pass_if_actual_is_sorted_according_to_given_comparator() {
    lists.assertIsSortedAccordingToComparator(someInfo(), list("Yoda", "Vador", "Luke", "Leia", "Leia"),
        stringDescendingOrderComparator);
  }

  @Test
  public void should_pass_if_actual_is_empty_whatever_given_comparator_is() {
    lists.assertIsSortedAccordingToComparator(someInfo(), list(), stringDescendingOrderComparator);
    lists.assertIsSortedAccordingToComparator(someInfo(), list(), comparator);
  }

  @Test
  public void should_pass_if_actual_contains_only_one_comparable_element() {
    lists.assertIsSortedAccordingToComparator(someInfo(), list("Obiwan"), comparator);
  }

  @Test
  public void should_fail_if_actual_is_null() {
    thrown.expectAssertionError(actualIsNull());
    lists.assertIsSortedAccordingToComparator(someInfo(), null, comparator);
  }

  @Test
  public void should_fail_if_comparator_is_null() {
    thrown.expect(NullPointerException.class);
    lists.assertIsSortedAccordingToComparator(someInfo(), list(), null);
  }

  @Test
  public void should_fail_if_actual_is_not_sorted_according_to_given_comparator() {
    AssertionInfo info = someInfo();
    List<String> actual = list("Yoda", "Vador", "Leia", "Leia", "Luke");
    try {
      lists.assertIsSortedAccordingToComparator(info, actual, stringDescendingOrderComparator);
    } catch (AssertionError e) {
      verify(failures).failure(info, shouldBeSortedAccordingToGivenComparator(3, actual));
      return;
    }
    failBecauseExpectedAssertionErrorWasNotThrown();
  }

  @Test
  public void should_fail_if_actual_has_some_not_mutually_comparable_elements_according_to_given_comparator() {
    AssertionInfo info = someInfo();
    List<Object> actual = list();
    actual.add("bar");
    actual.add(new Integer(5));
    actual.add("foo");
    try {
      lists.assertIsSortedAccordingToComparator(info, actual, stringDescendingOrderComparator);
    } catch (AssertionError e) {
      verify(failures).failure(info, shouldHaveComparableElementsAccordingToGivenComparator(actual));
      return;
    }
    failBecauseExpectedAssertionErrorWasNotThrown();
  }

  @Test
  public void should_fail_if_actual_has_one_element_only_not_comparable_according_to_given_comparator() {
    AssertionInfo info = someInfo();
    List<Object> actual = list(new Object());
    try {
      lists.assertIsSortedAccordingToComparator(info, actual, stringDescendingOrderComparator);
    } catch (AssertionError e) {
      verify(failures).failure(info, shouldHaveComparableElementsAccordingToGivenComparator(actual));
      return;
    }
    failBecauseExpectedAssertionErrorWasNotThrown();
  }

}
