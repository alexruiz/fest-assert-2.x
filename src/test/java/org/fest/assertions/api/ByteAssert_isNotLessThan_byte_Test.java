/*
 * Created on Oct 21, 2010
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 * 
 * Copyright @2010-2013 the original author or authors.
 */
package org.fest.assertions.api;

import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.fest.test.ExpectedException.none;
import static org.junit.Assert.assertSame;

/**
 * Tests for {@link ByteAssert#isNotLessThan(byte)}.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class ByteAssert_isNotLessThan_byte_Test {

  @Rule
  public ExpectedException thrown = none();
  private ByteAssert assertions;
  private Byte actual = 8;
  private byte expected = 6;

  @Before
  public void setUp() {
    assertions = new ByteAssert(actual);
  }

  @Test
  public void should_pass_if_actual_is_greater_than_expected() {
    assertions.isNotLessThan(expected);
  }

  @Test
  public void should_pass_if_actual_is_equal_to_expected() {
    expected = 8;
    assertions.isNotLessThan(expected);
  }

  @Test
  public void should_return_this() {
    ByteAssert returned = assertions.isNotLessThan(expected);
    assertSame(assertions, returned);
  }

  @Test
  public void should_throw_error_if_actual_is_null() {
    thrown.expect(AssertionError.class);
    actual = null;
    assertions.isNotLessThan(expected);
  }

  @Test
  public void should_fail_if_actual_is_less_than_expected() {
    thrown.expect(AssertionError.class);
    expected = 10;
    assertions.isNotLessThan(expected);
  }
}
