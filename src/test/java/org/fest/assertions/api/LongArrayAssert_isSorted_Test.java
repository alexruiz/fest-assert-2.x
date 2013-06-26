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

import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static junit.framework.Assert.assertSame;
import static org.fest.test.ExpectedException.none;

/**
 * Tests for {@link LongArrayAssert#isSorted()}.
 *
 * @author Joel Costigliola
 * @author Yvonne Wang
 */
public class LongArrayAssert_isSorted_Test {
  @Rule
  public ExpectedException thrown = none();
  private long[] actual = {1, 2, 3, 4, 5};
  private LongArrayAssert assertions;

  @Before
  public void setUp() {
    assertions = new LongArrayAssert(actual);
  }

  @Test
  public void should_pass_if_actual_is_sorted() {
    assertions.isSorted();
  }

  @Test
  public void should_pass_if_actual_is_empty() {
    assertions = new LongArrayAssert(new long[0]);
    assertions.isSorted();
  }

  @Test
  public void should_return_this_if_actual_is_sorted() {
    LongArrayAssert returned = assertions.isSorted();
    assertSame(returned, assertions);
  }

  @Test
  public void should_throw_error_if_actual_is_null() {
    thrown.expect(AssertionError.class);
    assertions = new LongArrayAssert(null);
    assertions.isSorted();
  }

  @Test
  public void should_fail_if_actual_is_not_sorted() {
    thrown.expect(AssertionError.class);
    actual = new long[]{1, 3, 2, 5, 4};
    assertions = new LongArrayAssert(actual);
    assertions.isSorted();
  }
}
