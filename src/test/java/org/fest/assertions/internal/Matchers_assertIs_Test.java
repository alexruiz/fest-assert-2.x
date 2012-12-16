/*
 * Created on Sep 27, 2010
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

import static org.fest.assertions.test.TestFailures.expectedAssertionErrorNotThrown;
import static org.fest.test.ExpectedException.none;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.fest.assertions.core.Matcher;
import org.fest.assertions.core.TestMatcher;
import org.fest.assertions.description.Description;
import org.fest.assertions.error.ErrorMessageFactory;
import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for {@link Matchers#assertIs(Description, Object, Matcher)}.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class Matchers_assertIs_Test {
  @Rule
  public ExpectedException thrown = none();

  private Failures failures;
  private TestMatcher<Object> matcher;
  private Matchers matchers;

  @Before
  public void setUp() {
    failures = spy(new Failures());
    matcher = new TestMatcher<Object>();
    matchers = new Matchers();
    matchers.failures = failures;
  }

  @Test
  public void should_throw_error_if_Matcher_is_null() {
    thrown.expect(NullPointerException.class);
    matchers.assertIs(mock(Description.class), "Yoda", null);
  }

  @Test
  public void should_pass_if_Matcher_is_met() {
    matcher.shouldMatch(true);
    matchers.assertIs(mock(Description.class), "Yoda", matcher);
  }

  @Test
  public void should_fail_if_Matcher_is_not_met() {
    matcher.shouldMatch(false);
    Description description = new TestDescription("Testing");
    try {
      matchers.assertIs(description, "Yoda", matcher);
    } catch (AssertionError e) {
      assertEquals("[Testing] expecting:<'Yoda'> to be:<TestMatcher>", e.getMessage());
      verify(failures).failure(same(description), any(ErrorMessageFactory.class));
      return;
    }
    throw expectedAssertionErrorNotThrown();
  }
}
