package org.fest.assertions.error;

import java.io.File;

/**
 * Creates an error message indicating that an assertion that verifies that a <code>{@link File}</code> is writable
 * failed.
 *
 * @author Olivier Demeijer
 *
 */
public class ShouldBeWritable extends BasicErrorMessageFactory {

  private ShouldBeWritable(File actual) {
    super("File:<%s> should be writable", actual);
  }

  /**
   * Creates a new <code>{@link ShouldBeWritable}</code>.
   * @param actual the actual value in the failed assertion.
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldBeWritable(File actual) {
    return new ShouldBeWritable(actual);
  }
}
