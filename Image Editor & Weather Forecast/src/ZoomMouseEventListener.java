import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

class ZoomMouseEventListener implements MouseListener, MouseWheelListener {

    private static final double ZOOM_IN_FACTOR = 1.1;
    private static final double ZOOM_OUT_FACTOR = 0.9;
    private final ImageEditor MAIN_PANEL;
    private boolean isZoomEnabled;
    private double zoomFactor;

    ZoomMouseEventListener(ImageEditor mainPanel, JPanel imagePanel) {
        this.isZoomEnabled = false;
        this.zoomFactor = 1;
        this.MAIN_PANEL = mainPanel;
        imagePanel.addMouseListener(this);
        imagePanel.addMouseWheelListener(this);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) {
            this.isZoomEnabled = true;
            this.MAIN_PANEL.repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) {
            this.isZoomEnabled = false;
            this.MAIN_PANEL.repaint();
        }
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (this.isZoomEnabled) {
            this.zoomFactor *= e.getWheelRotation() < 0 ? ZOOM_IN_FACTOR : ZOOM_OUT_FACTOR;
            this.MAIN_PANEL.addImage(ImageOperations.zoom(this.MAIN_PANEL.getOriginalImage(), this.zoomFactor), true);
        } else {
            this.MAIN_PANEL.getScrollPane().dispatchEvent(e);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    boolean isZoomEnabled() {
        return this.isZoomEnabled;
    }
}
