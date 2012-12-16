package org.fest.assertions.api.list;

import static org.fest.assertions.test.TestData.someIndex;

import static org.mockito.Mockito.verify;

import org.junit.BeforeClass;

import org.fest.assertions.api.ListAssert;
import org.fest.assertions.api.ListAssertBaseTest;
import org.fest.assertions.core.Matcher;
import org.fest.assertions.core.TestMatcher;
import org.fest.assertions.data.Index;

/**
 * Tests for <code>{@link ListAssert#is(Matcher, Index)}</code>.
 * 
 * @author Bo Gotthardt
 */
public class ListAssert_is_at_Index_Test extends ListAssertBaseTest {

  private static Matcher<Object> condition;
  private static Index index;

  @BeforeClass
  public static void setUpOnce() {
    condition = new TestMatcher<Object>();
    index = someIndex();
  }

  @Override
  protected ListAssert<String> invoke_api_method() {
    return assertions.is(condition, index);
  }

  @Override
  protected void verify_internal_effects() {
    verify(lists).assertIs(getInfo(assertions), getActual(assertions), condition, index);
  }
}
