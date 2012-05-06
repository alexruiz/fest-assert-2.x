/*
 * Created on Nov 19, 2010
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

import java.util.Collections;
import static junit.framework.Assert.assertSame;

import static org.fest.assertions.test.TestData.someIndex;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import org.fest.assertions.data.Index;
import org.fest.assertions.internal.Lists;

/**
 * Tests for <code>{@link ListAssert#contains(Object, Index)}</code>.
 *
 * @author Alex Ruiz
 * @author Joel Costigliola
 */
public class ListAssert_contains_at_Index_Test {

  private Lists lists;
  private ListAssert<String> assertions;

  @Before public void setUp() {
    lists = mock(Lists.class);
    assertions = new ListAssert<String>(Collections.<String>emptyList());
    assertions.lists = lists;
  }

  @Test public void should_verify_that_actual_contains_value_at_index() {
    Index index = someIndex();
    assertions.contains("Yoda", index);
    verify(lists).assertContains(assertions.info, assertions.actual, "Yoda", index);
  }

  @Test public void should_return_this() {
    ListAssert<String> returned = assertions.contains("Luke", someIndex());
    assertSame(assertions, returned);
  }
}
