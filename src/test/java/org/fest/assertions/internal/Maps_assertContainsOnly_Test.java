/*
 * Created on Apr 30, 2013
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
import static org.fest.assertions.error.ShouldContainOnly.shouldContainOnly;
import static org.fest.assertions.test.FailureMessages.actualIsNull;
import static org.fest.assertions.test.Maps.newMap;
import static org.fest.assertions.test.TestFailures.expectedAssertionErrorNotThrown;
import static org.fest.test.ExpectedException.none;
import static org.fest.util.Arrays.array;
import static org.fest.util.Sets.newLinkedHashSet;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.util.Map;
import java.util.Set;

import org.fest.assertions.data.MapEntry;
import org.fest.assertions.description.Description;
import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Yvonne Wang
 */
public class Maps_assertContainsOnly_Test {

  @Rule
  public ExpectedException thrown = none();

  private Maps maps;
  private Failures failures;
  private Description description;
  private Map<?, ?> actual = newMap(entry("name", "Yoda"), entry("princess", "Leia"));
  private MapEntry[] entries = array(entry("name", "Yoda"));

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
    maps.assertContainsOnly(description, null, entries);
  }

  @Test
  public void should_pass_if_actual_contains_only_given_entries_wt_differnt_values() {
    entries = array(entry("name", "Yoda"), entry("princess", "Leia"));
    maps.assertContainsOnly(description, actual, entries);
  }

  @Test
  public void should_pass_if_actual_contains_only_given_entries_wt_same_values() {
    actual = newMap(entry("name", "Leia"), entry("princess", "Leia"));
    entries = array(entry("name", "Leia"), entry("princess", "Leia"));
    maps.assertContainsOnly(description, actual, entries);
  }

  @Test
  public void should_pass_if_actual_contains_only_given_entries_in_any_order() {
    entries = array(entry("princess", "Leia"), entry("name", "Yoda"));
    maps.assertContainsOnly(description, actual, entries);
  }

  @Test
  public void should_fail_if_actual_contains_other_entries_other_than_given_entries() {
    entries = array(entry("name", "Yoda"), entry("dummy", "value"));
    Map<?, ?> notExpected = newMap(entry("princess", "Leia"));
    Set<MapEntry> notFound = newLinkedHashSet(entry("dummy", "value"));
    try {
      maps.assertContainsOnly(description, actual, entries);
    } catch (AssertionError e) {
      verify(failures).failure(description, shouldContainOnly(actual, entries, notFound, notExpected));
      return;
    }
    expectedAssertionErrorNotThrown();
  }

  @Test
  public void should_fail_if_given_entries_is_null() {
    thrown.expect(NullPointerException.class, "The array of entries to look for should not be null");
    maps.assertContainsOnly(description, actual, null);
  }

  @Test
  public void should_fail_if_given_entries_is_empty() {
    entries = new MapEntry[0];
    thrown.expect(IllegalArgumentException.class, "The array of entries to look for should not be empty");
    maps.assertContainsOnly(description, actual, entries);
  }
}
