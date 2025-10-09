import javax.swing.*;
import java.awt.*;

class ImagePanel extends JPanel {

    private final ImageEditor MAIN_PANEL;

    ImagePanel(ImageEditor mainPanel) {
        this.MAIN_PANEL = mainPanel;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.MAIN_PANEL.getImage() != null) {
            g.drawImage(this.MAIN_PANEL.getImage(), 0, 0, null);
            // If we are zooming, draw a magnifying glass at the mouse location.
            if (this.MAIN_PANEL.getZoomListener().isZoomEnabled()) {
                Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
                SwingUtilities.convertPointFromScreen(mouseLocation, this);
                int x = mouseLocation.x - 5;
                int y = mouseLocation.y - 5;
                g.setColor(Color.BLACK);
                g.drawOval(x, y, 10, 10);
                g.drawLine(x + 5, y, x + 5, y + 10);
                g.drawLine(x, y + 5, x + 10, y + 5);
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        if (this.MAIN_PANEL.getImage() == null) {
            return super.getPreferredSize();
        } else {
            return new Dimension(this.MAIN_PANEL.getImage().getWidth(), this.MAIN_PANEL.getImage().getHeight());
        }
    }
}
