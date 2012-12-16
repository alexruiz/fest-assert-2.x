/*
 * Created on Oct 22, 2010
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

import org.fest.assertions.description.Description;
import org.fest.assertions.internal.Objects;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for {@link BooleanAssert#isFalse()}.
 *
 * @author Alex Ruiz
 */
public class BooleanAssert_isFalse_Test {
  private Objects objects;
  private BooleanAssert assertions;

  @Before
  public void setUp() {
    objects = mock(Objects.class);
    assertions = new BooleanAssert(true, mock(Description.class));
    assertions.objects = objects;
  }

  @Test
  public void should_verify_that_actual_is_false() {
    assertions.isFalse();
    verify(objects).assertEqual(assertions.description, assertions.actual, false);
  }

  @Test
  public void should_return_this() {
    BooleanAssert returned = assertions.isFalse();
    assertSame(assertions, returned);
  }
}
