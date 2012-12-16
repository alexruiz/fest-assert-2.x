/*
 * Created on Jul 15, 2010
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
package org.fest.assertions.core;

import static org.junit.Assert.assertEquals;

import org.fest.assertions.description.TextDescription;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for <code>{@link Matcher#description()}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class Matcher_description_Test {
  private Matcher<Object> matcher;

  @Before
  public void setUp() {
    matcher = new TestMatcher<Object>();
  }

  @Test
  public void should_return_description() {
    matcher.description = new TextDescription("Testing");
    assertEquals("Testing", matcher.description().value());
  }
}
