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

import static org.fest.assertions.error.ShouldContainOnly.shouldContainOnly;
import static org.fest.assertions.error.ShouldNotBeNull.shouldNotBeNull;
import static org.fest.assertions.test.TestFailures.expectedAssertionErrorNotThrown;
import static org.fest.test.ExpectedException.none;
import static org.fest.util.Sets.newLinkedHashSet;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.util.Set;

import org.fest.assertions.description.Description;
import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for <code>{@link ByteArrays#assertContainsOnly(Description, byte[], byte[])}</code>.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class ByteArrays_assertContainsOnly_Test {

  @Rule
  public ExpectedException thrown = none();

  private ByteArrays arrays;
  private Description description;
  private Failures failures;
  private final byte[] actual = { (byte) 1, (byte) 2, (byte) 3 };

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
    byte[] other = new byte[1];
    String message = shouldNotBeNull().create(description);
    thrown.expect(AssertionError.class, message);
    arrays.assertContainsOnly(description, null, other);
  }

  @Test
  public void should_fail_if_other_is_null() {
    thrown.expect(NullPointerException.class, "The array of values to look for should not be null");
    arrays.assertContainsOnly(description, new byte[6], null);
  }

  @Test
  public void should_fail_if_other_is_empty() {
    thrown.expect(IllegalArgumentException.class, "The array of values to look for should not be empty");
    arrays.assertContainsOnly(description, new byte[1], new byte[0]);
  }

  @Test
  public void should_fail_if_actual_does_not_contain_other() {
    byte[] other = { (byte) 6, (byte) 8 };
    Set<Byte> notFound = newLinkedHashSet((byte) 6, (byte) 8);
    Set<Byte> notExpected = newLinkedHashSet((byte) 1, (byte) 2, (byte) 3);
    try {
      arrays.assertContainsOnly(description, actual, other);
    } catch (AssertionError e) {
      verify(failures).failure(description, shouldContainOnly(actual, other, notFound, notExpected));
      return;
    }
    expectedAssertionErrorNotThrown();
  }

  @Test
  public void should_fail_if_actual_contains_other_with_more_elements() {
    byte[] other = { (byte) 1, (byte) 2, (byte) 3, (byte) 4 };
    Set<Byte> notFound = newLinkedHashSet();
    notFound.add((byte) 4);
    Set<Byte> notExpected = newLinkedHashSet();
    try {
      arrays.assertContainsOnly(description, actual, other);
    } catch (AssertionError e) {
      verify(failures).failure(description, shouldContainOnly(actual, other, notFound, notExpected));
      return;
    }
    expectedAssertionErrorNotThrown();
  }

  @Test
  public void should_pass_if_actual_contains_other_in_any_order() {
    byte[] other = { (byte) 2, (byte) 3, (byte) 1 };
    arrays.assertContainsOnly(description, actual, other);
  }

  @Test
  public void should_pass_if_actual_contains_other_with_duplicates() {
    byte[] other = { (byte) 1, (byte) 2, (byte) 3, (byte) 1 };
    arrays.assertContainsOnly(description, actual, other);
  }
}
