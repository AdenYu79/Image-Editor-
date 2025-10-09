import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class OpenMenuItem extends JMenuItem implements ActionListener {

    private final ImageEditor MAIN_PANEL;

    OpenMenuItem(ImageEditor mainPanel) {
        super("Open (CTRL+O)");
        this.MAIN_PANEL = mainPanel;
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ShortcutKeyMap.setupOpenFileAction(this.MAIN_PANEL);
    }
}
