/*
 * Created on Oct 20, 2010
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
package org.fest.assertions.api;

import static org.junit.Assert.assertSame;

import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for {@link AbstractAssert#isNotEqualTo(Object)}.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class AbstractAssert_isNotEqualTo_Test {
  @Rule
  public ExpectedException thrown = ExpectedException.none();
  private ConcreteAssert assertions;

  @Before
  public void setUp() {
    assertions = new ConcreteAssert(6L);
  }

  @Test
  public void should_pass_that_actual_is_not_equal_to_expected() {
    assertions.isNotEqualTo(new Long(8L));
  }

  @Test
  public void should_return_this() {
    ConcreteAssert returned = assertions.isNotEqualTo(8L);
    assertSame(assertions, returned);
  }

  @Test
  public void should_fail_if_actual_is_equal_to_expected() {
    thrown.expect(AssertionError.class);
    assertions.isNotEqualTo(new Long(6L));
  }

  @Test
  public void should_pass_if_actual_is_null_and_expected_is_not_null() {
    assertions = new ConcreteAssert(null);
    assertions.isNotEqualTo(new Long(8L));
  }
}
