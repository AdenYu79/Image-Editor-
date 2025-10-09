import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Stack;
import java.util.Scanner;

class ImageEditor extends JPanel {

    private final Stack<BufferedImage> UNDO_STACK;
    private final Stack<BufferedImage> REDO_STACK;
    private final JPanel IMAGE_PANEL;
    private final JMenuBar MENU_BAR;
    private final JScrollPane SCROLL_PANE;
    private final ShortcutKeyMap SHORTCUT_KEY_MAP;
    private final ZoomMouseEventListener ZOOM_LISTENER;
    private int zoomImageIndex;

    ImageEditor() {
        this.UNDO_STACK = new Stack<>();
        this.REDO_STACK = new Stack<>();
        this.SHORTCUT_KEY_MAP = new ShortcutKeyMap(this);
        this.IMAGE_PANEL = new ImagePanel(this);
        this.SCROLL_PANE = new JScrollPane(this.IMAGE_PANEL, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.MENU_BAR = new MenuBar(this);
        this.ZOOM_LISTENER = new ZoomMouseEventListener(this, this.IMAGE_PANEL);
        this.zoomImageIndex = 0;
        this.setLayout(new BorderLayout());
        this.add(this.MENU_BAR, BorderLayout.NORTH);
        this.add(this.SCROLL_PANE, BorderLayout.CENTER);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.IMAGE_PANEL.repaint();
    }

    @Override
    public void revalidate() {
        super.revalidate();
        if (this.IMAGE_PANEL != null) {
            this.IMAGE_PANEL.revalidate();
        }
    }

    /**
     * Reads a PPM image file, parses its header, constructs a BufferedImage with those dimensions,
     * reads each pixel's RGB values, clears undo/redo history, and adds the resulting image to the editor
     *
     * @param in the file path to the input PPM image
     */
    void readPpmImage(String in) {
        try {
            Scanner sc = new Scanner(new File(in));
            String magic = sc.next();
            if (!"P3".equals(magic)) {
                sc.close();
                throw new IllegalArgumentException("Unsupported PPM format: " + magic);
            }
            int width = sc.nextInt();
            int height = sc.nextInt();
            int maxVal = sc.nextInt();
            if (maxVal != 255) {
                sc.close();
                throw new IOException(
                        "Unsupported PPM max color value: expected 255, got " + maxVal
                );
            }
            BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int r = sc.nextInt();
                    int g = sc.nextInt();
                    int b = sc.nextInt();
                    img.setRGB(x, y, new Color(r, g, b).getRGB());
                }
            }
            sc.close();

            // Do not modify the lines below.
            this.UNDO_STACK.clear();
            this.REDO_STACK.clear();
            this.zoomImageIndex = 0;
            this.addImage(img);
        } catch (IOException | IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Writes the current image at the top of the undo stack to a PPM file in ASCII format, including
     * the header and each pixel's red, green, and blue values
     *
     * @param out the file path for the output PPM image
     */
    void writePpmImage(String out) {
        try {
            BufferedImage img = this.getImage();
            if (img == null) {
                throw new IllegalStateException("No image loaded: nothing to write to " + out);
            }
            try (PrintWriter pw = new PrintWriter(new FileWriter(out))) {
                int width = img.getWidth();
                int height = img.getHeight();
                pw.println("P3");
                pw.println(width + " " + height);
                pw.println(255);
                for (int y = 0; y < height; y++) {
                    for (int x = 0; x < width; x++) {
                        int rgb = img.getRGB(x, y);
                        int r = ColorOperations.getRed(rgb);
                        int g = ColorOperations.getGreen(rgb);
                        int b = ColorOperations.getBlue(rgb);
                        pw.println(r + " " + g + " " + b);
                    }
                }
            }
        } catch (IOException ioe) {
            throw new UncheckedIOException("Failed to write PPM to " + out, ioe);
        }
    }

    /**
     * Adds a new image to the editor and the undo stack. It is assumed that the image
     * being passed is not zoomed. If so, use the other addImage method.
     *
     * @param img image to add.
     */
    void addImage(BufferedImage img) {
        this.UNDO_STACK.push(img);
        this.REDO_STACK.clear();
        this.revalidate();
        this.repaint();
        this.zoomImageIndex++;
    }

    /**
     * Adds a new zoomed image to the editor. Because we only want to apply transformations
     * to non-zoomed images, we need to keep track of where the last non-zoomed image is in
     * the undo stack.
     *
     * @param img    image to add.
     * @param zoomed flag indicating whether the image is zoomed. This is always true.
     */
    void addImage(BufferedImage img, boolean zoomed) {
        this.UNDO_STACK.push(img);
        this.REDO_STACK.clear();
        this.revalidate();
        this.repaint();
        if (!zoomed) {
            this.zoomImageIndex++;
        }
    }

    /**
     * Removes the current image from the editor and the undo stack.
     * The undone image is pushed to the redo stack. If there are no images
     * to undo, this method does nothing.
     */
    void undoImage() {
        if (!this.UNDO_STACK.isEmpty()) {
            this.REDO_STACK.push(this.UNDO_STACK.pop());
            this.revalidate();
            this.repaint();
        }
    }

    /**
     * Redoes the last undone image. The redone image is pushed to the undo stack.
     * If there are no images to redo, this method does nothing.
     */
    void redoImage() {
        if (!this.REDO_STACK.isEmpty()) {
            this.UNDO_STACK.push(this.REDO_STACK.pop());
            this.revalidate();
            this.repaint();
        }
    }

    Stack<BufferedImage> getUndoStack() {
        return this.UNDO_STACK;
    }

    Stack<BufferedImage> getRedoStack() {
        return this.REDO_STACK;
    }

    BufferedImage getImage() {
        return this.UNDO_STACK.isEmpty() ? null : this.UNDO_STACK.peek();
    }

    BufferedImage getOriginalImage() {
        if (this.zoomImageIndex < 1 || this.zoomImageIndex >= this.UNDO_STACK.size()) {
            return null;
        } else {
            return this.UNDO_STACK.elementAt(this.zoomImageIndex - 1);
        }
    }

    MenuBar getMenuBar() {
        return (MenuBar) MENU_BAR;
    }

    JScrollPane getScrollPane() {
        return this.SCROLL_PANE;
    }

    ZoomMouseEventListener getZoomListener() {
        return this.ZOOM_LISTENER;
    }
}
