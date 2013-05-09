/*
 * Created on Mar 27, 2013
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
import static org.fest.assertions.test.TestFailures.expectedAssertionErrorNotThrown;
import static org.fest.test.ExpectedException.none;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.fest.assertions.description.Description;
import org.fest.assertions.error.BasicErrorMessageFactory;
import org.fest.assertions.error.ShouldNotBeNull;
import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class LongArrays_assertContansSequence_Test {

  @Rule
  public ExpectedException thrown = none();

  private LongArrays arrays;
  private Description description;
  private Failures failures;

  @Before
  public void setUp() {
    arrays = new LongArrays();
    description = new TestDescription("testing");
    failures = spy(new Failures());
    Arrays parent = Arrays.instance();
    parent.failures = failures;
  }

  @Test
  public void should_fail_if_actual_is_null() {
    long[] sequence = new long[2];
    String message = ShouldNotBeNull.shouldNotBeNull().create(description);
    thrown.expect(AssertionError.class, message);
    arrays.assertContainsSequence(description, null, sequence);
  }

  @Test
  public void should_fail_if_sequence_is_null() {
    long[] actual = new long[2];
    thrown.expect(NullPointerException.class, "The array of values to look for should not be null");
    arrays.assertContainsSequence(description, actual, null);
  }

  @Test
  public void should_fail_if_sequence_is_empty() {
    long[] actual = new long[2];
    long[] sequence = {};
    thrown.expect(IllegalArgumentException.class, "The array of values to look for should not be empty");
    arrays.assertContainsSequence(description, actual, sequence);
  }

  @Test
  public void should_fail_if_actual_does_not_contain_sequence() {
    long[] actual = { 1, 2, 3, 4, 5, 6 };
    long[] sequence = { 2, 4 };
    try {
      arrays.assertContainsSequence(description, actual, sequence);
    } catch (AssertionError e) {
      verify(failures).failure(description, shouldContainSequence(actual, sequence));
      return;
    }
    throw expectedAssertionErrorNotThrown();
  }

  @Test
  public void should_fail_if_size_of_sequence_is_greater_than_the_size_of_actual() {
    long[] actual = { 1, 2, 3, 4, 5, 6 };
    long[] sequence = { 1, 2, 3, 4, 5, 6, 7 };
    try {
      arrays.assertContainsSequence(description, actual, sequence);
    } catch (AssertionError e) {
      String format = "The size of sequence is greater than the size of array";
      verify(failures).failure(description, new BasicErrorMessageFactory(format, actual, sequence));
      return;
    }
    throw expectedAssertionErrorNotThrown();
  }

  @Test
  public void should_pass_if_actual_contains_sequence() {
    long[] actual = { 1, 2, 3, 4, 5, 6 };
    long[] sequence = { 2, 3, 4 };
    arrays.assertContainsSequence(description, actual, sequence);
  }
}
