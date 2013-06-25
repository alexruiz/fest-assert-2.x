/*
 * Created on Apr 29, 2013
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
package org.fest.assertions.internal;

import static java.util.Collections.emptyList;

import static org.fest.assertions.error.ShouldNotHaveDuplicates.shouldNotHaveDuplicates;
import static org.fest.assertions.test.FailureMessages.actualIsNull;
import static org.fest.assertions.test.TestFailures.expectedAssertionErrorNotThrown;
import static org.fest.test.ExpectedException.none;
import static org.fest.util.Lists.newArrayList;
import static org.fest.util.Sets.newLinkedHashSet;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.util.Collection;
import java.util.List;

import org.fest.assertions.description.Description;
import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for {@link Lists#assertDoesNotHaveDuplicates(Description, List)}.
 *
 * @author Yvonne Wang
 */
public class Lists_assertDoesNotHaveDuplicates_Test {
  @Rule
  public ExpectedException thrown = none();
  private List<String> actual;
  private Failures failures;
  private Lists lists;
  private Description description;

  @Before
  public void setUp() {
    actual = newArrayList("Luke", "Yoda", "Leia");
    failures = spy(new Failures());
    lists = Lists.instance();
    lists.failures = failures;
    description = new TestDescription("testing");
  }

  @Test
  public void should_pass_if_actual_does_not_have_duplicates() {
    lists.assertDoesNotHaveDuplicates(description, actual);
  }

  @Test
  public void should_pass_if_actual_is_empty() {
    lists.assertDoesNotHaveDuplicates(description, emptyList());
  }

  @Test
  public void should_fail_if_actual_is_null() {
    thrown.expect(AssertionError.class, actualIsNull());
    lists.assertDoesNotHaveDuplicates(description, null);
  }

  @Test
  public void should_fail_if_actual_contains_duplicates() {
    Collection<String> duplicates = newLinkedHashSet("Luke", "Yoda");
    actual.addAll(duplicates);
    try {
      lists.assertDoesNotHaveDuplicates(description, actual);
    } catch (AssertionError e) {
      verify(failures).failure(description, shouldNotHaveDuplicates(actual, duplicates));
      return;
    }
    throw expectedAssertionErrorNotThrown();
  }
}
