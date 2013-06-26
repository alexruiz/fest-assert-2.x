/*
 * Created on Feb 14, 2013
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

import static org.fest.assertions.error.ShouldContain.shouldContain;
import static org.fest.assertions.test.ErrorMessages.valuesToLookForIsEmpty;
import static org.fest.assertions.test.TestFailures.expectedAssertionErrorNotThrown;
import static org.fest.test.ExpectedException.none;
import static org.fest.util.Arrays.array;
import static org.fest.util.Lists.newArrayList;
import static org.fest.util.Sets.newLinkedHashSet;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.util.Collection;
import java.util.List;

import org.fest.assertions.description.Description;
import org.fest.assertions.test.ErrorMessages;
import org.fest.assertions.test.FailureMessages;
import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for {@link Collections#assertContains(Description, Collection, Object[])}.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 *
 */
public class Collections_assertContains_Test {
  @Rule
  public ExpectedException thrown = none();
  private List<String> actual;
  private Failures failures;
  private Collections collections;
  private Description description;

  @Before
  public void setUp() {
    actual = newArrayList("Luke", "Yoda", "Leia");
    failures = spy(new Failures());
    collections = new Collections();
    collections.failures = failures;
    description = new TestDescription("testing");
  }

  @Test
  public void should_pass_if_actual_contains_given_values() {
    TestDescription description = new TestDescription("description for test");
    collections.assertContains(description, actual, array("Luke"));
  }

  @Test
  public void should_pass_if_actual_contains_given_values_in_different_order() {
    collections.assertContains(description, actual, array("Leia", "Yoda"));
  }

  @Test
  public void should_pass_if_actual_contains_all_given_values() {
    collections.assertContains(description, actual, array("Luke", "Yoda"));
  }

  @Test
  public void should_pass_if_actual_contains_given_values_more_than_once() {
    actual.addAll(newArrayList("Luke", "Luke"));
    collections.assertContains(description, actual, array("Luke"));
  }

  @Test
  public void should_pass_if_actual_contains_given_values_even_if_duplicated() {
    collections.assertContains(description, actual, array("Luke", "Luke"));
  }

  @Test
  public void should_throw_error_if_array_of_values_to_look_for_is_empty() {
    thrown.expect(IllegalArgumentException.class, valuesToLookForIsEmpty());
    collections.assertContains(description, actual, new Object[] {});
  }

  @Test
  public void should_throw_error_if_array_of_values_to_look_for_is_null() {
    thrown.expect(NullPointerException.class, ErrorMessages.valuesToLookForIsNull());
    collections.assertContains(description, actual, null);
  }

  @Test
  public void should_fail_if_actual_is_null() {
    thrown.expect(AssertionError.class, FailureMessages.actualIsNull());
    collections.assertContains(description, null, array("Yoda"));
  }

  @Test
  public void should_fail_if_actual_does_not_contain_values() {
    Object[] expected = { "Han", "Luke" };
    try {
      collections.assertContains(description, actual, expected);
    } catch (AssertionError e) {
      verify(failures).failure(description, shouldContain(actual, expected, newLinkedHashSet("Han")));
      return;
    }
    expectedAssertionErrorNotThrown();
  }
}
