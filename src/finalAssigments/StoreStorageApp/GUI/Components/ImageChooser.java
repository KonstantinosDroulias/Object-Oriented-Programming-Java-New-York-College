package finalAssigments.StoreStorageApp.GUI.Components;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ImageChooser {
    private String savePath;
    private String savedFileName;

    public void setSavePath(String path) {
        this.savePath = path;
    }

    public void choose(Component parent) {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Images (jpg, jpeg, png)", "jpg", "jpeg", "png");
        chooser.setFileFilter(filter);

        int result = chooser.showOpenDialog(parent);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();

            if (savePath == null || savePath.isEmpty()) {
                JOptionPane.showMessageDialog(parent, "Save path not set!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                File destination = new File(savePath, selectedFile.getName());
                Files.copy(selectedFile.toPath(), destination.toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
                savedFileName = selectedFile.getName();
                JOptionPane.showMessageDialog(parent, "Image saved: " + savedFileName);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(parent, "Failed to save image.", "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    }

    public String getSavedFileName() {
        return savedFileName;
    }
}
