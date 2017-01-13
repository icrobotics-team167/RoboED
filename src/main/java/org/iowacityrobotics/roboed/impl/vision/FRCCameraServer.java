package org.iowacityrobotics.roboed.impl.vision;

import edu.wpi.cscore.VideoSource;
import org.iowacityrobotics.roboed.api.vision.ICamera;
import org.iowacityrobotics.roboed.api.vision.ICameraServer;

public class FRCCameraServer implements ICameraServer {

    @Override
    public ICamera getCamera(VideoSource.Kind kind, String identifier) {
        return null; // TODO Implement
    }

}
