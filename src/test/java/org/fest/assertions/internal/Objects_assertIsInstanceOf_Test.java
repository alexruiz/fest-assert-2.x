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
 * Copyright @2010-2011 the original author or authors.
 */
package org.fest.assertions.internal;

import static org.fest.assertions.error.ShouldBeInstance.shouldBeInstance;
import static org.fest.assertions.test.ExpectedException.none;
import static org.fest.assertions.test.FailureMessages.actualIsNull;
import static org.fest.assertions.test.TestData.someInfo;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

import org.fest.assertions.core.AssertionInfo;
import org.fest.assertions.test.*;
import org.junit.*;

/**
 * Tests for <code>{@link Objects#assertIsInstanceOf(AssertionInfo, Object, Class)}</code>.
 *
 * @author Alex Ruiz
 */
public class Objects_assertIsInstanceOf_Test {

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

  @Test public void should_pass_if_actual_is_instance_of_type() {
    objects.assertIsInstanceOf(someInfo(), actual, Person.class);
  }

  @Test public void should_throw_error_if_type_is_null() {
    thrown.expectNullPointerException("The given type should not be null");
    objects.assertIsInstanceOf(someInfo(), actual, null);
  }

  @Test public void should_fail_if_actual_is_null() {
    thrown.expectAssertionError(actualIsNull());
    objects.assertIsInstanceOf(someInfo(), null, Object.class);
  }

  @Test public void should_fail_if_actual_is_not_instance_of_type() {
    AssertionInfo info = someInfo();
    try {
      objects.assertIsInstanceOf(info, actual, String.class);
      fail();
    } catch (AssertionError err) {
    	/* we will check this exception just after this block */
    }
    verify(failures).failure(info, shouldBeInstance(actual, String.class));
  }
}
