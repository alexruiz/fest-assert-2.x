/*
 * Created on Jan 28, 2011
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
 * Copyright @2011 the original author or authors.
 */
package org.fest.assertions.error;

import static junit.framework.Assert.assertEquals;
import static org.fest.util.Collections.list;
import static org.fest.util.Systems.LINE_SEPARATOR;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.fest.assertions.description.*;
import org.junit.*;

/**
 * Tests for <code>{@link ShouldHaveEqualContent#create(Description)}</code>.
 *
 * @author Yvonne Wang
 */
public class ShouldHaveEqualContent_create_Test {

  private ErrorMessageFactory factory;
  private List<String> diffs;

  @Before public void setUp() {
    diffs = list(
        "line:<0>, expected:<line0> but was:<line_0>",
        "line:<1>, expected:<line1> but was:<line_1>",
        "line:<2>, expected:<line2> but was:<line_2>"
    );
    
  }

  @Test public void should_create_error_message_file() {
	factory = ShouldHaveEqualContent.shouldHaveEqualContent(new FakeFile("abc"), new FakeFile("xyz"), diffs);
	  
    StringBuilder b = new StringBuilder();
    b.append("[Test] file:<abc> and file:<xyz> do not have equal content:");
    for (String diff : diffs)
      b.append(LINE_SEPARATOR).append(diff);
    assertEquals(b.toString(), factory.create(new TextDescription("Test")));
  }
  
  @Test public void should_create_error_message_inputstream() {
	factory = ShouldHaveEqualContent.shouldHaveEqualContent(
			new ByteArrayInputStream(new byte[]{'a'}), 
			new ByteArrayInputStream(new byte[]{'b'}), diffs);
	
	StringBuilder b = new StringBuilder();
	b.append("[Test] InputStreams do not have equal content:");
	for (String diff : diffs)
	  b.append(LINE_SEPARATOR).append(diff);
	assertEquals(b.toString(), factory.create(new TextDescription("Test")));
  }

}
