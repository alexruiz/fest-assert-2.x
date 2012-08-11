/*
 * Created on Oct 28, 2010
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
package org.fest.assertions.internal.doubles;

import static org.fest.assertions.error.ShouldBeEqual.shouldBeEqual;
import static org.fest.test.FailureMessages.actualIsNull;
import static org.fest.assertions.test.TestData.someInfo;
import static org.fest.test.TestFailures.failBecauseExpectedAssertionErrorWasNotThrown;

import static org.mockito.Mockito.verify;

import org.junit.Test;

import org.fest.assertions.core.AssertionInfo;
import org.fest.assertions.internal.Doubles;
import org.fest.assertions.internal.DoublesBaseTest;

/**
 * Tests for <code>{@link Doubles#assertEqual(AssertionInfo, Double, double)}</code>.
 * 
 * @author Alex Ruiz
 * @author Joel Costigliola
 */
public class Doubles_assertEqual_Test extends DoublesBaseTest {

  @Test
  public void should_fail_if_actual_is_null() {
    thrown.expectAssertionError(actualIsNull());
    doubles.assertEqual(someInfo(), null, 8d);
  }

  @Test
  public void should_pass_if_doubles_are_equal() {
    doubles.assertEqual(someInfo(), 8d, 8d);
  }

  @Test
  public void should_fail_if_doubles_are_not_equal() {
    AssertionInfo info = someInfo();
    try {
      doubles.assertEqual(info, 6d, 8d);
    } catch (AssertionError e) {
      verify(failures).failure(info, shouldBeEqual(6d, 8d));
      return;
    }
    failBecauseExpectedAssertionErrorWasNotThrown();
  }

  @Test
  public void should_fail_if_actual_is_null_whatever_custom_comparison_strategy_is() {
    thrown.expectAssertionError(actualIsNull());
    doublesWithAbsValueComparisonStrategy.assertEqual(someInfo(), null, 8d);
  }

  @Test
  public void should_pass_if_doubles_are_equal_according_to_custom_comparison_strategy() {
    doublesWithAbsValueComparisonStrategy.assertEqual(someInfo(), 8d, -8d);
  }

  @Test
  public void should_fail_if_doubles_are_not_equal_according_to_custom_comparison_strategy() {
    AssertionInfo info = someInfo();
    try {
      doublesWithAbsValueComparisonStrategy.assertEqual(info, 6d, 8d);
    } catch (AssertionError e) {
      verify(failures).failure(info, shouldBeEqual(6d, 8d, absValueComparisonStrategy));
      return;
    }
    failBecauseExpectedAssertionErrorWasNotThrown();
  }
}