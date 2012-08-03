/*
 * Created on Jun 4, 2012
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
package org.fest.assertions.api.floatarray;

import static org.fest.util.Collections.list;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.fest.assertions.api.FloatArrayAssert;
import org.fest.assertions.api.FloatArrayAssertTest;

/**
 * Tests for <code>{@link FloatArrayAssert#hasSameSizeAs(Iterable)}</code>.
 * 
 * @author Nicolas François
 */
public class FloatArrayAssert_hasSameSizeAs_with_Iterable_Test extends FloatArrayAssertTest {

  private final List<String> other = list("Yoda", "Luke");

  @Override
  protected FloatArrayAssert invoke_api_method() {
    return assertions.hasSameSizeAs(other);
  }

  @Override
  protected void verify_internal_effects() {
    verify(arrays).assertHasSameSizeAs(getInfo(assertions), getActual(assertions), other);
  }
}
