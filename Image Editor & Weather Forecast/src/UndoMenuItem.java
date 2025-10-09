import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class UndoMenuItem extends JMenuItem implements ActionListener {

    private final ImageEditor MAIN_PANEL;

    UndoMenuItem(ImageEditor mainPanel) {
        super("Undo (CTRL+Z)");
        this.MAIN_PANEL = mainPanel;
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.MAIN_PANEL.getUndoStack().isEmpty()) {
        } else {
            this.MAIN_PANEL.undoImage();
            this.MAIN_PANEL.repaint();
        }
    }
}
