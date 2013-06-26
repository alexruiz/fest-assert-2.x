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

import static org.fest.test.ExpectedException.none;
import static org.junit.Assert.assertSame;

import java.math.BigDecimal;

import org.fest.assertions.api.AbstractUnevenComparableAssert;
import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for {@link AbstractUnevenComparableAssert#isEqualByComparingTo(Comparable)}.
 *
 * @author Yvonne Wang
 */
public class AbstractUnevenComparableAssert_isEqualToByComparingTo_Test  {
  @Rule public ExpectedException thrown = none();
  private final BigDecimal actual = new BigDecimal(8);
  private ConcreteUnevenComparableAssert assertions;

  @Before
  public void setUp() {
    assertions = new ConcreteUnevenComparableAssert(actual);
  }

  @Test
  public void should_pass_if_actual_is_equal_to_expected() {
    assertions.isEqualByComparingTo(actual);
  }

  @Test
  public void should_return_this() {
    ConcreteUnevenComparableAssert returned = assertions.isEqualByComparingTo(actual);
    assertSame(assertions, returned);
  }

  @Test
  public void should_fail_if_actual_is_not_equal_to_expected() {
    thrown.expect(AssertionError.class);
    assertions.isEqualByComparingTo(new BigDecimal(2));
  }

  @Test
  public void should_throw_error_if_actual_is_null() {
    thrown.expect(AssertionError.class);
    assertions = new ConcreteUnevenComparableAssert(null);
    assertions.isEqualByComparingTo(actual);
  }

  @Test
  public void should_fail_if_expected_is_null() {
    thrown.expect(NullPointerException.class);
    assertions.isEqualByComparingTo(null);
  }
}
