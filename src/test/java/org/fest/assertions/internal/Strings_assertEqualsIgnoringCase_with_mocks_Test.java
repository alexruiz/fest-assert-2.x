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
import static org.fest.util.Lists.newArrayList;
import static org.fest.util.Strings.quote;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.fest.assertions.description.Description;
import org.fest.assertions.error.ErrorMessageFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Tests for {@link Strings#assertEqualsIgnoringCase(Description, String, String)}.
 *
 * @author Alex Ruiz
 */
@RunWith(Parameterized.class)
public class Strings_assertEqualsIgnoringCase_with_mocks_Test {
  private final String actual;
  private final String expected;

  private Failures failures;
  private Strings strings;

  @Parameters
  public static List<Object[]> parameters() {
    return newArrayList(new Object[][] {
       // actual, expected
        { null,   "Luke" },
        { "Yoda", "Luke" },
        { "Yoda", null   }
    });
  }

  public Strings_assertEqualsIgnoringCase_with_mocks_Test(String actual, String expected) {
    this.actual = actual;
    this.expected = expected;
  }

  @Before
  public void setUp() {
    failures = spy(new Failures());
    strings = new Strings();
    strings.failures = failures;
  }

  @Test
  public void should_fail_if_both_Strings_are_not_equal_regardless_of_case() {
    Description description = new TestDescription("Testing");
    try {
      strings.assertEqualsIgnoringCase(description, actual, expected);
    } catch (AssertionError e) {
      String message = "[Testing] expecting:<" + quote(actual) + "> to be equal to:<" + quote(expected)
          + ">, ignoring case considerations";
      assertEquals(message, e.getMessage());
      verify(failures).failure(same(description), any(ErrorMessageFactory.class));
      return;
    }
    throw expectedAssertionErrorNotThrown();
  }
}
