import javax.swing.*;

class EditMenu extends JMenu {

    EditMenu(ImageEditor mainPanel) {
        super("Edit");
        this.add(new UndoMenuItem(mainPanel));
        this.add(new RedoMenuItem(mainPanel));
//        this.add(new ZoomMenuItem(mainPanel));
    }
}
