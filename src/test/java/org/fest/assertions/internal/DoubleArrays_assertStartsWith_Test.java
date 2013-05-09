/*
 * Created on Mar 29, 2013
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

import static org.fest.assertions.error.ShouldStartWith.shouldStartWith;
import static org.fest.assertions.test.TestFailures.expectedAssertionErrorNotThrown;
import static org.fest.test.ExpectedException.none;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.fest.assertions.description.Description;
import org.fest.assertions.error.ShouldNotBeNull;
import org.fest.assertions.error.ShouldStartWith;
import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class DoubleArrays_assertStartsWith_Test {

  @Rule
  public ExpectedException thrown = none();

  private DoubleArrays arrays;
  private Description description;
  private Failures failures;

  @Before
  public void setUp() {
    arrays = new DoubleArrays();
    description = new TestDescription("testing");
    failures = spy(new Failures());
    Arrays parent = Arrays.instance();
    parent.failures = failures;
  }

  @Test
  public void should_fail_if_actual_is_null() {
    double[] sequence = new double[1];
    String message = ShouldNotBeNull.shouldNotBeNull().create(description);
    thrown.expect(AssertionError.class, message);
    arrays.assertStartsWith(description, null, sequence);
  }

  @Test
  public void should_fail_if_sequence_is_null() {
    double[] actual = new double[2];
    thrown.expect(NullPointerException.class, "The array of values to look for should not be null");
    arrays.assertStartsWith(description, actual, null);
  }

  @Test
  public void should_fail_if_sequence_is_empty() {
    double[] actual = new double[2];
    double[] sequence = {};
    thrown.expect(IllegalArgumentException.class, "The array of values to look for should not be empty");
    arrays.assertStartsWith(description, actual, sequence);
  }

  @Test
  public void should_fail_if_the_size_of_sequence_is_greater_than_the_size_of_actual() {
    double[] actual = new double[3];
    double[] sequence = new double[5];
    try {
      arrays.assertStartsWith(description, actual, sequence);
    } catch (AssertionError e) {
      verify(failures).failure(description, shouldStartWith(actual, sequence));
      return;
    }
    throw expectedAssertionErrorNotThrown();
  }

  @Test
  public void should_fail_if_actual_does_not_start_with_sequence() {
    double[] actual = { 1d, 2d, 3d, 4d, 5d };
    double[] sequence = { 1d, 1d, 2d, 3d };
    try {
      arrays.assertStartsWith(description, actual, sequence);
    } catch (AssertionError e) {
      verify(failures).failure(description, shouldStartWith(actual, sequence));
      return;
    }
    throw expectedAssertionErrorNotThrown();
  }

  @Test
  public void should_fail_if_actual_starts_with_given_sequence_with_duplicates() {
    double[] actual = { 1d, 2d, 3d, 4d, 5d };
    double[] sequence = { 1d, 2d, 2d };
    try {
      arrays.assertStartsWith(description, actual, sequence);
    } catch (AssertionError e) {
      verify(failures).failure(description, ShouldStartWith.shouldStartWith(actual, sequence));
      return;
    }
    expectedAssertionErrorNotThrown();
  }

  @Test
  public void should_pass_if_actual_starts_with_given_sequence() {
    double[] actual = { 1d, 2d, 3d, 4d, 5d };
    double[] sequence = { 1d, 2d };
    arrays.assertStartsWith(description, actual, sequence);
  }
}
