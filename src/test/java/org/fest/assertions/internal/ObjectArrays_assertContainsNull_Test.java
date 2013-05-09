/*
 * Created on Nov 29, 2010
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

import static org.fest.assertions.error.ShouldContainNull.shouldContainNull;
import static org.fest.assertions.test.FailureMessages.actualIsNull;
import static org.fest.assertions.test.TestFailures.expectedAssertionErrorNotThrown;
import static org.fest.test.ExpectedException.none;
import static org.fest.util.Arrays.array;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.fest.assertions.description.Description;
import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for <code>{@link ObjectArrays#assertContainsNull(Description, Object[])}</code>.
 *
 * @author Yvonne Wang
 */
public class ObjectArrays_assertContainsNull_Test {

  @Rule
  public ExpectedException thrown = none();

  private ObjectArrays arrays;
  private Failures failures;
  private Description description;
  private Object[] actual = array("Yoda", "Luke", null);

  @Before
  public void setUp() {
    arrays = ObjectArrays.instance();
    Arrays parent = Arrays.instance();
    failures = spy(new Failures());
    parent.failures = failures;
    description = new TestDescription("testing");
  }

  @Test
  public void should_pass_if_actual_contains_null() {
    arrays.assertContainsNull(description, actual);
  }

  @Test
  public void should_pass_if_actual_contains_only_null_values() {
    actual = array((String) null, (String) null);
    arrays.assertContainsNull(description, actual);
  }

  @Test
  public void should_pass_if_actual_contains_null_more_than_once() {
    actual = array("Luke", null, null);
    arrays.assertContainsNull(description, actual);
  }

  @Test
  public void should_throw_an_error_if_actual_is_null() {
    thrown.expect(AssertionError.class, actualIsNull());
    arrays.assertContainsNull(description, null);
  }

  @Test
  public void should_fail_if_actual_does_not_contain_null() {
    actual = array("Luke", "Yoda");
    try {
      arrays.assertContainsNull(description, actual);
    } catch (AssertionError e) {
      verify(failures).failure(description, shouldContainNull(actual));
      return;
    }
    expectedAssertionErrorNotThrown();
  }
}
