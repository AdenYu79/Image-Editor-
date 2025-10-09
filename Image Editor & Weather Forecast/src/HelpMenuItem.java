import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class HelpMenuItem extends JMenuItem implements ActionListener {

    private static final String HELP_MESSAGE =
            "This is a simple image editor.\n"
            + "Open Image: To open an image, use the \"Open\" option from the File menu.\n"
            + "Transformations:After opening an image, the \"Tools\" menu will be enabled. Use the Tools menu to apply various transformations to the image.\n"
            + "Zoom In/Out: You can also zoom in and out of the image by right-clicking on it and using the mouse wheel.\n"
            + "\tWARNING: Do not apply a transformation, then zoom in, and then apply another transformation. "
            + "\tTransformations can only be applied to a non-zoomed image.\n"
            + "Save Image: To save the image, use the \"Save As\" option from the File menu.";

    HelpMenuItem() {
        super("Getting Started");
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null, HELP_MESSAGE, "Help", JOptionPane.INFORMATION_MESSAGE);
    }
}
