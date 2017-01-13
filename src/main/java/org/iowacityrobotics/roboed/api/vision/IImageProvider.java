package org.iowacityrobotics.roboed.api.vision;

import org.opencv.core.Mat;

/**
 * Interface representing a device capable of producing images.
 * @author Evan Geng
 */
@FunctionalInterface
public interface IImageProvider {

    /**
     * Retrieves an image from this provider.
     * @return The image.
     */
    Mat getImage();

}
