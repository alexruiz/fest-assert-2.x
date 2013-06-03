/*
 * Created on Oct 2, 2012
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
 * Copyright @2012-2013 Google Inc. and others.
 */
package org.fest.assertions.api;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * All tests for {@link AbstractAssert}.
 *
 * @author alruiz@google.com (Alex Ruiz)
 * @author Yvonne Wang
 */
@RunWith(Suite.class)
@SuiteClasses({
    AbstractAssert_equals_hashCode_Test.class,
    AbstractAssert_isEqualTo_Test.class,
    AbstractAssert_isNotEqualTo_Test.class,
    AbstractAssert_isNotNull_Test.class,
    AbstractAssert_isNull_Test.class
})
public class AbstractAssert_TestSuite {}
