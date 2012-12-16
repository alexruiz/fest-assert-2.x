/*
 * Created on Aug 6, 2010
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
package org.fest.assertions.error;

import static junit.framework.Assert.assertEquals;
import static org.fest.assertions.error.NotEqualErrorFactory.shouldBeEqual;
import static org.fest.util.Strings.concat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.fest.assertions.description.Description;
import org.fest.assertions.internal.TestDescription;
import org.junit.Before;
import org.junit.ComparisonFailure;
import org.junit.Test;

/**
 * Tests for {@link NotEqualErrorFactory#newAssertionError(Description)}.
 *
 * @author Alex Ruiz
 * @author Joel Costigliola
 */
public class NotEqualErrorFactory_newAssertionError_Test {
  private Description description;
  private DescriptionFormatter formatter;

  @Before
  public void setUp() {
    description = new TestDescription("Jedi");
    formatter = mock(DescriptionFormatter.class);
  }

  @Test
  public void should_create_ComparisonFailure_if_JUnit4_is_present() {
    NotEqualErrorFactory factory = shouldBeEqual("Luke", "Yoda");
    factory.descriptionFormatter = formatter;
    when(formatter.format(description)).thenReturn("[Jedi]  ");
    AssertionError error = factory.newAssertionError(description);
    assertEquals(ComparisonFailure.class, error.getClass());
    assertEquals("[Jedi] expected:<'[Yoda]'> but was:<'[Luke]'>", error.getMessage());
  }

  @Test
  public void should_create_AssertionError_with_message_showing_difference_between_float_and_double() {
    NotEqualErrorFactory factory = shouldBeEqual(42f, 42d);
    factory.descriptionFormatter = formatter;
    when(formatter.format(description)).thenReturn("[Jedi]");
    AssertionError error = factory.newAssertionError(description);
    assertEquals("[Jedi] expected:<42.0[]> but was:<42.0[f]>", error.getMessage());
  }

  @Test
  public void should_create_AssertionError_with_message_showing_hashCode_of_values_when_toString_are_equal() {
    NotEqualErrorFactory factory = shouldBeEqual(new Person("Yoda"), new Person("Yoda"));
    factory.descriptionFormatter = formatter;
    when(formatter.format(description)).thenReturn("[Jedi]");
    AssertionError error = factory.newAssertionError(description);
    assertEquals("[Jedi] expected:<42.0[]> but was:<42.0[f]>", error.getMessage());
    assertEquals("[Jedi] expected:<[Person[name=Yoda] (@[49097994]4)> but was:<Person[name=Yoda] (@[126774703]4)]>>", error.getMessage());
  }

  private static class Person {
    private final String name;

    public Person(String name) {
      this.name = name;
    }

    @Override
    public String toString() {
      return concat("Person[name=", name, "]");
    }
  }
}
