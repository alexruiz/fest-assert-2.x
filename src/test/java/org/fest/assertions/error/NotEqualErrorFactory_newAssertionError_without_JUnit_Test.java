/*
 * Created on Aug 6, 2010
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
package org.fest.assertions.error;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;

import static org.fest.assertions.error.NotEqualErrorFactory.shouldBeEqual;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.fest.assertions.description.Description;
import org.fest.assertions.internal.TestDescription;
import org.junit.Before;
import org.junit.ComparisonFailure;
import org.junit.Test;

/**
 * Tests for >{@link NotEqualErrorFactory#newAssertionError(Description)}.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class NotEqualErrorFactory_newAssertionError_without_JUnit_Test {
  private Description description;
  private ConstructorInvoker constructorInvoker;
  private NotEqualErrorFactory factory;

  @Before
  public void setUp() {
    description = new TestDescription("Jedi");
    constructorInvoker = mock(ConstructorInvoker.class);
    factory = shouldBeEqual("Luke", "Yoda");
    factory.constructorInvoker = constructorInvoker;
  }

  @Test
  public void should_create_AssertionError_if_created_ComparisonFailure_is_null() throws Exception {
    when(constructorInvoker.newInstance(anyString(), any(Class[].class), any(Object[].class))).thenReturn(null);
    AssertionError actual = factory.newAssertionError(description);
    assertFalse(actual instanceof ComparisonFailure);
    assertEquals("[Jedi] expected:<'Yoda'> but was:<'Luke'>", actual.getMessage());
  }

  @Test
  public void should_create_AssertionError_if_error_is_thrown_when_creating_ComparisonFailure() throws Exception {
    RuntimeException error = new RuntimeException("Thrown on purpose");
    when(constructorInvoker.newInstance(anyString(), any(Class[].class), any(Object[].class))).thenThrow(error);
    AssertionError actual = factory.newAssertionError(description);
    assertFalse(actual instanceof ComparisonFailure);
    assertEquals("[Jedi] expected:<'Yoda'> but was:<'Luke'>", actual.getMessage());
  }
}
