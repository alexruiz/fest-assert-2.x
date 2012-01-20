/*
 * Created on Oct 3, 2010
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

import static java.util.Collections.emptyList;
import static org.fest.assertions.error.ShouldContainOnly.shouldContainOnly;
import static org.fest.assertions.test.ErrorMessages.*;
import static org.fest.assertions.test.ExpectedException.none;
import static org.fest.assertions.test.FailureMessages.actualIsNull;
import static org.fest.assertions.test.ObjectArrayFactory.emptyArray;
import static org.fest.assertions.test.TestData.someInfo;
import static org.fest.assertions.test.TestFailures.failBecauseExpectedAssertionErrorWasNotThrown;
import static org.fest.util.Arrays.array;
import static org.fest.util.Collections.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.fest.assertions.core.AssertionInfo;
import org.fest.assertions.test.ExpectedException;
import org.junit.*;

/**
 * Tests for <code>{@link Iterables#assertContainsOnly(AssertionInfo, Collection, Object[])}</code>.
 *
 * @author Alex Ruiz
 */
public class Collections_assertContainsOnly_Test {


  @Rule public ExpectedException thrown = none();

  private List<String> actual;
  private Failures failures;
  private Iterables collections;

  @Before public void setUp() {
    actual = list("Luke", "Yoda", "Leia");
    failures = spy(new Failures());
    collections = new Iterables();
    collections.failures = failures;
  }

  @Test public void should_pass_if_actual_contains_given_values_only() {
    collections.assertContainsOnly(someInfo(), actual, array("Luke", "Yoda", "Leia"));
  }

  @Test public void should_pass_if_actual_contains_given_values_only_in_different_order() {
    collections.assertContainsOnly(someInfo(), actual, array("Leia", "Yoda", "Luke"));
  }

  @Test public void should_pass_if_actual_contains_given_values_only_more_than_once() {
    actual.addAll(list("Luke", "Luke"));
    collections.assertContainsOnly(someInfo(), actual, array("Luke", "Yoda", "Leia"));
  }

  @Test public void should_pass_if_actual_contains_given_values_only_even_if_duplicated() {
    collections.assertContainsOnly(someInfo(), actual, array("Luke", "Luke", "Luke", "Yoda", "Leia"));
  }

  @Test public void should_pass_if_actual_and_expected_are_empty() {
    collections.assertContainsOnly(someInfo(), emptyList(), emptyArray());
  }

  @Test public void should_fail_if_actual_is_not_empty_and_expected_is_empty() {
	AssertionInfo info = someInfo();
	Object[] expected = emptyArray();
	try {
      collections.assertContainsOnly(info, actual, expected);
    } catch (AssertionError e) {
      verify(failures).failure(info, shouldContainOnly(actual, expected, set(), set("Luke", "Yoda", "Leia")));
      return;
    }
    failBecauseExpectedAssertionErrorWasNotThrown();
  }

  @Test public void should_fail_if_actual_is_empty_and_expected_is_not() {
	AssertionInfo info = someInfo();
	List<String> actual = list();
	String[] expected = array("Luke");
	try {
      collections.assertContainsOnly(info, actual, expected);
    } catch (AssertionError e) {
      verify(failures).failure(info, shouldContainOnly(actual, expected, set("Luke"), set()));
      return;
    }
    failBecauseExpectedAssertionErrorWasNotThrown();
  }
  
  @Test public void should_throw_error_if_array_of_values_to_look_for_is_null() {
    thrown.expectNullPointerException(valuesToLookForIsNull());
    collections.assertContainsOnly(someInfo(), emptyList(), null);
  }

  @Test public void should_fail_if_actual_is_null() {
    thrown.expectAssertionError(actualIsNull());
    collections.assertContainsOnly(someInfo(), null, array("Yoda"));
  }

  @Test public void should_fail_if_actual_does_not_contain_given_values_only() {
    AssertionInfo info = someInfo();
    Object[] expected = { "Luke", "Yoda", "Han" };
    try {
      collections.assertContainsOnly(info, actual, expected);
    } catch (AssertionError e) {
      verify(failures).failure(info, shouldContainOnly(actual, expected, set("Han"), set("Leia")));
      return;
    }
    failBecauseExpectedAssertionErrorWasNotThrown();
  }
}
