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
 * All tests for {@link DoubleAssert}.
 *
 * @author Yvonne Wang
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
  DoubleAssert_isNaN_Test.class,
  DoubleAssert_isNotNaN_Test.class,
  DoubleAssert_isNull_Test.class,
  DoubleAssert_isNotNull_Test.class,
  DoubleAssert_isEqualTo_double_with_offset_Test.class,
  DoubleAssert_isEqualTo_double_Test.class,
  DoubleAssert_isNotEqualTo_double_Test.class,
  DoubleAssert_isGreaterThan_double_Test.class,
  DoubleAssert_isNotGreaterThan_double_Test.class,
  DoubleAssert_isLessThan_double_Test.class,
  DoubleAssert_isNotLessThan_double_Test.class,
  DoubleAssert_isEqualTo_double_wrapper_with_offset_Test.class,
  DoubleAssert_isNotEqualTo_double_wrapper_Test.class,
  DoubleAssert_isEqualTo_double_wrapper_Test.class,
  DoubleAssert_isGreaterThan_double_wrapper_Test.class,
  DoubleAssert_isNotGreaterThan_double_wrapper_Test.class,
  DoubleAssert_isLessThan_double_wrapper_Test.class,
  DoubleAssert_isNotLessThan_double_wrapper_Test.class
})
public class DoubleAssert_TestSuite {
}
