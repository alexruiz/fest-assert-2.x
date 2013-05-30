/*
 * Created on May 9, 2013
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
package org.fest.assertions.api;

import static org.fest.util.Sets.newLinkedHashSet;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.LinkedHashSet;

import org.junit.Test;

/**
 * @author Yvonne Wang
 */
public class Assertions_assertThat_with_Sets_Test {

  @Test
  public void should_create_asserts() {
    SetAssert asserts = Assertions.assertThat(newLinkedHashSet());
    assertNotNull(asserts);
  }

  @Test
  public void should_pass_actual() {
    LinkedHashSet<String> sets = newLinkedHashSet("first", "second");
    SetAssert asserts = Assertions.assertThat(sets);
    assertEquals(sets, asserts.actual);
  }
}
