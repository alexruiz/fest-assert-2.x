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

import static org.fest.assertions.error.ShouldContain.shouldContain;
import static org.fest.assertions.test.TestFailures.expectedAssertionErrorNotThrown;
import static org.fest.util.Sets.newLinkedHashSet;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.util.Set;

import org.fest.assertions.description.Description;
import org.fest.assertions.error.ShouldNotBeNull;
import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class LongArrays_assertContains_Test {

  @Rule
  public ExpectedException thrown = ExpectedException.none();

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
    long[] values = { 1, 2 };
    String message = ShouldNotBeNull.shouldNotBeNull().create(description);
    thrown.expect(AssertionError.class, message);
    arrays.assertContains(description, null, values);
  }

  @Test
  public void should_fail_if_other_is_null() {
    thrown.expect(NullPointerException.class, "The array of values to look for should not be null");
    arrays.assertContains(description, new long[0], null);
  }

  @Test
  public void should_fail_if_other_is_empty() {
    thrown.expect(IllegalArgumentException.class, "The array of values to look for should not be empty");
    arrays.assertContains(description, new long[1], new long[0]);
  }

  @Test
  public void should_fail_if_actual_does_not_contain_partial_other() {
    long[] actual = { 1, 2, 3, 4 };
    long[] other = { 4, 5 };
    Set<Long> notFound = newLinkedHashSet();
    notFound.add(5l);
    try {
      arrays.assertContains(description, actual, other);
    } catch (AssertionError e) {
      verify(failures).failure(description, shouldContain(actual, other, notFound));
      return;
    }
    throw expectedAssertionErrorNotThrown();
  }

  @Test
  public void should_fail_if_actual_does_not_contain_entire_other() {
    long[] actual = { 1, 2, 3, 4 };
    long[] other = { 5, 6 };
    Set<Long> notFound = newLinkedHashSet();
    notFound.add(5l);
    notFound.add(6l);
    try {
      arrays.assertContains(description, actual, other);
    } catch (AssertionError e) {
      verify(failures).failure(description, shouldContain(actual, other, notFound));
      return;
    }
    throw expectedAssertionErrorNotThrown();
  }

  @Test
  public void should_pass_if_actual_contains_other() {
    long[] actual = { 1, 2, 3, 4 };
    long[] other = { 1, 2 };
    arrays.assertContains(description, actual, other);
  }

  @Test
  public void should_pass_if_actual_equals_to_other() {
    long[] actual = { 1, 2, 3, 4 };
    long[] other = { 1, 2, 3, 4 };
    arrays.assertContains(description, actual, other);
  }

  @Test
  public void should_pass_if_actual_contains_other_in_different_order() {
    long[] actual = { 1, 2, 3, 4 };
    long[] other = { 2, 1 };
    arrays.assertContains(description, actual, other);
  }

  @Test
  public void should_pass_if_actual_contains_other_with_duplicates() {
    long[] actual = { 1, 2, 3, 4 };
    long[] other = { 1, 2, 1, 2 };
    arrays.assertContains(description, actual, other);
  }
}
