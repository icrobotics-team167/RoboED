package org.iowacityrobotics.roboed.util.vision;

import edu.wpi.first.wpilibj.image.HSLImage;

/**
 * Iterates over pixels in an HSLImage.
 * @author Evan Geng
 */
public class HSLImageIndexer {

    /**
     * The image indexed by this indexer.
     */
    private final HSLImage img;
    
    /**
     * Creates a new indexer over an image.
     * @param img The image to index.
     */
    public HSLImageIndexer(HSLImage img) {
        this.img = img;
    }
    
    /**
     * Adds a new pixel processor.
     * @param proc The processor.
     * @return This instance, for chaining.
     */
    public HSLImageIndexer withProcessor(PixelProcessor proc) {
        // TODO Implement
        return this;
    }
    
    /**
     * Performs the iteration.
     */
    public void iterate() {
        // TODO Implement
    }

}
