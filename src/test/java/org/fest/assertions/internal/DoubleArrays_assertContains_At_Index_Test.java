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

import static org.fest.assertions.error.ShouldContainAtIndex.shouldContainAtIndex;
import static org.fest.assertions.error.ShouldNotBeEmpty.shouldNotBeEmpty;
import static org.fest.assertions.error.ShouldNotBeNull.shouldNotBeNull;
import static org.fest.assertions.test.TestFailures.expectedAssertionErrorNotThrown;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.lang.reflect.Array;

import org.fest.assertions.data.Index;
import org.fest.assertions.description.Description;
import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Alex
 * @author Alex
 */
public class DoubleArrays_assertContains_At_Index_Test {

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  private DoubleArrays arrays;
  private Description description;
  private Failures failures;
  private double value;

  @Before
  public void setUp() {
    arrays = new DoubleArrays();
    description = new TestDescription("testing");
    value = 6d;
    failures = spy(new Failures());
    Arrays parent = Arrays.instance();
    parent.failures = failures;
  }

  @Test
  public void should_fail_if_actual_is_null() {
    String message = shouldNotBeNull().create(description);
    thrown.expect(AssertionError.class, message);
    arrays.assertContains(description, null, value, Index.atIndex(0));
  }

  @Test
  public void should_fail_if_actual_is_empty() {
    try {
      arrays.assertContains(description, new double[0], value, Index.atIndex(0));
    } catch (AssertionError e) {
      verify(failures).failure(description, shouldNotBeEmpty());
      return;
    }
    expectedAssertionErrorNotThrown();
  }

  @Test
  public void should_fail_if_index_is_null() {
    double[] actual = new double[1];
    thrown.expect(NullPointerException.class, "Index should not be null");
    arrays.assertContains(description, actual, value, null);
  }

  @Test
  public void should_fail_if_index_is_negative() {
    thrown.expect(IllegalArgumentException.class, "The value of the index should not be negative");
    arrays.assertContains(description, new double[1], 0, Index.atIndex(-1));
  }

  @Test
  public void should_fail_if_index_is_out_of_boundary() {
    double[] actual = { 1d, 2d, 3d, 4d };
    Index index = Index.atIndex(4);
    String format = "Index should be between <%d> and <%d> (inclusive,) but was <%d>";
    thrown.expect(IndexOutOfBoundsException.class, String.format(format, 0, 3, 4));
    arrays.assertContains(description, actual, value, index);
  }

  @Test
  public void should_fail_if_actual_does_not_contain_value_at_given_index() {
    double[] actual = { 1d, 2d, 3d, 4d };
    double value = 3;
    Index index = Index.atIndex(3);
    try {
      arrays.assertContains(description, actual, value, index);
    } catch (AssertionError e) {
      verify(failures).failure(description, shouldContainAtIndex(actual, value, index, Array.get(actual, index.value)));
      return;
    }
    throw expectedAssertionErrorNotThrown();
  }

  @Test
  public void should_pass_if_actual_contains_value_at_given_index() {
    double[] actual = { 1d, 2d, 3d, 4d };
    double value = 3;
    Index index = Index.atIndex(2);
    arrays.assertContains(description, actual, value, index);
  }
}
