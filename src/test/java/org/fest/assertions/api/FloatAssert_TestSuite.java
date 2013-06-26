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
 * All tests for {@link FloatAssert}.
 *
 * @author Yvonne Wang
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
  FloatAssert_isNaN_Test.class,
  FloatAssert_isNotNaN_Test.class,
  FloatAssert_isNull_Test.class,
  FloatAssert_isNotNull_Test.class,
  FloatAssert_isEqualTo_float_with_offset_Test.class,
  FloatAssert_isEqualTo_float_Test.class,
  FloatAssert_isNotEqualTo_float_Test.class,
  FloatAssert_isGreaterThan_float_Test.class,
  FloatAssert_isNotGreaterThan_float_Test.class,
  FloatAssert_isLessThan_float_Test.class,
  FloatAssert_isNotLessThan_float_Test.class,
  FloatAssert_isEqualTo_float_wrapper_with_offset_Test.class,
  FloatAssert_isNotEqualTo_float_wrapper_Test.class,
  FloatAssert_isEqualTo_float_wrapper_Test.class,
  FloatAssert_isGreaterThan_float_wrapper_Test.class,
  FloatAssert_isNotGreaterThan_float_wrapper_Test.class,
  FloatAssert_isLessThan_float_wrapper_Test.class,
  FloatAssert_isNotLessThan_float_wrapper_Test.class
})
public class FloatAssert_TestSuite {
}
