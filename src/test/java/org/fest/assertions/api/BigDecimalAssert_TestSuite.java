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

/**
 * All tests for {@link BigDecimalAssert}.
 *
 * @author Yvonne Wang
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    BigDecimalAssert_isEqualByComparingTo_Test.class,
    BigDecimalAssert_isNotEqualByComparingTo_Test.class,
    BigDecimalAssert_isEqualTo_Test.class,
    BigDecimalAssert_isNotEqualTo_Test.class,
    BigDecimalAssert_isLessThan_Test.class,
    BigDecimalAssert_isNotLessThan_Test.class,
    BigDecimalAssert_isGreaterThan_Test.class,
    BigDecimalAssert_isNotGreaterThan_Test.class,
    BigDecimalAssert_isNull_Test.class,
    BigDecimalAssert_isNotNull_Test.class
})
public class BigDecimalAssert_TestSuite {

}
