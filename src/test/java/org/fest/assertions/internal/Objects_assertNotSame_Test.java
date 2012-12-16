/*
 * Created on Sep 17, 2010
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

import static org.fest.assertions.test.TestFailures.expectedAssertionErrorNotThrown;
import static org.fest.util.Strings.quote;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.fest.assertions.description.Description;
import org.fest.assertions.error.ErrorMessageFactory;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for {@link Objects#assertNotSame(Description, Object, Object)}.
 *
 * @author Alex Ruiz
 */
public class Objects_assertNotSame_Test {
  private Failures failures;
  private Objects objects;

  @Before
  public void setUp() {
    failures = spy(new Failures());
    objects = new Objects();
    objects.failures = failures;
  }

  @Test public void should_pass_if_objects_are_not_same() {
    objects.assertNotSame(mock(Description.class), "Yoda", "Luke");
  }

  @Test public void should_fail_if_objects_are_same() {
    Description description = new TestDescription("Testing");
    Object actual = new Person("Yoda");
    try {
      objects.assertNotSame(description, actual, actual);
    } catch (AssertionError e) {
      assertEquals("[Testing] expected not same:<Person[name='Yoda']>", e.getMessage());
      verify(failures).failure(same(description), any(ErrorMessageFactory.class));
      return;
    }
    throw expectedAssertionErrorNotThrown();
  }

  private static class Person {
    private final String name;

    Person(String name) {
      this.name = name;
    }

    @Override
    public String toString() {
      String format = "Person[name=%s]";
      return String.format(format, quote(name));
    }
  }
}
