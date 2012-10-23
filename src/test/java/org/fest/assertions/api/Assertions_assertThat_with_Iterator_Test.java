/*
 * Created on Oct 18, 2010
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
 * Copyright @2010-2011 the original author or authors.
 */
package org.fest.assertions.api;

import static java.util.Arrays.asList;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.util.Sets.newLinkedHashSet;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItems;

import java.util.Iterator;

import org.junit.Test;

/**
 * Tests for <code>{@link Assertions#assertThat(Iterator)}</code>.
 * 
 * @author Julien Meddah
 * @author Joel Costigliola
 */
public class Assertions_assertThat_with_Iterator_Test {

  @Test
  public void should_create_Assert() {
    IterableAssert<Object> assertions = Assertions.assertThat(newLinkedHashSet());
    assertNotNull(assertions);
  }

  @Test
  public void should_initialise_actual() {
    Iterator<String> names = asList("Luke", "Leia").iterator();
    IterableAssert<String> assertions = assertThat(names);
    assertThat(assertions.actual, hasItems("Leia", "Luke"));
  }
}
