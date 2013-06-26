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
 * All tests for {@link ByteArrayAssert}.
 *
 * @author: Yvonne Wang
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    ByteArrayAssert_isNullOrEmpty_Test.class,
    ByteArrayAssert_isNotEmpty_Test.class,
    ByteArrayAssert_isEmpty_Test.class,
    ByteArrayAssert_isNull_Test.class,
    ByteArrayAssert_isEqualTo_Test.class,
    ByteArrayAssert_isNotEqualTo_Test.class,
    ByteArrayAssert_hasSize_Test.class,
    ByteArrayAssert_contains_Test.class,
    ByteArrayAssert_containsOnly_Test.class,
    ByteArrayAssert_containsSequence_Test.class,
    ByteArrayAssert_doesNotContain_Test.class,
    ByteArrayAssert_doesNotHaveDuplicates_Test.class,
    ByteArrayAssert_startsWith_Test.class,
    ByteArrayAssert_endsWith_Test.class,
    ByteArrayAssert_isSorted_Test.class
})
public class ByteArrayAssert_TestSuite {
}
