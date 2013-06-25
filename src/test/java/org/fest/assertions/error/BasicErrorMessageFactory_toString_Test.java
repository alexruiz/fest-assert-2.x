/*
 * Created on Jan 15, 2011
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
 * Copyright @2011-2013 the original author or authors.
 */
package org.fest.assertions.error;

import static junit.framework.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests for {@link BasicErrorMessageFactory#toString()}.
 *
 * @author Yvonne Wang
 */
public class BasicErrorMessageFactory_toString_Test {
  private BasicErrorMessageFactory factory;

  @Before
  public void setUp() {
    factory = new BasicErrorMessageFactory("Hello %s", "Yoda");
  }

  @Test
  public void should_implement_toString() {
    assertEquals("BasicErrorMessageFactory[format='Hello %s', arguments=['Yoda']]", factory.toString());
  }
}
