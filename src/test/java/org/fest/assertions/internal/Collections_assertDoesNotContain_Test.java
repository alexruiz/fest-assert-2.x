/*
 * Created on Oct 12, 2010
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
import static org.fest.assertions.error.ShouldNotContain.shouldNotContain;
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
 * Tests for <code>{@link Iterables#assertDoesNotContain(AssertionInfo, Collection, Object[])}</code>.
 *
 * @author Alex Ruiz
 */
public class Collections_assertDoesNotContain_Test {

  private static List<String> actual;

  @Rule public ExpectedException thrown = none();

  private Failures failures;
  private Iterables collections;

  @BeforeClass public static void setUpOnce() {
    actual = list("Luke", "Yoda", "Leia");
  }

  @Before public void setUp() {
    failures = spy(new Failures());
    collections = new Iterables();
    collections.failures = failures;
  }

  @Test public void should_pass_if_actual_does_not_contain_given_values() {
    collections.assertDoesNotContain(someInfo(), actual, array("Han"));
  }

  @Test public void should_pass_if_actual_does_not_contain_given_values_even_if_duplicated() {
    collections.assertDoesNotContain(someInfo(), actual, array("Han", "Han", "Anakin"));
  }

  @Test public void should_pass_if_actual_and_expected_are_empty() {
    collections.assertDoesNotContain(someInfo(), list(), emptyArray());
  }

  @Test public void should_throw_error_if_array_of_values_to_look_for_is_null() {
    thrown.expectNullPointerException(valuesToLookForIsNull());
    collections.assertDoesNotContain(someInfo(), emptyList(), null);
  }

  @Test public void should_fail_if_actual_is_null() {
    thrown.expectAssertionError(actualIsNull());
    collections.assertDoesNotContain(someInfo(), null, array("Yoda"));
  }

  @Test public void should_fail_if_actual_contains_given_values() {
    AssertionInfo info = someInfo();
    Object[] expected = { "Luke", "Yoda", "Han" };
    try {
      collections.assertDoesNotContain(info, actual, expected);
    } catch (AssertionError e) {
      verify(failures).failure(info, shouldNotContain(actual, expected, set("Luke", "Yoda")));
      return;
    }
    failBecauseExpectedAssertionErrorWasNotThrown();
  }
  
  @Test public void should_pass_if_actual_is_not_empty_and_expected_is_empty() {
	AssertionInfo info = someInfo();
	Object[] expected = emptyArray();
    collections.assertDoesNotContain(info, actual, expected);
  }

  @Test public void should_pass_if_actual_is_empty_and_expected_is_not() {
	AssertionInfo info = someInfo();
	List<String> actual = list();
    collections.assertDoesNotContain(info, actual, array("Luke"));
  }

}
