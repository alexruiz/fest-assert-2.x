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

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * All tests for {@link SetAssert}.
 *
 * @author Yvonne Wang
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
  SetAssert_isEmpty_Test.class,
  SetAssert_isNull_Test.class,
  SetAssert_isNotNull_Test.class,
  SetAssert_isNullOrEmpty_Test.class,
  SetAssert_isNotEmpty_Test.class,
  SetAssert_isEqualTo_Test.class,
  SetAssert_isNotEqualTo_Test.class,
  SetAssert_contains_Test.class,
  SetAssert_containsNull_Test.class,
  SetAssert_containsOnly_Test.class,
  SetAssert_containsSequence_Test.class,
  SetAssert_doesNotContain_Test.class,
  SetAssert_doesNotContainNull_Test.class,
  SetAssert_hasSize_Test.class,
  SetAssert_startsWith_Test.class,
  SetAssert_endsWith_Test.class
})
public class SetAssert_TestSuite {
}
