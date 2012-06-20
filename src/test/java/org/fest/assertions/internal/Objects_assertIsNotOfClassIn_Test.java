/*
 * Created on Jun 12, 2012
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

import static org.fest.assertions.error.ShouldNotBeOfClassIn.shouldNotBeOfClassIn;
import static org.fest.assertions.test.ExpectedException.none;
import static org.fest.assertions.test.FailureMessages.actualIsNull;
import static org.fest.assertions.test.TestData.someInfo;
import static org.fest.assertions.test.TestFailures.failBecauseExpectedAssertionErrorWasNotThrown;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.io.File;

import org.fest.assertions.core.AssertionInfo;
import org.fest.assertions.test.ExpectedException;
import org.fest.assertions.test.Person;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for <code>{@link Objects#assertIsNotOfClassIn(AssertionInfo, Object, Class[])}</code>.
 * 
 * @author Nicolas François
 */
public class Objects_assertIsNotOfClassIn_Test {

  private static Person actual;

  @BeforeClass public static void setUpOnce() {
    actual = new Person("Yoda");
  }

  @Rule public ExpectedException thrown = none();

  private Failures failures;
  private Objects objects;

  @Before public void setUp() {
    failures = spy(new Failures());
    objects = new Objects();
    objects.failures = failures;
  }

  @Test public void should_pass_if_actual_is_not_of_class_in_types() {
    Class<?>[] types = new Class[] { File.class, String.class };
    objects.assertIsNotOfClassIn(someInfo(), actual, types);
  }

  @Test public void should_pass_if_actual_is__of_class_in_empty_types() {
    Class<?>[] types = new Class[] {};
    objects.assertIsNotOfClassIn(someInfo(), actual, types);
  }

  @Test public void should_throw_error_if_type_is_null() {
    thrown.expectNullPointerException("The given types should not be null");
    objects.assertIsNotOfClassIn(someInfo(), actual, null);
  }

  @Test public void should_fail_if_actual_is_null() {
    Class<?>[] types = new Class[] { File.class, Person.class, String.class };
    thrown.expectAssertionError(actualIsNull());
    objects.assertIsNotOfClassIn(someInfo(), null, types);
  }

  @Test public void should_fail_if_actual_is_of_class_in_types() {
    AssertionInfo info = someInfo();
    Class<?>[] types = new Class[] { File.class, Person.class, String.class };
    try {
      objects.assertIsNotOfClassIn(info, actual, types);
      failBecauseExpectedAssertionErrorWasNotThrown();
    } catch (AssertionError err) {
      verify(failures).failure(info, shouldNotBeOfClassIn(actual, types));
    }
  }

}
