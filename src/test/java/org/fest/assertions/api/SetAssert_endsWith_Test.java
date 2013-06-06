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

package org.fest.assertions.api;

import org.fest.test.ExpectedException;
import org.fest.util.Sets;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.Collection;
import java.util.Set;

import static org.fest.test.ExpectedException.none;

/**
 * Tests for {@link SetAssert#endsWith(Collection[])}.
 *
 * @author Yvonne Wang
 */
public class SetAssert_endsWith_Test {
  @Rule
  public ExpectedException thrown = none();
  private Set<String> actual = Sets.newLinkedHashSet("one", "two", "three", "four");
  private Set<String> sequence = Sets.newLinkedHashSet("three", "four");
  private SetAssert assertions;

  @Before
  public void setUp() {
    assertions = new SetAssert(actual);
  }

  @Test
  public void should_throw_error_if_actual_is_null() {
    thrown.expect(AssertionError.class);
    assertions = new SetAssert(null);
    assertions.endsWith();
  }

  @Test
  public void should_throw_error_if_sequence_is_null() {
    sequence = null;
    thrown.expect(AssertionError.class);
    assertions.endsWith(sequence);
  }

  @Test
  public void should_throw_error_if_sequence_is_empty() {
    sequence = Sets.newHashSet();
    thrown.expect(AssertionError.class);
    assertions.endsWith(sequence);
  }

  @Test
  public void should_throw_error_if_parameter_sequence_is_missing() {
    thrown.expect(IllegalArgumentException.class);
    assertions.endsWith();
  }

  @Test
  public void should_fail_if_actual_does_not_end_with_sequence() {
    sequence.add("five");
    thrown.expect(AssertionError.class);
    assertions.endsWith(sequence);
  }

  @Test
  public void should_fail_if_sequence_size_is_larger_than_actual_size() {
    sequence.addAll(actual);
    thrown.expect(AssertionError.class);
    assertions.endsWith(sequence);
  }

  @Test
  public void should_pass_if_actual_ends_with_sequence() {
    assertions.endsWith(sequence);
  }

  @Test
  public void should_pass_if_actual_ends_with_itself() {
    assertions.endsWith(actual);
  }
}
