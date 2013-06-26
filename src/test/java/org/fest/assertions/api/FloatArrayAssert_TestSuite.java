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
 * All tests for {@link FloatArrayAssert}.
 *
 * @author Yvonne Wang
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
  FloatArrayAssert_isEmpty_Test.class,
  FloatArrayAssert_isNullOrEmpty_Test.class,
  FloatArrayAssert_isNotEmpty_Test.class,
  FloatArrayAssert_contains_Test.class,
  FloatArrayAssert_contains_at_Index_Test.class,
  FloatArrayAssert_containsOnly_Test.class,
  FloatArrayAssert_containsSequence_Test.class,
  FloatArrayAssert_doesNotContain_Test.class,
  FloatArrayAssert_doesNotContain_at_Index_Test.class,
  FloatArrayAssert_doesNotHaveDuplicates_Test.class,
  FloatArrayAssert_hasSize_Test.class,
  FloatArrayAssert_startsWith_Test.class,
  FloatArrayAssert_endsWith_Test.class
})
public class FloatArrayAssert_TestSuite {
}
