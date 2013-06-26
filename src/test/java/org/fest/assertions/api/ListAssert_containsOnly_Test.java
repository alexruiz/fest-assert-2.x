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
 * Tests for {@link ListAssert#containsOnly(Object...)}.
 *
 * @author Yvonne Wang
 */
public class ListAssert_containsOnly_Test {
  @Rule
  public ExpectedException thrown = none();
  private final List<String> actual = Lists.newArrayList("one", "two");
  private Object[] expected = { "two", "one" };
  private ListAssert assertions;

  @Before
  public void setUp() {
    assertions = new ListAssert(actual);
  }

  @Test
  public void should_throw_error_if_actual_is_null() {
    assertions = new ListAssert(null);
    thrown.expect(AssertionError.class);
    assertions.containsOnly(expected);
  }

  @Test
  public void should_throw_error_if_expected_is_null() {
    expected = null;
    thrown.expect(NullPointerException.class);
    assertions.containsOnly(expected);
  }

  @Test
  public void should_throw_error_if_paramter_expected_is_missing() {
    thrown.expect(IllegalArgumentException.class);
    assertions.containsOnly();
  }

  @Test
  public void should_fail_if_expected_has_more_elements_than_actual() {
    thrown.expect(AssertionError.class);
    Object[] expected = { "two", "one", "three" };
    thrown.expect(AssertionError.class);
    assertions.containsOnly(expected);
  }

  @Test
  public void should_fail_if_actual_has_more_elements_than_expected() {
    actual.add("three");
    thrown.expect(AssertionError.class);
    assertions.containsOnly(expected);
  }

  @Test
  public void should_fail_if_expected_has_multiple_same_element_while_actual_does_not() {
    Object[] expected = { "two", "one", "three", "one", "one" };
    thrown.expect(AssertionError.class);
    assertions.containsOnly(expected);
  }

  @Test
  public void should_pass_if_actual_contains_only_expected() {
    assertions.containsOnly(actual.toArray());
  }

  @Test
  public void should_pass_if_actual_contains_only_expected_in_different_order() {
    assertions.containsOnly(expected);
  }
}
