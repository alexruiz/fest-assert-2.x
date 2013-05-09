/*
 * Created on Mar 25, 2013
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
import static org.fest.assertions.error.ShouldNotBeNull.shouldNotBeNull;
import static org.fest.assertions.test.TestFailures.expectedAssertionErrorNotThrown;
import static org.fest.test.ExpectedException.none;
import static org.fest.util.Sets.newLinkedHashSet;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.util.Set;

import org.fest.assertions.description.Description;
import org.fest.assertions.error.ShouldContain;
import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class ShortArrays_assertContains_Test {

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
    short[] values = { 1, 2 };
    String message = shouldNotBeNull().create(description);
    thrown.expect(AssertionError.class, message);
    arrays.assertContains(description, null, values);
  }

  @Test
  public void should_fail_if_other_is_null() {
    thrown.expect(NullPointerException.class, "The array of values to look for should not be null");
    arrays.assertContains(description, new short[1], null);
  }

  @Test
  public void should_fail_if_other_are_empty() {
    thrown.expect(IllegalArgumentException.class, "The array of values to look for should not be empty");
    arrays.assertContains(description, new short[1], new short[0]);
  }

  @Test
  public void should_fail_if_actual_does_not_contain_other() {
    short[] actual = { 1, 2, 3 };
    short[] other = { 4, 5 };
    Set<Short> notFound = newLinkedHashSet();
    notFound.add((short) 4);
    notFound.add((short) 5);
    try {
      arrays.assertContains(description, actual, other);
    } catch (AssertionError e) {
      verify(failures).failure(description, shouldContain(actual, other, notFound));
      return;
    }
    expectedAssertionErrorNotThrown();
  }

  @Test
  public void should_fail_if_actual_contains_only_partial_other() {
    short[] actual = { 1, 2, 3 };
    short[] other = { 1, 2, 3, 4 };
    Set<Short> notFound = newLinkedHashSet();
    notFound.add((short) 4);
    try {
      arrays.assertContains(description, actual, other);
    } catch (AssertionError e) {
      verify(failures).failure(description, ShouldContain.shouldContain(actual, other, notFound));
      return;
    }
    expectedAssertionErrorNotThrown();
  }

  @Test
  public void should_pass_if_actual_cotains_other_in_the_same_order() {
    short[] actual = { 1, 2, 3 };
    short[] other = { 2, 3 };
    arrays.assertContains(description, actual, other);
  }

  @Test
  public void should_pass_if_actual_cotains_other_in_different_order() {
    short[] actual = { 1, 2, 3 };
    short[] other = { 3, 2 };
    arrays.assertContains(description, actual, other);
  }

  @Test
  public void should_pass_if_actual_contains_every_element_of_other() {
    short[] actual = { 1, 2, 3 };
    short[] other = { 1, 2, 3 };
    arrays.assertContains(description, actual, other);
  }

  @Test
  public void should_pass_if_actual_contains_other_with_duplicates() {
    short[] actual = { 1, 2, 3 };
    short[] other = { 1, 2, 3, 2, 3 };
    arrays.assertContains(description, actual, other);
  }
}
