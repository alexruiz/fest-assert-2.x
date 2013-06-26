/*
 * Created on Mar 29, 2009
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
package org.fest.assertions.api;

import static org.fest.test.ExpectedException.none;

import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for {@link FloatArrayAssert#startsWith(float...)}.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class FloatArrayAssert_startsWith_Test {
  @Rule
  public ExpectedException thrown = none();
  private final float[] actual = { 6f, 8f, 10f, 16f, 18f };
  private float[] sequence = { 6f, 8f, 10f };
  private FloatArrayAssert assertions;

  @Before
  public void setUp() {
    assertions = new FloatArrayAssert(actual);
  }

  @Test
  public void should_pass_if_actual_starts_with_given_sequence() {
    assertions.startsWith(sequence);
  }

  @Test
  public void should_return_this_if_actual_starts_with_given_sequence() {
    assertions.startsWith(sequence);
  }

  @Test
  public void should_pass_if_actual_starts_is_itself() {
    assertions.startsWith(actual);
  }

  @Test
  public void should_fail_if_actual_starts_with_given_sequence_multiple_times() {
    thrown.expect(AssertionError.class);
    assertions.startsWith(6f, 6f, 8f, 10f, 10f);
  }

  @Test
  public void should_throw_error_if_actual_is_null() {
    thrown.expect(AssertionError.class);
    assertions = new FloatArrayAssert(null);
    assertions.startsWith(sequence);
  }

  @Test
  public void should_fail_if_actual_is_empty() {
    thrown.expect(AssertionError.class);
    assertions = new FloatArrayAssert(new float[0]);
    assertions.startsWith(sequence);
  }

  @Test
  public void should_throw_error_if_given_sequence_is_null() {
    thrown.expect(NullPointerException.class);
    sequence = null;
    assertions.startsWith(sequence);
  }

  @Test
  public void should_throw_error_if_parameter_given_sequence_is_missing() {
    thrown.expect(IllegalArgumentException.class);
    assertions.startsWith();
  }


  @Test
  public void should_throw_error_if_given_sequence_is_empty() {
    thrown.expect(IllegalArgumentException.class);
    assertions.startsWith();
  }

  @Test
  public void should_fail_if_actual_does_not_start_with_given_sequence() {
    thrown.expect(AssertionError.class);
    assertions.startsWith(2f, 4f);
  }
}
