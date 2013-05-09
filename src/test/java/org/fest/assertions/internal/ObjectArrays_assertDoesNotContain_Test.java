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

import static org.fest.assertions.error.ShouldNotContain.shouldNotContain;
import static org.fest.assertions.test.ErrorMessages.valuesToLookForIsEmpty;
import static org.fest.assertions.test.ErrorMessages.valuesToLookForIsNull;
import static org.fest.assertions.test.FailureMessages.actualIsNull;
import static org.fest.assertions.test.ObjectArrays.emptyArray;
import static org.fest.assertions.test.TestFailures.expectedAssertionErrorNotThrown;
import static org.fest.test.ExpectedException.none;
import static org.fest.util.Arrays.array;
import static org.fest.util.Sets.newLinkedHashSet;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.fest.assertions.description.Description;
import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for <code>{@link ObjectArrays#assertDoesNotContain(Description, Object[], Object[])}</code>.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class ObjectArrays_assertDoesNotContain_Test {

  @Rule
  public ExpectedException thrown = none();

  private ObjectArrays arrays;
  private Failures failures;
  private Description description;
  private final Object[] actual = array("Yoda", "Luke", "Leia");

  @Before
  public void setUp() {
    arrays = ObjectArrays.instance();
    Arrays parent = Arrays.instance();
    failures = spy(new Failures());
    parent.failures = failures;
    description = new TestDescription("testing");
  }

  @Test
  public void should_pass_if_actual_does_not_contain_given_values() {
    arrays.assertDoesNotContain(description, actual, array("Han"));
  }

  @Test
  public void should_pass_if_actual_does_not_contain_given_values_even_if_duplicated() {
    arrays.assertDoesNotContain(description, actual, array("Han", "Han", "Anakin"));
  }

  @Test
  public void should_throw_error_if_array_of_values_to_look_for_is_empty() {
    thrown.expect(IllegalArgumentException.class, valuesToLookForIsEmpty());
    arrays.assertDoesNotContain(description, actual, emptyArray());
  }

  @Test
  public void should_throw_error_if_array_of_values_to_look_for_is_null() {
    thrown.expect(NullPointerException.class, valuesToLookForIsNull());
    arrays.assertDoesNotContain(description, actual, null);
  }

  @Test
  public void should_throw_error_if_actual_is_null() {
    thrown.expect(AssertionError.class, actualIsNull());
    arrays.assertDoesNotContain(description, null, array("Yoda"));
  }

  @Test
  public void should_fail_if_actual_contains_given_values() {
    Object[] expected = { "Luke", "Yoda", "Han" };
    try {
      arrays.assertDoesNotContain(description, actual, expected);
    } catch (AssertionError e) {
      verify(failures).failure(description, shouldNotContain(actual, expected, newLinkedHashSet("Luke", "Yoda")));
      return;
    }
    expectedAssertionErrorNotThrown();
  }

  @Test
  public void should_fail_if_actual_contains_given_values_with_duplicates() {
    Object[] expected = { "Luke", "Yoda", "Han", "Luke", "Yoda" };
    try {
      arrays.assertDoesNotContain(description, actual, expected);
    } catch (AssertionError e) {
      verify(failures).failure(description, shouldNotContain(actual, expected, newLinkedHashSet("Luke", "Yoda")));
      return;
    }
    expectedAssertionErrorNotThrown();
  }
}
