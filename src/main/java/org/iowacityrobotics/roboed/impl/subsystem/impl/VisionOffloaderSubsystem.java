package org.iowacityrobotics.roboed.impl.subsystem.impl;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.http.utils.ResponseUtils;
import org.iowacityrobotics.roboed.api.data.Data;
import org.iowacityrobotics.roboed.api.data.DataUnavailableException;
import org.iowacityrobotics.roboed.api.data.IDataSource;
import org.iowacityrobotics.roboed.api.subsystem.ISubsystem;
import org.iowacityrobotics.roboed.api.subsystem.ISubsystemType;
import org.iowacityrobotics.roboed.api.subsystem.provider.IGenericSubsystemProvider;
import org.iowacityrobotics.roboed.impl.subsystem.FRCSubsystem;
import org.iowacityrobotics.roboed.impl.subsystem.FRCSubsystemType;
import org.iowacityrobotics.roboed.impl.subsystem.FRCSysRegistry;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Evan Geng
 */
public class VisionOffloaderSubsystem extends FRCSubsystem<byte[], byte[]> {

    public static final ISubsystemType<byte[], byte[], Provider> TYPE = new FRCSubsystemType<>();

    private final String host, routine;
    private final IDataSource<byte[]> output;
    private IDataSource<byte[]> input;

    protected VisionOffloaderSubsystem(int id, VisionOffloaderConfig config) {
        super(TYPE, id);
        this.host = config.host;
        this.routine = config.routine;
        this.output = Data.provider(() -> {
            byte[] imgData = input.request();
            try {
                HttpResponse<InputStream> resp = Unirest.post(host)
                        .queryString("routine", routine)
                        .body(imgData)
                        .asBinary();
                if (resp.getStatus() != 200)
                    throw new DataUnavailableException("Encountered " + resp.getStatus() + ": " + resp.getStatusText());
                try (InputStream str = resp.getBody()) {
                    return ResponseUtils.getBytes(str);
                }
            } catch (UnirestException | IOException e) {
                throw new DataUnavailableException("Vision server comms failed: " + e.getMessage());
            }
        });
    }

    @Override
    public void bind(IDataSource<byte[]> input) {
        this.input = input;
    }

    @Override
    public IDataSource<byte[]> output() {
        return output;
    }

    public static class VisionOffloaderConfig {

        public final String host, routine;

        public VisionOffloaderConfig(String host, String routine) {
            this.host = host;
            this.routine = routine;
        }


    }

    public static class Provider implements IGenericSubsystemProvider<byte[], byte[], VisionOffloaderConfig> {

        private final FRCSysRegistry registry;

        public Provider(FRCSysRegistry registry) {
            this.registry = registry;
        }

        @Override
        public ISubsystem<byte[], byte[]> getSubsystem(VisionOffloaderConfig config) {
            return new VisionOffloaderSubsystem(registry.nextUnusedId(), config);
        }

    }

}
