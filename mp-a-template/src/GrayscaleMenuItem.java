import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class GrayscaleMenuItem extends JMenuItem implements ActionListener {

    private final ImageEditor MAIN_PANEL;

    GrayscaleMenuItem(ImageEditor mainPanel) {
        super("Grayscale");
        this.MAIN_PANEL = mainPanel;
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.MAIN_PANEL.addImage(ImageOperations.grayscale(MAIN_PANEL.getImage()));
        this.MAIN_PANEL.repaint();
    }
}
