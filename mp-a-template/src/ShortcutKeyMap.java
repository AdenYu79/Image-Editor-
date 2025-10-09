import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

class ShortcutKeyMap {

    private final ImageEditor MAIN_PANEL;

    ShortcutKeyMap(ImageEditor mainPanel) {
        this.MAIN_PANEL = mainPanel;
        this.setupUndo();
        this.setupRedo();
        this.setupOpen();
        this.setupSave();
    }

    static void setupOpenFileAction(ImageEditor mainPanel) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setDialogTitle("Open Image");
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("PPM Images", "ppm"));
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            // Write the image to the file.
            mainPanel.readPpmImage(fileChooser.getSelectedFile().getAbsolutePath());
            mainPanel.repaint();
            mainPanel.getMenuBar().enableToolsMenu();
        }
    }

    static void setupSaveFileAction(ImageEditor mainPanel) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setDialogTitle("Save Image");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("PPM Images", "ppm"));
        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            // Write the image to the file.
            mainPanel.writePpmImage(fileChooser.getSelectedFile().getAbsolutePath());
        }
    }

    private void setupUndo() {
        this.MAIN_PANEL.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK), "undo");
        this.MAIN_PANEL.getActionMap().put("undo", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MAIN_PANEL.undoImage();
            }
        });
    }

    private void setupRedo() {
        this.MAIN_PANEL.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_DOWN_MASK), "redo");
        this.MAIN_PANEL.getActionMap().put("redo", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MAIN_PANEL.redoImage();
            }
        });
    }

    private void setupOpen() {
        this.MAIN_PANEL.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK), "open");
        this.MAIN_PANEL.getActionMap().put("open", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setupOpenFileAction(MAIN_PANEL);
            }
        });
    }

    private void setupSave() {
        this.MAIN_PANEL.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK), "save");
        this.MAIN_PANEL.getActionMap().put("save", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setupSaveFileAction(MAIN_PANEL);
            }
        });
    }
}
