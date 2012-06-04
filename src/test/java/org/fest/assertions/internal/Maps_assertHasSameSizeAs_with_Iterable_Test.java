/*
 * Created on Jun 4, 2012
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
 * Copyright @2010-2012 the original author or authors.
 */
package org.fest.assertions.internal;

import static org.fest.assertions.data.MapEntry.entry;
import static org.fest.assertions.error.ShouldHaveSameSizeAs.shouldHaveSameSizeAs;
import static org.fest.assertions.test.ExpectedException.none;
import static org.fest.assertions.test.FailureMessages.actualIsNull;
import static org.fest.assertions.test.MapFactory.map;
import static org.fest.assertions.test.TestData.someInfo;
import static org.fest.assertions.test.TestFailures.failBecauseExpectedAssertionErrorWasNotThrown;
import static org.fest.util.Collections.list;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.Map;

import org.fest.assertions.core.AssertionInfo;
import org.fest.assertions.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for <code>{@link Maps#assertHasSameSizeAs(AssertionInfo, boolean[], Iterable)}</code>.
 *
 * @author Nicolas François
 */
public class Maps_assertHasSameSizeAs_with_Iterable_Test  {

  @Rule public ExpectedException thrown = none();

  private Failures failures;
  private Maps maps;
  private Map<?, ?> actual = map(entry("name", "Yoda"), entry("job", "Yedi Master"));  

  @Before public void setUp() {
    failures = spy(new Failures());
    maps = new Maps();
    maps.failures = failures;
  }

  @Test public void should_fail_if_actual_is_null() {
	 thrown.expectAssertionError(actualIsNull());
	 maps.assertHasSameSizeAs(someInfo(), null, list("Solo", "Leia"));
  }

  @Test public void should_fail_if_size_of_actual_is_not_equal_to_expected_size() {
    AssertionInfo info = someInfo();
    List<String> other = list("Solo", "Leia", "Yoda");    
    try {
      maps.assertHasSameSizeAs(info, actual, other);
    } catch (AssertionError e) {
      verify(failures).failure(info, shouldHaveSameSizeAs(actual, actual.size(), other.size()));
      return;
    }
    failBecauseExpectedAssertionErrorWasNotThrown();
  }

  @Test public void should_pass_if_size_of_actual_is_equal_to_expected_size() {
	  maps.assertHasSameSizeAs(someInfo(), actual, list("Solo", "Leia"));
  }

}
