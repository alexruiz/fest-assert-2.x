package org.fest.assertions.api;

import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.junit.Assume;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.fail;

@RunWith(Theories.class)
public class JodaTimeAssertionsTest {

  @DataPoint
  public static DateTime a = new DateTime(2000, 12, 14, 0, 0);

  @DataPoint
  public static DateTime b = new DateTime(2000, 12, 13, 23, 59, 59, 999);

  @DataPoint
  public static DateTime c = new DateTime(2000, 12, 14, 0, 0, 0, 1);

  @DataPoint
  public static DateTime d = new DateTime(2000, 12, 14, 22, 15, 15, 875);

  @DataPoint
  public static DateTime e = new DateTime(2000, 12, 14, 22, 15, 15, 874);

  @DataPoint
  public static DateTime f = new DateTime(2000, 12, 14, 22, 15, 15, 876);

  //Test isBefore

  @Theory
  public void isBeforeShouldAssertThatTimeIsBefore(final DateTime reference, DateTime before, final DateTime after) {
    assumptions(reference, before, after);

    assertThat(before).isBefore(reference);
    assertThat(before.toLocalDateTime()).isBefore(reference.toLocalDateTime());

    assertIsBeforeThrowsException(reference, reference);
    assertIsBeforeThrowsException(reference.toLocalDateTime(), reference.toLocalDateTime());

    assertIsBeforeThrowsException(after, reference);
    assertIsBeforeThrowsException(after.toLocalDateTime(), reference.toLocalDateTime());
  }

  private void assumptions(DateTime reference, DateTime before, DateTime after) {
    Assume.assumeTrue(before.isBefore(reference));
    Assume.assumeTrue(after.isAfter(reference));
  }


  //Test isBeforeOrEqual

  @Theory
  public void isBeforeOrAtShouldAssertThatTimeIsBeforeOrEqual(final DateTime reference, DateTime before, final DateTime after) {
    assumptions(reference, before, after);

    assertThat(before).isBeforeOrAt(reference);
    assertThat(before.toLocalDateTime()).isBeforeOrAt(reference.toLocalDateTime());

    assertThat(reference).isBeforeOrAt(reference);
    assertThat(reference.toLocalDateTime()).isBeforeOrAt(reference.toLocalDateTime());

    assertIsBeforeOrAtThrowsException(after, reference);
    assertIsBeforeOrAtThrowsException(after.toLocalDateTime(), reference.toLocalDateTime());
  }


  //Test isAfter

  @Theory
  public void isAfterShouldAssertThatTimeIsAfter(final DateTime reference, final DateTime before, final DateTime after) {
    assumptions(reference, before, after);

    assertIsAfterThrowsException(before, reference);
    assertIsAfterThrowsException(before.toLocalDateTime(), reference.toLocalDateTime());

    assertIsAfterThrowsException(reference, reference);
    assertIsAfterThrowsException(reference.toLocalDateTime(), reference.toLocalDateTime());

    assertThat(after).isAfter(reference);
    assertThat(after.toLocalDateTime()).isAfter(reference.toLocalDateTime());
  }


  //Test isAfterOrEqual
  @Theory
  public void isAfterOrAtShouldAssertThatTimeIsAfterOrEqual(final DateTime reference, final DateTime before, final DateTime after) {
    assumptions(reference, before, after);

    assertIsAfterOrAtThrowsException(before, reference);
    assertIsAfterOrAtThrowsException(before.toLocalDateTime(), reference.toLocalDateTime());

    assertIsAfterOrAtThrowsException(reference, reference);
    assertIsAfterOrAtThrowsException(reference.toLocalDateTime(), reference.toLocalDateTime());

    assertThat(after).isAfterOrEqual(reference);
    assertThat(after.toLocalDateTime()).isAfterOrAt(reference.toLocalDateTime());
  }

  private void assertIsBeforeThrowsException(final DateTime dateToCheck, final DateTime reference) {
    try {
      assertThat(dateToCheck).isBefore(reference);
      fail("Should have thrown AssertionError");
    } catch (AssertionError e) {
      //Expected exception
    } catch (Exception e) {
      fail("Unexpected exception thrown", e);
    }
  }

  private void assertIsBeforeThrowsException(final LocalDateTime dateToCheck, final LocalDateTime reference) {
    try {
      assertThat(dateToCheck).isBefore(reference);
      fail("Should have thrown AssertionError");
    } catch (AssertionError e) {
      //Expected exception
    } catch (Exception e) {
      fail("Unexpected exception thrown", e);
    }
  }

  private void assertIsBeforeOrAtThrowsException(final LocalDateTime dateToCheck, final LocalDateTime reference) {
    try {
      assertThat(dateToCheck).isBeforeOrAt(reference);
      fail("Should have thrown AssertionError");
    } catch (AssertionError e) {
      //Expected exception
    } catch (Exception e) {
      fail("Unexpected exception thrown", e);
    }
  }

  private void assertIsBeforeOrAtThrowsException(final DateTime dateToCheck, final DateTime reference) {
    try {
      assertThat(dateToCheck).isBeforeOrAt(reference);
      fail("Should have thrown AssertionError");
    } catch (AssertionError e) {
      //Expected exception
    } catch (Exception e) {
      fail("Unexpected exception thrown", e);
    }
  }

  private void assertIsAfterThrowsException(final LocalDateTime dateToCheck, final LocalDateTime reference) {
    try {
      assertThat(dateToCheck).isAfter(reference);
      fail("Should have thrown AssertionError");
    } catch (AssertionError e) {
      //Expected exception
    } catch (Exception e) {
      fail("Unexpected exception thrown", e);
    }
  }

  private void assertIsAfterThrowsException(final DateTime dateToCheck, final DateTime reference) {
    try {
      assertThat(dateToCheck).isAfter(reference);
      fail("Should have thrown AssertionError");
    } catch (AssertionError e) {
      //Expected exception
    } catch (Exception e) {
      fail("Unexpected exception thrown", e);
    }
  }

  private void assertIsAfterOrAtThrowsException(final LocalDateTime dateToCheck, final LocalDateTime reference) {
    try {
      assertThat(dateToCheck).isAfterOrAt(reference);
      fail("Should have thrown AssertionError");
    } catch (AssertionError e) {
      //Expected exception
    } catch (Exception e) {
      fail("Unexpected exception thrown", e);
    }
  }

  private void assertIsAfterOrAtThrowsException(final DateTime dateToCheck, final DateTime reference) {
    try {
      assertThat(dateToCheck).isAfterOrEqual(reference);
      fail("Should have thrown AssertionError");
    } catch (AssertionError e) {
      //Expected exception
    } catch (Exception e) {
      fail("Unexpected exception thrown", e);
    }
  }

}
