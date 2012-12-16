/*
 * Created on Dec 24, 2010
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
 * Copyright @2010-2012 the original author or authors.
 */
package org.fest.assertions.internal;

import static org.fest.assertions.test.CharArrays.newArray;
import static org.mockito.Mockito.mock;

import org.fest.assertions.description.Description;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for {@link Strings#assertEqualsIgnoringCase(Description, String, String)}.
 *
 * @author Alex Ruiz
 */
public class Strings_assertEqualsIgnoringCase_Test {
  private Strings strings;

  @Before
  public void setUp() {
    strings = new Strings();
  }

  @Test
  public void should_pass_if_both_Strings_are_null() {
    strings.assertEqualsIgnoringCase(mock(Description.class), null, null);
  }

  @Test
  public void should_pass_if_both_Strings_are_the_same() {
    String s = "Yoda";
    strings.assertEqualsIgnoringCase(mock(Description.class), s, s);
  }

  @Test
  public void should_pass_if_both_Strings_are_equal_but_not_same() {
    strings.assertEqualsIgnoringCase(mock(Description.class), "Yoda", new String(newArray('Y', 'o', 'd', 'a')));
  }

  @Test
  public void should_pass_if_both_Strings_are_equal_ignoring_case() {
    strings.assertEqualsIgnoringCase(mock(Description.class), "Yoda", "YODA");
  }
}
