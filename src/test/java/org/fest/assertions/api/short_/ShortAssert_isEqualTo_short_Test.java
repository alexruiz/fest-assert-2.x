/*
 * Created on Oct 20, 2010
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
package org.fest.assertions.api.short_;

import static org.mockito.Mockito.verify;

import org.fest.assertions.api.ShortAssert;
import org.fest.assertions.api.ShortAssertTest;

/**
 * Tests for <code>{@link ShortAssert#isEqualTo(short)}</code>.
 * 
 * @author Alex Ruiz
 */
public class ShortAssert_isEqualTo_short_Test extends ShortAssertTest {

  @Override
  protected ShortAssert invoke_api_method() {
    return assertions.isEqualTo((short) 8);
  }

  @Override
  protected void verify_internal_effects() {
    verify(shorts).assertEqual(getInfo(assertions), getActual(assertions), (short) 8);
  }
}
