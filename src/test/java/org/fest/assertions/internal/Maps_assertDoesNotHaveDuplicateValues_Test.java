/*
 * Created on May 1, 2013
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
package org.fest.assertions.internal;

import static org.fest.assertions.data.MapEntry.entry;
import static org.fest.assertions.error.ShouldNotHaveDuplicates.shouldNotHaveDuplicates;
import static org.fest.assertions.test.FailureMessages.actualIsNull;
import static org.fest.assertions.test.Maps.newMap;
import static org.fest.assertions.test.TestFailures.expectedAssertionErrorNotThrown;
import static org.fest.test.ExpectedException.none;
import static org.fest.util.Collections.set;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.util.Collection;
import java.util.Map;

import org.fest.assertions.description.Description;
import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Yvonne Wang
 */
public class Maps_assertDoesNotHaveDuplicateValues_Test {

  @Rule
  public ExpectedException thrown = none();

  private Maps maps;
  private Failures failures;
  private Description description;
  private Map<?, ?> actual = newMap(entry("name", "Yoda"), entry("princess", "Leia"));

  @Before
  public void setUp() {
    maps = Maps.instance();
    failures = spy(new Failures());
    maps.failures = failures;
    description = new TestDescription("testing");
  }

  @Test
  public void should_fail_if_actual_is_null() {
    thrown.expect(AssertionError.class, actualIsNull());
    maps.assertDoesNotContainDuplicateValues(description, null);
  }

  @Test
  public void should_pass_if_actual_does_not_contain_duplicate_values() {
    maps.assertDoesNotContainDuplicateValues(description, actual);
  }

  @Test
  public void should_fail_if_actual_contains_duplicate_values() {
    actual = newMap(entry("name", "Leia"), entry("princess", "Leia"), entry("more", "Leia"));
    Collection<String> duplicates = set("Leia");
    try {
      maps.assertDoesNotContainDuplicateValues(description, actual);
    } catch (AssertionError e) {
      verify(failures).failure(description, shouldNotHaveDuplicates(actual, duplicates));
      return;
    }
    expectedAssertionErrorNotThrown();
  }
}
