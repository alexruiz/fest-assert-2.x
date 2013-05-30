/*
 *
 *  * Created on Dec 16, 2010
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the
 *  * License. You may obtain a copy of the License at
 *  *
 *  * http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS"
 *  * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language
 *  * governing permissions and limitations under the License.
 *  *
 *  * Copyright @2013 the original author or authors.
 *
 */

package org.fest.assertions.api;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * All tests for {@link IntegerAssert}.
 * <p/>
 * Author: Yvonne Wang
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    IntegerAssert_isEqualTo_int_Test.class,
    IntegerAssert_isNotEqualTo_int_Test.class,
    IntegerAssert_isLessThan_int_Test.class,
    IntegerAssert_isNotLessThan_int_Test.class,
    IntegerAssert_isGreaterThan_int_Test.class,
    IntegerAssert_isNotGreaterThan_int_Test.class,
    IntegerAssert_isEqualTo_Integer_Test.class,
    IntegerAssert_isNotEqualTo_Integer_Test.class,
    IntegerAssert_isLessThan_Integer_Test.class,
    IntegerAssert_isNotLessThan_Integer_Test.class,
    IntegerAssert_isGreaterThan_Integer_Test.class,
    IntegerAssert_isNotGreaterThan_Integer_Test.class
})
public class IntegerAssert_TestSuite {
}
