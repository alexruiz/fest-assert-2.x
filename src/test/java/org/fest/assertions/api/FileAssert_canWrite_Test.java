package org.fest.assertions.api;

import static junit.framework.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.File;

import org.fest.assertions.internal.Files;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for <code>{@link FileAssert#canWrite()}</code>.
 * 
 * @author Olivier Demeijer
 * 
 */
public class FileAssert_canWrite_Test {
  private Files files;
  private FileAssert assertions;

  @Before public void setUp() {
    files = mock(Files.class);
    assertions = new FileAssert(new File("abc"));
    assertions.files = files;
  }

  @Test public void should_verify_that_actual_can_write() {
    assertions.canWrite();
    verify(files).assertCanWrite(assertions.info, assertions.actual);
  }

  @Test public void should_return_this() {
    FileAssert returned = assertions.canWrite();
    assertSame(assertions, returned);
  }

}
