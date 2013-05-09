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

import static java.util.Collections.emptyMap;

import static org.fest.assertions.data.MapEntry.entry;
import static org.fest.assertions.error.ShouldBeEmpty.shouldBeEmpty;
import static org.fest.assertions.test.FailureMessages.actualIsNull;
import static org.fest.assertions.test.Maps.newMap;
import static org.fest.assertions.test.TestFailures.expectedAssertionErrorNotThrown;
import static org.fest.test.ExpectedException.none;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.util.Map;

import org.fest.assertions.description.Description;
import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for <code>{@link Maps#assertEmpty(Description, Map)}</code>.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class Maps_assertEmpty_Test {

  @Rule
  public ExpectedException thrown = none();

  private Maps maps;
  private Failures failures;
  private Description description;

  @Before
  public void setUp() {
    maps = Maps.instance();
    failures = spy(new Failures());
    maps.failures = failures;
    description = new TestDescription("testing");
  }

  @Test
  public void should_pass_if_actual_is_empty() {
    maps.assertEmpty(description, emptyMap());
  }

  @Test
  public void should_fail_if_actual_is_null() {
    thrown.expect(AssertionError.class, actualIsNull());
    maps.assertEmpty(description, null);
  }

  @Test
  public void should_fail_if_actual_has_elements() {
    Map<?, ?> actual = newMap(entry("name", "Yoda"));
    try {
      maps.assertEmpty(description, actual);
    } catch (AssertionError e) {
      verify(failures).failure(description, shouldBeEmpty(actual));
      return;
    }
    expectedAssertionErrorNotThrown();
  }
}
