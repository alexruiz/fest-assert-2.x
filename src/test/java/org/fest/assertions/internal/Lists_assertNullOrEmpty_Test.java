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

import static org.fest.assertions.error.ShouldBeNullOrEmpty.shouldBeNullOrEmpty;
import static org.fest.assertions.test.TestFailures.expectedAssertionErrorNotThrown;
import static org.fest.util.Lists.list;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.fest.assertions.description.Description;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Yvonne Wang
 */
public class Lists_assertNullOrEmpty_Test {

  private Failures failures;
  private Lists lists;
  private Description description;

  @Before
  public void setUp() {
    failures = spy(new Failures());
    lists = Lists.instance();
    lists.failures = failures;
    description = new TestDescription("testing");
  }

  @Test
  public void should_pass_if_actual_is_null() {
    lists.assertNullOrEmpty(description, null);
  }

  @Test
  public void should_pass_if_actual_is_empty() {
    lists.assertNullOrEmpty(description, emptyList());
  }

  @Test
  public void should_fail_if_actual_has_elements() {
    List<String> actual = list("Yoda");
    try {
      lists.assertNullOrEmpty(description, actual);
    } catch (AssertionError e) {
      verify(failures).failure(description, shouldBeNullOrEmpty(actual));
      return;
    }
    expectedAssertionErrorNotThrown();
  }

}
