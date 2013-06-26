/*
 * Created on Jun 11, 2013
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

/**
 * Tests for {@link ObjectAssert#isEqualTo(Object)}.
 *
 * @author Yvonne Wang
 */
public class ObjectAssert_isEqualTo_Test {
  @Rule
  public ExpectedException thrown = ExpectedException.none();
  private Object actual = "actual";
  private ObjectAssert assertions;

  @Before
  public void setUp() {
    assertions = new ObjectAssert(actual);
  }

  @Test
  public void should_pass_if_actual_is_equal_to_expected() {
    assertions.isEqualTo(actual);
  }

  @Test
  public void should_pass_if_both_actual_and_expected_are_null() {
    actual = null;
    assertions = new ObjectAssert(actual);
    assertions.isEqualTo(null);
  }

  @Test
  public void should_fail_if_actual_is_not_equal_to_expected() {
    thrown.expect(AssertionError.class);
    assertions.isEqualTo(true);
  }

  @Test
  public void should_fail_if_actual_is_null_but_expected_is_not_null() {
    thrown.expect(AssertionError.class);
    actual = null;
    assertions = new ObjectAssert(actual);
    assertions.isEqualTo("some value");
  }

  @Test
  public void should_fail_if_expected_is_null_but_actual_is_not_null() {
    thrown.expect(AssertionError.class);
    assertions.isEqualTo(null);
  }
}
