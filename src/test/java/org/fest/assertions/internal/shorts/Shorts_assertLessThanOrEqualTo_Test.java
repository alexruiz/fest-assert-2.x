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
package org.fest.assertions.internal.shorts;

import static org.fest.assertions.error.ShouldBeLessOrEqual.shouldBeLessOrEqual;
import static org.fest.assertions.test.FailureMessages.actualIsNull;
import static org.fest.assertions.test.TestData.someInfo;
import static org.fest.assertions.test.TestFailures.expectedAssertionErrorNotThrown;

import static org.mockito.Mockito.verify;

import org.junit.Test;

import org.fest.assertions.core.AssertionInfo;
import org.fest.assertions.internal.Shorts;
import org.fest.assertions.internal.ShortsBaseTest;

/**
 * Tests for <code>{@link Shorts#assertNotGreaterThan(AssertionInfo, Short, short)}</code>.
 * 
 * @author Alex Ruiz
 * @author Joel Costigliola
 */
public class Shorts_assertLessThanOrEqualTo_Test extends ShortsBaseTest {

  @Test
  public void should_fail_if_actual_is_null() {
    thrown.expectAssertionError(actualIsNull());
    shorts.assertNotGreaterThan(someInfo(), null, (short) 8);
  }

  @Test
  public void should_pass_if_actual_is_less_than_other() {
    shorts.assertNotGreaterThan(someInfo(), (short) 6, (short) 8);
  }

  @Test
  public void should_pass_if_actual_is_equal_to_other() {
    shorts.assertNotGreaterThan(someInfo(), (short) 6, (short) 6);
  }

  @Test
  public void should_fail_if_actual_is_greater_than_other() {
    AssertionInfo info = someInfo();
    try {
      shorts.assertNotGreaterThan(info, (short) 8, (short) 6);
    } catch (AssertionError e) {
      verify(failures).failure(info, shouldBeLessOrEqual((short) 8, (short) 6));
      return;
    }
    expectedAssertionErrorNotThrown();
  }

  @Test
  public void should_fail_if_actual_is_null_whatever_custom_comparison_strategy_is() {
    thrown.expectAssertionError(actualIsNull());
    shortsWithAbsValueComparisonStrategy.assertNotGreaterThan(someInfo(), null, (short) 8);
  }

  @Test
  public void should_pass_if_actual_is_less_than_other_according_to_custom_comparison_strategy() {
    shortsWithAbsValueComparisonStrategy.assertNotGreaterThan(someInfo(), (short) 6, (short) -8);
  }

  @Test
  public void should_pass_if_actual_is_equal_to_other_according_to_custom_comparison_strategy() {
    shortsWithAbsValueComparisonStrategy.assertNotGreaterThan(someInfo(), (short) 6, (short) -6);
  }

  @Test
  public void should_fail_if_actual_is_greater_than_other_according_to_custom_comparison_strategy() {
    AssertionInfo info = someInfo();
    try {
      shortsWithAbsValueComparisonStrategy.assertNotGreaterThan(info, (short) -8, (short) 6);
    } catch (AssertionError e) {
      verify(failures).failure(info, shouldBeLessOrEqual((short) -8, (short) 6, absValueComparisonStrategy));
      return;
    }
    expectedAssertionErrorNotThrown();
  }
}
