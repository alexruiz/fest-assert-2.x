/*
 *
 *  * Created on Mar 29, 2009
 *  * 
 *  * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 *  * the License. You may obtain a copy of the License at
 *  * 
 *  * http://www.apache.org/licenses/LICENSE-2.0
 *  * 
 *  * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 *  * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 *  * specific language governing permissions and limitations under the License.
 *  * 
 *  * Copyright @2013 the original author or authors.
 *  
 */

package org.fest.assertions.api;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * All tests for {@link LongAssert}.
 *
 * @author Yvonne Wang
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
  LongAssert_isNull_Test.class,
  LongAssert_isNotNull_Test.class,
  LongAssert_isEqualTo_long_Test.class,
  LongAssert_isNotEqualTo_long_Test.class,
  LongAssert_isGreaterThan_long_Test.class,
  LongAssert_isNotLessThan_long_Test.class,
  LongAssert_isLessThan_long_Test.class,
  LongAssert_isNotGreaterThan_long_Test.class,
  LongAssert_isEqualTo_long_wrapper_Test.class,
  LongAssert_isNotEqualTo_long_wrapper_Test.class,
  LongAssert_isGreaterThan_long_wrapper_Test.class,
  LongAssert_isNotLessThan_long_wrapper_Test.class,
  LongAssert_isLessThan_long_wrapper_Test.class,
  LongAssert_isNotGreaterThan_long_wrapper_Test.class
})
public class LongAssert_TestSuite {
}
