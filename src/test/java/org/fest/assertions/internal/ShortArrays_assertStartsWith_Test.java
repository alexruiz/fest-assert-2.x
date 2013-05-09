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

import static org.fest.assertions.error.ShouldNotBeNull.shouldNotBeNull;
import static org.fest.assertions.error.ShouldStartWith.shouldStartWith;
import static org.fest.assertions.test.TestFailures.expectedAssertionErrorNotThrown;
import static org.fest.test.ExpectedException.none;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.fest.assertions.description.Description;
import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class ShortArrays_assertStartsWith_Test {

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
    arrays.assertStartsWith(description, null, new short[2]);
  }

  @Test
  public void should_fail_if_sequence_is_null() {
    thrown.expect(NullPointerException.class, "The array of values to look for should not be null");
    arrays.assertStartsWith(description, new short[6], null);
  }

  @Test
  public void should_fail_if_sequence_is_empty() {
    thrown.expect(IllegalArgumentException.class, "The array of values to look for should not be empty");
    arrays.assertStartsWith(description, new short[1], new short[0]);
  }

  @Test
  public void should_fail_if_actual_does_not_start_with_sequence() {
    short[] actual = { (short)2, (short)4, (short)6, (short)8 };
    short[] sequence = { (short)1, (short)2 };
    try {
      arrays.assertStartsWith(description, actual, sequence);
    } catch (AssertionError e) {
      verify(failures).failure(description, shouldStartWith(actual, sequence));
      return;
    }
    expectedAssertionErrorNotThrown();
  }

  @Test
  public void should_fail_if_actual_starts_with_only_beginning_of_sequence_not_entire_sequence() {
    short[] actual = { (short)2, (short)4, (short)6, (short)8 };
    short[] sequence = { (short)2, (short)6 };
    try {
      arrays.assertStartsWith(description, actual, sequence);
    } catch (AssertionError e) {
      verify(failures).failure(description, shouldStartWith(actual, sequence));
      return;
    }
    expectedAssertionErrorNotThrown();
  }

  @Test
  public void should_fail_if_the_size_of_sequence_is_greater_than_the_size_of_actual() {
    short[] actual = new short[4];
    short[] sequence = new short[6];
    try {
      arrays.assertStartsWith(description, actual, sequence);
    } catch (AssertionError e) {
      verify(failures).failure(description, shouldStartWith(actual, sequence));
      return;
    }
    expectedAssertionErrorNotThrown();
  }

  @Test
  public void should_pass_if_actual_starts_with_sequence() {
    short[] actual = { (short)2, (short)4, (short)6, (short)8 };
    short[] sequence = { (short)2, (short)4, (short)6 };
    arrays.assertStartsWith(description, actual, sequence);
  }

  @Test
  public void should_pass_if_actual_and_sequence_are_same() {
    short[] actual = { (short)2, (short)4, (short)6, (short)8 };
    short[] sequence = { (short)2, (short)4, (short)6, (short)8 };
    arrays.assertStartsWith(description, actual, sequence);
  }
}
