/*
 * Created on Oct 19, 2010
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
 * Tests for {@link Comparables#assertGreaterThan(Description, Comparable, Comparable)}.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
@RunWith(Parameterized.class)
public class Comparables_assertGreaterThan_with_mocks_Test {
  private final String actual;
  private final String other;

  @Parameters
  public static List<Object[]> parameters() {
    return newArrayList(new Object[][] {
       // actual, other
        { "Yoda", "Yoda" },
        { "Leia", "Luke" }
    });
  }

  public Comparables_assertGreaterThan_with_mocks_Test(String actual, String other) {
    this.actual = actual;
    this.other = other;
  }

  private Failures failures;
  private Comparables comparables;

  @Before
  public void setUp() {
    failures = spy(new Failures());
    comparables = new Comparables();
    comparables.failures = failures;
  }

  @Test
  public void should_fail_if_actual_is_not_greater_than_other() {
    Description description = new TestDescription("Testing");
    try {
      comparables.assertGreaterThan(description, actual, other);
    } catch (AssertionError e) {
      String message = "[Testing] expected:<" + quote(actual) + "> to be greater than:<" + quote(other) + ">";
      assertEquals(message, e.getMessage());
      verify(failures).failure(same(description), any(ErrorMessageFactory.class));
      return;
    }
    throw expectedAssertionErrorNotThrown();
  }
}
