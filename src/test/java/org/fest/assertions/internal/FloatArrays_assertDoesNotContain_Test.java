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
 * Copyright @2010-2013 the original author or authors.
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
 * Tests for <code>{@link FloatArrays#assertDoesNotContain(Description, float[], float[])}</code>.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class FloatArrays_assertDoesNotContain_Test {

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
    float[] other = { 1f, 2f };
    String message = shouldNotBeNull().create(description);
    thrown.expect(AssertionError.class, message);
    arrays.assertDoesNotContain(description, null, other);
  }

  @Test
  public void should_fail_if_other_is_null() {
    float[] actual = new float[1];
    thrown.expect(NullPointerException.class, "The array of values to look for should not be null");
    arrays.assertDoesNotContain(description, actual, null);
  }

  @Test
  public void should_fail_if_other_is_empty() {
    float[] actual = new float[1];
    float[] other = new float[0];
    thrown.expect(IllegalArgumentException.class, "The array of values to look for should not be empty");
    arrays.assertDoesNotContain(description, actual, other);
  }

  @Test
  public void should_fail_if_actual_contains_partial_other() {
    float[] actual = { 1f, 2f, 3f, 4f };
    float[] other = { 3f, 5f };
    Set<Float> found = newLinkedHashSet(3f);
    try {
      arrays.assertDoesNotContain(description, actual, other);
    } catch (AssertionError e) {
      verify(failures).failure(description, shouldNotContain(actual, other, found));
    }
  }

  @Test
  public void should_fail_if_actual_contains_entire_other() {
    float[] actual = { 1f, 2f, 3f, 4f };
    float[] other = { 3f, 4f };
    Set<Float> found = newLinkedHashSet(3f);
    found.add(4f);
    try {
      arrays.assertDoesNotContain(description, actual, other);
    } catch (AssertionError e) {
      verify(failures).failure(description, shouldNotContain(actual, other, found));
    }
  }

  @Test
  public void should_pass_if_actual_does_not_contain_other() {
    float[] actual = { 1f, 2f, 3f, 4f };
    float[] values = { 5f, 6f };
    arrays.assertDoesNotContain(description, actual, values);
  }

  @Test
  public void should_pass_if_actual_does_not_contain_other_with_duplicates() {
    float[] actual = { 1f, 2f, 3f, 4f };
    float[] values = { 5f, 5f, 6f, 6f };
    arrays.assertDoesNotContain(description, actual, values);
  }
}
