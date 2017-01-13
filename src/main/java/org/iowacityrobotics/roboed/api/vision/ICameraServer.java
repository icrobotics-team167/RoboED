package org.iowacityrobotics.roboed.api.vision;

import edu.wpi.cscore.VideoSource;

/**
 * Interface representing the camera server, which provides instances of {@link ICamera}.
 * @author Evan Geng
 */
public interface ICameraServer {

    ICamera getCamera(VideoSource.Kind kind, String identifier);

}
