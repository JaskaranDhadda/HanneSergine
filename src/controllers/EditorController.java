package controllers;

import models.EditGroupModel;
import models.EditManagerModel;
import models.EditorModel;
import views.EditManager;
import views.Editor;
import views.HTMLViewer;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class EditorController extends BaseController {

    JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

    /**
     * Clears the text area of the editor.
     *
     * @return
     */
    public String newFile() {
        return "";
    }

    public void rerender() {
        EditorModel.setTrackChanges(false);
        Editor.txtArea.setText(EditorModel.getChangedContent());
        EditorModel.setTrackChanges(true);
    }

    /**
     * Opens a text file and loads its content into the editor's textarea.
     *
     * @return
     */
    public String openFile() {
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        jfc.setDialogTitle("Open a .txt file");
        jfc.setFileFilter(filter);
        int chooserOption = jfc.showSaveDialog(null);

        StringBuilder fileContent = new StringBuilder();

        if(chooserOption == JFileChooser.APPROVE_OPTION) {
            File f = new File(jfc.getSelectedFile().getAbsolutePath());
            try {
                FileReader read = new FileReader(f);
                Scanner scan = new Scanner(read);
                while (scan.hasNextLine()) {
                    String line = scan.nextLine() + "\n";
                    fileContent.append(line);
                }
                scan.close();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        }

        return fileContent.toString();
    }

    /**
     * Saves the text within editor textarea into a file.
     *
     * @param fileText
     */
    public void saveFile(String fileText) {
        try {
            jfc.setDialogTitle("Choose destination.");
            int chooserOption = jfc.showSaveDialog(null);
            File f = new File(jfc.getSelectedFile().getAbsolutePath());
            FileWriter out = new FileWriter(f);
            out.write(fileText);
            out.close();
        } catch (IOException ex) {
            Component f = null;
            JOptionPane.showMessageDialog(f, "Error");
        }
    }

    /**
     * Renders the 'edit manager' view.
     */
    public void editManager() {
        ArrayList<EditGroupModel> edits = EditManagerModel.getEditGroups();
        EditManager em = new EditManager(edits);
        em.setSize(640, 480);
        em.setLocationRelativeTo(null);
        em.setVisible(true);
    }

    /**
     * Renders the 'help' view.
     */
    public void help() {
        HTMLViewer h = new HTMLViewer("Help", Paths.get("src/resources/text/help.html"));
        h.setSize(640, 480);
        h.setLocationRelativeTo(null);
        h.setVisible(true);
    }

    /**
     * Renders the 'about us' view.
     */
    public void about() {
        HTMLViewer h = new HTMLViewer("About", Paths.get("src/resources/text/aboutUs.html"));
        h.setSize(640, 480);
        h.setLocationRelativeTo(null);
        h.setVisible(true);
    }

    /**
     * Terminates the running application.
     */
    public void exitApp() {
        System.exit(0);
    }
}
