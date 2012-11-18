package org.fest.assertions.api;

import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;

public class LocalDateTimeAssert extends AbstractAssert<LocalDateTimeAssert, LocalDateTime> {

  private final DateTimeAssert delegate;

  protected LocalDateTimeAssert(Class<LocalDateTimeAssert> selfType, LocalDateTime actual) {
    super(actual, selfType);
    delegate = new DateTimeAssert(DateTimeAssert.class, actual.toDateTime(DateTimeZone.UTC));
  }

  public LocalDateTimeAssert isBefore(LocalDateTime moment) {
    delegate.isBefore(moment.toDateTime(DateTimeZone.UTC));
    return this;
  }

  public LocalDateTimeAssert isBeforeOrAt(LocalDateTime moment) {
    delegate.isBeforeOrAt(moment.toDateTime(DateTimeZone.UTC));
    return this;
  }

  public LocalDateTimeAssert isAfterOrAt(LocalDateTime moment) {
    delegate.isAfterOrEqual(moment.toDateTime(DateTimeZone.UTC));
    return this;
  }

  public LocalDateTimeAssert isAfter(LocalDateTime moment) {
    delegate.isAfter(moment.toDateTime(DateTimeZone.UTC));
    return this;
  }
}