/*
 * Created on Dec 21, 2010
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
package org.fest.assertions.api.shortarray;

import static org.fest.assertions.test.ShortArrayFactory.array;
import static org.mockito.Mockito.verify;

import org.fest.assertions.api.ShortArrayAssert;
import org.fest.assertions.api.ShortArrayAssertTest;

/**
 * Tests for <code>{@link ShortArrayAssert#startsWith(short...)}</code>.
 * 
 * @author Alex Ruiz
 */
public class ShortArrayAssert_startsWith_Test extends ShortArrayAssertTest {

  @Override
  protected ShortArrayAssert invoke_api_method() {
    return assertions.startsWith((short) 6, (short) 8);
  }

  @Override
  protected void verify_internal_effects() {
    verify(arrays).assertStartsWith(getInfo(assertions), getActual(assertions), array(6, 8));
  }
}
