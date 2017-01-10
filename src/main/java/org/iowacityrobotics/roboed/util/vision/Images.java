package org.iowacityrobotics.roboed.util.vision;

import edu.wpi.first.wpilibj.image.HSLImage;

/**
 * Image manipulation utilities.
 * @author Evan Geng
 */
public class Images {

    /**
     * Serializes an image as a base 64 string.
     * @param img The image to serialize.
     * @return The serialized image.
     */
    public static String serialize(HSLImage img) {
        // TODO Implement
    }
    
    /**
     * Deserializes a base 64 string representing an image.
     * @param ser The serialized image.
     * @return The deserialized image.
     */
    public static HSLImage deserialize(String ser) {
        // TODO Implement
    }
    
    /**
     * Creates an indexer over the pixels in an image.
     * @param img The image to iterate over.
     * @return The indexer.
     */
    public static HSLImageIndexer iterate(HSLImage img) {
        return new HSLImageIndexer(img);
    }

}
