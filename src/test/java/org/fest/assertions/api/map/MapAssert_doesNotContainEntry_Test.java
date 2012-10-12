package org.fest.assertions.api.map;


import org.fest.assertions.api.MapAssert;
import org.fest.assertions.api.MapAssertBaseTest;
import org.fest.assertions.data.MapEntry;

import static org.fest.assertions.data.MapEntry.entry;
import static org.fest.util.Arrays.array;
import static org.mockito.Mockito.verify;

/**
 * Tests for <code>{@link org.fest.assertions.api.MapAssert#doesNotContainEntry(Object, Object)}}</code>.
 *
 * @author Sam Beran
 */
public class MapAssert_doesNotContainEntry_Test extends MapAssertBaseTest {

  final MapEntry[] entries = array(entry("key1", "value1"));

  @Override
  protected MapAssert<Object, Object> invoke_api_method() {
    return assertions.doesNotContainEntry("key1", "value1");
  }

  @Override
  protected void verify_internal_effects() {
    verify(maps).assertDoesNotContain(getInfo(assertions), getActual(assertions), entries);
  }
}
