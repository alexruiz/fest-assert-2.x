/*
 *
 *  * Created on Dec 16, 2010
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the
 *  * License. You may obtain a copy of the License at
 *  *
 *  * http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS"
 *  * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language
 *  * governing permissions and limitations under the License.
 *  *
 *  * Copyright @2013 the original author or authors.
 *
 */

package org.fest.assertions.api;

import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Tests for {@link ByteArrayAssert}.
 * <p/>
 * Author: Yvonne Wang
 */
public class ByteArrayAssert_isEqualTo_Test {
  @Rule
  public ExpectedException thrown = ExpectedException.none();
  private byte[] actual = {6, 8};
  private byte[] expected = {6, 8};
  private ByteArrayAssert assertions;

  @Before
  public void setUp() {
    assertions = new ByteArrayAssert(actual);
  }

  @Test
  public void should_return_this() {
    ByteArrayAssert returned = assertions.isEqualTo(expected);
    assertEquals(returned, assertions);
  }

  @Test
  public void should_throw_error_if_actual_is_null() {
    thrown.expect(AssertionError.class);
    actual = null;
    assertions.isEqualTo(expected);
  }

  @Test
  public void should_fail_if_expected_is_null() {
    thrown.expect(AssertionError.class);
    assertions.isEqualTo(expected);
  }

  @Test
  public void should_fail_if_actual_is_not_equal_to_expected() {
    thrown.expect(AssertionError.class);
    actual = new byte[]{1, 2};
    assertions.isEqualTo(expected);
  }

  @Test
  public void should_pass_if_actual_is_equal_to_expected() {
    assertions.isEqualTo(expected);
  }

  @Test
  public void should_pass_if_actual_is_equal_to_expected_with_different_order() {
    expected = new byte[]{8, 6};
    assertions.isEqualTo(expected);
  }

  @Test
  public void should_pass_if_expected_is_empty() {
    assertions.isEqualTo(new byte[0]);
  }
}
