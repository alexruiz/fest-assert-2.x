/*
 * Created on Dec 21, 2010
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
 * Copyright @2010-2011 the original author or authors.
 */
package org.fest.assertions.api;

import static java.util.Collections.emptyMap;
import static org.mockito.Mockito.mock;

import org.fest.assertions.internal.Maps;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Tests for <code>{@link MapAssert#isEmpty()}</code>.
 *
 * @author Alex Ruiz
 * @author Nicolas François
 */
public class MapAssert_isEmpty_Test {

  private Maps maps;
  private MapAssert<Object, Object> assertions;

  @Before public void setUp() {
    maps = mock(Maps.class);
    assertions = new MapAssert<Object, Object>(emptyMap());
    assertions.maps = maps;
  }

  @Test public void should_verify_that_actual_is_empty() {
    assertions.isEmpty();
    Mockito.verify(maps).assertEmpty(assertions.info, assertions.actual);
  }
}
