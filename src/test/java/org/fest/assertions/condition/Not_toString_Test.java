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
 * Copyright @2011-2013 the original author or authors.
 */
package org.fest.assertions.condition;

import static junit.framework.Assert.assertEquals;

import static org.fest.assertions.condition.Not.not;

import org.fest.assertions.core.Matcher;
import org.fest.assertions.core.TestMatcher;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for {@link Not#toString()}.
 *
 * @author Nicolas François
 * @author Yvonne Wang
 */
public class Not_toString_Test {
  private TestMatcher<Object> condition;
  private Matcher<Object> not;

  @Before
  public void setUp() {
    condition = new TestMatcher<Object>("Jedi");
    not = not(condition);
  }

  @Test
  public void should_implement_toString_showing_descriptions_of_inner_Conditions() {
    String expected = "not :<Jedi>";
    assertEquals(expected, not.toString());
  }
}
