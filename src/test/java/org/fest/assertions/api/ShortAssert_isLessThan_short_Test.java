/*
 * Created on Oct 20, 2010
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
 * Copyright @2010-2011 the original author or authors.
 */
package org.fest.assertions.api;

import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for <code>{@link ShortAssert#isLessThan(short)}</code>.
 *
 * @author Alex Ruiz
 */
public class ShortAssert_isLessThan_short_Test {

  @Rule
  public ExpectedException thrown = ExpectedException.none();
  private ShortAssert assertions;
  private Short actual;

  @Before
  public void setUp() {
    actual = (short) 6;
    assertions = new ShortAssert(actual);
  }

  @Test
  public void should_pass_if_actual_is_less_than_expected() {
    assertions.isLessThan((short) 8);
  }

  @Test
  public void should_fail_if_actual_is_equal_to_expected() {
    thrown.expect(AssertionError.class);
    assertions.isLessThan((short) 6);
  }

  @Test
  public void should_fail_if_actual_is_greater_than_expected() {
    thrown.expect(AssertionError.class);
    assertions.isLessThan((short) 2);
  }

  @Test
  public void should_throw_error_if_actual_is_null() {
    thrown.expect(AssertionError.class);
    actual = null;
    assertions.isLessThan((short) 2);
  }
}
