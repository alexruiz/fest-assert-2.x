/*
 * Created on Oct 19, 2010
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

import static org.fest.assertions.error.ShouldBeGreaterThan.shouldBeGreaterThan;
import static org.fest.assertions.test.FailureMessages.actualIsNull;
import static org.fest.assertions.test.TestFailures.expectedAssertionErrorNotThrown;
import static org.fest.test.ExpectedException.none;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;

import org.fest.assertions.description.Description;
import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for {@link Comparables#assertGreaterThan(Description, Comparable, Comparable)}.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class Comparables_assertGreaterThan_Test {
  @Rule
  public ExpectedException thrown = none();

  private Comparables comparables;
  private Description description;
  private Failures failures;
  private BigDecimal actual = new BigDecimal(8.00);
  private BigDecimal other = new BigDecimal(8.000);

  @Before
  public void setUp() {
    comparables = new Comparables();
    description = new TestDescription("testing");
    failures = spy(new Failures());
    comparables.failures = failures;
  }

  @Test
  public void should_fail_if_actual_is_null() {
    thrown.expect(AssertionError.class, actualIsNull());
    comparables.assertGreaterThan(mock(Description.class), null, other);
  }

  @Test
  public void should_pass_if_actual_is_greater_than_other() {
    other = new BigDecimal(6);
    comparables.assertGreaterThan(mock(Description.class), actual, other);
  }

  @Test
  public void should_fail_if_actual_is_equal_to_other() {
    try {
      comparables.assertGreaterThan(description, actual, other);
    } catch (AssertionError e) {
      verify(failures).failure(description, shouldBeGreaterThan(actual, other));
      return;
    }
    expectedAssertionErrorNotThrown();
  }

  @Test
  public void should_fail_if_actual_is_less_than_other() {
    actual = new BigDecimal(6);
    try {
      comparables.assertGreaterThan(description, actual, other);
    } catch (AssertionError e) {
      verify(failures).failure(description, shouldBeGreaterThan(actual, other));
      return;
    }
    expectedAssertionErrorNotThrown();
  }
}
