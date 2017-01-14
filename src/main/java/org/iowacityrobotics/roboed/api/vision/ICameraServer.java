package org.iowacityrobotics.roboed.api.vision;

/**
 * Interface representing the camera server, which provides instances of {@link IImageProvider}.
 * @author Evan Geng
 */
public interface ICameraServer {

    /**
     * Creates a new camera wrapper to grab images from.
     * @param type The type of the camera.
     * @param identifier The camera's identifier.
     * @param <T> The type of the identifier.
     * @return A wrapper around the specified camera.
     */
    <T> IImageProvider getCamera(CameraType<T> type, T identifier);

    /**
     * Registers a new image source to be polled for images to send to the driver station.
     * @param name The name to assign to the image source.
     * @param provider The image source.
     */
    void putImageSource(String name, IImageProvider provider);

}
