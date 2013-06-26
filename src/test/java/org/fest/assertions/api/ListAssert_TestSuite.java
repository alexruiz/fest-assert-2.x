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
 * All tests for {@link ListAssert}.
 *
 * @author Yvonne Wang
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
  ListAssert_isEmpty_Test.class,
  ListAssert_isNull_Test.class,
  ListAssert_isNotNull_Test.class,
  ListAssert_isNullOrEmpty_Test.class,
  ListAssert_isNotEmpty_Test.class,
  ListAssert_isEqualTo_Test.class,
  ListAssert_isNotEqualTo_Test.class,
  ListAssert_contains_Test.class,
  ListAssert_contains_at_Index_Test.class,
  ListAssert_containsNull_Test.class,
  ListAssert_containsOnly_Test.class,
  ListAssert_containsSequence_Test.class,
  ListAssert_doesNotContain_Test.class,
  ListAssert_doesNotContain_at_Index_Test.class,
  ListAssert_doesNotContainNull_Test.class,
  ListAssert_doesNotHaveDuplicates_Test.class,
  ListAssert_hasSize_Test.class,
  ListAssert_startsWith_Test.class,
  ListAssert_endsWith_Test.class
})
public class ListAssert_TestSuite {
}
