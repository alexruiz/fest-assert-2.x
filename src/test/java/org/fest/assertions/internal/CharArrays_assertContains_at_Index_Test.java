/*
 * Created on Dec 20, 2010
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

import static org.fest.assertions.data.Index.atIndex;
import static org.fest.assertions.error.ShouldContainAtIndex.shouldContainAtIndex;
import static org.fest.assertions.test.CharArrays.emptyArray;
import static org.fest.assertions.test.CharArrays.newArray;
import static org.fest.assertions.test.FailureMessages.actualIsNull;
import static org.fest.assertions.test.TestFailures.expectedAssertionErrorNotThrown;
import static org.fest.test.ExpectedException.none;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.fest.assertions.data.Index;
import org.fest.assertions.description.Description;
import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for <code>{@link CharArrays#assertContains(Description, char[], char, Index)}</code>.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class CharArrays_assertContains_at_Index_Test {

  @Rule
  public ExpectedException thrown = none();

  private CharArrays arrays;
  private Description description;
  private Failures failures;
  private final char[] actual = newArray('a', 'b', 'c', 'd');

  @Before
  public void setUp() {
    arrays = new CharArrays();
    description = new TestDescription("testing");
    Arrays parent = Arrays.instance();
    failures = spy(new Failures());
    parent.failures = failures;
  }

  @Test
  public void should_fail_if_actual_is_null() {
    thrown.expect(AssertionError.class, actualIsNull());
    arrays.assertContains(description, null, 'a', Index.atIndex(0));
  }

  @Test
  public void should_fail_if_actual_is_empty() {
    thrown.expect(AssertionError.class);
    arrays.assertContains(description, emptyArray(), 'a', Index.atIndex(0));
  }

  @Test
  public void should_throw_error_if_Index_is_null() {
    thrown.expect(NullPointerException.class, "Index should not be null");
    arrays.assertContains(description, actual, 'a', null);
  }

  @Test
  public void should_throw_error_if_Index_is_out_of_bounds() {
    thrown.expect(IndexOutOfBoundsException.class, "Index should be between <0> and <3> (inclusive,) but was <6>");
    arrays.assertContains(description, actual, 'a', atIndex(6));
  }

  @Test
  public void should_fail_if_actual_does_not_contain_value_at_index() {
    Index index = atIndex(0);
    try {
      arrays.assertContains(description, actual, 'b', index);
    } catch (AssertionError e) {
      verify(failures).failure(description, shouldContainAtIndex(actual, 'b', index, 'a'));
      return;
    }
    expectedAssertionErrorNotThrown();
  }

  @Test
  public void should_pass_if_actual_contains_value_at_index() {
    arrays.assertContains(description, actual, 'b', atIndex(1));
  }
}
