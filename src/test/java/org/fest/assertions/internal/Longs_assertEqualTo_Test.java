/*
 * Created on Mar 23, 2013
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

import static org.fest.assertions.error.ShouldBeEqual.shouldBeEqual;
import static org.fest.assertions.test.FailureMessages.actualIsNull;
import static org.fest.assertions.test.TestFailures.expectedAssertionErrorNotThrown;
import static org.fest.test.ExpectedException.none;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.fest.assertions.description.Description;
import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Alex Ruiz
 * @author Yvonne wang
 *
 */
public class Longs_assertEqualTo_Test {

  @Rule
  public ExpectedException thrown = none();

  private Longs longs;
  private Description description;
  private Failures failures;

  @Before
  public void setUp() {
    longs = new Longs();
    description = new TestDescription("testing");
    failures = spy(new Failures());
    longs.failures = failures;
  }

  @Test
  public void should_fail_if_actual_is_null() {
    thrown.expect(AssertionError.class, "[" + description + "] " + actualIsNull());
    longs.assertEqualTo(description, null, 6l);
  }

  @Test
  public void should_fail_if_actual_not_equal_to_expected() {
    try {
      longs.assertEqualTo(description, 6l, 8l);
    } catch (AssertionError e) {
      verify(failures).failure(description, shouldBeEqual(6l, 8l));
      return;
    }
    expectedAssertionErrorNotThrown();
  }

  @Test
  public void should_pass_if_actual_is_equla_to_expected() {
    long actual = 6l;
    long expected = 6l;
    longs.assertEqualTo(description, actual, expected);
  }
}
