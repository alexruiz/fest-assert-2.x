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

import java.util.List;

import org.fest.test.ExpectedException;
import org.fest.util.Lists;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for {@link ListAssert#endsWith(Object...)}.
 *
 * @author Yvonne Wang
 */
public class ListAssert_endsWith_Test {
  @Rule
  public ExpectedException thrown = none();
  private final List<String> actual = Lists.newArrayList("one", "two", "three", "four");
  private Object[] sequence = { "three", "four" };
  private ListAssert assertions;

  @Before
  public void setUp() {
    assertions = new ListAssert(actual);
  }

  @Test
  public void should_throw_error_if_actual_is_null() {
    thrown.expect(AssertionError.class);
    assertions = new ListAssert(null);
    assertions.endsWith(sequence);
  }

  @Test
  public void should_throw_error_if_sequence_is_null() {
    sequence = null;
    thrown.expect(NullPointerException.class);
    assertions.endsWith(sequence);
  }

  @Test
  public void should_throw_error_if_sequence_is_empty() {
    sequence = new Object[0];
    thrown.expect(IllegalArgumentException.class);
    assertions.endsWith(sequence);
  }

  @Test
  public void should_throw_error_if_parameter_sequence_is_missing() {
    thrown.expect(IllegalArgumentException.class);
    assertions.endsWith();
  }

  @Test
  public void should_fail_if_actual_does_not_end_with_sequence() {
    thrown.expect(AssertionError.class);
    Object[] sequence = { "three", "four", "five" };
    assertions.endsWith(sequence);
  }

  @Test
  public void should_fail_if_sequence_size_is_larger_than_actual_size() {
    Object[] sequence = { "three", "four", "one", "two", "three", "four" };
    thrown.expect(AssertionError.class);
    assertions.endsWith(sequence);
  }

  @Test
  public void should_fail_if_sequence_has_multiple_same_elements_while_actual_does_not() {
    Object[] sequence = { "three", "four", "four" };
    thrown.expect(AssertionError.class);
    assertions.endsWith(sequence);
  }

  @Test
  public void should_pass_if_actual_ends_with_sequence() {
    assertions.endsWith(sequence);
  }

  @Test
  public void should_pass_if_actual_ends_with_itself() {
    assertions.endsWith(actual.toArray());
  }
}
