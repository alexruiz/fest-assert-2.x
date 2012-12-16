/*
 * Created on Dec 24, 2010
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

import static org.fest.assertions.test.TestFailures.expectedAssertionErrorNotThrown;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.util.regex.PatternSyntaxException;

import org.fest.assertions.description.Description;
import org.fest.assertions.error.ErrorMessageFactory;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for {@link Strings#assertDoesNotMatch(Description, String, String)}.
 *
 * @author Alex Ruiz
 */
public class Strings_assertDoesNotMatch_String_Test {
  private Failures failures;
  private Strings strings;

  @Before
  public void setUp() {
    failures = spy(new Failures());
    strings = new Strings();
    strings.failures = failures;
  }

  @Test
  public void should_pass_if_actual_is_null() {
    strings.assertDoesNotMatch(mock(Description.class), null, "Luke");
  }

  @Test
  public void should_pass_if_actual_does_not_match_regular_expression() {
    strings.assertDoesNotMatch(mock(Description.class), "Yoda", "Luke");
  }

  @Test(expected = NullPointerException.class)
  public void should_throw_error_if_regular_expression_is_null() {
    String pattern = null;
    strings.assertDoesNotMatch(mock(Description.class), "Yoda", pattern);
  }

  @Test(expected = PatternSyntaxException.class)
  public void should_throw_error_if_syntax_of_regular_expression_is_invalid() {
    strings.assertDoesNotMatch(mock(Description.class), "Yoda", "*...");
  }

  @Test
  public void should_fail_if_actual_matches_regular_expression() {
    Description description = new TestDescription("Testing");
    try {
      strings.assertDoesNotMatch(description, "Yoda", ".*");
    } catch (AssertionError e) {
      assertEquals("[Testing] expecting:<'Yoda'> not to match regular expression:<'.*'>", e.getMessage());
      verify(failures).failure(same(description), any(ErrorMessageFactory.class));
      return;
    }
    throw expectedAssertionErrorNotThrown();
  }
}
