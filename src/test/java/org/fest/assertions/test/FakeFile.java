/*
 * Created on Jan 29, 2011
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
 * Copyright @2011-2012 the original author or authors.
 */
package org.fest.assertions.test;

import java.io.File;

/**
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class FakeFile extends File {
  private static enum Type {
    WRITABLE_FILE, DIRECTORY, NON_EXISTING_RESOURCE;
  }

  private final String absolutePath;
  private final boolean canWrite;
  private final boolean exists;
  private final boolean isFile;
  private final boolean isDirectory;

  public static File newWritableFile(String absolutePath) {
    return new FakeFile(absolutePath, Type.WRITABLE_FILE);
  }

  public static File newDirectory(String absolutePath) {
    return new FakeFile(absolutePath, Type.DIRECTORY);
  }

  public static File newNonExistingResource(String absolutePath) {
    return new FakeFile(absolutePath, Type.NON_EXISTING_RESOURCE);
  }

  private FakeFile(String absolutePath, Type type) {
    super(absolutePath);
    this.absolutePath = absolutePath;
    switch (type) {
    case WRITABLE_FILE:
      canWrite = true;
      exists = true;
      isDirectory = false;
      isFile = true;
      break;
    case DIRECTORY:
      canWrite = false;
      exists = true;
      isDirectory = true;
      isFile = false;
      break;
    default:
      canWrite = false;
      exists = false;
      isDirectory = false;
      isFile = false;
    }
  }

  @Override
  public boolean canWrite() {
    return canWrite;
  }

  @Override
  public boolean exists() {
    return exists;
  }

  @Override
  public boolean isFile() {
    return isFile;
  }

  @Override
  public boolean isDirectory() {
    return isDirectory;
  }

  @Override
  public String getAbsolutePath() {
    // ToStringOf uses absolute path instead of toString
    return absolutePath;
  }
}