/*
 * Created on May 6, 2013
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

import static org.fest.assertions.error.ShouldBeSorted.shouldBeSorted;
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
 * @author Yvonne Wang
 */
public class IntArrays_assertIsSorted_Test {

  @Rule
  public ExpectedException thrown = none();

  private IntArrays arrays;
  private Description description;
  private Failures failures;
  private int[] actual = { 1, 2, 3, 4, 5, 6 };

  @Before
  public void setUp() {
    arrays = IntArrays.instance();
    failures = spy(new Failures());
    arrays.failures = failures;
    description = new TestDescription("testing");
  }

  @Test
  public void should_pass_if_actual_is_sorted() {
    arrays.assertIsSorted(description, actual);
  }

  @Test
  public void should_pass_if_actual_is_empty() {
    arrays.assertIsSorted(description, new int[0]);
  }

  @Test
  public void should_pass_if_actual_is_sorted_with_same_elements() {
    actual = new int[] { 1, 2, 3, 4, 5, 5, 6, 6 };
    arrays.assertIsSorted(description, actual);
  }

  @Test
  public void should_fail_if_actual_is_null() {
    thrown.expect(AssertionError.class, "The actual is null");
    arrays.assertIsSorted(description, null);
  }

  @Test
  public void should_fail_if_actual_is_not_sorted() {
    actual = new int[] { 1, 3, 2, 6, 5, 6 };
    try {
      arrays.assertIsSorted(description, actual);
    } catch (AssertionError e) {
      verify(failures).failure(description, shouldBeSorted(1, actual));
      return;
    }
    expectedAssertionErrorNotThrown();
  }

  @Test
  public void should_fail_if_actual_is_not_sorted_even_with_same_elements() {
    actual = new int[] { 1, 3, 3, 2, 6, 5, 6 };
    try {
      arrays.assertIsSorted(description, actual);
    } catch (AssertionError e) {
      verify(failures).failure(description, shouldBeSorted(2, actual));
      return;
    }
    expectedAssertionErrorNotThrown();
  }
}
