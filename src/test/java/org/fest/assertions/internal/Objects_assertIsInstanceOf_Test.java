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
 * Copyright @2010-2012 the original author or authors.
 */
package org.fest.assertions.internal;

import static org.fest.assertions.test.FailureMessages.actualIsNull;
import static org.fest.assertions.test.TestFailures.expectedAssertionErrorNotThrown;
import static org.fest.test.ExpectedException.none;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.fest.assertions.description.Description;
import org.fest.assertions.error.ErrorMessageFactory;
import org.fest.assertions.test.Person;
import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for {@link Objects#assertIsInstanceOf(Description, Object, Class)}.
 *
 * @author Alex Ruiz
 */
public class Objects_assertIsInstanceOf_Test {
  @Rule
  public ExpectedException thrown = none();

  private Person actual;
  private Failures failures;
  private Objects objects;

  @Before
  public void setUp() {
    actual = new Person("Yoda");
    failures = spy(new Failures());
    objects = new Objects();
    objects.failures = failures;
  }

  @Test
  public void should_pass_if_actual_is_instance_of_type() {
    objects.assertIsInstanceOf(mock(Description.class), actual, Person.class);
  }

  @Test
  public void should_throw_error_if_type_is_null() {
    thrown.expect(NullPointerException.class);
    objects.assertIsInstanceOf(mock(Description.class), actual, null);
  }

  @Test
  public void should_fail_if_actual_is_null() {
    thrown.expectAssertionError(actualIsNull());
    objects.assertIsInstanceOf(mock(Description.class), null, Object.class);
  }

  @Test
  public void should_fail_if_actual_is_not_instance_of_type() {
    Description description = new TestDescription("Testing");
    try {
      objects.assertIsInstanceOf(description, actual, String.class);
    } catch (AssertionError e) {
      String message = "[Testing] expecting <Person[name='Yoda']> to be an instance of:<java.lang.String> but was " +
          "instance of:<org.fest.assertions.test.Person>";
      assertEquals(message, e.getMessage());
      verify(failures).failure(same(description), any(ErrorMessageFactory.class));
      return;
    }
    throw expectedAssertionErrorNotThrown();
  }
}
