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

import java.util.Set;

import org.fest.test.ExpectedException;
import org.fest.util.Sets;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for {@link SetAssert#doesNotContain(Object...)}.
 *
 * @author Yvonne Wang
 */
public class SetAssert_doesNotContain_Test {
  @Rule
  public ExpectedException thrown = none();
  private Set<String> actual = Sets.newLinkedHashSet("one", "two", "three");
  private Object[] expected = { "four", "five" };
  private SetAssert assertions;

  @Before
  public void setUp() {
    assertions = new SetAssert(actual);
  }

  @Test
  public void should_throw_error_if_actual_is_null() {
    actual = null;
    assertions = new SetAssert(actual);
    thrown.expect(AssertionError.class);
    assertions.doesNotContain(expected);
  }

  @Test
  public void should_throw_if_expected_is_null() {
    expected = null;
    thrown.expect(NullPointerException.class);
    assertions.doesNotContain(expected);
  }

  @Test
  public void should_throw_if_expected_is_empty() {
    Object[] expected = new Object[0];
    thrown.expect(IllegalArgumentException.class);
    assertions.doesNotContain(expected);
  }

  @Test
  public void should_throw_error_if_parameter_expected_is_missing() {
    thrown.expect(IllegalArgumentException.class);
    assertions.doesNotContain();
  }

  @Test
  public void should_fail_if_actual_contains_expected() {
    Object[] expected = { "two", "three" };
    thrown.expect(AssertionError.class);
    assertions.doesNotContain(expected);
  }

  @Test
  public void should_fail_if_actual_contains_itself() {
    thrown.expect(AssertionError.class);
    assertions.doesNotContain(actual.toArray());
  }

  @Test
  public void should_fail_if_actual_contains_expected_in_different_order() {
    Object[] expected = { "three", "two" };
    thrown.expect(AssertionError.class);
    assertions.doesNotContain(expected);
  }

  @Test
  public void should_pass_if_actual_is_empty() {
    actual = Sets.newHashSet();
    assertions = new SetAssert(actual);
    assertions.doesNotContain(expected);
  }

  @Test
  public void should_pass_if_actual_does_not_contain_expected() {
    assertions.doesNotContain(expected);
  }

  @Test
  public void should_pass_if_size_of_expected_is_greater_than_size_of_actual() {
    Object[] expected = new Object[10];
    assertions.doesNotContain(expected);
  }
}
