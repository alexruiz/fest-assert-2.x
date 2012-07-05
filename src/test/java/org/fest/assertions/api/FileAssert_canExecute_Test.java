package org.fest.assertions.api;

import static junit.framework.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.File;

import org.fest.assertions.internal.Files;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for <code>{@link FileAssert#canExecute()}</code>.
 * 
 * @author Olivier Demeijer
 * 
 */
public class FileAssert_canExecute_Test {
  private Files files;
  private FileAssert assertions;

  @Before public void setUp() {
    files = mock(Files.class);
    assertions = new FileAssert(new File("abc"));
    assertions.files = files;
  }

  @Test public void should_verify_that_actual_can_execute() {
    assertions.canExecute();
    verify(files).assertCanExecute(assertions.info, assertions.actual);
  }

  @Test public void should_return_this() {
    FileAssert returned = assertions.canExecute();
    assertSame(assertions, returned);
  }

}
