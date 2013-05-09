/*
 * Created on Apr 25, 2013
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
import static org.fest.assertions.test.DoubleArrays.emptyArray;
import static org.fest.assertions.test.DoubleArrays.newArray;
import static org.fest.assertions.test.TestFailures.expectedAssertionErrorNotThrown;
import static org.fest.test.ExpectedException.none;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.fest.assertions.description.Description;
import org.fest.assertions.error.BasicErrorMessageFactory;
import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Yvonne Wang
 */
public class DoubleArrays_assertContainsSequence_Test {

  @Rule
  public ExpectedException thrown = none();

  private DoubleArrays arrays;
  private Failures failures;
  private Description description;
  private double[] actual = newArray(1d, 6d, 8d);
  private double[] sequence = newArray(1d, 6d);

  @Before
  public void setUp() {
    arrays = new DoubleArrays();
    Arrays parent = Arrays.instance();
    failures = spy(new Failures());
    parent.failures = failures;
    description = new TestDescription("testing");
  }

  @Test
  public void should_fail_if_actual_is_null() {
    thrown.expect(AssertionError.class, "[testing] expecting: actual value not to be null");
    arrays.assertContainsSequence(description, null, new double[1]);
  }

  @Test
  public void should_fail_if_actual_is_empty() {
    actual = emptyArray();
    try {
      arrays.assertContainsSequence(description, actual, sequence);
    } catch (AssertionError e) {
      String format = "The size of sequence is greater than the size of array";
      failures.failure(description, new BasicErrorMessageFactory(format, actual, sequence));
      return;
    }
    expectedAssertionErrorNotThrown();
  }

  @Test
  public void should_throw_error_if_array_of_values_to_look_for_is_null() {
    thrown.expect(NullPointerException.class, "The array of values to look for should not be null");
    arrays.assertContainsSequence(description, actual, null);
  }

  @Test
  public void shouild_throw_error_if_array_of_values_to_look_for_is_empty() {
    thrown.expect(IllegalArgumentException.class, "The array of values to look for should not be empty");
    arrays.assertContainsSequence(description, actual, emptyArray());
  }

  @Test
  public void should_fail_if_actual_only_contains_partial_sequence() {
    sequence = newArray(1d, 5d);
    try {
      arrays.assertContainsSequence(description, actual, sequence);
    } catch (AssertionError e) {
      verify(failures).failure(description, shouldContainSequence(actual, sequence));
      return;
    }
    expectedAssertionErrorNotThrown();
  }

  @Test
  public void should_fail_if_actual_contains_sequence_with_duplicates() {
    sequence = newArray(1d, 6d, 6d);
    try {
      arrays.assertContainsSequence(description, actual, sequence);
    } catch (AssertionError e) {
      verify(failures).failure(description, shouldContainSequence(actual, sequence));
      return;
    }
    expectedAssertionErrorNotThrown();
  }

  @Test
  public void should_pass_if_actual_contains_entire_sequence() {
    arrays.assertContainsSequence(description, actual, sequence);
  }

  @Test
  public void should_pass_if_actual_with_duplicates_contains_entire_sequence() {
    actual = newArray(1d, 6d, 8d, 6d);
    arrays.assertContainsSequence(description, actual, sequence);
  }
}
