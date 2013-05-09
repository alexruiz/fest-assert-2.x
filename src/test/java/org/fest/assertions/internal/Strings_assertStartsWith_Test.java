/*
 * Created on Dec 26, 2010
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

import static org.fest.assertions.error.ShouldStartWith.shouldStartWith;
import static org.fest.assertions.test.FailureMessages.actualIsNull;
import static org.fest.assertions.test.TestFailures.expectedAssertionErrorNotThrown;
import static org.fest.test.ExpectedException.none;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.fest.assertions.description.Description;
import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for {@link Strings#assertStartsWith(Description, String, String)}.
 *
 * @author Alex Ruiz
 */
public class Strings_assertStartsWith_Test {
  @Rule
  public ExpectedException thrown = none();

  private Failures failures;
  private Strings strings;

  @Before
  public void setUp() {
    failures = spy(new Failures());
    strings = new Strings();
    strings.failures = failures;
  }

  @Test
  public void should_pass_if_actual_starts_with_prefix() {
    strings.assertStartsWith(mock(Description.class), "Yoda", "Yo");
  }

  @Test
  public void should_throw_error_if_prefix_is_null() {
    thrown.expect(NullPointerException.class);
    strings.assertStartsWith(mock(Description.class), "Yoda", null);
  }

  @Test
  public void should_fail_if_actual_is_null() {
    thrown.expect(AssertionError.class, actualIsNull());
    strings.assertStartsWith(mock(Description.class), null, "Yoda");
  }

  @Test
  public void should_fail_if_actual_does_not_start_with_prefix() {
    Description description = new TestDescription("Testing");
    try {
      strings.assertStartsWith(description, "Yoda", "Luke");
    } catch (AssertionError e) {
      verify(failures).failure(description, shouldStartWith("Yoda", "Luke"));
      return;
    }
    throw expectedAssertionErrorNotThrown();
  }
}
