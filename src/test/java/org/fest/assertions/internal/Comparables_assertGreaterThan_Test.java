/*
 * Created on Oct 19, 2010
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

import static org.fest.assertions.test.FailureMessages.actualIsNull;
import static org.fest.test.ExpectedException.none;
import static org.mockito.Mockito.mock;

import org.fest.assertions.description.Description;
import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for {@link Comparables#assertGreaterThan(Description, Comparable, Comparable)}.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class Comparables_assertGreaterThan_Test {
  @Rule
  public ExpectedException thrown = none();

  private Comparables comparables;

  @Before
  public void setUp() {
    comparables = new Comparables();
  }

  @Test
  public void should_fail_if_actual_is_null() {
    thrown.expectAssertionError(actualIsNull());
    comparables.assertGreaterThan(mock(Description.class), null, 8);
  }

  @Test
  public void should_pass_if_actual_is_greater_than_other() {
    comparables.assertGreaterThan(mock(Description.class), 8, 6);
  }
}
