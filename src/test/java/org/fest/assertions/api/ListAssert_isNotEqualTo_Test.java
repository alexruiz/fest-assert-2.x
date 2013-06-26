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
 * Tests for {@link ListAssert#isNotEqualTo(List)}.
 *
 * @author Yvonne Wang
 */
public class ListAssert_isNotEqualTo_Test {
  @Rule
  public ExpectedException thrown = none();
  private final List<Object> actual = Lists.newArrayList();
  private final List<Object> expected = Lists.newArrayList();
  private ListAssert assertions;

  @Before
  public void setUp() {
    actual.add("one");
    actual.add("two");
    expected.add("one");
    assertions = new ListAssert(actual);
  }

  @Test
  public void should_pass_if_actual_is_null_and_expected_is_not_null() {
    assertions = new ListAssert(null);
    assertions.isNotEqualTo(expected);
  }

  @Test
  public void should_pass_if_expected_is_null_and_actual_is_null() {
    assertions.isNotEqualTo(null);
  }

  @Test
  public void should_fail_if_both_actual_and_expected_are_null() {
    assertions = new ListAssert(null);
    thrown.expect(AssertionError.class);
    assertions.isNotEqualTo(null);
  }

  @Test
  public void should_fail_if_actual_is_equal_to_expected() {
    thrown.expect(AssertionError.class);
    expected.add("two");
    assertions.isNotEqualTo(expected);
  }

  @Test
  public void should_pass_if_actual_is_not_equal_to_expected() {
    assertions.isNotEqualTo(expected);
  }
}
