/*
 * Created on Mar 29, 2009
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
package org.fest.assertions.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import org.fest.assertions.api.Fail;
import org.junit.Test;

/**
 * Tests for {@link Fail#fail(String, Throwable)}.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class Fail_fail_withMessageAndCause_Test {

  @Test
  public void shouldThrowErrorWithGivenMessageAndCause() {
    String message = "Some Throwable";
    Throwable cause = new Throwable();
    try {
      Fail.fail(message, cause);
      fail("AssertionError should have been thrown");
    } catch (AssertionError e) {
      assertEquals(e.getMessage(), message);
      assertSame(e.getCause(), cause);
    }
  }
}
