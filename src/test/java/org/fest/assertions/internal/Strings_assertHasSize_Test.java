/*
 * Created on Dec 14, 2010
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

import static org.fest.assertions.error.ShouldHaveSize.shouldHaveSize;
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
 * Tests for {@link Strings#assertHasSize(Description, String, int)}.
 *
 * @author Alex Ruiz
 */
public class Strings_assertHasSize_Test {
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
  public void should_pass_if_size_of_actual_is_equal_to_expected_size() {
    strings.assertHasSize(mock(Description.class), "Han", 3);
  }

  @Test
  public void should_fail_if_actual_is_null() {
    thrown.expect(AssertionError.class, actualIsNull());
    strings.assertHasSize(mock(Description.class), null, 3);
  }

  @Test
  public void should_fail_if_size_of_actual_is_not_equal_to_expected_size() {
    Description description = new TestDescription("Testing");
    String actual = "Han";
    int expectedSize = 6;
    try {
      strings.assertHasSize(description, actual, expectedSize);
    } catch (AssertionError e) {
      verify(failures).failure(description, shouldHaveSize(actual, actual.length(), expectedSize));
      return;
    }
    throw expectedAssertionErrorNotThrown();
  }
}
