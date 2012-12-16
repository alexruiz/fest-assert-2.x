/*
 * Created on Mar 18, 2012
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
package org.fest.assertions.api;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests for {@link AbstractAssert#equals(Object)} and {@link AbstractAssert#hashCode()}.
 *
 * @author Nicolas François
 * @author Alex Ruiz
 */
public class AbstractAssert_equals_hashCode_Test {
  private ConcreteAssert assertions;

  @Before
  public void setUp() {
    assertions = new ConcreteAssert(6);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void equals_should_fail_when_invoked() {
    assertions.equals(8);
  }

  @Test
  public void hashCode_should_return_1() {
    assertEquals(1, assertions.hashCode());
  }
}