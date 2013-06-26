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

import static org.fest.assertions.error.ShouldEndWith.shouldEndWith;
import static org.fest.assertions.test.BooleanArrays.emptyArray;
import static org.fest.assertions.test.BooleanArrays.newArray;
import static org.fest.assertions.test.ErrorMessages.valuesToLookForIsEmpty;
import static org.fest.assertions.test.ErrorMessages.valuesToLookForIsNull;
import static org.fest.assertions.test.FailureMessages.actualIsNull;
import static org.fest.assertions.test.TestFailures.expectedAssertionErrorNotThrown;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.fest.assertions.description.Description;
import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for <code>{@link BooleanArrays#assertEndsWith(Description, boolean[], boolean[])}</code>.
 *
 * @author Alex Ruiz
 * @author Joel Costigliola
 * @author Yvonne Wang
 */
public class BooleanArrays_assertEndsWith_Test {

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
  public void should_throw_error_if_sequence_is_null() {
    thrown.expect(NullPointerException.class, valuesToLookForIsNull());
    arrays.assertEndsWith(description, actual, null);
  }

  @Test
  public void should_throw_error_if_sequence_is_empty() {
    thrown.expect(IllegalArgumentException.class, valuesToLookForIsEmpty());
    arrays.assertEndsWith(description, actual, emptyArray());
  }

  @Test
  public void should_fail_if_actual_is_null() {
    thrown.expect(AssertionError.class, actualIsNull());
    arrays.assertEndsWith(description, null, newArray(true));
  }

  @Test
  public void should_fail_if_sequence_is_bigger_than_actual() {
    boolean[] sequence = { true, false, false, true, true, false };
    try {
      arrays.assertEndsWith(description, actual, sequence);
    } catch (AssertionError e) {
      verifyFailureThrownWhenSequenceNotFound(description, sequence);
      return;
    }
    expectedAssertionErrorNotThrown();
  }

  @Test
  public void should_fail_if_actual_does_not_end_with_sequence() {
    boolean[] sequence = { true, false };
    try {
      arrays.assertEndsWith(description, actual, sequence);
    } catch (AssertionError e) {
      verifyFailureThrownWhenSequenceNotFound(description, sequence);
      return;
    }
    expectedAssertionErrorNotThrown();
  }

  @Test
  public void should_fail_if_actual_ends_with_first_elements_of_sequence_only() {
    boolean[] sequence = { false, false };
    try {
      arrays.assertEndsWith(description, actual, sequence);
    } catch (AssertionError e) {
      verifyFailureThrownWhenSequenceNotFound(description, sequence);
      return;
    }
    expectedAssertionErrorNotThrown();
  }

  private void verifyFailureThrownWhenSequenceNotFound(Description description, boolean[] sequence) {
    verify(failures).failure(description, shouldEndWith(actual, sequence));
  }

  @Test
  public void should_pass_if_actual_ends_with_sequence() {
    arrays.assertEndsWith(description, actual, newArray(false, true));
  }

  @Test
  public void should_pass_if_actual_and_sequence_are_equal() {
    arrays.assertEndsWith(description, actual, actual);
  }
}
