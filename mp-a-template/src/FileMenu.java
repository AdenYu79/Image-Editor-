import javax.swing.*;

class FileMenu extends JMenu {

    FileMenu(ImageEditor mainPanel) {
        super("File");
        this.add(new OpenMenuItem(mainPanel));
        this.add(new SaveMenuItem(mainPanel));
    }
}
