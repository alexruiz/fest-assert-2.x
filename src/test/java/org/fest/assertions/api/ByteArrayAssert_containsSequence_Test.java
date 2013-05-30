/*
 * Created on Dec 17, 2010
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

import junit.framework.Assert;
import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for <code>{@link ByteArrayAssert#containsSequence(byte...)}</code>.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class ByteArrayAssert_containsSequence_Test {
  @Rule
  public ExpectedException thrown = ExpectedException.none();
  private byte[] actual = {6, 8, 10, 16, 18};
  private byte[] sequence = {8, 10};
  private ByteArrayAssert assertions;

  @Before
  public void setUp() {
    assertions = new ByteArrayAssert(actual);
  }

  @Test
  public void should_return_this() {
    ByteArrayAssert returned = assertions.containsSequence(sequence);
    Assert.assertEquals(returned, assertions);
  }

  @Test
  public void should_pass_if_actual_contains_given_sequence() {
    assertions.containsSequence(sequence);
  }

  @Test
  public void should_pass_if_actual_contains_given_sequence_multiple_times() {
    actual = new byte[]{8, 10, 8, 10};
    assertions.containsSequence(sequence);
  }

  @Test
  public void should_pass_if_given_sequence_is_empty() {
    assertions.containsSequence(new byte[0]);
  }

  @Test
  public void should_fail_if_actual_does_not_contain_given__sequence_in_same_order() {
    thrown.expect(AssertionError.class);
    actual = new byte[]{6, 10, 8, 16, 18};
    assertions.containsSequence(sequence);
  }

  @Test
  public void should_fail_if_actual_does_not_contain_given_sequence() {
    thrown.expect(AssertionError.class);
    actual = new byte[]{6, 8, 16, 18};
    assertions.containsSequence(sequence);
  }

  @Test
  public void should_throw_error_if_actual_is_null() {
    thrown.expect(AssertionError.class);
    actual = null;
    assertions.containsSequence(sequence);
  }

  @Test
  public void should_throw_error_if_actual_is_empty() {
    thrown.expect(AssertionError.class);
    actual = new byte[0];
    assertions.containsSequence(sequence);
  }

  @Test
  public void should_fail_if_given_sequence_is_null() {
    thrown.expect(AssertionError.class);
    assertions.containsSequence(null);
  }
}
