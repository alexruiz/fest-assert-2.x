/*
 * Created on Dec 15, 2010
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

import static org.fest.assertions.data.Index.atIndex;
import static org.fest.assertions.error.ShouldContainAtIndex.shouldContainAtIndex;
import static org.fest.assertions.test.BooleanArrays.emptyArray;
import static org.fest.assertions.test.FailureMessages.actualIsEmpty;
import static org.fest.assertions.test.FailureMessages.actualIsNull;
import static org.fest.assertions.test.TestFailures.expectedAssertionErrorNotThrown;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.fest.assertions.data.Index;
import org.fest.assertions.description.Description;
import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for <code>{@link BooleanArrays#assertContains(Description, boolean[], boolean, Index)}</code>.
 *
 * @author Alex Ruiz
 * @author Joel Costigliola
 * @author Yvonne Wang
 */
public class BooleanArrays_assertContains_at_Index_Test {

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  private BooleanArrays arrays;
  private Description description;
  private Failures failures;
  private final boolean[] actual = { true, false, true };

  @Before
  public void setUp() {
    arrays = new BooleanArrays();
    description = new TestDescription("testing");
    failures = spy(new Failures());
    Arrays parent = Arrays.instance();
    parent.failures = failures;
  }

  @Test
  public void should_fail_if_actual_is_null() {
    thrown.expect(AssertionError.class, actualIsNull());
    arrays.assertContains(description, null, true, Index.atIndex(0));
  }

  @Test
  public void should_fail_if_actual_is_empty() {
    thrown.expect(AssertionError.class, actualIsEmpty());
    arrays.assertContains(description, emptyArray(), true, Index.atIndex(0));
  }

  @Test
  public void should_throw_error_if_Index_is_null() {
    thrown.expect(NullPointerException.class, "Index should not be null");
    arrays.assertContains(description, actual, true, null);
  }

  @Test
  public void should_throw_error_if_Index_is_out_of_bounds() {
    thrown.expect(IndexOutOfBoundsException.class, "Index should be between <0> and <2> (inclusive,) but was <6>");
    arrays.assertContains(description, actual, true, atIndex(6));
  }

  @Test
  public void should_fail_if_actual_does_not_contain_value_at_index() {
    boolean value = true;
    Index index = atIndex(1);
    try {
      arrays.assertContains(description, actual, value, index);
    } catch (AssertionError e) {
      verify(failures).failure(description, shouldContainAtIndex(actual, value, index, false));
      return;
    }
    expectedAssertionErrorNotThrown();
  }

  @Test
  public void should_pass_if_actual_contains_value_at_index() {
    arrays.assertContains(description, actual, false, atIndex(1));
  }
}
