/*
 * Created on Dec 14, 2010
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

import static org.fest.assertions.error.ShouldNotBeNull.shouldNotBeNull;
import static org.fest.assertions.test.TestFailures.expectedAssertionErrorNotThrown;
import static org.fest.test.ExpectedException.none;
import static org.fest.util.Sets.newLinkedHashSet;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.util.Set;

import org.fest.assertions.description.Description;
import org.fest.assertions.error.ShouldNotContain;
import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for <code>{@link ByteArrays#assertDoesNotContain(Description, byte[], byte[])}</code>.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class ByteArrays_assertDoesNotContain_Test {

  @Rule
  public ExpectedException thrown = none();

  private ByteArrays arrays;
  private Description description;
  private Failures failures;

  @Before
  public void setUp() {
    arrays = new ByteArrays();
    description = new TestDescription("testing");
    failures = spy(new Failures());
    Arrays parent = Arrays.instance();
    parent.failures = failures;
  }

  @Test
  public void should_fail_if_actual_is_null() {
    String message = shouldNotBeNull().create(description);
    thrown.expect(AssertionError.class, message);
    arrays.assertDoesNotContain(description, null, new byte[2]);
  }

  @Test
  public void should_fail_if_other_is_null() {
    thrown.expect(NullPointerException.class, "The array of values to look for should not be null");
    arrays.assertDoesNotContain(description, new byte[6], null);
  }

  @Test
  public void should_fail_if_other_is_empty() {
    thrown.expect(IllegalArgumentException.class, "The array of values to look for should not be empty");
    arrays.assertDoesNotContain(description, new byte[1], new byte[0]);
  }

  @Test
  public void should_fail_if_actual_contains_other() {
    byte[] actual = { (byte) 2, (byte) 4, (byte) 6, (byte) 8 };
    byte[] other = { (byte) 6, (byte) 8 };
    Set<Byte> found = newLinkedHashSet((byte) 6, (byte) 8);
    try {
      arrays.assertDoesNotContain(description, actual, other);
    } catch (AssertionError e) {
      verify(failures).failure(description, ShouldNotContain.shouldNotContain(actual, other, found));
      return;
    }
    expectedAssertionErrorNotThrown();
  }

  @Test
  public void should_pass_if_actual_does_not_contain_other() {
    byte[] actual = { (byte) 1, (byte) 2, (byte) 3, (byte) 4 };
    byte[] other = { (byte) 6, (byte) 8 };
    arrays.assertDoesNotContain(description, actual, other);
  }

  @Test
  public void should_pass_if_actual_contains_other_with_duplicates() {
    byte[] actual = { (byte) 1, (byte) 2, (byte) 3, (byte) 4 };
    byte[] other = { (byte) 6, (byte) 8, (byte) 8 };
    arrays.assertDoesNotContain(description, actual, other);
  }

  @Test
  public void should_pass_if_actual_is_empty() {
    arrays.assertDoesNotContain(description, new byte[0], new byte[1]);
  }
}
