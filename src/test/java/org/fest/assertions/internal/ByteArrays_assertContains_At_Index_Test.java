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

import static org.fest.assertions.error.ShouldNotBeEmpty.shouldNotBeEmpty;
import static org.fest.assertions.error.ShouldNotBeNull.shouldNotBeNull;
import static org.fest.assertions.test.TestFailures.expectedAssertionErrorNotThrown;
import static org.fest.test.ExpectedException.none;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.fest.assertions.data.Index;
import org.fest.assertions.description.Description;
import org.fest.assertions.error.ShouldContainAtIndex;
import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for <code>{@link ByteArrays#assertContains(Description, byte[], byte, Index)}</code>.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class ByteArrays_assertContains_At_Index_Test {

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
    byte value = (byte) 6;
    String message = shouldNotBeNull().create(description);
    thrown.expect(AssertionError.class, message);
    arrays.assertContains(description, null, value, Index.atIndex(0));
  }

  @Test
  public void should_fail_if_actual_is_empty() {
    try {
      arrays.assertContains(description, new byte[0], (byte) 6, Index.atIndex(0));
    } catch (AssertionError e) {
      verify(failures).failure(description, shouldNotBeEmpty());
      return;
    }
    expectedAssertionErrorNotThrown();
  }

  @Test
  public void should_fail_if_index_is_null() {
    thrown.expect(NullPointerException.class, "Index should not be null");
    arrays.assertContains(description, new byte[6], (byte) 3, null);
  }

  @Test
  public void should_fail_if_index_is_negative() {
    thrown.expect(IllegalArgumentException.class, "The value of the index should not be negative");
    arrays.assertContains(description, new byte[1], (byte) 0, Index.atIndex(-1));
  }

  @Test
  public void should_fail_if_index_greater_than_the_size_of_actual() {
    String format = "Index should be between <%d> and <%d> (inclusive,) but was <%d>";
    thrown.expect(IndexOutOfBoundsException.class, String.format(format, 0, 6, 8));
    arrays.assertContains(description, new byte[7], (byte) 1, Index.atIndex(8));
  }

  @Test
  public void should_fail_if_actual_does_not_cotain_value_at_given_index() {
    byte[] actual = { (byte) 1, (byte) 2, (byte) 3 };
    byte value = (byte) 3;
    Index index = Index.atIndex(0);
    try {
      arrays.assertContains(description, actual, value, index);
    } catch (AssertionError e) {
      verify(failures).failure(description, ShouldContainAtIndex.shouldContainAtIndex(actual, value, index, (byte) 1));
      return;
    }
    expectedAssertionErrorNotThrown();
  }

  @Test
  public void should_pass_if_actual_contains_value_at_given_index() {
    byte[] actual = { (byte) 1, (byte) 2, (byte) 3 };
    byte value = 3;
    Index index = Index.atIndex(2);
    arrays.assertContains(description, actual, value, index);
  }
}
