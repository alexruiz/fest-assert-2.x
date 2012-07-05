/*
 * Created on Jun 4, 2012
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
 * Copyright @2010-2012 the original author or authors.
 */
package org.fest.assertions.api;

import static junit.framework.Assert.assertSame;
import static org.fest.assertions.test.ByteArrayFactory.emptyArray;
import static org.fest.util.Arrays.array;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.fest.assertions.internal.ByteArrays;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for <code>{@link ByteArrayAssert#hasSameSizeAs(Object[])}</code>.
 *
 * @author Nicolas François
 */
public class ByteArrayAssert_hasSameSizeAs_with_Array_Test {

  private ByteArrays arrays;
  private ByteArrayAssert assertions;
  private final String[] other = array("Yoda", "Luke");
  
  @Before
  public void setUp() {
    arrays = mock(ByteArrays.class);
    assertions = new ByteArrayAssert(emptyArray());
    assertions.arrays = arrays;
  }

  @Test
  public void should_verify_that_actual_has_expected_size() {
    assertions.hasSameSizeAs(other);
    verify(arrays).assertHasSameSizeAs(assertions.info, assertions.actual, other);
  }

  @Test public void should_return_this() {
	ByteArrayAssert returned = assertions.hasSameSizeAs(other);
    assertSame(assertions, returned);
  }
}
