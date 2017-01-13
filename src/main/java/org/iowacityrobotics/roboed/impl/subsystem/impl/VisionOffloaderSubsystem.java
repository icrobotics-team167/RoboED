package org.iowacityrobotics.roboed.impl.subsystem.impl;

import org.iowacityrobotics.roboed.api.data.Data;
import org.iowacityrobotics.roboed.api.data.DataUnavailableException;
import org.iowacityrobotics.roboed.api.data.IDataSource;
import org.iowacityrobotics.roboed.api.subsystem.ISubsystem;
import org.iowacityrobotics.roboed.api.subsystem.ISubsystemType;
import org.iowacityrobotics.roboed.api.subsystem.provider.IGenericSubsystemProvider;
import org.iowacityrobotics.roboed.impl.subsystem.FRCSubsystem;
import org.iowacityrobotics.roboed.impl.subsystem.FRCSubsystemType;
import org.iowacityrobotics.roboed.impl.subsystem.FRCSysRegistry;
import org.iowacityrobotics.roboed.util.vision.Images;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import org.opencv.core.Mat;

/**
 * @author Evan Geng
 */
public class VisionOffloaderSubsystem extends FRCSubsystem<Mat, JsonNode> {

    public static final ISubsystemType<Mat, JsonNode, Provider> TYPE = new FRCSubsystemType<>();

    private final String host, routine;
    private final IDataSource<JsonNode> output;
    private IDataSource<Mat> input;

    protected VisionOffloaderSubsystem(int id, VisionOffloaderConfig config) {
        super(TYPE, id);
        this.host = config.host;
        this.routine = config.routine;
        this.output = Data.provider(() -> {
            Mat img = input.request();
            try {
                HttpResponse<JsonNode> resp = Unirest.post(host)
                        .queryString("routine", routine)
                        .body(Images.serialize(img))
                        .asJson();
                if (resp.getStatus() != 200)
                    throw new DataUnavailableException("Encountered " + resp.getStatus() + ": " + resp.getStatusText());
                return resp.getBody();
            } catch (UnirestException e) {
                throw new DataUnavailableException("Vision server comms failed: " + e.getMessage());
            }
        });
    }

    @Override
    public void bind(IDataSource<Mat> input) {
        this.input = input;
    }

    @Override
    public IDataSource<JsonNode> output() {
        return output;
    }

    public static class VisionOffloaderConfig {

        public final String host, routine;

        public VisionOffloaderConfig(String host, String routine) {
            this.host = host;
            this.routine = routine;
        }

    }

    public static class Provider implements IGenericSubsystemProvider<Mat, JsonNode, VisionOffloaderConfig> {

        private final FRCSysRegistry registry;

        public Provider(FRCSysRegistry registry) {
            this.registry = registry;
        }

        @Override
        public ISubsystem<Mat, JsonNode> getSubsystem(VisionOffloaderConfig config) {
            return new VisionOffloaderSubsystem(registry.nextUnusedId(), config);
        }

    }

}
