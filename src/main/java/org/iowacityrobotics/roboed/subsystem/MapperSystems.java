package org.iowacityrobotics.roboed.subsystem;

import com.mashape.unirest.http.JsonNode;
import org.iowacityrobotics.roboed.data.mapper.Mapper;
import org.iowacityrobotics.roboed.subsystem.impl.VisionOffloaderMapper;
import org.opencv.core.Mat;

/**
 * Data mappers representing various physical subsystems.
 * @author Evan Geng
 */
public final class MapperSystems {

    /**
     * Vision-related subsystems.
     */
    public static final class VISION {

        /**
         * Creates a vision offloading mapper.
         * @param host The external processor's hostname.
         * @param routine The routine to run.
         * @return The new mapper.
         */
        public static Mapper<Mat, JsonNode> offload(String host, String routine) {
            return new VisionOffloaderMapper(host, routine);
        }

    }

}
