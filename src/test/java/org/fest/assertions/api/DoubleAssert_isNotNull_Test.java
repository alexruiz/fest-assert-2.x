package org.fest.assertions.api;

import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static junit.framework.Assert.assertSame;
import static org.fest.test.ExpectedException.none;

/**
 * Tests for {@link DoubleAssert#isNotNull()}.
 *
 * @author Yvonne Wang
 */
public class DoubleAssert_isNotNull_Test {
  @Rule
  public ExpectedException thrown = none();
  private Double actual;
  private DoubleAssert assertions;

  @Before
  public void setUp() {
    actual = new Double(6d);
    assertions = new DoubleAssert(actual);
  }

  @Test
  public void should_pass_if_actual_is_not_Null() {
    assertions.isNotNull();
  }

  @Test
  public void should_return_this() {
    DoubleAssert returned = assertions.isNotNull();
    assertSame(returned, assertions);
  }

  @Test
  public void should_fail_if_actual_is_null() {
    thrown.expect(AssertionError.class);
    assertions = new DoubleAssert(null);
    assertions.isNotNull();
  }
}
