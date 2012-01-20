/*
 * Created on Oct 9, 2010
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

import static java.util.Collections.emptyList;
import static junit.framework.Assert.assertSame;
import static org.mockito.Mockito.*;

import org.fest.assertions.internal.Iterables;
import org.junit.*;

/**
 * Tests for <code>{@link AbstractIterableAssert#doesNotContain(Object...)}</code>.
 *
 * @author Alex Ruiz
 */
public class AbstractCollectionAssert_doesNotContain_Test {

  private Iterables collections;
  private ConcreteCollectionAssert assertions;

  @Before public void setUp() {
    collections = mock(Iterables.class);
    assertions = new ConcreteCollectionAssert(emptyList());
    assertions.iterables = collections;
  }

  @Test public void should_verify_that_actual_does_not_contain_given_values() {
    Object[] values = { "Yoda", "Luke" };
    assertions.doesNotContain(values);
    verify(collections).assertDoesNotContain(assertions.info, assertions.actual, values);
  }

  @Test public void should_return_this() {
    ConcreteCollectionAssert returned = assertions.doesNotContain("Luke");
    assertSame(assertions, returned);
  }
}
