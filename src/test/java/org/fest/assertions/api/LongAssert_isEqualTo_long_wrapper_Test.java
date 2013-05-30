/*
 *
 *  * Created on Mar 29, 2009
 *  * 
 *  * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 *  * the License. You may obtain a copy of the License at
 *  * 
 *  * http://www.apache.org/licenses/LICENSE-2.0
 *  * 
 *  * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 *  * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 *  * specific language governing permissions and limitations under the License.
 *  * 
 *  * Copyright @2013 the original author or authors.
 *  
 */
package org.fest.assertions.api;

import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.fest.test.ExpectedException.none;

/**
 * Tests for {@link LongAssert#isEqualTo(Comparable)}.
 *
 * @author Yvonne Wang
 */
public class LongAssert_isEqualTo_long_wrapper_Test {

  @Rule
  public ExpectedException thrown = none();
  private LongAssert assertions;
  private Long actual;
  private Long expected;

  @Before
  public void setUp() {
    actual = (long) 6;
    expected = (long) 6;
    assertions = new LongAssert(actual);
  }

  @Test
  public void should_pass_if_actual_is_equal_to_expected() {
    assertions.isEqualTo(actual);
  }

  @Test
  public void should_fail_if_actual_is_not_equal_to_expected() {
    thrown.expect(AssertionError.class);
    assertions.isEqualTo((long) 2);
  }

  @Test
  public void should_throw_error_if_actual_is_null() {
    thrown.expect(AssertionError.class);
    actual = null;
    assertions.isEqualTo(expected);
  }

  @Test
  public void should_throw_error_if_expected_is_null() {
    thrown.expect(AssertionError.class);
    assertions.isEqualTo(null);
  }
}
