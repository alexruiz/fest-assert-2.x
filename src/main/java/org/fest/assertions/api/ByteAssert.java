/*
 * Created on Oct 21, 2010
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
 * Copyright @2010-2013 the original author or authors.
 */
package org.fest.assertions.api;

import org.fest.assertions.description.Description;
import org.fest.assertions.internal.Bytes;
import org.fest.util.VisibleForTesting;

/**
 * Assertion methods for bytes.
 * <p>
 * To create an instance of this class, invoke {@link Assertions#assertThat(Byte)} or
 * {@link Assertions#assertThat(byte)}.
 * </p>
 *
 * @author Yvonne Wang
 * @author David DIDIER
 * @author Ansgar Konermann
 * @author Alex Ruiz
 */
public class ByteAssert extends AbstractComparableAssert<ByteAssert, Byte> {

  @VisibleForTesting
  Bytes bytes = Bytes.instance();

  protected ByteAssert(Byte actual) {
    super(actual, ByteAssert.class);
  }

  protected ByteAssert(Byte actual, Description description) {
    super(actual, ByteAssert.class, description);
  }

  public ByteAssert isEqualTo(byte expected) {
    bytes.assertEqualTo(description, actual, expected);
    return this;
  }

  public ByteAssert isNotEqualTo(byte expected) {
    bytes.assertNotEqualTo(description, actual, expected);
    return this;
  }

  public ByteAssert isLessThan(byte expected) {
    bytes.assertLessThan(description, actual, expected);
    return this;
  }

  public ByteAssert isNotLessThan(byte expected) {
    bytes.assertNotLessThan(description, actual, expected);
    return this;
  }

  public ByteAssert isGreaterThan(byte expected) {
    bytes.assertGreaterThan(description, actual, expected);
    return this;
  }

  public ByteAssert isNotGreaterThan(byte expected) {
    bytes.assertGreaterThan(description, actual, expected);
    return this;
  }
}
