/*
 * Created on Aug 02, 2012
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 * 
 * Copyright @2010-2011 the original author or authors.
 */
package org.fest.assertions.api;

import static org.fest.assertions.test.TestData.fivePixelBlueImage;
import static org.mockito.Mockito.mock;

import java.awt.image.BufferedImage;

import org.fest.assertions.internal.Images;

/**
 * Base class for {@link ImageAssert} tests.
 * 
 * @author Olivier Michallat
 */
public abstract class ImageAssertTest extends BaseAssertTest<ImageAssert, BufferedImage> {
  protected Images images;

  @Override
  protected ImageAssert create_assertions() {
    return new ImageAssert(fivePixelBlueImage());
  }

  @Override
  protected void inject_internal_objects() {
    super.inject_internal_objects();
    images = mock(Images.class);
    assertions.images = images;
  }

  protected Images getImages(ImageAssert someAssertions) {
    return someAssertions.images;
  }
}
