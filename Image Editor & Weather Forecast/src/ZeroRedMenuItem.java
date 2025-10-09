import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ZeroRedMenuItem extends JMenuItem implements ActionListener {

    private final ImageEditor MAIN_PANEL;

    ZeroRedMenuItem(ImageEditor mainPanel) {
        super("Zero Red");
        this.MAIN_PANEL = mainPanel;
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.MAIN_PANEL.addImage(ImageOperations.zeroRed(MAIN_PANEL.getImage()));
        this.MAIN_PANEL.repaint();
    }
}
