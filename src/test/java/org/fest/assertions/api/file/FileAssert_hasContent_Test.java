/*
 * Created on Jul 21, 2012
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 * 
 * Copyright @2011 the original author or authors.
 */
package org.fest.assertions.api.file;

import static org.mockito.Mockito.verify;

import org.fest.assertions.api.FileAssert;
import org.fest.assertions.api.FileAssertTest;
import org.junit.BeforeClass;

/**
 * Tests for <code>{@link FileAssert#hasContent(String)}</code>.
 * 
 * @author Olivier Michallat
 */
public class FileAssert_hasContent_Test extends FileAssertTest {

  private static String expected;

  @BeforeClass
  public static void beforeOnce() {
    expected = "xyz";
  }

  @Override
  protected FileAssert invoke_api_method() {
    return assertions.hasContent(expected);
  }

  @Override
  protected void verify_internal_effects() {
    verify(files).assertHasContent(getInfo(assertions), getActual(assertions), expected, getCharset(assertions));
  }
}
