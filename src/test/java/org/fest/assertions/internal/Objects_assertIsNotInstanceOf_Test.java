/*
 * Created on Jun 3, 2012
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
 * Copyright @2012 the original author or authors.
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

import org.fest.assertions.description.Description;
import org.fest.assertions.error.ErrorMessageFactory;
import org.fest.assertions.test.Person;
import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for {@link Objects#assertIsNotInstanceOf(Description, Object, Class)}.
 *
 * @author Nicolas François
 * @author Alex Ruiz
 */
public class Objects_assertIsNotInstanceOf_Test {
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
  public void should_pass_if_actual_is_not_instance_of_type() {
    objects.assertIsNotInstanceOf(mock(Description.class), actual, String.class);
  }

  @Test
  public void should_pass_if_actual_is_null() {
    objects.assertIsNotInstanceOf(mock(Description.class), null, Object.class);
  }

  @Test
  public void should_throw_error_if_type_is_null() {
    thrown.expect(NullPointerException.class);
    objects.assertIsNotInstanceOf(mock(Description.class), actual, null);
  }

  @Test
  public void should_fail_if_actual_is_instance_of_type() {
    Description description = new TestDescription("Testing");
    try {
      objects.assertIsNotInstanceOf(description, actual, Person.class);
    } catch (AssertionError e) {
      String message = "[Testing] expecting <Person[name='Yoda']> not to be an instance of:"
          + "<org.fest.assertions.test.Person>";
      assertEquals(message, e.getMessage());
      verify(failures).failure(same(description), any(ErrorMessageFactory.class));
      return;
    }
    throw expectedAssertionErrorNotThrown();
  }
}
