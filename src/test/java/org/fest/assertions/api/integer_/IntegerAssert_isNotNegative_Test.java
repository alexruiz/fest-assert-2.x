/*
 * Created on May 28, 2012
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
 * Copyright @2010-2012 the original author or authors.
 */
package org.fest.assertions.api.integer_;

import static org.mockito.Mockito.verify;

import org.fest.assertions.api.IntegerAssert;
import org.fest.assertions.api.IntegerAssertTest;

/**
 * Tests for <code>{@link IntegerAssert#isNotNegative()}</code>.
 * 
 * @author Nicolas François
 */
public class IntegerAssert_isNotNegative_Test extends IntegerAssertTest {

  @Override
  protected IntegerAssert invoke_api_method() {
    return assertions.isNotNegative();
  }

  @Override
  protected void verify_internal_effects() {
    verify(integers).assertIsNotNegative(getInfo(assertions), getActual(assertions));
  }
}
