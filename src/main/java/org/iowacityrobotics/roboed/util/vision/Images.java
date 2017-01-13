package org.iowacityrobotics.roboed.util.vision;

import org.apache.commons.codec.binary.Base64;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;

import java.nio.ByteBuffer;

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
    public static String serialize(Mat img) {
        Size imgSize = img.size();
        int width = (int)imgSize.width;
        int height = (int)imgSize.height;
        int depth = img.channels();
        ByteBuffer buf = ByteBuffer.allocate(width * height * depth + 6);
        buf.put(new byte[] {
                (byte)((height & 0xFF00) >> 8),
                (byte)(height & 0xFF),
                (byte)((width & 0xFF00) >> 8),
                (byte)(width & 0xFF),
                (byte)((depth & 0xFF00) >> 8),
                (byte)(depth & 0xFF)
        });
        byte[] temp = new byte[depth];
        for (int row = 0; row < img.rows(); row++) {
            for (int col = 0; col < img.cols(); col++) {
                img.get(row, col, temp);
                buf.put(temp);
            }
        }
        return Base64.encodeBase64String(buf.array());
    }
    
    /**
     * Deserializes a base 64 string representing an image.
     * @param ser The serialized image.
     * @return The deserialized image.
     */
    public static Mat deserialize(String ser) {
        byte[] imgArr = Base64.decodeBase64(ser);
        int width = (Byte.toUnsignedInt(imgArr[0]) << 8) | Byte.toUnsignedInt(imgArr[1]);
        int height = (Byte.toUnsignedInt(imgArr[2]) << 8) | Byte.toUnsignedInt(imgArr[3]);
        int depth = (Byte.toUnsignedInt(imgArr[4]) << 8) | Byte.toUnsignedInt(imgArr[5]);
        Mat img = Mat.zeros(width, height, CvType.CV_8UC(depth));
        byte[] temp = new byte[depth];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int index = 6 + i * height * depth + j * depth;
                System.arraycopy(imgArr, index, temp, 0, depth);
                img.put(i, j, temp);
            }
        }
        return img;
    }

}
