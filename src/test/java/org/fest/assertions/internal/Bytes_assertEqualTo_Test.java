/*
 * Created on Oct 21, 2010
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
package org.fest.assertions.internal;

import static org.fest.assertions.test.FailureMessages.actualIsNull;
import static org.fest.assertions.test.TestFailures.expectedAssertionErrorNotThrown;
import static org.fest.test.ExpectedException.none;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.fest.assertions.description.Description;
import org.fest.assertions.error.ShouldBeEqual;
import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for <code>{@link Bytes#assertEqualTo(Description, Byte, byte)}</code>.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class Bytes_assertEqualTo_Test {

  @Rule
  public ExpectedException thrown = none();

  private Bytes bytes;
  private Description description;
  private Failures failures;

  @Before
  public void setUp() {
    bytes = new Bytes();
    description = new TestDescription("testing");
    failures = spy(new Failures());
    bytes.failures = failures;
  }

  @Test
  public void should_fail_if_actual_is_null() {
    thrown.expect(AssertionError.class, "[" + description + "] " + actualIsNull());
    bytes.assertEqualTo(description, null, (byte) 6);
  }

  @Test
  public void should_fail_if_actual_not_equal_to_expected() {
    try {
      bytes.assertEqualTo(description, (byte) 6, (byte) 8);
    } catch (AssertionError e) {
      verify(failures).failure(description, ShouldBeEqual.shouldBeEqual((byte) 6, (byte) 8));
      return;
    }
    expectedAssertionErrorNotThrown();
  }

  @Test
  public void should_pass_if_actual_is_equla_to_expected() {
    byte actual = (byte) 6;
    bytes.assertEqualTo(description, actual, actual);
  }
}
