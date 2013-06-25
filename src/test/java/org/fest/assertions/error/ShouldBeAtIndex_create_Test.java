/*
 * Created on Feb 8, 2011
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
package org.fest.assertions.error;

import static org.fest.assertions.data.Index.atIndex;
import static org.fest.assertions.error.ShouldBeAtIndex.shouldBeAtIndex;
import static org.fest.util.Lists.newArrayList;
import static org.junit.Assert.assertEquals;

import org.fest.assertions.core.TestMatcher;
import org.fest.assertions.description.TextDescription;
import org.junit.Test;

/**
 * Tests for {@link ShouldBeAtIndex#create(org.fest.assertions.description.Description)}.
 *
 * @author Bo Gotthardt
 * @author Yvonne Wang
 */
public class ShouldBeAtIndex_create_Test {

  @Test
  public void should_create_error_message() {
    ErrorMessageFactory factory = shouldBeAtIndex(newArrayList("Yoda", "Luke"), new TestMatcher<String>(
        "red lightsaber"), atIndex(1), "Luke");
    String message = factory.create(new TextDescription("Test"));
    assertEquals("[Test] expecting:\n<'Luke'> at index <1> to be:<red lightsaber> in:\n <['Yoda', 'Luke']>\n", message);
  }
}
