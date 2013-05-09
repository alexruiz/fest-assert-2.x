/*
 * Created on Dec 20, 2010
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
 * Copyright @2010-2011 the original author or authors.
 */
package org.fest.assertions.internal;

import static org.fest.assertions.error.ShouldNotBeNull.shouldNotBeNull;
import static org.fest.assertions.test.TestFailures.expectedAssertionErrorNotThrown;
import static org.fest.test.ExpectedException.none;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.fest.assertions.data.Index;
import org.fest.assertions.description.Description;
import org.fest.assertions.error.ShouldNotContainAtIndex;
import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for <code>{@link FloatArrays#assertDoesNotContain(Description, float[], float, Index)}</code>.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class FloatArrays_assertDoesNotContain_At_Index_Test {

  @Rule
  public ExpectedException thrown = none();

  private FloatArrays arrays;
  private Description description;
  private Failures failures;

  @Before
  public void setUp() {
    arrays = new FloatArrays();
    description = new TestDescription("testing");
    failures = spy(new Failures());
    Arrays parent = Arrays.instance();
    parent.failures = failures;
  }

  @Test
  public void should_fail_if_actual_is_null() {
    float value = 6f;
    String message = shouldNotBeNull().create(description);
    thrown.expect(AssertionError.class, message);
    arrays.assertDoesNotContain(description, null, value, Index.atIndex(0));
  }

  @Test
  public void should_fail_if_index_is_null() {
    float[] actual = new float[1];
    float value = 2f;
    thrown.expect(NullPointerException.class, "Index should not be null");
    arrays.assertDoesNotContain(description, actual, value, null);
  }

  @Test
  public void should_fail_if_index_is_negative() {
    float[] actual = new float[1];
    float value = 2;
    thrown.expect(IllegalArgumentException.class, "The value of the index should not be negative");
    arrays.assertDoesNotContain(description, actual, value, Index.atIndex(-1));
  }

  @Test
  public void should_fail_if_actual_contains_value_at_given_index() {
    float[] actual = { 1f, 2f, 3f, 4f };
    float value = 3f;
    Index index = Index.atIndex(2);
    try {
      arrays.assertDoesNotContain(description, actual, value, index);
    } catch (AssertionError e) {
      verify(failures).failure(description, ShouldNotContainAtIndex.shouldNotContainAtIndex(actual, value, index));
      return;
    }
    throw expectedAssertionErrorNotThrown();
  }

  @Test
  public void should_pass_if_actual_is_empty() {
    float[] actual = {};
    float value = 3f;
    Index index = Index.atIndex(1);
    arrays.assertDoesNotContain(description, actual, value, index);
  }

  @Test
  public void should_pass_if_actual_does_not_contain_value_at_given_index() {
    float[] actual = { 1f, 2f, 3f, 4f };
    float value = 3f;
    Index index = Index.atIndex(1);
    arrays.assertDoesNotContain(description, actual, value, index);
  }
}
