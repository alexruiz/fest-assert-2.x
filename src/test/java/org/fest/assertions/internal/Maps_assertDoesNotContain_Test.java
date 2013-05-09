/*
 * Created on Dec 21, 2010
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

import static org.fest.assertions.data.MapEntry.entry;
import static org.fest.assertions.error.ShouldNotContain.shouldNotContain;
import static org.fest.assertions.test.ErrorMessages.entriesToLookForIsEmpty;
import static org.fest.assertions.test.ErrorMessages.entriesToLookForIsNull;
import static org.fest.assertions.test.FailureMessages.actualIsNull;
import static org.fest.assertions.test.Maps.newMap;
import static org.fest.assertions.test.TestFailures.expectedAssertionErrorNotThrown;
import static org.fest.test.ExpectedException.none;
import static org.fest.util.Arrays.array;
import static org.fest.util.Sets.newLinkedHashSet;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.util.Map;

import org.fest.assertions.data.MapEntry;
import org.fest.assertions.description.Description;
import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for <code>{@link Maps#assertDoesNotContain(Description, Map, MapEntry[])}</code>.
 *
 * @author Alex Ruiz
 * @author Joel Costigliola
 * @author Yvonne Wang
 */
public class Maps_assertDoesNotContain_Test {

  @Rule
  public ExpectedException thrown = none();

  private Maps maps;
  private Failures failures;
  private Description description;
  private final Map<?, ?> actual = newMap(entry("name", "Yoda"), entry("princess", "Leia"));

  @Before
  public void setUp() {
    maps = Maps.instance();
    failures = spy(new Failures());
    maps.failures = failures;
    description = new TestDescription("testing");
  }

  @Test
  public void should_pass_if_actual_does_not_contain_given_values() {
    maps.assertDoesNotContain(description, actual, array(entry("job", "Jedi")));
  }

  @Test
  public void should_throw_error_if_array_of_entries_to_look_for_is_empty() {
    thrown.expect(IllegalArgumentException.class, entriesToLookForIsEmpty());
    maps.assertDoesNotContain(description, actual, new MapEntry[0]);
  }

  @Test
  public void should_throw_error_if_array_of_entries_to_look_for_is_null() {
    thrown.expect(NullPointerException.class, entriesToLookForIsNull());
    maps.assertDoesNotContain(description, actual, null);
  }

  @Test
  public void should_fail_if_actual_is_null() {
    thrown.expect(AssertionError.class, actualIsNull());
    maps.assertDoesNotContain(description, null, array(entry("job", "Jedi")));
  }

  @Test
  public void should_fail_if_actual_contains_given_entries() {
    MapEntry[] expected = { entry("name", "Yoda"), entry("job", "Jedi") };
    try {
      maps.assertDoesNotContain(description, actual, expected);
    } catch (AssertionError e) {
      verify(failures)
          .failure(description, shouldNotContain(actual, expected, newLinkedHashSet(entry("name", "Yoda"))));
      return;
    }
    expectedAssertionErrorNotThrown();
  }
}
