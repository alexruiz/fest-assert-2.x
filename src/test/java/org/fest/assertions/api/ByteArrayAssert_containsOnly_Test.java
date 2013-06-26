/*
 * Created on Dec 17, 2010
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
 * Copyright @2010-2013 the original author or authors.
 */
package org.fest.assertions.api;

import static org.fest.test.ExpectedException.none;

import junit.framework.Assert;

import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for {@link ByteArrayAssert#containsOnly(byte...)}.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class ByteArrayAssert_containsOnly_Test {
  @Rule
  public ExpectedException thrown = none();
  private byte[] actual = { 6, 8 };
  private final byte[] values = { 6, 8 };
  private ByteArrayAssert assertions;

  @Before
  public void setUp() {
    assertions = new ByteArrayAssert(actual);
  }

  @Test
  public void should_return_this() {
    ByteArrayAssert returned = assertions.containsOnly(values);
    Assert.assertSame(returned, assertions);
  }

  @Test
  public void should_pass_if_actual_contains_only_given_values() {
    assertions.containsOnly(values);
  }

  @Test
  public void should_pass_if_actual_contains_given_values_multiple_times() {
    actual = new byte[] { 6, 8, 6, 8 };
    assertions = new ByteArrayAssert(actual);
    assertions.containsOnly(values);
  }

  @Test
  public void should_pass_if_actual_contains_given_values_in_different_order() {
    actual = new byte[] { 8, 6 };
    assertions = new ByteArrayAssert(actual);
    assertions.containsOnly(values);
  }

  @Test
  public void should_fail_if_actual_does_not_contain_only_given_value() {
    thrown.expect(AssertionError.class);
    actual = new byte[] { 6, 8, 10 };
    assertions = new ByteArrayAssert(actual);
    assertions.containsOnly(values);
  }

  @Test
  public void should_throw_error_if_actual_is_null() {
    thrown.expect(AssertionError.class);
    actual = null;
    assertions = new ByteArrayAssert(actual);
    assertions.containsOnly(values);
  }

  @Test
  public void should_throw_error_if_actual_is_empty() {
    thrown.expect(AssertionError.class);
    actual = new byte[0];
    assertions = new ByteArrayAssert(actual);
    assertions.containsOnly(values);
  }

  @Test
  public void should_fail_if_values_is_null() {
    thrown.expect(NullPointerException.class);
    assertions.containsOnly(null);
  }

}
