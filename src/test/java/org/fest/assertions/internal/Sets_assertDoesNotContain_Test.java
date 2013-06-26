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

import static org.fest.assertions.error.ShouldNotContain.shouldNotContain;
import static org.fest.assertions.test.ErrorMessages.valuesToLookForIsEmpty;
import static org.fest.assertions.test.ErrorMessages.valuesToLookForIsNull;
import static org.fest.assertions.test.TestFailures.expectedAssertionErrorNotThrown;
import static org.fest.test.ExpectedException.none;
import static org.fest.util.Arrays.array;
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
 * @author Alex
 * @author Alex
 */
public class Sets_assertDoesNotContain_Test {

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
    thrown.expect(AssertionError.class);
    sets.assertDoesNotContain(description, null, array("value"));
  }

  @Test
  public void should_fail_if_actual_is_empty() {
    thrown.expect(AssertionError.class);
    sets.assertDoesNotContain(description, newLinkedHashSet(), array("value"));
  }

  @Test
  public void should_fail_if_given_values_is_null() {
    thrown.expect(NullPointerException.class, valuesToLookForIsNull());
    sets.assertDoesNotContain(description, actual, null);
  }

  @Test
  public void should_fail_if_given_values_is_empty() {
    thrown.expect(IllegalArgumentException.class, valuesToLookForIsEmpty());
    sets.assertDoesNotContain(description, actual, new Object[0]);
  }

  @Test
  public void should_fail_if_actual_contains_given_values() {
    String[] expected = array("Apple", "Peal");
    try {
      sets.assertDoesNotContain(description, actual, expected);
    } catch(AssertionError e) {
      verify(failures).failure(description, shouldNotContain(actual, expected, newLinkedHashSet("Apple", "Peal")));
      return;
    }
    expectedAssertionErrorNotThrown();
  }

  @Test
  public void should_pass_if_actual_does_not_contain_given_values() {
    sets.assertDoesNotContain(description, actual, array("Peach", "Berry"));
  }
}
