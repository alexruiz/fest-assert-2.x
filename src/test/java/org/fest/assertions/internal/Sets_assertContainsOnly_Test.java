/*
 * Created on May 2, 2013
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

import static org.fest.assertions.error.ShouldContainOnly.shouldContainOnly;
import static org.fest.assertions.test.ErrorMessages.valuesToLookForIsEmpty;
import static org.fest.assertions.test.ErrorMessages.valuesToLookForIsNull;
import static org.fest.assertions.test.FailureMessages.actualIsNull;
import static org.fest.assertions.test.TestFailures.expectedAssertionErrorNotThrown;
import static org.fest.test.ExpectedException.none;
import static org.fest.util.Arrays.array;
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
 * @author Yvonne Wang
 */
public class Sets_assertContainsOnly_Test {

  @Rule
  public ExpectedException thrown = none();

  private final Set<?> actual = newLinkedHashSet("Apple", "Peal", "Bananna");
  private final String[] values = array("Apple", "Peal");
  private Sets sets;
  private Description description;
  private Failures failures;

  @Before
  public void setUp() {
    sets = Sets.instance();
    failures = spy(new Failures());
    sets.failures = failures;
    description = new TestDescription("testing");
  }

  @Test
  public void should_fail_if_actual_is_null() {
    thrown.expect(AssertionError.class, actualIsNull());
    sets.assertContainsOnly(description, null, values);
  }

  @Test
  public void should_fail_if_actual_is_empty() {
    thrown.expect(AssertionError.class);
    sets.assertContainsOnly(description, newLinkedHashSet(), values);
  }

  @Test
  public void should_fail_if_given_values_is_null() {
    thrown.expect(NullPointerException.class, valuesToLookForIsNull());
    sets.assertContainsOnly(description, actual, null);
  }

  @Test
  public void should_fail_if_given_values_is_empty() {
    thrown.expect(IllegalArgumentException.class, valuesToLookForIsEmpty());
    sets.assertContainsOnly(description, actual, new Object[0]);
  }

  @Test
  public void should_pass_if_actual_contains_only_given_values() {
    sets.assertContainsOnly(description, actual, array("Apple", "Peal", "Bananna"));
  }

  @Test
  public void should_pass_if_actual_contains_only_given_values_in_different_order() {
    sets.assertContainsOnly(description, actual, array("Bananna", "Apple", "Peal"));
  }

  @Test
  public void should_pass_if_actual_contains_only_given_values_with_duplicates() {
    sets.assertContainsOnly(description, actual, array("Apple", "Peal", "Bananna", "Apple", "Peal"));
  }

  @Test
  public void should_fail_if_actual_contains_more_elements_than_the_given_values() {
    try {
      sets.assertContainsOnly(description, actual, values);
    } catch (AssertionError e) {
      verify(failures).failure(description,
          shouldContainOnly(actual, values, newLinkedHashSet(), newLinkedHashSet("Bananna")));
      return;
    }
    expectedAssertionErrorNotThrown();
  }

  @Test
  public void should_fail_if_actual_contains_less_elements_than_the_given_values() {
    String[] expected = array("Apple", "Peal", "Bananna", "Strawberry", "Peach");
    try {
      sets.assertContainsOnly(description, actual, expected);
    } catch (AssertionError e) {
      verify(failures).failure(description,
          shouldContainOnly(actual, expected, newLinkedHashSet("Strawberry", "Peach"), newLinkedHashSet()));
      return;
    }
    expectedAssertionErrorNotThrown();
  }
}
