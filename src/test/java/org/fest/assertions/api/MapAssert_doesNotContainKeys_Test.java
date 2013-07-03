/*
 * Created on Jul 03, 2013
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

import org.fest.test.ExpectedException;
import org.fest.util.Maps;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.Map;

/**
 * Tests for {@link org.fest.assertions.api.MapAssert#doesNotContainKeys(Object[])}.
 *
 * @author dorzey
 */
public class MapAssert_doesNotContainKeys_Test {
  @Rule
  public ExpectedException thrown = ExpectedException.none();
  private Map<String, String> actual = Maps.newHashMap();
  private String aKey = "ten";
  private String anotherKey = "eleven";
  private MapAssert assertions;

  @Before
  public void setUp() {
    actual.put("one", "apple");
    actual.put("two", "pear");
    assertions = new MapAssert(actual);
  }

  @Test
  public void should_pass_if_actual_does_not_contain_given_keys() {
    assertions.doesNotContainKeys(aKey, anotherKey);
  }

  @Test
  public void should_throw_error_if_actual_is_null() {
    thrown.expect(AssertionError.class);
    assertions = new MapAssert(null);
    assertions.doesNotContainKeys(aKey);
  }

  @Test
  public void should_pass_if_keys_are_null_and_actual_does_not_have_null_key() {
    aKey = null;
    anotherKey = null;
    assertions.doesNotContainKeys(aKey, anotherKey);
  }

  @Test
  public void should_pass_if_actual_is_empty() {
    actual = Maps.newHashMap();
    assertions = new MapAssert(actual);
    assertions.doesNotContainKeys(aKey, anotherKey);
  }

  @Test
  public void should_fail_if_actual_contains_given_key() {
    thrown.expect(AssertionError.class);
    assertions.doesNotContainKeys("one");
  }

  @Test
  public void should_fail_if_actual_contains_given_keys() {
    thrown.expect(AssertionError.class);
    assertions.doesNotContainKeys("one", "two");
  }

  @Test
  public void should_fail_if_actual_contains_one_of_the_given_keys() {
    thrown.expect(AssertionError.class);
    assertions.doesNotContainKeys("eleven", "one");
  }
}
