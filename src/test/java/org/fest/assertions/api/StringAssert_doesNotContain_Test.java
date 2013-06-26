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

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.fest.assertions.description.Description;
import org.fest.assertions.internal.Strings;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for {@link StringAssert#doesNotContain(String)}.
 *
 * @author Alex Ruiz
 */
public class StringAssert_doesNotContain_Test {
  private Strings strings;
  private StringAssert assertions;

  @Before
  public void setUp() {
    strings = mock(Strings.class);
    assertions = new StringAssert("Yoda", mock(Description.class));
    assertions.strings = strings;
  }

  @Test
  public void should_verify_that_actual_does_not_contain_sequence() {
    assertions.doesNotContain("Luke");
    verify(strings).assertDoesNotContain(assertions.description, assertions.actual, "Luke");
  }

  @Test
  public void should_return_this() {
    StringAssert returned = assertions.doesNotContain("yo");
    assertSame(assertions, returned);
  }
}
