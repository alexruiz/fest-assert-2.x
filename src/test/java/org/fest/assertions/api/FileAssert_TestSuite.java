/*
 * Created on May 9, 2013
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

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * @author Yvonne Wang
 */
@RunWith(Suite.class)
@SuiteClasses({
  FileAssert_doesNotExist_Test.class,
  FileAssert_exists_Test.class,
  FileAssert_hasContentEqualTo_Test.class,
  FileAssert_hasContentEqualTo_with_charset_Test.class,
  FileAssert_isDirectory_Test.class,
  FileAssert_isFile_Test.class
})
public class FileAssert_TestSuite {}
