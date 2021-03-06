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

import static org.fest.assertions.error.ShouldContainOnly.shouldContainOnly;
import static org.fest.assertions.test.CharArrays.emptyArray;
import static org.fest.assertions.test.CharArrays.newArray;
import static org.fest.assertions.test.ErrorMessages.valuesToLookForIsEmpty;
import static org.fest.assertions.test.ErrorMessages.valuesToLookForIsNull;
import static org.fest.assertions.test.FailureMessages.actualIsNull;
import static org.fest.assertions.test.TestFailures.expectedAssertionErrorNotThrown;
import static org.fest.test.ExpectedException.none;
import static org.fest.util.Sets.newLinkedHashSet;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.fest.assertions.description.Description;
import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for <code>{@link CharArrays#assertContainsOnly(Description, char[], char[])}</code>.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class CharArrays_assertContainsOnly_Test {

  @Rule
  public ExpectedException thrown = none();

  private CharArrays arrays;
  private Description description;
  private Failures failures;
  private char[] actual = newArray('a', 'b', 'c');

  @Before
  public void setUp() {
    arrays = new CharArrays();
    description = new TestDescription("testing");
    Arrays parent = Arrays.instance();
    failures = spy(new Failures());
    parent.failures = failures;
  }

  @Test
  public void should_pass_if_actual_contains_given_values_only() {
    arrays.assertContainsOnly(description, actual, newArray('a', 'b', 'c'));
  }

  @Test
  public void should_pass_if_actual_contains_given_values_only_in_different_order() {
    arrays.assertContainsOnly(description, actual, newArray('c', 'b', 'a'));
  }

  @Test
  public void should_pass_if_actual_contains_given_values_only_more_than_once() {
    actual = newArray('a', 'b', 'c', 'c', 'c');
    arrays.assertContainsOnly(description, actual, newArray('a', 'b', 'c'));
  }

  @Test
  public void should_pass_if_actual_contains_given_values_only_even_if_duplicated() {
    arrays.assertContainsOnly(description, actual, newArray('a', 'b', 'c', 'a', 'b', 'c'));
  }

  @Test
  public void should_throw_error_if_array_of_values_to_look_for_is_empty() {
    thrown.expect(IllegalArgumentException.class, valuesToLookForIsEmpty());
    arrays.assertContainsOnly(description, actual, emptyArray());
  }

  @Test
  public void should_throw_error_if_array_of_values_to_look_for_is_null() {
    thrown.expect(NullPointerException.class, valuesToLookForIsNull());
    arrays.assertContainsOnly(description, actual, null);
  }

  @Test
  public void should_fail_if_actual_is_null() {
    thrown.expect(AssertionError.class, actualIsNull());
    arrays.assertContainsOnly(description, null, newArray('a'));
  }

  @Test
  public void should_fail_if_actual_does_not_contain_given_values_only() {
    char[] expected = { 'a', 'b', 'd' };
    try {
      arrays.assertContainsOnly(description, actual, expected);
    } catch (AssertionError e) {
      verify(failures).failure(description,
          shouldContainOnly(actual, expected, newLinkedHashSet('d'), newLinkedHashSet('c')));
      return;
    }
    expectedAssertionErrorNotThrown();
  }
}
