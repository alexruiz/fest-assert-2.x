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
import static org.fest.assertions.error.ShouldNotContainAtIndex.shouldNotContainAtIndex;
import static org.fest.assertions.test.TestFailures.expectedAssertionErrorNotThrown;
import static org.fest.test.ExpectedException.none;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.fest.assertions.data.Index;
import org.fest.assertions.description.Description;
import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Alex Ruiz
 * @author Yvonne Wang
 */

public class ShortArrays_assertDoesNotContain_At_Index_Test {

  @Rule
  public ExpectedException thrown = none();

  private ShortArrays arrays;
  private Description description;
  private Failures failures;
  private short value;

  @Before
  public void setUp() {
    arrays = new ShortArrays();
    description = new TestDescription("testing");
    value = 6;
    failures = spy(new Failures());
    Arrays parent = Arrays.instance();
    parent.failures = failures;
  }

  @Test
  public void should_fail_if_actual_is_null() {
    String message = shouldNotBeNull().create(description);
    thrown.expect(AssertionError.class, message);
    arrays.assertDoesNotContain(description, null, value, Index.atIndex(0));
  }

  @Test
  public void should_fail_if_index_is_null() {
    short[] actual = new short[1];
    thrown.expect(NullPointerException.class, "Index should not be null");
    arrays.assertDoesNotContain(description, actual, value, null);
  }

  @Test
  public void should_fail_if_index_is_negative() {
    short[] actual = new short[1];
    thrown.expect(IllegalArgumentException.class, "The value of the index should not be negative");
    arrays.assertDoesNotContain(description, actual, value, Index.atIndex(-1));
  }

  @Test
  public void should_fail_if_actual_contains_value_at_given_index() {
    short[] actual = { 2, 4, 6, 8 };
    try {
      arrays.assertDoesNotContain(description, actual, value, Index.atIndex(2));
    } catch (AssertionError e) {
      verify(failures).failure(description, shouldNotContainAtIndex(actual, value, Index.atIndex(2)));
      return;
    }
    expectedAssertionErrorNotThrown();
  }

  @Test
  public void should_pass_if_actual_does_not_contain_value_at_given_index() {
    short[] actual = { 2, 4, 6, 8 };
    arrays.assertDoesNotContain(description, actual, value, Index.atIndex(0));
  }

  @Test
  public void should_pass_if_actual_is_empty() {
    arrays.assertDoesNotContain(description, new short[0], value, Index.atIndex(0));
  }
}
