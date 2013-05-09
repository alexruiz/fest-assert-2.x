/*
 * Created on Mar 28, 2013
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

import static org.fest.assertions.error.ShouldBeGreaterThan.shouldBeGreaterThan;
import static org.fest.assertions.error.ShouldNotBeNull.shouldNotBeNull;
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
 * @author Yvonne Wang
 */
public class Doubles_assertGreaterThan_Test {

  @Rule
  public ExpectedException thrown = none();

  private Doubles doubles;
  private Description description;
  private Failures failures;

  @Before
  public void setUp() {
    doubles = new Doubles();
    description = new TestDescription("testing");
    failures = spy(new Failures());
    doubles.failures = failures;
  }

  @Test
  public void should_fail_if_actual_is_null() {
    thrown.expect(AssertionError.class, shouldNotBeNull().create(description));
    doubles.assertGreaterThan(description, null, 8d);
  }

  @Test
  public void should_fail_if_actual_is_not_greater_than_other() {
    try {
      doubles.assertGreaterThan(description, 6d, 8d);
    } catch (AssertionError e) {
      verify(failures).failure(description, shouldBeGreaterThan(6d, 8d));
      return;
    }
    expectedAssertionErrorNotThrown();
  }

  @Test
  public void should_fail_if_actual_is_identical_to_other() {
    try {
      doubles.assertGreaterThan(description, 8d, 8d);
    } catch (AssertionError e) {
      verify(failures).failure(description, shouldBeGreaterThan(8d, 8d));
      return;
    }
    expectedAssertionErrorNotThrown();
  }

  @Test
  public void should_pass_if_actual_is_greater_than_other() {
    doubles.assertGreaterThan(description, 8d, 6d);
  }
}
