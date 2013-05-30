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
 *  * Copyright @2013 the original @@author or @@authors.
 *
 */

package org.fest.assertions.api;

import junit.framework.Assert;
import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for {@link ByteArrayAssert#isNotEqualTo(Object)}.
 *
 * @author: Yvonne Wang
 */
public class ByteArrayAssert_isNotEqualTo_Test {
  @Rule
  public ExpectedException thrown = ExpectedException.none();
  private byte[] actual = {6, 8};
  private byte[] expected = {1, 2};
  private ByteArrayAssert assertions;

  @Before
  public void setUp() {
    assertions = new ByteArrayAssert(actual);
  }

  @Test
  public void should_return_this() {
    ByteArrayAssert returned = assertions.isNotEqualTo(expected);
    Assert.assertEquals(returned, assertions);
  }

  @Test
  public void should_pass_if_actual_is_not_equal_to_other() {
    assertions.isNotEqualTo(expected);
  }

  @Test
  public void should_pass_if_actual_is_empty() {
    actual = new byte[0];
    assertions.isNotEqualTo(expected);
  }

  @Test
  public void should_pass_if_other_is_empty() {
    assertions.isNotEqualTo(new byte[0]);
  }

  @Test
  public void should_fail_if_actual_is_equal_to_other() {
    thrown.expect(AssertionError.class);
    actual = new byte[]{6, 8};
    assertions.isNotEqualTo(expected);
  }

  @Test
  public void should_throw_error_if_actual_is_null() {
    thrown.expect(AssertionError.class);
    actual = null;
    assertions.isNotEqualTo(expected);
  }

  @Test
  public void should_throw_error_if_other_is_null() {
    thrown.expect(AssertionError.class);
    assertions.isNotEqualTo(null);
  }
}
