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
 * Copyright @2010-2013 the original author or authors.
 */
package org.fest.assertions.api;

import static junit.framework.Assert.assertSame;

import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for {@link ObjectAssert#isInstanceOf(Class)}.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class ObjectAssert_isInstanceOf_Test {
  @Rule
  public ExpectedException thrown = ExpectedException.none();
  private Object actual = "Yoda";
  private ObjectAssert assertions;

  @Before
  public void setUp() {
    assertions = new ObjectAssert(actual);
  }

  @Test
  public void should_pass_if_actual_is_instance_of_given_type() {
    assertions.isInstanceOf(String.class);
  }

  @Test
  public void should_return_this() {
    ObjectAssert returned = assertions.isInstanceOf(String.class);
    assertSame(assertions, returned);
  }

  @Test
  public void should_fail_if_actual_is_not_instance_of_given_type() {
    thrown.expect(AssertionError.class);
    assertions.isInstanceOf(Integer.class);
  }

  @Test
  public void should_throw_error_if_given_type_is_null() {
    thrown.expect(NullPointerException.class);
    assertions.isInstanceOf(null);
  }

  @Test
  public void should_throw_error_if_actual_is_null() {
    actual = null;
    assertions = new ObjectAssert(actual);
    thrown.expect(AssertionError.class);
    assertions.isInstanceOf(String.class);
  }
}
