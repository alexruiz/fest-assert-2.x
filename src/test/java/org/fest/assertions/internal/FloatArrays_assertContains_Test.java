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

import static org.fest.assertions.error.ShouldContain.shouldContain;
import static org.fest.assertions.error.ShouldNotBeNull.shouldNotBeNull;
import static org.fest.assertions.test.TestFailures.expectedAssertionErrorNotThrown;
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
 * Tests for <code>{@link FloatArrays#assertContains(Description, float[], float[])}</code>.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class FloatArrays_assertContains_Test {

  @Rule
  public ExpectedException thrown = ExpectedException.none();

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
    float[] values = { 1f, 2f };
    String message = shouldNotBeNull().create(description);
    thrown.expect(AssertionError.class, message);
    arrays.assertContains(description, null, values);
  }

  @Test
  public void should_fail_if_other_is_null() {
    thrown.expect(NullPointerException.class, "The array of values to look for should not be null");
    arrays.assertContains(description, new float[0], null);
  }

  @Test
  public void should_fail_if_other_is_empty() {
    thrown.expect(IllegalArgumentException.class, "The array of values to look for should not be empty");
    arrays.assertContains(description, new float[1], new float[0]);
  }

  @Test
  public void should_fail_if_actual_does_not_contain_other() {
    float[] actual = { 1f, 2f, 3f, 4f };
    float[] other = { 4f, 5f };
    Set<Float> notFound = newLinkedHashSet();
    notFound.add(5f);
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
    float[] actual = { 1f, 2f, 3f, 4f };
    float[] other = { 1f, 2f };
    arrays.assertContains(description, actual, other);
  }
}
