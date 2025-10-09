import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.Color;

class ImageOperations {

    /**
     * Removes the red channel from every pixel of the image
     *
     * @param img the source image
     * @return a new BufferedImage with red channel set to 0
     */
    static BufferedImage zeroRed(BufferedImage img) {
        int width = img.getWidth();
        int height = img.getHeight();
        BufferedImage newImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = img.getRGB(x, y);
                int g = ColorOperations.getGreen(rgb);
                int b = ColorOperations.getBlue(rgb);
                int newRgb = new Color(0, g, b).getRGB();
                newImg.setRGB(x, y, newRgb);
            }
        }
        return newImg;
    }

    /**
     * Converts the image to grayscale by averaging color channels
     *
     * @param img the source image
     * @return a new BufferedImage in grayscale
     */
    static BufferedImage grayscale(BufferedImage img) {
        int width = img.getWidth();
        int height = img.getHeight();
        BufferedImage newImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = img.getRGB(x, y);
                int r = ColorOperations.getRed(rgb);
                int g = ColorOperations.getGreen(rgb);
                int b = ColorOperations.getBlue(rgb);
                int avg = (r + g + b) / 3;
                int grayRgb = new Color(avg, avg, avg).getRGB();
                newImg.setRGB(x, y, grayRgb);
            }
        }
        return newImg;
    }

    /**
     * Inverts the color of each pixel
     *
     * @param img the source image
     * @return a new BufferedImage with inverted colors
     */
    static BufferedImage invert(BufferedImage img) {
        int width = img.getWidth();
        int height = img.getHeight();
        BufferedImage newImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = img.getRGB(x, y);
                int r = 255 - ColorOperations.getRed(rgb);
                int g = 255 - ColorOperations.getGreen(rgb);
                int b = 255 - ColorOperations.getBlue(rgb);
                int invert = new Color(r, g, b).getRGB();
                newImg.setRGB(x, y, invert);
            }
        }
        return newImg;
    }

    /**
     * Mirrors the image either left-to-right or top-to-bottom
     *
     * @param img the source image
     * @param dir the direction to mirror
     * @return a new BufferedImage that is mirrored
     */
    static BufferedImage mirror(BufferedImage img, MirrorMenuItem.MirrorDirection dir) {
        int width = img.getWidth();
        int height = img.getHeight();
        BufferedImage newImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        if (dir == MirrorMenuItem.MirrorDirection.VERTICAL) {
            int mid = width / 2;
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    if (x < mid) {
                        newImg.setRGB(x, y, img.getRGB(x, y));
                    } else {
                        newImg.setRGB(x, y, img.getRGB(width - 1 - x, y));
                    }
                }
            }
        } else {
            int mid = height / 2;
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    if (y < mid) {
                        newImg.setRGB(x, y, img.getRGB(x, y));
                    } else {
                        newImg.setRGB(x, y, img.getRGB(x, height - 1 - y));
                    }
                }
            }
        }
        return newImg;
    }

    /**
     * Rotates the image 90 degrees clockwise or counterclockwise
     *
     * @param img the source image
     * @param dir the direction to rotate
     * @return a new BufferedImage that is rotated
     */
    static BufferedImage rotate(BufferedImage img, RotateMenuItem.RotateDirection dir) {
        int width = img.getWidth();
        int height = img.getHeight();
        BufferedImage newImg = new BufferedImage(height, width, BufferedImage.TYPE_INT_RGB);
        if (dir == RotateMenuItem.RotateDirection.CLOCKWISE) {
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int rgb = img.getRGB(x, y);
                    newImg.setRGB(height - 1 - y, x, rgb);
                }
            }
        } else {
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int rgb = img.getRGB(x, y);
                    newImg.setRGB(y, width - 1 - x, rgb);
                }
            }
        }
        return newImg;
    }

    /**
     * Repeats the image either side-by-side or top-to-bottom n times
     *
     * @param img the source image
     * @param n   number of repetitions
     * @param dir the direction to repeat
     * @return a new BufferedImage with the repeated tiles
     */
    static BufferedImage repeat(BufferedImage img, int n, RepeatMenuItem.RepeatDirection dir) {
        int width = img.getWidth();
        int height = img.getHeight();
        BufferedImage newImg;
        if (dir == RepeatMenuItem.RepeatDirection.HORIZONTAL) {
            newImg = new BufferedImage(width * n, height, BufferedImage.TYPE_INT_RGB);
            for (int tile = 0; tile < n; tile++) {
                for (int y = 0; y < height; y++) {
                    for (int x = 0; x < width; x++) {
                        newImg.setRGB(tile * width + x, y, img.getRGB(x, y));
                    }
                }
            }
        } else {
            newImg = new BufferedImage(width, height * n, BufferedImage.TYPE_INT_RGB);
            for (int tile = 0; tile < n; tile++) {
                for (int y = 0; y < height; y++) {
                    for (int x = 0; x < width; x++) {
                        newImg.setRGB(x, tile * height + y, img.getRGB(x, y));
                    }
                }
            }
        }
        return newImg;
    }

    /**
     * Zooms in on the image. The zoom factor increases in multiplicatives of 10% and
     * decreases in multiplicatives of 10%.
     *
     * @param img        the original image to zoom in on. The image cannot be already zoomed in
     *                   or out because then the image will be distorted.
     * @param zoomFactor The factor to zoom in by.
     * @return the zoomed in image.
     */
    static BufferedImage zoom(BufferedImage img, double zoomFactor) {
        if (img == null) {
            return null;
        }
        int newImageWidth = (int) (img.getWidth() * zoomFactor);
        int newImageHeight = (int) (img.getHeight() * zoomFactor);
        BufferedImage newImg = new BufferedImage(newImageWidth, newImageHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = newImg.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(img, 0, 0, newImageWidth, newImageHeight, null);
        g2d.dispose();
        return newImg;
    }
}