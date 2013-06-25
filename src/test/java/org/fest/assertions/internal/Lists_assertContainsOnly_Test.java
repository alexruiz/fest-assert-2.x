/*
 * Created on Apr 29, 2013
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

import static java.util.Collections.emptyList;

import static org.fest.assertions.error.ShouldContainOnly.shouldContainOnly;
import static org.fest.assertions.test.ErrorMessages.valuesToLookForIsEmpty;
import static org.fest.assertions.test.ErrorMessages.valuesToLookForIsNull;
import static org.fest.assertions.test.FailureMessages.actualIsNull;
import static org.fest.assertions.test.TestFailures.expectedAssertionErrorNotThrown;
import static org.fest.test.ExpectedException.none;
import static org.fest.util.Arrays.array;
import static org.fest.util.Lists.newArrayList;
import static org.fest.util.Sets.newLinkedHashSet;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.fest.assertions.description.Description;
import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for {@link Lists#assertContainsOnly(Description, List, Object[])}.
 *
 * @author Yvonne Wang
 */
public class Lists_assertContainsOnly_Test {
  @Rule
  public ExpectedException thrown = none();
  private List<String> actual;
  private Failures failures;
  private Lists lists;
  private Description description;

  @Before
  public void setUp() {
    actual = newArrayList("Luke", "Yoda", "Leia");
    failures = spy(new Failures());
    lists = Lists.instance();
    lists.failures = failures;
    description = new TestDescription("testing");
  }

  @Test
  public void should_pass_if_actual_contains_given_values_only() {
    lists.assertContainsOnly(description, actual, array("Luke", "Yoda", "Leia"));
  }

  @Test
  public void should_pass_if_actual_contains_given_values_only_in_different_order() {
    lists.assertContainsOnly(description, actual, array("Leia", "Yoda", "Luke"));
  }

  @Test
  public void should_pass_if_actual_with_xtuplicates_contains_given_values_only() {
    actual.addAll(newArrayList("Luke", "Luke"));
    lists.assertContainsOnly(description, actual, array("Luke", "Yoda", "Leia"));
  }

  @Test
  public void should_pass_if_actual_contains_only_given_values_with_xtuplicated() {
    lists.assertContainsOnly(description, actual, array("Luke", "Luke", "Luke", "Yoda", "Leia"));
  }

  @Test
  public void should_throw_error_if_array_of_values_to_look_for_is_empty() {
    thrown.expect(IllegalArgumentException.class, valuesToLookForIsEmpty());
    lists.assertContainsOnly(description, actual, new Object[] {});
  }

  @Test
  public void should_throw_error_if_array_of_values_to_look_for_is_null() {
    thrown.expect(NullPointerException.class, valuesToLookForIsNull());
    lists.assertContainsOnly(description, emptyList(), null);
  }

  @Test
  public void should_fail_if_actual_is_null() {
    thrown.expect(AssertionError.class, actualIsNull());
    lists.assertContainsOnly(description, null, array("Yoda"));
  }

  @Test
  public void should_fail_if_actual_does_not_contain_given_values_only() {
    Object[] expected = { "Luke", "Yoda", "Han" };
    try {
      lists.assertContainsOnly(description, actual, expected);
    } catch (AssertionError e) {
      verify(failures).failure(description,
          shouldContainOnly(actual, expected, newLinkedHashSet("Han"), newLinkedHashSet("Leia")));
      return;
    }
    expectedAssertionErrorNotThrown();
  }

}
