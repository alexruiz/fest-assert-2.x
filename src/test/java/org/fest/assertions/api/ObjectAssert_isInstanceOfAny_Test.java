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
package org.fest.assertions.api;

import static junit.framework.Assert.assertSame;

import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for {@link ObjectAssert#isInstanceOfAny(Class...)}.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class ObjectAssert_isInstanceOfAny_Test {
  @Rule
  public ExpectedException thrown = ExpectedException.none();
  private Object actual = "Yoda";
  private Class<?>[] types = { String.class, Object.class };
  private ObjectAssert assertions;

  @Before
  public void setUp() {
    assertions = new ObjectAssert(actual);
  }

  @Test
  public void should_pass_if_actual_is_instance_of_any_type() {
    assertions.isInstanceOfAny(types);
  }

  @Test
  public void should_return_this() {
    ObjectAssert returned = assertions.isInstanceOfAny(String.class);
    assertSame(assertions, returned);
  }

  @Test
  public void should_fail_if_actual_is_not_instance_of_any_given_types() {
    thrown.expect(AssertionError.class);
    assertions.isInstanceOfAny(Integer.class);
  }

  @Test
  public void should_throw_error_if_given_type_is_null() {
    thrown.expect(NullPointerException.class);
    types = null;
    assertions.isInstanceOfAny(types);
  }

  @Test
  public void should_throw_error_if_actual_is_null() {
    actual = null;
    assertions = new ObjectAssert(actual);
    thrown.expect(AssertionError.class);
    assertions.isInstanceOfAny(String.class);
  }
}
