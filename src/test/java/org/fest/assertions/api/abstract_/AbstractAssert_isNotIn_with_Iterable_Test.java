/*
 * Created on Feb 5, 2011
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
 * Copyright @2011 the original author or authors.
 */
package org.fest.assertions.api.abstract_;

import static org.fest.util.Collections.list;
import static org.mockito.Mockito.verify;

import org.fest.assertions.api.AbstractAssert;
import org.fest.assertions.api.AbstractAssertTest;
import org.fest.assertions.api.ConcreteAssert;
import org.junit.BeforeClass;

/**
 * Tests for <code>{@link AbstractAssert#isNotIn(Iterable)}</code>.
 * 
 * @author Yvonne Wang
 * @author Joel Costigliola
 * @author Nicolas François
 */
public class AbstractAssert_isNotIn_with_Iterable_Test extends AbstractAssertTest {

  private static Iterable<?> values;

  @BeforeClass
  public static void setUpOnce() {
    values = list("Yoda", "Luke");
  }

  @Override
  protected ConcreteAssert invoke_api_method() {
    return assertions.isNotIn(values);
  }

  @Override
  protected void verify_internal_effects() {
    verify(objects).assertIsNotIn(getInfo(assertions), getActual(assertions), values);
  }
}
