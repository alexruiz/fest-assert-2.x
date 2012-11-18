package org.fest.assertions.api;

import org.fest.assertions.internal.Failures;
import org.joda.time.DateTime;

import java.util.Date;

import static java.lang.String.format;

public class DateTimeAssert extends AbstractAssert<DateTimeAssert, DateTime> {

  public static final String PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";

  /**
   * Creates a new <code>{@link org.fest.assertions.api.AbstractAssert}</code>.
   *
   * @param selfType the "self type"
   * @param actual   the actual value to verify
   */
  protected DateTimeAssert(Class<DateTimeAssert> selfType, DateTime actual) {
    super(actual, selfType);
  }

  public DateTimeAssert isBefore(DateTime moment) {
    if (!actual.isBefore(moment)) {
      throw Failures.instance().failure(format("Moment %s is not before %s", actual.toString(PATTERN), moment.toString(PATTERN)));
    }

    return this;
  }

  public DateTimeAssert isBefore(Date moment) {
    if (!actual.isBefore(new DateTime(moment))) {
      throw Failures.instance().failure(format("Moment %s is not before %s", actual.toString(PATTERN), new DateTime(moment).toString(PATTERN)));
    }

    return this;
  }

  public DateTimeAssert isBeforeOrAt(DateTime moment) {
    if (actual.isAfter(moment)) {
      throw Failures.instance().failure(format("Moment %s is not before %s nor at the same time.", actual.toString(PATTERN), moment.toString(PATTERN)));
    }

    return this;
  }

  public DateTimeAssert isBeforeOrEqual(Date moment) {
    if (actual.isAfter(new DateTime(moment))) {
      throw Failures.instance().failure(format("Moment %s is not before %s nor at the same time.", actual.toString(PATTERN), new DateTime(moment).toString(PATTERN)));
    }

    return this;
  }

  public DateTimeAssert isAfterOrEqual(DateTime moment) {
    if (actual.isBefore(moment)) {
      throw Failures.instance().failure(format("Moment %s is not after %s nor at the same time", actual.toString(PATTERN), moment.toString(PATTERN)));
    }

    return this;
  }

  public DateTimeAssert isAfterOrAt(Date moment) {
    if (actual.isBefore(new DateTime(moment))) {
      throw Failures.instance().failure(format("Moment %s is not after %s nor at the same time", actual.toString(PATTERN), new DateTime(moment).toString(PATTERN)));
    }

    return this;
  }

  public DateTimeAssert isAfter(DateTime moment) {
    if (!actual.isAfter(moment)) {
      throw Failures.instance().failure(format("Moment %s is not after %s", actual.toString(PATTERN), moment.toString(PATTERN)));
    }

    return this;
  }

  public DateTimeAssert isAfter(Date moment) {
    if (!actual.isAfter(new DateTime(moment))) {
      throw Failures.instance().failure(format("Moment %s is not after %s", actual.toString(PATTERN), new DateTime(moment).toString(PATTERN)));
    }

    return this;
  }
}