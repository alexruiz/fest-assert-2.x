/*
 * Created on Feb 8, 2011
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
package org.fest.assertions.internal;

import static org.fest.assertions.test.TestFailures.expectedAssertionErrorNotThrown;
import static org.fest.test.ExpectedException.none;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;

import org.fest.assertions.description.Description;
import org.fest.assertions.error.ShouldNotBeEqual;
import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for <code>{@link BigDecimals#assertIsNotZero(Description, BigDecimal)}</code>.
 *
 * @author Yvonne Wang
 * @author Joel Costigliola
 */
public class BigDecimals_assertIsNotZero_Test {

  @Rule
  public ExpectedException thrown = none();

  private BigDecimals bigDecimals;
  private Description description;
  private Failures failures;

  @Before
  public void setUp() {
    bigDecimals = new BigDecimals();
    description = new TestDescription("testing");
    failures = spy(new Failures());
    Comparables comparables = Comparables.instance();
    comparables.failures = failures;
  }

  @Test
  public void should_pass_if_actual_is_not_zero() {
    bigDecimals.assertIsNotZero(description, BigDecimal.ONE);
  }

  @Test
  public void should_fail_if_actual_is_zero() {
    try {
      bigDecimals.assertIsNotZero(description, BigDecimal.ZERO);
    } catch (AssertionError e) {
      assertEquals(e.getMessage(), "[testing] expecting:\n<0> should not be equal to:<0>");
      verify(failures).failure(description, ShouldNotBeEqual.shouldNotBeEqual(BigDecimal.ZERO, BigDecimal.ZERO));
      return;
    }
    expectedAssertionErrorNotThrown();
  }
}
