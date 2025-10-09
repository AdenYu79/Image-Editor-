import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class RepeatMenuItem extends JMenu {

    enum RepeatDirection {
        HORIZONTAL,
        VERTICAL
    }

    RepeatMenuItem(ImageEditor mainPanel) {
        super("Repeat");
        this.add(new RepeatHorizontalMenuItem(mainPanel));
        this.add(new RepeatVerticalMenuItem(mainPanel));
    }

    private static class RepeatHorizontalMenuItem extends JMenuItem implements ActionListener {

        private final ImageEditor MAIN_PANEL;

        RepeatHorizontalMenuItem(ImageEditor mainPanel) {
            super("Horizontal");
            this.MAIN_PANEL = mainPanel;
            this.addActionListener(this);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int n = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of times to repeat the image horizontally:"));
            this.MAIN_PANEL.addImage(ImageOperations.repeat(MAIN_PANEL.getImage(), n, RepeatDirection.HORIZONTAL));
            this.MAIN_PANEL.repaint();
        }
    }

    private static class RepeatVerticalMenuItem extends JMenuItem implements ActionListener {

        private final ImageEditor MAIN_PANEL;

        RepeatVerticalMenuItem(ImageEditor mainPanel) {
            super("Vertical");
            this.MAIN_PANEL = mainPanel;
            this.addActionListener(this);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int n = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of times to repeat the image horizontally:"));
            this.MAIN_PANEL.addImage(ImageOperations.repeat(MAIN_PANEL.getImage(), n, RepeatDirection.VERTICAL));
            this.MAIN_PANEL.repaint();
        }
    }
}
