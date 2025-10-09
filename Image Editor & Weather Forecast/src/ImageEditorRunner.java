import javax.swing.*;
import java.awt.*;

class xImageEditorRunner {

    private static final String TITLE = "Image Editor";
    private static final int DEFAULT_WIDTH = 512;
    private static final int DEFAULT_HEIGHT = 512;

    public static void main(String[] args) {
        JFrame frame = new JFrame(TITLE);
        frame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        frame.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        frame.setMaximumSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(new ImageEditor());
        frame.setVisible(true);
    }
}
