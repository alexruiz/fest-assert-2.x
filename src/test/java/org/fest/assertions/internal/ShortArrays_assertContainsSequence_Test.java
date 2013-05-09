/*
 * Created on Mar 26, 2013
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
 * Copyright @2013 the original author or authors.
 */
package org.fest.assertions.internal;

import static org.fest.assertions.error.ShouldContainSequence.shouldContainSequence;
import static org.fest.assertions.error.ShouldNotBeNull.shouldNotBeNull;
import static org.fest.test.ExpectedException.none;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.fest.assertions.description.Description;
import org.fest.assertions.error.BasicErrorMessageFactory;
import org.fest.assertions.test.TestFailures;
import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class ShortArrays_assertContainsSequence_Test {

  @Rule
  public ExpectedException thrown = none();

  private ShortArrays arrays;
  private Description description;
  private Failures failures;

  @Before
  public void setUp() {
    arrays = new ShortArrays();
    description = new TestDescription("testing");
    failures = spy(new Failures());
    Arrays parent = Arrays.instance();
    parent.failures = failures;
  }

  @Test
  public void should_fail_if_actual_is_null() {
    String message = shouldNotBeNull().create(description);
    thrown.expect(AssertionError.class, message);
    arrays.assertContainsSequence(description, null, new short[2]);
  }

  @Test
  public void should_fail_if_other_is_null() {
    thrown.expect(NullPointerException.class, "The array of values to look for should not be null");
    arrays.assertContainsSequence(description, new short[6], null);
  }

  @Test
  public void should_fail_if_other_is_empty() {
    thrown.expect(IllegalArgumentException.class, "The array of values to look for should not be empty");
    arrays.assertContainsSequence(description, new short[1], new short[0]);
  }

  @Test
  public void should_fail_if_the_size_of_sequence_is_greater_than_the_size_of_actual() {
    short[] actual = { 2, 4, 6, 8 };
    short[] sequence = { 2, 4, 6, 8, 10 };
    try {
      arrays.assertContainsSequence(description, actual, sequence);
    } catch (AssertionError e) {
      String format = "The size of sequence is greater than the size of array";
      verify(failures).failure(description, new BasicErrorMessageFactory(format, actual, sequence));
      return;
    }
    TestFailures.expectedAssertionErrorNotThrown();
  }

  @Test
  public void should_fail_if_actual_contains_other_in_the_different_order() {
    short[] actual = { 2, 4, 6, 8 };
    short[] sequence = { 6, 4 };
    try {
      arrays.assertContainsSequence(description, actual, sequence);
    } catch (AssertionError e) {
      verify(failures).failure(description, shouldContainSequence(actual, sequence));
      return;
    }
    TestFailures.expectedAssertionErrorNotThrown();
  }

  @Test
  public void should_fail_if_actual_contains_other_with_duplicates() {
    short[] actual = { 2, 4, 6, 8 };
    short[] sequence = { 4, 4, 6, 6 };
    try {
      arrays.assertContainsSequence(description, actual, sequence);
    } catch (AssertionError e) {
      verify(failures).failure(description, shouldContainSequence(actual, sequence));
      return;
    }
    TestFailures.expectedAssertionErrorNotThrown();
  }

  @Test
  public void should_pass_if_actual_contains_partial_other_in_the_same_order() {
    short[] actual = { 2, 4, 6, 8 };
    short[] sequence = { 4, 6 };
    arrays.assertContainsSequence(description, actual, sequence);
  }

  @Test
  public void should_pass_if_actual_contains_entire_other_in_the_same_order() {
    short[] actual = { 2, 4, 6, 8 };
    short[] sequence = { 2, 4, 6, 8 };
    arrays.assertContainsSequence(description, actual, sequence);
  }
}
