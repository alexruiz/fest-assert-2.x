/*
 * Created on Oct 20, 2010
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
 * Copyright @2010-2013 the original author or authors.
 */
package org.fest.assertions.api;

import org.fest.assertions.description.Description;
import org.fest.assertions.internal.TestDescription;
import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for {@link AbstractAssert#isNull()}.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class AbstractAssert_isNull_Test {
  @Rule public ExpectedException thrown = ExpectedException.none();
  private final Description description = new TestDescription("testing");
  private ConcreteAssert assertions;

  @Before
  public void setUp() {
    assertions = new ConcreteAssert(null, description);
  }

  @Test
  public void should_pass_that_actual_value_is_null() {
    assertions.isNull();
  }

  @Test
  public void should_fail_that_actual_value_is_not_null() {
    assertions = new ConcreteAssert("one", description);
    thrown.expect(AssertionError.class);
    assertions.isNull();
  }
}
