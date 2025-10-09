import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class InvertMenuItem extends JMenuItem implements ActionListener {

    private final ImageEditor MAIN_PANEL;

    InvertMenuItem(ImageEditor mainPanel) {
        super("Invert");
        this.MAIN_PANEL = mainPanel;
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.MAIN_PANEL.addImage(ImageOperations.invert(MAIN_PANEL.getImage()));
        this.MAIN_PANEL.repaint();
    }
}
