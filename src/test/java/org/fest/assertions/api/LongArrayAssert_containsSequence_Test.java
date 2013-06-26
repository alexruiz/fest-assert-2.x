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
 * Tests for {@link LongArrayAssert#containsSequence(long...)}.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class LongArrayAssert_containsSequence_Test {
  @Rule
  public ExpectedException thrown = none();
  private final long[] actual = { 6, 8, 10, 16, 18 };
  private final long[] sequence = { 6, 8, 10 };
  private LongArrayAssert assertions;

  @Before
  public void setUp() {
    assertions = new LongArrayAssert(actual);
  }

  @Test
  public void should_pass_if_actual_contains_given_sequence() {
    assertions.containsSequence(actual);
  }

  @Test
  public void should_return_this_if_actual_contains_given_sequence() {
    assertions.containsSequence(actual);
  }

  @Test
  public void should_fail_if_actual_contains_given_sequence_multiple_times() {
    thrown.expect(AssertionError.class);
    assertions.containsSequence(new long[] { 6, 6, 8, 10, 10 });
  }

  @Test
  public void should_throw_error_if_actual_is_null() {
    thrown.expect(AssertionError.class);
    assertions = new LongArrayAssert(null);
    assertions.containsSequence(sequence);
  }

  @Test
  public void should_fail_if_actual_is_empty() {
    thrown.expect(AssertionError.class);
    assertions = new LongArrayAssert(new long[0]);
    assertions.containsSequence(sequence);
  }

  @Test
  public void should_throw_error_if_given_sequence_is_null() {
    thrown.expect(NullPointerException.class);
    assertions.containsSequence(null);
  }

  @Test
  public void should_throw_error_if_given_sequence_is_empty() {
    thrown.expect(IllegalArgumentException.class);
    assertions.containsSequence(new long[0]);
  }

  @Test
  public void should_fail_if_actual_does_not_contain_given_sequence() {
    thrown.expect(AssertionError.class);
    assertions.containsSequence(new long[] { 2, 4 });
  }
}
