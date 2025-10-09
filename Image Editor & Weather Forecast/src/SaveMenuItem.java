import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class SaveMenuItem extends JMenuItem implements ActionListener {

    private final ImageEditor MAIN_PANEL;

    SaveMenuItem(ImageEditor mainPanel) {
        super("Save As (CTRL+S)");
        this.MAIN_PANEL = mainPanel;
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ShortcutKeyMap.setupSaveFileAction(this.MAIN_PANEL);
    }
}
