/*
 * Created on Oct 20, 2010
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

import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for {@link AbstractObjectAssert#isSameAs(Object)}.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class AbstractObjectAssert_isSameAs_Test {
  @Rule
  public ExpectedException thrown = ExpectedException.none();
  private final Object actual = "Yoda";
  private final Object value = "Yoda";
  private ConcreteObjectAssert assertions;

  @Before
  public void setUp() {
    assertions = new ConcreteObjectAssert(actual);
  }

  @Test
  public void should_pass_if_actual_is_same_as_given_value() {
    assertions.isSameAs(value);
  }

  @Test
  public void should_pass_if_both_actual_and_given_value_are_null() {
    assertions = new ConcreteObjectAssert(null);
    assertions.isSameAs(null);
  }

  @Test
  public void should_fail_if_actual_is_not_same_as_given_value() {
    thrown.expect(AssertionError.class);
    assertions.isSameAs("R2-D2");
  }

  @Test
  public void should_fail_if_actual_is_null_and_given_value_is_not() {
    thrown.expect(AssertionError.class);
    assertions = new ConcreteObjectAssert(null);
    assertions.isSameAs(value);
  }

  @Test
  public void should_fail_if_given_value_is_null_but_actual_is_not_null() {
    thrown.expect(AssertionError.class);
    assertions.isSameAs(null);
  }
}