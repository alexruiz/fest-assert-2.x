/*
 * Created on Dec 21, 2010
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
package org.fest.assertions.internal;

import static org.fest.assertions.error.ShouldBeEmpty.shouldBeEmpty;
import static org.fest.assertions.error.ShouldBeNullOrEmpty.shouldBeNullOrEmpty;
import static org.fest.assertions.error.ShouldContain.shouldContain;
import static org.fest.assertions.error.ShouldContainKey.shouldContainKey;
import static org.fest.assertions.error.ShouldContainOnly.shouldContainOnly;
import static org.fest.assertions.error.ShouldContainValue.shouldContainValue;
import static org.fest.assertions.error.ShouldHaveSize.shouldHaveSize;
import static org.fest.assertions.error.ShouldNotBeEmpty.shouldNotBeEmpty;
import static org.fest.assertions.error.ShouldNotContain.shouldNotContain;
import static org.fest.assertions.error.ShouldNotContainKey.shouldNotContainKey;
import static org.fest.assertions.error.ShouldNotContainValue.shouldNotContainValue;
import static org.fest.assertions.error.ShouldNotHaveDuplicates.shouldNotHaveDuplicates;
import static org.fest.util.Collections.duplicatesFrom;
import static org.fest.util.Objects.areEqual;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.fest.assertions.data.MapEntry;
import org.fest.assertions.description.Description;
import org.fest.util.VisibleForTesting;

/**
 * Reusable assertions for <code>{@link Map}</code>s.
 *
 * @author Alex Ruiz
 * @author Nicolas François
 * @author Yvonne Wang
 */
public class Maps {

  private static Maps INSTANCE = new Maps();

  /**
   * Returns the singleton instance of this class.
   *
   * @return the singleton instance of this class.
   */
  public static Maps instance() {
    return INSTANCE;
  }

  @VisibleForTesting
  Failures failures = Failures.instance();

  @VisibleForTesting
  Maps() {
  }

  /**
   * Asserts that the given {@code Map} is {@code null} or empty.
   *
   * @param description contains information about the assertion.
   * @param actual the given map.
   * @throws AssertionError if the given {@code Map} is not {@code null} *and* contains one or more entries.
   */
  public void assertNullOrEmpty(Description description, Map<?, ?> actual) {
    if (actual == null || actual.isEmpty()) {
      return;
    }
    throw failures.failure(description, shouldBeNullOrEmpty(actual));
  }

  /**
   * Asserts that the given {@code Map} is empty.
   *
   * @param description contains information about the assertion.
   * @param actual the given {@code Map}.
   * @throws AssertionError if the given {@code Map} is {@code null}.
   * @throws AssertionError if the given {@code Map} is not empty.
   */
  public void assertEmpty(Description description, Map<?, ?> actual) {
    assertNotNull(description, actual);
    if (!actual.isEmpty()) {
      throw failures.failure(description, shouldBeEmpty(actual));
    }
  }

  /**
   * Asserts that the given {@code Map} is not empty.
   *
   * @param description contains information about the assertion.
   * @param actual the given {@code Map}.
   * @throws AssertionError if the given {@code Map} is {@code null}.
   * @throws AssertionError if the given {@code Map} is empty.
   */
  public void assertNotEmpty(Description description, Map<?, ?> actual) {
    assertNotNull(description, actual);
    if (actual.isEmpty()) {
      throw failures.failure(description, shouldNotBeEmpty());
    }
  }

  /**
   * Asserts that the given {@code Map} contains the given entries, in any order.
   *
   * @param description contains information about the assertion.
   * @param actual the given {@code Map}.
   * @param entries the entries that are expected to be in the given {@code Map}.
   * @throws NullPointerException if the array of entries is {@code null}.
   * @throws IllegalArgumentException if the array of entries is empty.
   * @throws NullPointerException if any of the entries in the given array is {@code null}.
   * @throws AssertionError if the given {@code Map} is {@code null}.
   * @throws AssertionError if the given {@code Map} does not contain the given entries.
   */
  public void assertContains(Description description, Map<?, ?> actual, MapEntry[] entries) {
    isNotEmptyOrNull(entries);
    assertNotNull(description, actual);
    Set<MapEntry> notFound = new LinkedHashSet<MapEntry>();
    for (MapEntry entry : entries) {
      if (!containsEntry(actual, entry)) {
        notFound.add(entry);
      }
    }
    if (!notFound.isEmpty()) {
      throw failures.failure(description, shouldContain(actual, entries, notFound));
    }
  }

  /**
   * Asserts that the given {@code Map} contains only the given entries, nothing else.
   *
   * @param description contains information about the assertion.
   * @param actual the given {@code Map}.
   * @param entries the entries that are expected to be in the given {@code Map}.
   * @throws NullPointerException if the array of entries is {@code null}.
   * @throws IllegalArgumentException if the array of entries is empty.
   * @throws NullPointerException if any of the entries in the given array is {@code null}.
   * @throws AssertionError if the given {@code Map} is {@code null}.
   * @throws AssertionError if the given {@code Map} does not contain the given entries.
   */
  public <K, V> void assertContainsOnly(Description description, Map<K, V> actual, MapEntry[] entries) {
    isNotEmptyOrNull(entries);
    assertNotNull(description, actual);
    Map<K, V> notExpected = new LinkedHashMap<K, V>(actual);
    Set<MapEntry> notFound = containsOnly(actual, entries, notExpected);
    if (!notFound.isEmpty() || !notExpected.isEmpty()) {
      throw failures.failure(description, shouldContainOnly(actual, entries, notFound, notExpected));
    }
  }

  private <K, V> Set<MapEntry> containsOnly(Map<K, V> actual, MapEntry[] entries, Map<K, V> notExpected) {
    Set<MapEntry> notFound = new LinkedHashSet<MapEntry>();
    Set<?> notExpectedKeys = notExpected.keySet();
    Collection<?> notExpectedValues = notExpected.values();
    for (MapEntry entry : entries) {
      if (!containsEntry(actual, entry)) {
        notFound.add(entry);
      } else {
        notExpectedKeys.remove(entry.key);
        notExpectedValues.remove(entry.value);
      }
    }
    return notFound;
  }

  /**
   * Asserts that the given {@code Map} does not contain the given entries.
   *
   * @param description contains information about the assertion.
   * @param actual the given {@code Map}.
   * @param entries the entries that are expected to be in the given {@code Map}.
   * @throws NullPointerException if the array of entries is {@code null}.
   * @throws IllegalArgumentException if the array of entries is empty.
   * @throws NullPointerException if any of the entries in the given array is {@code null}.
   * @throws AssertionError if the given {@code Map} is {@code null}.
   * @throws AssertionError if the given {@code Map} contains any of the given entries.
   */
  public void assertDoesNotContain(Description description, Map<?, ?> actual, MapEntry[] entries) {
    isNotEmptyOrNull(entries);
    assertNotNull(description, actual);
    Set<MapEntry> found = new LinkedHashSet<MapEntry>();
    for (MapEntry entry : entries) {
      if (containsEntry(actual, entry)) {
        found.add(entry);
      }
    }
    if (!found.isEmpty()) {
      throw failures.failure(description, shouldNotContain(actual, entries, found));
    }
  }

  /**
   * Asserts that the actual map contain the given key.
   *
   * @param description contains information about the assertion.
   * @param actual the given {@code Map}.
   * @param key the given key
   * @throws AssertionError if the actual map is {@code null}.
   * @throws AssertionError if the actual map not contains the given key.
   */
  public <K> void assertContainsKey(Description description, Map<?, ?> actual, K key) {
    assertNotNull(description, actual);
    if (!actual.containsKey(key)) {
      throw failures.failure(description, shouldContainKey(actual, key));
    }
  }

  /**
   * Asserts that the actual map not contains the given key.
   *
   * @param description contains information about the assertion.
   * @param actual the given {@code Map}.
   * @param key the given key
   * @throws AssertionError if the actual map is {@code null}.
   * @throws AssertionError if the actual map contains the given key.
   */
  public <K> void assertDoesNotContainKey(Description description, Map<?, ?> actual, K key) {
    assertNotNull(description, actual);
    if (actual.containsKey(key)) {
      throw failures.failure(description, shouldNotContainKey(actual, key));
    }
  }

  /**
   * Asserts that the actual map contain the given value.
   *
   * @param description contains information about the assertion.
   * @param actual the given {@code Map}.
   * @param value the given value
   * @throws AssertionError if the actual map is {@code null}.
   * @throws AssertionError if the actual map not contains the given value.
   */
  public <V> void assertContainsValue(Description description, Map<?, ?> actual, V value) {
    assertNotNull(description, actual);
    if (!actual.containsValue(value)) {
      throw failures.failure(description, shouldContainValue(actual, value));
    }
  }

  /**
   * Asserts that the actual map does not contain the given value.
   *
   * @param description contains information about the assertion.
   * @param actual the given {@code Map}.
   * @param value the given value
   * @throws AssertionError if the actual map is {@code null}.
   * @throws AssertionError if the actual map contains the given value.
   */
  public <V> void assertDoesNotContainValue(Description description, Map<?, ?> actual, V value) {
    assertNotNull(description, actual);
    if (actual.containsValue(value)) {
      throw failures.failure(description, shouldNotContainValue(actual, value));
    }
  }

  /**
   * Asserts that the actual map does not contain the duplicate values.
   *
   * @param description contains information about the assertion.
   * @param actual the given {@code Map}.
   * @throws AssertionError if the actual map is {@code null}.
   * @throws AssertionError if the actual map contains the given value.
   */
  public <K, V> void assertDoesNotContainDuplicateValues(Description description, Map<K, V> actual) {
    assertNotNull(description, actual);
    Collection<?> duplicates = duplicatesFrom(actual.values());
    if (!duplicates.isEmpty()) {
      throw failures.failure(description, shouldNotHaveDuplicates(actual, duplicates));
    }
  }

  /**
   * Asserts that the number of entries in the given {@code Map} is equal to the expected one.
   *
   * @param description contains information about the assertion.
   * @param actual the given {@code Map}.
   * @param expectedSize the expected size of {@code actual}.
   * @throws AssertionError if the given {@code Map} is {@code null}.
   * @throws AssertionError if the number of entries in the given {@code Map} is different than the expected one.
   */
  public <K, V> void assertHasSize(Description description, Map<K, V> actual, int expectedSize) {
    assertNotNull(description, actual);
    int sizeOfActual = actual.size();
    if (sizeOfActual != expectedSize) {
      throw failures.failure(description, shouldHaveSize(actual, sizeOfActual, expectedSize));
    }
  }

  private void isNotEmptyOrNull(MapEntry[] entries) {
    if (entries == null) {
      throw new NullPointerException("The array of entries to look for should not be null");
    }
    if (entries.length == 0) {
      throw new IllegalArgumentException("The array of entries to look for should not be empty");
    }
  }

  private boolean containsEntry(Map<?, ?> actual, MapEntry entry) {
    if (entry == null) {
      throw new NullPointerException("Entries to look for should not be null");
    }
    if (!actual.containsKey(entry.key)) {
      return false;
    }
    return areEqual(actual.get(entry.key), entry.value);
  }

  private <K, V> void assertNotNull(Description description, Map<K, V> actual) {
    Objects.instance().assertNotNull(description, actual);
  }
}
