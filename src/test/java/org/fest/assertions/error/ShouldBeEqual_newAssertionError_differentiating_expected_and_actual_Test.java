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
 * Copyright @2010-2013 the original author or authors.
 */
package org.fest.assertions.error;

import static org.fest.assertions.error.NotEqualErrorFactory.shouldBeEqual;
import static org.fest.util.Strings.concat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.fest.assertions.description.Description;
import org.fest.assertions.internal.TestDescription;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for {@link NotEqualErrorFactory#newAssertionError(Description)}.
 *
 * @author Joel Costigliola (based on Tomasz Nurkiewicz ideas)
 * @author Yvonne Wang
 */
public class ShouldBeEqual_newAssertionError_differentiating_expected_and_actual_Test {
  private final String formattedDescription = "[my test]";
  private Description description;
  private NotEqualErrorFactory shouldBeEqual;

  @Before
  public void setUp() {
    description = new TestDescription("my test");
  }

  @Test
  public void should_create_AssertionError_with_message_differentiating_expected_double_and_actual_float() {
    Float actual = 42f;
    Double expected = 42d;
    shouldBeEqual = shouldBeEqual(actual, expected);
    shouldBeEqual.descriptionFormatter = mock(DescriptionFormatter.class);
    when(shouldBeEqual.descriptionFormatter.format(description)).thenReturn(formattedDescription);
    AssertionError error = shouldBeEqual.newAssertionError(description);
    assertEquals("[my test] expected:<42.0[]> but was:<42.0[f]>", error.getMessage());
  }

  /*@Test
  public void should_create_AssertionError_with_message_differentiating_expected_and_actual_persons() {
    Person actual = new Person("Jake", 43);
    Person expected = new Person("Jake", 47);
    shouldBeEqual = shouldBeEqual(actual, expected);
    shouldBeEqual.descriptionFormatter = mock(DescriptionFormatter.class);
    when(shouldBeEqual.descriptionFormatter.format(description)).thenReturn(formattedDescription);
    AssertionError error = shouldBeEqual.newAssertionError(description);
    assertEquals("[my test] expected:<Person[name=Jake] (" + convertHashCode(expected.hashCode())
        + ")> but was:<Person[name=Jake] (" + convertHashCode(actual.hashCode()) + ")>", error.getMessage());
  }*/

  private String convertHashCode(int code) {
    String codeInString = "" + code;
    String formatted = null;
    if(codeInString.length() >= 0) {
      formatted = "@" + "[" + codeInString + "]";//"@" + codeInString.substring(0, 1) + "[" + codeInString.substring(1) + "]";
      return formatted;
    }
    return codeInString;
  }

  private static class Person {
    private final String name;

    public Person(String name, int age) {
      this.name = name;
    }

    @Override
    public String toString() {
      return concat("Person[name=", name, "]");
    }
  }
}
