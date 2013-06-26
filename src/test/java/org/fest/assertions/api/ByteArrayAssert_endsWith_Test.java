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
import static org.junit.Assert.assertSame;

import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for {@link ByteArrayAssert#endsWith(byte...)}.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class ByteArrayAssert_endsWith_Test {

  @Rule
  public ExpectedException thrown = none();
  private final byte[] actual = { 6, 8, 16, 18 };
  private final byte[] values = { 16, 18 };
  private ByteArrayAssert assertions;

  @Before
  public void setUp() {
    assertions = new ByteArrayAssert(actual);
  }

  @Test
  public void should_return_this() {
    ByteArrayAssert returned = assertions.endsWith(values);
    assertSame(returned, assertions);
  }

  @Test
  public void should_pass_if_actual_end_with_given_sequence() {
    assertions.endsWith(values);
  }

  @Test
  public void should_fail_if_actual_does_not_end_with_given_sequence() {
    thrown.expect(AssertionError.class);
    assertions.endsWith((byte) 2, (byte) 3);
  }
}
