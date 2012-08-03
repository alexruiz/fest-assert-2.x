/*
 * Created on Oct 24, 2010
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
package org.fest.assertions.api.float_;

import static org.fest.assertions.data.Offset.offset;
import static org.mockito.Mockito.verify;

import org.fest.assertions.api.FloatAssert;
import org.fest.assertions.api.FloatAssertTest;
import org.fest.assertions.data.Offset;

/**
 * Tests for <code>{@link FloatAssert#isEqualTo(Float, Offset)}</code>.
 * 
 * @author Alex Ruiz
 */
public class FloatAssert_isEqualTo_with_offset_Test extends FloatAssertTest {

  private final Offset<Float> offset = offset(5f);
  private final Float expected = new Float(8f);

  @Override
  protected FloatAssert invoke_api_method() {
    return assertions.isEqualTo(expected, offset);
  }

  @Override
  protected void verify_internal_effects() {
    verify(floats).assertEqual(getInfo(assertions), getActual(assertions), expected, offset);
  }
}
