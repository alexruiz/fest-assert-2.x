/*
 * Created on Dec 16, 2010
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
 * Copyright @2010-2013 the original author or authors.
 */
package org.fest.assertions.api;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * All tests for {@link BooleanArrayAssert}.
 *
 * @author Yvonne Wang
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    BooleanArrayAssert_isEmpty_Test.class,
    BooleanArrayAssert_isNotEmpty_Test.class,
    BooleanArrayAssert_isNullOrEmpty_Test.class,
    BooleanArrayAssert_isNull_Test.class,
    BooleanArrayAssert_isNotNull_Test.class,
    BooleanArrayAssert_isEqualTo_Test.class,
    BooleanArrayAssert_isNotEqualTo_Test.class,
    BooleanArrayAssert_contains_Test.class,
    BooleanArrayAssert_contains_at_Index_Test.class,
    BooleanArrayAssert_doesNotContain_Test.class,
    BooleanArrayAssert_doesNotContain_at_Index_Test.class,
    BooleanArrayAssert_containsOnly_Test.class,
    BooleanArrayAssert_containsSequence_Test.class,
    BooleanArrayAssert_hasSize_Test.class,
    BooleanArrayAssert_doesNotHaveDuplicates_Test.class,
    BooleanArrayAssert_startsWith_Test.class,
    BooleanArrayAssert_endsWith_Test.class
})
public class BooleanArrayAssert_TestSuite {
}
