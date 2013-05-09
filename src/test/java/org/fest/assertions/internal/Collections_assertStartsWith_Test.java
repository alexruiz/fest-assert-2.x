/*
 * Created on Apr 23, 2013
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

import static org.fest.assertions.error.ShouldStartWith.shouldStartWith;
import static org.fest.assertions.test.ErrorMessages.valuesToLookForIsEmpty;
import static org.fest.assertions.test.ErrorMessages.valuesToLookForIsNull;
import static org.fest.assertions.test.FailureMessages.actualIsNull;
import static org.fest.assertions.test.TestFailures.expectedAssertionErrorNotThrown;
import static org.fest.test.ExpectedException.none;
import static org.fest.util.Arrays.array;
import static org.fest.util.Collections.list;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.fest.assertions.description.Description;
import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Alex
 * @author Alex
 */
public class Collections_assertStartsWith_Test {

  @Rule
  public ExpectedException thrown = none();

  private List<String> actual;
  private Failures failures;
  private Collections collections;
  private Description description;

  @Before
  public void setUp() {
    actual = list("Luke", "Yoda", "Leia");
    failures = spy(new Failures());
    collections = Collections.instance();
    collections.failures = failures;
    description = new TestDescription("testing");
  }

  @Test
  public void should_throw_error_if_sequence_is_null() {
    thrown.expect(NullPointerException.class, valuesToLookForIsNull());
    collections.assertStartsWith(description, actual, null);
  }

  @Test
  public void should_throw_error_if_sequence_is_empty() {
    thrown.expect(IllegalArgumentException.class, valuesToLookForIsEmpty());
    collections.assertStartsWith(description, actual, new Object[] {});
  }

  @Test
  public void should_fail_if_actual_is_null() {
    thrown.expect(AssertionError.class, actualIsNull());
    collections.assertStartsWith(description, null, array("Yoda"));
  }

  @Test
  public void should_fail_if_sequence_is_bigger_than_actual() {
    Object[] sequence = new Object[10];
    try {
      collections.assertStartsWith(description, actual, sequence);
    } catch (AssertionError e) {
      verifyFailureThrownWhenSequenceNotFound(description, sequence);
      return;
    }
    throw expectedAssertionErrorNotThrown();
  }

  @Test
  public void should_fail_if_actual_does_not_start_with_sequence() {
    Object[] sequence = { "Han", "C-3PO" };
    try {
      collections.assertStartsWith(description, actual, sequence);
    } catch (AssertionError e) {
      verifyFailureThrownWhenSequenceNotFound(description, sequence);
      return;
    }
    throw expectedAssertionErrorNotThrown();
  }

  @Test
  public void should_fail_if_actual_starts_with_first_elements_of_sequence_only() {
    Object[] sequence = { "Leia", "Obi-Wan", "Han" };
    try {
      collections.assertStartsWith(description, actual, sequence);
    } catch (AssertionError e) {
      verifyFailureThrownWhenSequenceNotFound(description, sequence);
      return;
    }
    throw expectedAssertionErrorNotThrown();
  }

  private void verifyFailureThrownWhenSequenceNotFound(Description description, Object[] sequence) {
    verify(failures).failure(description, shouldStartWith(actual, sequence));
  }

  @Test
  public void should_pass_if_actual_starts_with_sequence() {
    collections.assertStartsWith(description, actual, array("Luke", "Yoda"));
  }

  @Test
  public void should_pass_if_actual_and_sequence_are_equal() {
    collections.assertStartsWith(description, actual, array("Luke", "Yoda", "Leia"));
  }
}
