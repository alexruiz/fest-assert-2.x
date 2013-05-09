/*
 * Created on Nov 29, 2010
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

import static org.fest.assertions.error.ShouldContainSequence.shouldContainSequence;
import static org.fest.assertions.test.ErrorMessages.valuesToLookForIsEmpty;
import static org.fest.assertions.test.ErrorMessages.valuesToLookForIsNull;
import static org.fest.assertions.test.FailureMessages.actualIsNull;
import static org.fest.assertions.test.ObjectArrays.emptyArray;
import static org.fest.assertions.test.TestFailures.expectedAssertionErrorNotThrown;
import static org.fest.test.ExpectedException.none;
import static org.fest.util.Arrays.array;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.fest.assertions.description.Description;
import org.fest.assertions.error.BasicErrorMessageFactory;
import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for <code>{@link ObjectArrays#assertContainsSequence(Description, Object[], Object[])}</code>.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class ObjectArrays_assertContainsSequence_Test {

  @Rule
  public ExpectedException thrown = none();

  private ObjectArrays arrays;
  private Failures failures;
  private Description description;
  private Object[] actual = array("Yoda", "Luke", "Leia");

  @Before
  public void setUp() {
    arrays = ObjectArrays.instance();
    Arrays parent = Arrays.instance();
    failures = spy(new Failures());
    parent.failures = failures;
    description = new TestDescription("testing");
  }

  @Test
  public void should_throw_an_error_if_actual_is_null() {
    thrown.expect(AssertionError.class, actualIsNull());
    arrays.assertContainsSequence(description, null, array("Yoda"));
  }

  @Test
  public void should_throw_error_if_sequence_is_null() {
    thrown.expect(NullPointerException.class, valuesToLookForIsNull());
    arrays.assertContainsSequence(description, actual, null);
  }

  @Test
  public void should_throw_error_if_sequence_is_empty() {
    thrown.expect(IllegalArgumentException.class, valuesToLookForIsEmpty());
    arrays.assertContainsSequence(description, actual, emptyArray());
  }

  @Test
  public void should_fail_if_sequence_is_bigger_than_actual() {
    Object[] sequence = new Object[10];
    try {
      arrays.assertContainsSequence(description, actual, sequence);
    } catch (AssertionError e) {
      String format = "The size of sequence is greater than the size of array";
      verify(failures).failure(description, new BasicErrorMessageFactory(format, actual, sequence));
      return;
    }
    expectedAssertionErrorNotThrown();
  }

  @Test
  public void should_fail_if_actual_does_not_contain_whole_sequence() {
    Object[] sequence = { "Han", "C-3PO" };
    try {
      arrays.assertContainsSequence(description, actual, sequence);
    } catch (AssertionError e) {
      verify(failures).failure(description, shouldContainSequence(actual, sequence));
      return;
    }
    expectedAssertionErrorNotThrown();
  }

  @Test
  public void should_fail_if_actual_contains_first_elements_of_sequence_only() {
    Object[] sequence = { "Leia", "Obi-Wan", "Han" };
    try {
      arrays.assertContainsSequence(description, actual, sequence);
    } catch (AssertionError e) {
      verify(failures).failure(description, shouldContainSequence(actual, sequence));
      return;
    }
    expectedAssertionErrorNotThrown();
  }

  @Test
  public void should_fail_if_sequence_is_not_in_the_same_order_as_actual() {
    Object[] sequence = { "Luke", "Leia", "Yoda" };
    try {
      arrays.assertContainsSequence(description, actual, sequence);
    } catch (AssertionError e) {
      verify(failures).failure(description, shouldContainSequence(actual, sequence));
      return;
    }
    expectedAssertionErrorNotThrown();
  }

  @Test
  public void should_fail_if_sequence_with_duplicates_where_actual_does_not() {
    actual = array("Luke", "Leia", "Yoda", "R2", "D2");
    Object[] sequence = { "Luke", "Leia", "Yoda", "Yoda" };
    try {
      arrays.assertContainsSequence(description, actual, sequence);
    } catch (AssertionError e) {
      verify(failures).failure(description, shouldContainSequence(actual, sequence));
      return;
    }
    expectedAssertionErrorNotThrown();
  }

  @Test
  public void should_pass_if_actual_contains_sequence() {
    arrays.assertContainsSequence(description, actual, array("Luke", "Leia"));
  }

  @Test
  public void should_pass_if_actual_and_sequence_are_equal() {
    arrays.assertContainsSequence(description, actual, array("Yoda", "Luke", "Leia"));
  }

  @Test
  public void should_pass_if_actual_contains_sequence_with_same_duplicates() {
    actual = array("Luke", "Leia", "Yoda", "Yoda", "R2");
    Object[] sequence = { "Luke", "Leia", "Yoda", "Yoda" };
    arrays.assertContainsSequence(description, actual, sequence);
  }
}
