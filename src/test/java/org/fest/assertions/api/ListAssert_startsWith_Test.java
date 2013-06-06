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
import org.fest.util.Lists;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.Collection;
import java.util.List;

import static org.fest.test.ExpectedException.none;

/**
 * Tests for {@link ListAssert#startsWith(Collection[])}.
 *
 * @author Yvonne Wang
 */
public class ListAssert_startsWith_Test {
  @Rule
  public ExpectedException thrown = none();
  private List<String> actual = Lists.newArrayList("one", "two", "three", "four");
  private List<String> sequence = Lists.newArrayList("one", "two");
  private ListAssert assertions;

  @Before
  public void setUp() {
    assertions = new ListAssert(actual);
  }

  @Test
  public void should_throw_error_if_actual_is_null() {
    thrown.expect(AssertionError.class);
    assertions = new ListAssert(null);
    assertions.startsWith();
  }

  @Test
  public void should_throw_error_if_sequence_is_null() {
    sequence = null;
    thrown.expect(AssertionError.class);
    assertions.startsWith(sequence);
  }

  @Test
  public void should_throw_error_if_sequence_is_empty() {
    sequence = Lists.emptyList();
    thrown.expect(AssertionError.class);
    assertions.startsWith(sequence);
  }

  @Test
  public void should_throw_error_if_parameter_sequence_is_missing() {
    thrown.expect(IllegalArgumentException.class);
    assertions.startsWith();
  }

  @Test
  public void should_fail_if_actual_does_not_start_with_sequence() {
    thrown.expect(AssertionError.class);
    sequence.add("five");
    assertions.startsWith(sequence);
  }

  @Test
  public void should_fail_if_sequence_size_is_larger_than_actual_size() {
    sequence.addAll(actual);
    thrown.expect(AssertionError.class);
    assertions.startsWith(sequence);
  }

  @Test
  public void should_fail_if_sequence_has_multiple_same_elements_while_actual_does_not() {
    sequence.add("one");
    sequence.add("one");
    thrown.expect(AssertionError.class);
    assertions.startsWith(sequence);
  }

  @Test
  public void should_pass_if_actual_starts_with_sequence() {
    assertions.startsWith(sequence);
  }

  @Test
  public void should_pass_if_actual_starts_with_itself() {
    assertions.startsWith(actual);
  }
}
