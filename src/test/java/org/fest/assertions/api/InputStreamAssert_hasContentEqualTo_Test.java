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
package org.fest.assertions.api;

import static junit.framework.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.fest.assertions.internal.InputStreams;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests for <code>{@link InputStreamAssert#hasContentEqualTo(InputStream)}</code>.
 */
public class InputStreamAssert_hasContentEqualTo_Test {

  private static InputStream expected;

  @BeforeClass public static void setUpOnce() {
    expected = new ByteArrayInputStream(new byte[]{'b'});
  }

  private InputStreams inputStreams;
  private InputStreamAssert assertions;

  @Before public void setUp() {
    inputStreams = mock(InputStreams.class);
    assertions = new InputStreamAssert(new ByteArrayInputStream(new byte[]{'a'}));
    assertions.inputStreams = inputStreams;
  }

  @Test public void should_verify_that_content_in_actual_is_equal_to_expected() {
    assertions.hasContentEqualTo(expected);
    verify(inputStreams).assertEqualContent(assertions.info, assertions.actual, expected);
  }

}
