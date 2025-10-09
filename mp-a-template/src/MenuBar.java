import javax.swing.*;

class MenuBar extends JMenuBar {

    private final JMenu FILE_MENU;
    private final JMenu EDIT_MENU;
    private final JMenu TOOLS_MENU;
    private final JMenu HELP_MENU;

    MenuBar(ImageEditor mainPanel) {
        this.FILE_MENU = new FileMenu(mainPanel);
        this.EDIT_MENU = new EditMenu(mainPanel);
        this.TOOLS_MENU = new ToolsMenu(mainPanel);
        this.HELP_MENU = new HelpMenu();
        this.TOOLS_MENU.setEnabled(false); // Disabled until an image is loaded.
        this.add(this.FILE_MENU);
        this.add(this.EDIT_MENU);
        this.add(this.TOOLS_MENU);
        this.add(this.HELP_MENU);
    }

    void enableToolsMenu() {
        this.TOOLS_MENU.setEnabled(true);
    }
}
