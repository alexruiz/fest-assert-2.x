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

import static org.fest.assertions.error.ShouldStartWith.shouldStartWith;
import static org.fest.assertions.test.CharArrays.emptyArray;
import static org.fest.assertions.test.CharArrays.newArray;
import static org.fest.assertions.test.ErrorMessages.valuesToLookForIsEmpty;
import static org.fest.assertions.test.ErrorMessages.valuesToLookForIsNull;
import static org.fest.assertions.test.FailureMessages.actualIsNull;
import static org.fest.assertions.test.TestFailures.expectedAssertionErrorNotThrown;
import static org.fest.test.ExpectedException.none;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.fest.assertions.description.Description;
import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for <code>{@link CharArrays#assertStartsWith(Description, char[], char[])}</code>.
 *
 * @author Alex Ruiz
 * @author Joel Costigliola
 * @author Yvonne Wang
 */
public class CharArrays_assertStartsWith_Test {

  @Rule
  public ExpectedException thrown = none();

  private CharArrays arrays;
  private Description description;
  private Failures failures;
  private final char[] actual = { 'a', 'b', 'c' };

  @Before
  public void setUp() {
    arrays = new CharArrays();
    description = new TestDescription("testing");
    Arrays parent = Arrays.instance();
    failures = spy(new Failures());
    parent.failures = failures;
  }

  @Test
  public void should_throw_error_if_sequence_is_null() {
    thrown.expect(NullPointerException.class, valuesToLookForIsNull());
    arrays.assertStartsWith(description, actual, null);
  }

  @Test
  public void should_throw_error_if_sequence_is_empty() {
    thrown.expect(IllegalArgumentException.class, valuesToLookForIsEmpty());
    arrays.assertStartsWith(description, actual, emptyArray());
  }

  @Test
  public void should_fail_if_actual_is_null() {
    thrown.expect(AssertionError.class, actualIsNull());
    arrays.assertStartsWith(description, null, newArray('a'));
  }

  @Test
  public void should_fail_if_sequence_is_bigger_than_actual() {
    char[] sequence = new char[10];
    try {
      arrays.assertStartsWith(description, actual, sequence);
    } catch (AssertionError e) {
      verify(failures).failure(description, shouldStartWith(actual, sequence));
      return;
    }
    expectedAssertionErrorNotThrown();
  }

  @Test
  public void should_fail_if_actual_does_not_start_with_sequence() {
    char[] sequence = { 'b', 'c' };
    try {
      arrays.assertStartsWith(description, actual, sequence);
    } catch (AssertionError e) {
      verify(failures).failure(description, shouldStartWith(actual, sequence));
      return;
    }
    expectedAssertionErrorNotThrown();
  }

  @Test
  public void should_fail_if_actual_starts_with_first_elements_of_sequence_only() {
    char[] sequence = { 'a', 'x' };
    try {
      arrays.assertStartsWith(description, actual, sequence);
    } catch (AssertionError e) {
      verify(failures).failure(description, shouldStartWith(actual, sequence));
      return;
    }
    expectedAssertionErrorNotThrown();
  }

  @Test
  public void should_pass_if_actual_starts_with_sequence() {
    arrays.assertStartsWith(description, actual, newArray('a', 'b', 'c'));
  }

  @Test
  public void should_pass_if_actual_and_sequence_are_equal() {
    arrays.assertStartsWith(description, actual, newArray('a', 'b', 'c'));
  }
}
