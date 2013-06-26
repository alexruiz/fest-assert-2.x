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
 * Tests for {@link ShortArrayAssert#endsWith(short...)}.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class ShortArrayAssert_endsWith_Test {
  @Rule
  public ExpectedException thrown = none();
  private final short[] actual = { (short) 6, (short) 8, (short) 10, (short) 16, (short) 18 };
  private short[] sequence = { (short) 16, (short) 18 };
  private ShortArrayAssert assertions;

  @Before
  public void setUp() {
    assertions = new ShortArrayAssert(actual);
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
  public void should_throw_error_if_actual_ends_with_given_sequence_multiple_times() {
    thrown.expect(AssertionError.class);
    assertions.endsWith((short) 16, (short) 16, (short) 18);
  }

  @Test
  public void should_throw_error_if_actual_is_null() {
    thrown.expect(AssertionError.class);
    assertions = new ShortArrayAssert(null);
    assertions.endsWith(sequence);
  }

  @Test
  public void should_fail_if_actual_is_empty() {
    thrown.expect(AssertionError.class);
    assertions = new ShortArrayAssert(new short[0]);
    assertions.endsWith(sequence);
  }

  @Test
  public void should_throw_error_if_given_sequence_is_null() {
    thrown.expect(NullPointerException.class);
    sequence = null;
    assertions.endsWith(sequence);
  }

  @Test
  public void should_throw_error_if_given_sequence_is_empty() {
    thrown.expect(IllegalArgumentException.class);
    assertions.endsWith();
  }

  @Test
  public void should_fail_if_actual_does_not_end_with_given_sequence() {
    thrown.expect(AssertionError.class);
    assertions.endsWith((short) 2, (short) 4);
  }
}
