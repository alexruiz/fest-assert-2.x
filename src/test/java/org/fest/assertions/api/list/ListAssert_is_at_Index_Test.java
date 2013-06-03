/*
 * Created on Mar 29, 2009
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
 * Copyright @2013 the original author or authors.
 */

package org.fest.assertions.api.list;

import static org.fest.assertions.test.TestData.someIndex;

import static org.mockito.Mockito.verify;

import org.junit.BeforeClass;

import org.fest.assertions.api.ListAssert;
import org.fest.assertions.api.ListAssertBaseTest;
import org.fest.assertions.core.Matcher;
import org.fest.assertions.core.TestMatcher;
import org.fest.assertions.data.Index;

/**
 * Tests for <code>{@link org.fest.assertions.api.ListAssert#is(org.fest.assertions.core.Matcher, org.fest.assertions.data.Index)}</code>.
 * 
 * @author Bo Gotthardt
 */
public class ListAssert_is_at_Index_Test extends ListAssertBaseTest {

  private static Matcher<Object> condition;
  private static Index index;

  @BeforeClass
  public static void setUpOnce() {
    condition = new TestMatcher<Object>();
    index = someIndex();
  }

  @Override
  protected ListAssert<String> invoke_api_method() {
    return assertions.is(condition, index);
  }

  @Override
  protected void verify_internal_effects() {
    verify(lists).assertIs(getInfo(assertions), getActual(assertions), condition, index);
  }
}
