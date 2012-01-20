/*
 * Created on Nov 22, 2010
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

import static org.fest.assertions.error.ShouldContainSequence.shouldContainSequence;
import static org.fest.assertions.test.ErrorMessages.*;
import static org.fest.assertions.test.ExpectedException.none;
import static org.fest.assertions.test.FailureMessages.actualIsNull;
import static org.fest.assertions.test.ObjectArrayFactory.emptyArray;
import static org.fest.assertions.test.TestData.someInfo;
import static org.fest.assertions.test.TestFailures.failBecauseExpectedAssertionErrorWasNotThrown;
import static org.fest.util.Arrays.array;
import static org.fest.util.Collections.list;
import static org.mockito.Mockito.*;

import java.util.Collection;
import java.util.List;

import org.fest.assertions.core.AssertionInfo;
import org.fest.assertions.test.ExpectedException;
import org.junit.*;

/**
 * Tests for <code>{@link Iterables#assertContainsSequence(AssertionInfo, Collection, Object[])}</code>.
 *
 * @author Alex Ruiz
 */
public class Collections_assertContainsSequence_Test {

  private static Collection<String> actual;

  @Rule public ExpectedException thrown = none();

  private Failures failures;
  private Iterables collections;

  @BeforeClass public static void setUpOnce() {
    actual = list("Yoda", "Luke", "Leia", "Obi-Wan");
  }

  @Before public void setUp() {
    failures = spy(new Failures());
    collections = new Iterables();
    collections.failures = failures;
  }

  @Test public void should_throw_error_if_sequence_is_null() {
    thrown.expectNullPointerException(valuesToLookForIsNull());
    collections.assertContainsSequence(someInfo(), actual, null);
  }

  @Test public void should_pass_if_actual_and_expected_are_empty() {
    collections.assertContainsSequence(someInfo(), list(), emptyArray());
  }

  @Test public void should_fail_if_actual_is_null() {
    thrown.expectAssertionError(actualIsNull());
    collections.assertContainsSequence(someInfo(), null, array("Yoda"));
  }

  @Test public void should_fail_if_sequence_is_bigger_than_actual() {
    AssertionInfo info = someInfo();
    Object[] sequence = { "Luke", "Leia", "Obi-Wan", "Han", "C-3PO", "R2-D2", "Anakin" };
    try {
      collections.assertContainsSequence(info, actual, sequence);
    } catch (AssertionError e) {
      verifyFailureThrownWhenSequenceNotFound(info, sequence);
      return;
    }
    failBecauseExpectedAssertionErrorWasNotThrown();
  }

  @Test public void should_fail_if_actual_does_not_contain_whole_sequence() {
    AssertionInfo info = someInfo();
    Object[] sequence = { "Han", "C-3PO" };
    try {
      collections.assertContainsSequence(info, actual, sequence);
    } catch (AssertionError e) {
      verifyFailureThrownWhenSequenceNotFound(info, sequence);
      return;
    }
    failBecauseExpectedAssertionErrorWasNotThrown();
  }

  @Test public void should_fail_if_actual_contains_first_elements_of_sequence_but_not_whole_sequence() {
    AssertionInfo info = someInfo();
    Object[] sequence = { "Luke", "Leia", "Han" };
    try {
      collections.assertContainsSequence(info, actual, sequence);
    } catch (AssertionError e) {
      verifyFailureThrownWhenSequenceNotFound(info, sequence);
      return;
    }
    failBecauseExpectedAssertionErrorWasNotThrown();
  }

  private void verifyFailureThrownWhenSequenceNotFound(AssertionInfo info, Object[] sequence) {
    verify(failures).failure(info, shouldContainSequence(actual, sequence));
  }

  @Test public void should_pass_if_actual_contains_sequence() {
    collections.assertContainsSequence(someInfo(), actual, array("Luke", "Leia"));
  }

  @Test public void should_pass_if_actual_and_sequence_are_equal() {
    collections.assertContainsSequence(someInfo(), actual, array("Yoda", "Luke", "Leia", "Obi-Wan"));
  }
  
  @Test public void should_pass_if_actual_is_not_empty_and_expected_is_empty() {
	AssertionInfo info = someInfo();
	collections.assertContainsSequence(info, actual, emptyArray());
  }

  @Test public void should_fail_if_actual_is_empty_and_expected_is_not() {
	AssertionInfo info = someInfo();
	List<String> actual = list();
	Object[] sequence = { "Luke", "Leia", "Han" };
	try {
	  collections.assertContainsSequence(info, actual, sequence);
	} catch (AssertionError e) {
	  verify(failures).failure(info, shouldContainSequence(actual, sequence));
	  return;
	}
	failBecauseExpectedAssertionErrorWasNotThrown();
   }
  
}
