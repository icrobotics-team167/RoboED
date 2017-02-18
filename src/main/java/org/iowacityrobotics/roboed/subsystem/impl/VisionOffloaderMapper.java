package org.iowacityrobotics.roboed.subsystem.impl;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.iowacityrobotics.roboed.data.mapper.IMapper;
import org.iowacityrobotics.roboed.util.vision.Images;
import org.opencv.core.Mat;

/**
 * Vision offloader implementation using cotc-vision.
 * @author Evan Geng
 */
public class VisionOffloaderMapper implements IMapper<Mat, JsonNode> {

    /**
     * The vision processor's host address.
     */
    private final String host;

    /**
     * The name of the vision routine to run.
     */
    private final String routine;

    /**
     * Constructs a new vision offloading mapper.
     * @param host The external processor's hostname.
     * @param routine The routine name.
     */
    public VisionOffloaderMapper(String host, String routine) {
        this.host = host;
        this.routine = routine;
    }

    @Override
    public JsonNode apply(Mat data) {
        try {
            HttpResponse<JsonNode> resp = Unirest.post(host)
                    .queryString("routine", routine)
                    .body(Images.serialize(data))
                    .asJson();
            if (resp.getStatus() != 200)
                return null;
            return resp.getBody();
        } catch (UnirestException e) {
            return null;
        }
    }

}
