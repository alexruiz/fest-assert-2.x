/*
 * Created on Mar 20, 2013
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
import static org.fest.assertions.test.TestFailures.expectedAssertionErrorNotThrown;
import static org.fest.test.ExpectedException.none;
import static org.fest.util.Sets.newLinkedHashSet;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.util.Set;

import org.fest.assertions.data.Index;
import org.fest.assertions.description.Description;
import org.fest.assertions.error.ShouldNotContainAtIndex;
import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Alex
 *
 */
public class IntArrays_assertDoesNotContain_Test {

  @Rule public ExpectedException thrown = none();

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
    int[] other = {1, 2};
    String message = shouldNotBeNull().create(description);
    thrown.expect(AssertionError.class, message);
    arrays.assertDoesNotContain(description, null, other);
  }

  @Test
  public void should_fail_if_other_is_null() {
    int[] actual = new int[1];
    thrown.expect(NullPointerException.class, "The array of values to look for should not be null");
    arrays.assertDoesNotContain(description, actual, null);
  }

  @Test
  public void should_fail_if_other_is_empty() {
    int[] actual = new int[1];
    int[] other = new int[0];
    thrown.expect(IllegalArgumentException.class, "The array of values to look for should not be empty");
    arrays.assertDoesNotContain(description, actual, other);
  }

  @Test
  public void should_fail_if_index_is_null() {
    int[] actual = new int[1];
    int value = 2;
    thrown.expect(NullPointerException.class, "Index should not be null");
    arrays.assertDoesNotContain(description, actual, value, null);
  }

  @Test
  public void should_fail_if_index_is_negative() {
    int[] actual = new int[1];
    int value = 2;
    thrown.expect(IllegalArgumentException.class, "The value of the index should not be negative");
    arrays.assertDoesNotContain(description, actual, value, Index.atIndex(-1));
  }

  @Test
  public void should_fail_if_actual_contains_other() {
    int[] actual = {1,2,3,4};
    int[] other = {3, 5};
    Set<Integer> found = newLinkedHashSet(3);
    try {
      arrays.assertDoesNotContain(description, actual, other);
    } catch(AssertionError e) {
      verify(failures).failure(description, shouldNotContain(actual, other, found));
    }
  }

  @Test
  public void should_fail_if_actual_contains_value_at_given_index() {
    int[] actual = {1, 2, 3, 4};
    int value = 3;
    Index index = Index.atIndex(2);
    try {
      arrays.assertDoesNotContain(description, actual, value, index);
    } catch(AssertionError e) {
      verify(failures).failure(description, ShouldNotContainAtIndex.shouldNotContainAtIndex(actual, value, index));
      return;
    }
    throw expectedAssertionErrorNotThrown();
  }

  @Test
  public void should_pass_if_actual_does_not_contain_other() {
    int[] actual = {1, 2, 3, 4};
    int[] values = {5, 6};
    arrays.assertDoesNotContain(description, actual, values);
  }

  @Test
  public void should_pass_if_actual_does_not_contain_value_at_given_index() {
    int[] actual = {1, 2, 3, 4};
    int value = 3;
    Index index = Index.atIndex(1);
    arrays.assertDoesNotContain(description, actual, value, index);
  }
}
