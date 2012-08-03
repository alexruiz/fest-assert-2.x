/*
 * Created on Sep 17, 2010
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 * 
 * Copyright @2010-2011 the original author or authors.
 */
package org.fest.assertions.api.iterable;

import static org.mockito.Mockito.verify;

import org.fest.assertions.api.AbstractIterableAssert;
import org.fest.assertions.api.ConcreteIterableAssert;
import org.fest.assertions.api.IterableAssertTest;
import org.junit.Test;

/**
 * Tests for <code>{@link AbstractIterableAssert#isNullOrEmpty()}</code>.
 * 
 * @author Alex Ruiz
 * @author Yvonne Wang
 * @author Joel Costigliola
 */
public class IterableAssert_isNullOrEmpty_Test extends IterableAssertTest {

  @Test
  public void should_verify_actual_is_null_or_empty() {}

  @Override
  protected ConcreteIterableAssert<Object> invoke_api_method() {
    assertions.isNullOrEmpty();
    return null;
  }

  @Override
  protected void verify_internal_effects() {
    verify(iterables).assertNullOrEmpty(getInfo(assertions), getActual(assertions));
  }

  @Override
  @Test
  public void should_return_this() {
    // Disable this test because isNullOrEmpty is void
  }
}
