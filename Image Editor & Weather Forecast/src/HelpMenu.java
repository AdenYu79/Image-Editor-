import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class HelpMenu extends JMenu {

    HelpMenu() {
        super("Help");
        this.add(new HelpMenuItem());
    }
}
