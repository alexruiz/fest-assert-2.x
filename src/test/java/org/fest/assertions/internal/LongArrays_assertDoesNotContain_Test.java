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

import static org.fest.assertions.error.ShouldNotBeNull.shouldNotBeNull;
import static org.fest.assertions.error.ShouldNotContain.shouldNotContain;
import static org.fest.test.ExpectedException.none;
import static org.fest.util.Sets.newLinkedHashSet;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.util.Set;

import org.fest.assertions.description.Description;
import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class LongArrays_assertDoesNotContain_Test {

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
    long[] other = { 1, 2 };
    String message = shouldNotBeNull().create(description);
    thrown.expect(AssertionError.class, message);
    arrays.assertDoesNotContain(description, null, other);
  }

  @Test
  public void should_fail_if_other_is_null() {
    long[] actual = new long[1];
    thrown.expect(NullPointerException.class, "The array of values to look for should not be null");
    arrays.assertDoesNotContain(description, actual, null);
  }

  @Test
  public void should_fail_if_other_is_empty() {
    long[] actual = new long[1];
    long[] other = new long[0];
    thrown.expect(IllegalArgumentException.class, "The array of values to look for should not be empty");
    arrays.assertDoesNotContain(description, actual, other);
  }

  @Test
  public void should_fail_if_actual_contains_partial_other() {
    long[] actual = { 1, 2, 3, 4 };
    long[] other = { 3, 5 };
    Set<Long> found = newLinkedHashSet(3l);
    try {
      arrays.assertDoesNotContain(description, actual, other);
    } catch (AssertionError e) {
      verify(failures).failure(description, shouldNotContain(actual, other, found));
    }
  }

  @Test
  public void should_fail_if_actual_contains_entire_other() {
    long[] actual = { 1, 2, 3, 4 };
    long[] other = { 3, 4 };
    Set<Long> found = newLinkedHashSet(3l);
    found.add(4l);
    try {
      arrays.assertDoesNotContain(description, actual, other);
    } catch (AssertionError e) {
      verify(failures).failure(description, shouldNotContain(actual, other, found));
    }
  }

  @Test
  public void should_pass_if_actual_does_not_contain_other() {
    long[] actual = { 1, 2, 3, 4 };
    long[] values = { 5, 6 };
    arrays.assertDoesNotContain(description, actual, values);
  }

  @Test
  public void should_pass_if_actual_does_not_contain_other_with_duplicates() {
    long[] actual = { 1, 2, 3, 4 };
    long[] values = { 5, 5, 6, 6 };
    arrays.assertDoesNotContain(description, actual, values);
  }
}
