/*
 * Created on Oct 17, 2010
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

import static org.fest.assertions.test.FailureMessages.actualIsNull;
import static org.fest.assertions.test.TestFailures.expectedAssertionErrorNotThrown;
import static org.fest.test.ExpectedException.none;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

import java.math.BigDecimal;

import org.fest.assertions.description.Description;
import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for {@link Comparables#assertEqual(Description, Comparable, Comparable)}.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class Comparables_assertEqual_Test {
  @Rule
  public ExpectedException thrown = none();

  private Failures failures;
  private Comparables comparables;

  @Before
  public void setUp() {
    failures = spy(new Failures());
    comparables = new Comparables();
    comparables.failures = failures;
  }

  @Test
  public void should_fail_if_actual_is_null() {
    thrown.expect(AssertionError.class, actualIsNull());
    comparables.assertEqual(mock(Description.class), null, 8);
  }

  @Test
  public void should_pass_if_objects_are_equal() {
    BigDecimal actual = new BigDecimal("10.0");
    BigDecimal expected = new BigDecimal("10.000");
    // We use BigDecimal to ensure that 'compareTo' is being called, since BigDecimal is the only Comparable where
    // 'compareTo' is not consistent with 'equals'.
    assertFalse(actual.equals(expected));
    comparables.assertEqual(mock(Description.class), actual, expected);
  }

  @Test
  public void should_fail_if_objects_are_not_equal() {
    Description info = new TestDescription("Testing");
    try {
      comparables.assertEqual(info, "Luke", "Yoda");
    } catch (AssertionError e) {
      assertEquals("[Testing] expected:<'[Yoda]'> but was:<'[Luke]'>", e.getMessage());
      return;
    }
    throw expectedAssertionErrorNotThrown();
  }
}
