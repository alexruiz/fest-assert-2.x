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

import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.fest.test.ExpectedException.none;

/**
 * Tests for {@link LongArrayAssert#endsWith(long...)}.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class LongArrayAssert_endsWith_Test {
  @Rule
  public ExpectedException thrown = none();
  private long[] actual = {6, 8, 10, 16, 18};
  private long[] sequence = {16, 18};
  private LongArrayAssert assertions;

  @Before
  public void setUp() {
    assertions = new LongArrayAssert(actual);
  }

  @Test
  public void should_pass_if_actual_ends_with_given_sequence() {
    assertions.endsWith(sequence);
  }

  @Test
  public void should_return_this_if_actual_ends_with_given_sequence() {
    assertions.endsWith(sequence);
  }

  @Test
  public void should_pass_if_actual_starts_is_itself() {
    assertions.endsWith(actual);
  }

  @Test
  public void should_pass_if_actual_ends_with_given_sequence_multiple_times() {
    assertions.endsWith(16, 16, 18);
  }

  @Test
  public void should_throw_error_if_actual_is_null() {
    thrown.expect(AssertionError.class);
    assertions = new LongArrayAssert(null);
    assertions.endsWith(sequence);
  }

  @Test
  public void should_fail_if_actual_is_empty() {
    thrown.expect(AssertionError.class);
    assertions = new LongArrayAssert(new long[0]);
    assertions.endsWith(sequence);
  }

  @Test
  public void should_throw_error_if_given_sequence_is_null() {
    thrown.expect(AssertionError.class);
    sequence = null;
    assertions.endsWith(sequence);
  }

  @Test
  public void should_throw_error_if_given_sequence_is_empty() {
    thrown.expect(AssertionError.class);
    assertions.endsWith();
  }

  @Test
  public void should_fail_if_actual_does_not_end_with_given_sequence() {
    thrown.expect(AssertionError.class);
    assertions.endsWith(2, 4);
  }
}