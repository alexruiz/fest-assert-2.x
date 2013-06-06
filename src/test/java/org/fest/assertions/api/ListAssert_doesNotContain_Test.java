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
import static org.fest.util.Lists.newArrayList;

/**
 * Tests for {@link ListAssert#doesNotContain(Collection[])}.
 *
 * @author Yvonne Wang
 */
public class ListAssert_doesNotContain_Test {
  @Rule
  public ExpectedException thrown = none();
  private List<String> actual = newArrayList("one", "two");
  private List<String> expected = newArrayList("three");
  private ListAssert assertions;

  @Before
  public void setUp() {
    assertions = new ListAssert(actual);
  }

  @Test
  public void should_throw_error_if_actual_is_null() {
    assertions = new ListAssert(null);
    assertions.doesNotContain(expected);
  }

  @Test
  public void should_throw_error_if_expected_is_null() {
    expected = null;
    assertions.doesNotContain(expected);
  }

  @Test
  public void should_throw_error_if_expected_is_empty_list() {
    thrown.expect(AssertionError.class);
    assertions.doesNotContain(Lists.emptyList());
  }

  @Test
  public void should_fail_if_actual_contains_expected() {
    thrown.expect(AssertionError.class);
    assertions.doesNotContain(newArrayList("one"));
  }

  @Test
  public void should_fail_if_actual_contains_itself() {
    thrown.expect(AssertionError.class);
    assertions.doesNotContain(actual);
  }

  @Test
  public void should_pass_if_actual_does_not_contain_expected() {
    assertions.doesNotContain(expected);
  }
}
