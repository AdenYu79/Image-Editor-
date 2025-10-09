import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class MirrorMenuItem extends JMenu {

    enum MirrorDirection {
        VERTICAL,
        HORIZONTAL
    }

    MirrorMenuItem(ImageEditor mainPanel) {
        super("Mirror");
        this.add(new MirrorHorizontalMenuItem(mainPanel));
        this.add(new MirrorVerticalMenuItem(mainPanel));
    }

    private static class MirrorHorizontalMenuItem extends JMenuItem implements ActionListener {

        private final ImageEditor MAIN_PANEL;

        MirrorHorizontalMenuItem(ImageEditor mainPanel) {
            super("Horizontal");
            this.MAIN_PANEL = mainPanel;
            this.addActionListener(this);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            this.MAIN_PANEL.addImage(ImageOperations.mirror(MAIN_PANEL.getImage(), MirrorDirection.HORIZONTAL));
            this.MAIN_PANEL.repaint();
        }
    }

    private static class MirrorVerticalMenuItem extends JMenuItem implements ActionListener {

        private final ImageEditor MAIN_PANEL;

        MirrorVerticalMenuItem(ImageEditor mainPanel) {
            super("Vertical");
            this.MAIN_PANEL = mainPanel;
            this.addActionListener(this);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            this.MAIN_PANEL.addImage(ImageOperations.mirror(MAIN_PANEL.getImage(), MirrorDirection.VERTICAL));
            this.MAIN_PANEL.repaint();
        }
    }
}
