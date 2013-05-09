/*
 * Created on Mar 22, 2013
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

import static org.fest.assertions.error.ShouldEndWith.shouldEndWith;
import static org.fest.assertions.test.TestFailures.expectedAssertionErrorNotThrown;
import static org.fest.test.ExpectedException.none;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.fest.assertions.description.Description;
import org.fest.assertions.error.ShouldNotBeNull;
import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Alex Ruiz
 * @author Yvonne Wang
 *
 */
public class IntArrays_assertEndsWith_Test {

  @Rule
  public ExpectedException thrown = none();

  private IntArrays arrays;
  private Description description;
  private Failures failures;

  @Before
  public void setUp() {
    arrays = new IntArrays();
    description = new TestDescription("testing");
    failures = spy(new Failures());
    Arrays parent = Arrays.instance();
    parent.failures = failures;
  }

  @Test
  public void should_fail_if_actual_is_null() {
    int[] sequence = new int[1];
    String message = ShouldNotBeNull.shouldNotBeNull().create(description);
    thrown.expect(AssertionError.class, message);
    arrays.assertEndsWith(description, null, sequence);
  }

  @Test
  public void should_fail_if_sequence_is_null() {
    int[] actual = new int[2];
    thrown.expect(NullPointerException.class, "The array of values to look for should not be null");
    arrays.assertEndsWith(description, actual, null);
  }

  @Test
  public void should_fail_if_sequence_is_empty() {
    int[] actual = new int[2];
    int[] sequence = {};
    thrown.expect(IllegalArgumentException.class, "The array of values to look for should not be empty");
    arrays.assertEndsWith(description, actual, sequence);
  }

  @Test
  public void should_fail_if_the_size_of_sequence_is_greater_than_the_size_of_actual() {
    int[] actual = new int[2];
    int[] sequence = new int[4];
    try {
      arrays.assertEndsWith(description, actual, sequence);
    } catch (AssertionError e) {
      verify(failures).failure(description, shouldEndWith(actual, sequence));
      return;
    }
    throw expectedAssertionErrorNotThrown();
  }

  @Test
  public void should_fail_if_actual_does_not_end_with_sequence() {
    int[] actual = { 1, 2, 3, 4, 5 };
    int[] sequence = { 4, 6 };
    try {
      arrays.assertEndsWith(description, actual, sequence);
    } catch (AssertionError e) {
      verify(failures).failure(description, shouldEndWith(actual, sequence));
      return;
    }
    throw expectedAssertionErrorNotThrown();
  }

  @Test
  public void should_pass_if_actual_ends_with_sequence() {
    int[] actual = { 1, 2, 3, 4, 5 };
    int[] sequence = { 4, 5 };
    arrays.assertEndsWith(description, actual, sequence);
  }

  @Test
  public void should_pass_if_actual_equals_to_sequence() {
    int[] actual = { 1, 2, 3, 4, 5 };
    int[] sequence = { 1, 2, 3, 4, 5 };
    arrays.assertEndsWith(description, actual, sequence);
  }
}
