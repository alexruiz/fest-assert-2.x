/*
 * Created on Jun 3, 2012
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
import static org.fest.assertions.error.ShouldContainValue.shouldContainValue;
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
 * Tests for <code>{@link Maps#assertContainsValue(Description, Map, Object)}</code>.
 *
 * @author Nicolas François
 * @author Joel Costigliola
 * @author Yvonne Wang
 */
public class Maps_assertContainsValue_Test {

  @Rule
  public ExpectedException thrown = none();

  private Maps maps;
  private Failures failures;
  private Description description;
  @SuppressWarnings("unchecked")
  private final Map<String, String> actual = (Map<String, String>) newMap(entry("name", "Yoda"),
      entry("princess", "Leia"));

  @Before
  public void setUp() {
    maps = Maps.instance();
    failures = spy(new Failures());
    maps.failures = failures;
    description = new TestDescription("testing");
  }

  @Test
  public void should_pass_if_actual_contains_given_value() {
    maps.assertContainsValue(description, actual, "Yoda");
  }

  @Test
  public void should_pass_if_actual_contains_null_value() {
    actual.put("value", null);
    maps.assertContainsValue(description, actual, null);
  }

  @Test
  public void should_fail_if_actual_is_null() {
    thrown.expect(AssertionError.class, actualIsNull());
    maps.assertContainsValue(description, null, "Yoda");
  }

  @Test
  public void should_fail_if_actual_does_not_contain_null_value() {
    try {
      maps.assertContainsValue(description, actual, null);
    } catch (AssertionError e) {
      verify(failures).failure(description, shouldContainValue(actual, null));
      return;
    }
    expectedAssertionErrorNotThrown();
  }

  @Test
  public void should_fail_if_actual_does_not_contain_given_value() {
    String value = "veryOld";
    try {
      maps.assertContainsValue(description, actual, value);
    } catch (AssertionError e) {
      verify(failures).failure(description, shouldContainValue(actual, value));
      return;
    }
    expectedAssertionErrorNotThrown();
  }
}
