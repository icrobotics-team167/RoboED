package org.iowacityrobotics.roboed.api.vision;

import org.opencv.core.Mat;

/**
 * Interface representing a camera.
 * @author Evan Geng
 */
public interface ICamera {

    Mat capture();

}
