/*
 * Created on Mar 29, 2009
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

import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.fest.test.ExpectedException.none;

/**
 * Tests for {@link ObjectArrayAssert#contains(Object...)}.
 *
 * @author Alex Ruiz
 * @author Mikhail Mazursky
 * @author Yvonne Wang
 */
public class ObjectArrayAssert_contains_Test {
  @Rule
  public ExpectedException thrown = none();
  private Object[] actual = {'a', 'b', 'c'};
  private Object[] values = {'a', 'b'};
  private ObjectArrayAssert assertions;

  @Before
  public void setUp() {
    assertions = new ObjectArrayAssert(actual);
  }

  @Test
  public void should_pass_if_actual_contains_given_values() {
    assertions.contains(values);
  }

  @Test
  public void should_pass_if_actual_contains_given_values_multiple_times() {
    assertions.contains('a', 'b', 'a', 'b');
  }

  @Test
  public void should_pass_if_both_actual_and_given_values_are_empty() {
    assertions = new ObjectArrayAssert(new Object[0]);
    assertions.contains();
  }

  @Test
  public void should_return_this_if_actual_contains_given_value() {
    assertions.contains(values);
  }

  @Test
  public void should_throw_error_if_actual_is_null() {
    thrown.expect(AssertionError.class);
    assertions = new ObjectArrayAssert(null);
    assertions.contains(values);
  }

  @Test
  public void should_throw_error_if_given_values_is_null() {
    thrown.expect(AssertionError.class);
    values = null;
    assertions.contains(values);
  }

  @Test
  public void should_throw_error_if_given_values_is_empty() {
    thrown.expect(AssertionError.class);
    assertions.contains();
  }
}
