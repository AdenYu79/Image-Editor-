import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class RedoMenuItem extends JMenuItem implements ActionListener {

    private final ImageEditor MAIN_PANEL;

    RedoMenuItem(ImageEditor mainPanel) {
        super("Redo (CTRL+Y)");
        this.MAIN_PANEL = mainPanel;
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.MAIN_PANEL.getRedoStack().isEmpty()) {
        } else {
            this.MAIN_PANEL.redoImage();
            this.MAIN_PANEL.repaint();
        }
    }
}
