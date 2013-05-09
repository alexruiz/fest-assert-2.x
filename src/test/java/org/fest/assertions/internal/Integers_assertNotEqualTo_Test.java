/*
 * Created on Mar 19, 2013
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

import static org.fest.assertions.error.ShouldNotBeEqual.shouldNotBeEqual;
import static org.fest.assertions.test.FailureMessages.actualIsNull;
import static org.fest.assertions.test.TestFailures.expectedAssertionErrorNotThrown;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.fest.assertions.description.Description;
import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Alex Ruiz
 * @author Yvonne Price
 *
 */
public class Integers_assertNotEqualTo_Test {

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  private Integers integers;
  private Failures failures;
  private Description description;

  @Before
  public void setUp() {
    integers = new Integers();
    failures = spy(new Failures());
    integers.failures = failures;
    description = new TestDescription("testing");
  }

  @Test
  public void should_fail_if_actual_is_null() {
    thrown.expect(AssertionError.class, "[" + description + "] " + actualIsNull());
    integers.assertNotEqual(description, null, 8);
  }

  @Test
  public void should_pass_if_integers_are_not_equal() {
    integers.assertNotEqual(description, 8, 6);
  }

  @Test
  public void should_fail_if_integers_are_equal() {
    try {
      integers.assertNotEqual(description, 8, 8);
    }catch(AssertionError e) {
      verify(failures).failure(description, shouldNotBeEqual(8, 8));
      return;
    }
    throw expectedAssertionErrorNotThrown();
  }
}
