/*
 * Created on Sep 30, 2010
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
 * Copyright @2010-2011 the original author or authors.
 */
package org.fest.assertions.internal;

import static org.fest.assertions.error.ShouldContainNull.shouldContainNull;
import static org.fest.assertions.test.FailureMessages.actualIsNull;
import static org.fest.assertions.test.TestData.someInfo;
import static org.fest.assertions.test.TestFailures.failBecauseExpectedAssertionErrorWasNotThrown;
import static org.fest.util.Collections.list;

import static org.mockito.Mockito.verify;

import java.util.Collection;
import java.util.List;

import org.junit.Test;

import org.fest.assertions.core.AssertionInfo;

/**
 * Tests for <code>{@link Iterables#assertContainsNull(AssertionInfo, Collection)}</code>.
 *
 * @author Joel Costigliola
 */
public class Iterables_assertContainsNull_Test extends AbstractTest_for_Iterables {

  private List<String> actual = list("Luke", "Yoda", null);

  @Test public void should_pass_if_actual_contains_null() {
    iterables.assertContainsNull(someInfo(), actual);
  }

  @Test public void should_pass_if_actual_contains_only_null_values() {
    actual = list(null, null);
    iterables.assertContainsNull(someInfo(), actual);
  }

  @Test public void should_pass_if_actual_contains_null_more_than_once() {
    actual.add(null);
    iterables.assertContainsNull(someInfo(), actual);
  }

  @Test public void should_fail_if_actual_is_null() {
    thrown.expectAssertionError(actualIsNull());
    iterables.assertContainsNull(someInfo(), null);
  }

  @Test public void should_fail_if_actual_does_not_contain_null() {
    AssertionInfo info = someInfo();
    actual = list("Luke", "Yoda");
    try {
      iterables.assertContainsNull(info, actual);
    } catch (AssertionError e) {
      verify(failures).failure(info, shouldContainNull(actual));
      return;
    }
    failBecauseExpectedAssertionErrorWasNotThrown();
  }
  
  @Test public void should_pass_if_actual_contains_null_whatever_custom_comparison_strategy_is() {
    iterablesWithCaseInsensitiveComparisonStrategy.assertContainsNull(someInfo(), actual);
  }
  
  @Test public void should_pass_if_actual_contains_only_null_values_whatever_custom_comparison_strategy_is() {
    actual = list(null, null);
    iterablesWithCaseInsensitiveComparisonStrategy.assertContainsNull(someInfo(), actual);
  }
  
  @Test public void should_pass_if_actual_contains_null_more_than_once_whatever_custom_comparison_strategy_is() {
    actual.add(null);
    iterablesWithCaseInsensitiveComparisonStrategy.assertContainsNull(someInfo(), actual);
  }
  
  @Test public void should_fail_if_actual_is_null_whatever_custom_comparison_strategy_is() {
    thrown.expectAssertionError(actualIsNull());
    iterablesWithCaseInsensitiveComparisonStrategy.assertContainsNull(someInfo(), null);
  }
  
  @Test public void should_fail_if_actual_does_not_contain_null_whatever_custom_comparison_strategy_is() {
    AssertionInfo info = someInfo();
    actual = list("Luke", "Yoda");
    try {
      iterablesWithCaseInsensitiveComparisonStrategy.assertContainsNull(info, actual);
    } catch (AssertionError e) {
      verify(failures).failure(info, shouldContainNull(actual));
      return;
    }
    failBecauseExpectedAssertionErrorWasNotThrown();
  }
}
