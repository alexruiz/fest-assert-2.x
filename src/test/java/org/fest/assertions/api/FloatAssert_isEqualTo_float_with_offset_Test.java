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

import static junit.framework.Assert.assertSame;

import static org.fest.test.ExpectedException.none;

import org.fest.assertions.data.Offset;
import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for {@link FloatAssert#isEqualTo(float, Offset)}.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class FloatAssert_isEqualTo_float_with_offset_Test {
  @Rule
  public ExpectedException thrown = none();
  private Float actual;
  private float expected;
  private FloatAssert assertions;
  private Offset<Float> offset;

  @Before
  public void setUp() {
    actual = new Float(6f);
    expected = 6f;
    offset = Offset.offset(0.001f);
    assertions = new FloatAssert(actual);
  }

  @Test
  public void should_pass_if_actual_is_equal_to_expected_within_given_offset() {
    assertions.isEqualTo(expected, offset);
  }

  @Test
  public void should_return_this() {
    FloatAssert returned = assertions.isEqualTo(expected, offset);
    assertSame(returned, assertions);
  }

  @Test
  public void should_throw_error_if_actual_is_null() {
    thrown.expect(AssertionError.class);
    assertions = new FloatAssert(null);
    assertions.isEqualTo(expected, offset);
  }

  @Test
  public void should_throw_error_if_offset_is_null() {
    offset = null;
    thrown.expect(NullPointerException.class);
    assertions.isEqualTo(expected, offset);
  }

  @Test
  public void should_fail_if_actual_is_not_equal_to_expected_within_given_offset() {
    thrown.expect(AssertionError.class);
    assertions.isEqualTo(4f, offset);
  }
}
