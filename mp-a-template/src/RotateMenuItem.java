import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class RotateMenuItem extends JMenu {

    enum RotateDirection {
        CLOCKWISE,
        COUNTER_CLOCKWISE
    }

    RotateMenuItem(ImageEditor mainPanel) {
        super("Rotate");
        this.add(new RotateClockwiseMenuItem(mainPanel));
        this.add(new RotateCounterClockwiseMenuItem(mainPanel));
    }

    private static class RotateClockwiseMenuItem extends JMenuItem implements ActionListener {

        private final ImageEditor MAIN_PANEL;

        RotateClockwiseMenuItem(ImageEditor mainPanel) {
            super("Clockwise ↻");
            this.MAIN_PANEL = mainPanel;
            this.addActionListener(this);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            this.MAIN_PANEL.addImage(ImageOperations.rotate(MAIN_PANEL.getImage(), RotateDirection.CLOCKWISE));
            this.MAIN_PANEL.repaint();
        }
    }

    private static class RotateCounterClockwiseMenuItem extends JMenuItem implements ActionListener {

        private final ImageEditor MAIN_PANEL;

        RotateCounterClockwiseMenuItem(ImageEditor mainPanel) {
            super("Counter-clockwise ↺");
            this.MAIN_PANEL = mainPanel;
            this.addActionListener(this);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            this.MAIN_PANEL.addImage(ImageOperations.rotate(MAIN_PANEL.getImage(), RotateDirection.COUNTER_CLOCKWISE));
            this.MAIN_PANEL.repaint();
        }
    }
}
