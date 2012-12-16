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
 * Copyright @2010-2012 the original author or authors.
 */
package org.fest.assertions.api;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.fest.assertions.core.Matcher;
import org.fest.assertions.core.TestMatcher;
import org.fest.assertions.internal.Matchers;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for {@link AbstractAssert#isNot(Matcher)}.
 *
 * @author Alex Ruiz
 */
public class AbstractAssert_isNot_Test {
  private Matcher<Object> matcher;
  private Matchers matchers;
  private ConcreteAssert assertions;

  @Before
  public void setUp() {
    matchers = mock(Matchers.class);
    matcher = new TestMatcher<Object>();
    assertions = new ConcreteAssert(8L);
    assertions.matchers = matchers;
  }

  @Test
  public void should_verify_that_actual_does_not_satisfy_Matcher() {
    assertions.isNot(matcher);
    verify(matchers).assertIsNot(assertions.description, assertions.actual, matcher);
  }

  @Test
  public void should_return_this() {
    ConcreteAssert returned = assertions.isNot(matcher);
    assertSame(assertions, returned);
  }
}
