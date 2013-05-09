/*
 * Created on May 2, 2013
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

import static org.fest.assertions.error.ShouldNotBeEmpty.shouldNotBeEmpty;
import static org.fest.assertions.test.FailureMessages.actualIsNull;
import static org.fest.assertions.test.TestFailures.expectedAssertionErrorNotThrown;
import static org.fest.test.ExpectedException.none;
import static org.fest.util.Sets.newLinkedHashSet;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.util.Set;

import org.fest.assertions.description.Description;
import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Yvonne Wang
 */
public class Sets_assertNotEmpty_Test {

  @Rule
  public ExpectedException thrown = none();

  private final Set<?> actual = newLinkedHashSet("Apple", "Peal", "Bananna");
  private Sets sets;
  private Description description;
  private Failures failures;

  @Before
  public void setUp() {
    sets = Sets.instance();
    failures = spy(new Failures());
    sets.failures = failures;
    description = new TestDescription("testing");
  }

  @Test
  public void should_fail_if_actual_is_null() {
    thrown.expect(AssertionError.class, actualIsNull());
    sets.assertNotEmpty(description, null);
  }

  @Test
  public void should_pass_if_actual_is_not_empty() {
    sets.assertNotEmpty(description, actual);
  }

  @Test
  public void should_fail_if_actual_is_empty() {
    try {
      sets.assertNotEmpty(description, newLinkedHashSet());
    } catch (AssertionError e) {
      verify(failures).failure(description, shouldNotBeEmpty());
      return;
    }
    expectedAssertionErrorNotThrown();
  }
}
