/*
 * Created on Jun 11, 2013
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
package org.fest.assertions.api;

import static junit.framework.Assert.assertSame;

import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for {@link ObjectAssert#isNotInstanceOfAny(Class...)}.
 *
 * @author Yvonne Wang
 */
public class ObjectAssert_isNotInstanceOfAny_Test {
  @Rule
  public ExpectedException thrown = ExpectedException.none();
  private Object actual = true;
  private Class<?>[] types = { String.class, Integer.class };
  private ObjectAssert assertions;

  @Before
  public void setUp() {
    assertions = new ObjectAssert(actual);
  }

  @Test
  public void should_pass_if_actual_is_not_instance_of_any_type() {
    assertions.isNotInstanceOfAny(types);
  }

  @Test
  public void should_return_this() {
    ObjectAssert returned = assertions.isNotInstanceOfAny(types);
    assertSame(assertions, returned);
  }

  @Test
  public void should_throw_error_if_given_type_is_null() {
    thrown.expect(NullPointerException.class);
    types = null;
    assertions.isNotInstanceOfAny(types);
  }

  @Test
  public void should_pass_if_actual_is_null() {
    actual = null;
    assertions = new ObjectAssert(actual);
    assertions.isNotInstanceOfAny(types);
  }

  @Test
  public void should_fail_if_actual_is_instance_of_any_given_types() {
    thrown.expect(AssertionError.class);
    assertions.isNotInstanceOfAny(Boolean.class);
  }
}
