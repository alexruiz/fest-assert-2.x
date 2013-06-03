/*
 * Created on May 9, 2013
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

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;

import org.fest.assertions.description.Description;
import org.fest.assertions.internal.Comparables;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Yvonne Wang
 */
public class BigDecimalAssert_isNotEqualByComparingTo_Test {

  private Comparables comparables;
  private BigDecimalAssert assertions;
  private final BigDecimal expected = new BigDecimal(6);

  @Before
  public void setUp() {
    comparables = mock(Comparables.class);
    assertions = new BigDecimalAssert(new BigDecimal(8), mock(Description.class));
    assertions.comparables = comparables;
  }

  @Test
  public void should_return_this_assertion_object() {
    BigDecimalAssert returned = assertions.isNotEqualByComparingTo(expected);
    Assert.assertSame(returned, assertions);
  }

  @Test
  public void verify_comparables_is_called() {
    assertions.isNotEqualByComparingTo(new BigDecimal(6));
    verify(comparables).assertNotEqual(assertions.description, assertions.actual, expected);
  }
}
