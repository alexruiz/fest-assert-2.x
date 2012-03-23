/*
 * Created on Mar 22, 2012
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
 * Copyright @2011 the original author or authors.
 */
package org.fest.assertions.condition;

import static junit.framework.Assert.assertEquals;

import org.fest.assertions.core.Condition;
import org.fest.assertions.core.TestCondition;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for <code>{@link HasNot#toString()}</code>.
 *
 * @author Nicolas François
 */
public class HasNot_toString_Test {

  private TestCondition<Object> condition;
  private Condition<Object> hasNot;

  @Before public void setUp() {
    condition = new TestCondition<Object>("Jedi");
    hasNot = HasNot. hasNot(condition);
  }

  @Test public void should_implement_toString_showing_descriptions_of_inner_Conditions() {
    String expected = "has not :<Jedi>";
    assertEquals(expected, hasNot.toString());
  }
	
}
