import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * This class is a utility class that provides methods for retrieving color information from images.
 * It should be used by the ImageEditor class and, more importantly, the ImageOperations class.
 * Of course, if you know how to use bitwise operations, then this entire class is unnecessary; we
 * provide it as a means for students who are not familiar with bitwise operations to complete the assignment.
 */
class ColorOperations {

    /**
     * Retrieves the color at the specified coordinate position in the image.
     *
     * @param img image to retrieve color from.
     * @param x   x-coordinate of the pixel.
     * @param y   y-coordinate of the pixel.
     * @return Color object representing the color at the specified position.
     */
    static Color getColorAtPos(BufferedImage img, int x, int y) {
        int rgb = img.getRGB(x, y);
        int red = (rgb >> 16) & 0xFF;
        int grn = (rgb >> 8) & 0xFF;
        int blu = rgb & 0xFF;
        return new Color(red, grn, blu);
    }

    /**
     * Retrieves the red channel value from the specified RGB value.
     *
     * @param rgb RGB integer value.
     * @return red channel value.
     */
    static int getRed(int rgb) {
        return (rgb >> 16) & 0xFF;
    }

    /**
     * Retrieves the green channel value from the specified RGB value.
     *
     * @param rgb RGB integer value.
     * @return green channel value.
     */
    static int getGreen(int rgb) {
        return (rgb >> 8) & 0xFF;
    }

    /**
     * Retrieves the blue channel value from the specified RGB value.
     *
     * @param rgb RGB integer value.
     * @return blue channel value.
     */
    static int getBlue(int rgb) {
        return rgb & 0xFF;
    }

}
