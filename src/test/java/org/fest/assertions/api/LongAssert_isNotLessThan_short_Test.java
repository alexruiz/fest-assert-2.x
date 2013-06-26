/*
 * Created on Feb 22, 2011
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
 * Copyright @2011-2013 the original author or authors.
 */
package org.fest.assertions.api;

import static org.fest.test.ExpectedException.none;

import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for {@link LongAssert#isNotLessThan(long)}.
 *
 * @author Yvonne Wang
 */
public class LongAssert_isNotLessThan_short_Test {
  @Rule
  public ExpectedException thrown = none();
  private LongAssert assertions;
  private Long actual = new Long(8L);

  @Before
  public void setUp() {
    assertions = new LongAssert(actual);
  }

  @Test
  public void should_pass_if_actual_is_greater_than_expected() {
    assertions.isNotLessThan(6L);
  }

  @Test
  public void should_pass_if_actual_is_equal_to_expected() {
    assertions.isNotLessThan(actual);
  }

  @Test
  public void should_fail_if_actual_is_less_than_expected() {
    thrown.expect(AssertionError.class);
    assertions.isNotLessThan(10L);
  }

  @Test
  public void should_throw_error_if_actual_is_null() {
    thrown.expect(AssertionError.class);
    actual = null;
    assertions = new LongAssert(actual);
    assertions.isNotLessThan(2L);
  }
}
