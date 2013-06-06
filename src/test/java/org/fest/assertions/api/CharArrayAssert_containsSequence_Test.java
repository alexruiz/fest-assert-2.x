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
 * Tests for {@link CharArrayAssert#containsSequence(char...)}.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class CharArrayAssert_containsSequence_Test {
  @Rule
  public ExpectedException thrown = none();
  private char[] actual = {'a', 'b', 'c', 'd', 'e'};
  private char[] sequence = {'a', 'b', 'c'};
  private CharArrayAssert assertions;

  @Before
  public void setUp() {
    assertions = new CharArrayAssert(actual);
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
  public void should_pass_if_actual_contains_given_sequence_multiple_times() {
    assertions.containsSequence('a', 'b', 'c', 'a', 'c');
  }

  @Test
  public void should_throw_error_if_actual_is_null() {
    thrown.expect(AssertionError.class);
    assertions = new CharArrayAssert(null);
    assertions.containsSequence(sequence);
  }

  @Test
  public void should_fail_if_actual_is_empty() {
    thrown.expect(AssertionError.class);
    assertions = new CharArrayAssert(new char[0]);
    assertions.containsSequence(sequence);
  }

  @Test
  public void should_throw_error_if_given_sequence_is_null() {
    thrown.expect(AssertionError.class);
    sequence = null;
    assertions.containsSequence(sequence);
  }

  @Test
  public void should_throw_error_if_given_sequence_is_empty() {
    thrown.expect(AssertionError.class);
    assertions.containsSequence();
  }

  @Test
  public void should_fail_if_actual_does_not_contain_given_sequence() {
    thrown.expect(AssertionError.class);
    assertions.containsSequence('e', 'f');
  }
}
