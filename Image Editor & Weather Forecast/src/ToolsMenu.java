import javax.swing.*;

class ToolsMenu extends JMenu {

    ToolsMenu(ImageEditor mainPanel) {
        super("Tools");
        // Create the menu items and disable them.
        JMenuItem zeroRed = new ZeroRedMenuItem(mainPanel);
        zeroRed.setEnabled(true);
        JMenuItem grayscale = new GrayscaleMenuItem(mainPanel);
        grayscale.setEnabled(true);
        JMenuItem invert = new InvertMenuItem(mainPanel);
        invert.setEnabled(true);
        JMenuItem mirror = new MirrorMenuItem(mainPanel);
        mirror.setEnabled(true);
        JMenuItem repeat = new RepeatMenuItem(mainPanel);
        repeat.setEnabled(true);
        JMenuItem rotate = new RotateMenuItem(mainPanel);
        rotate.setEnabled(true);

        // Add the menu items to the menu.
        this.add(zeroRed);
        this.add(grayscale);
        this.add(invert);
        this.add(mirror);
        this.add(repeat);
        this.add(rotate);
    }
}
