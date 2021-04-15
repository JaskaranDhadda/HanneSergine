package main.controllers;

import main.models.EditGroupModel;
import main.models.EditManagerModel;
import main.models.EditorModel;
import main.views.EditManager;
import main.views.Editor;
import main.views.HTMLViewer;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class EditorController extends BaseController {

    JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

    /**
     * Clears the text area of the editor.
     */
    public void newFile() {
        // TODO Delete all groups and edits
        EditorModel.setTrackChanges(false);
        Editor.txtArea.setText("");
        EditorModel.setTrackChanges(true);
    }

    /**
     * Renders the altered text into the editor's textarea following any modification to the stored edits.
     */
    public void renderContent() {
        //  Temporarily disable the tracking for edits with the text area
        EditorModel.setTrackChanges(false);
        Editor.txtArea.setText(EditorModel.getChangedContent());
        EditorModel.setTrackChanges(true);
    }

    /**
     * Opens a text file and loads its content into the editor's textarea.
     */
    public void openFile() {
        StringBuilder fileContent = new StringBuilder();

        try {
            //  Set the title for JFileChooser window
            this.jfc.setDialogTitle("Open a .txt file");

            //  Instruction to display only files
            this.jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);

            //  Accept only .txt files
            FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
            this.jfc.setFileFilter(filter);

            //  Pops up an "Open File" file chooser dialog.
            int chooserOption = this.jfc.showOpenDialog(null);

            //  If approve (yes, ok, open) is chosen
            if(chooserOption == JFileChooser.APPROVE_OPTION) {
                //  Try to read the chosen file
                File f = new File(this.jfc.getSelectedFile().getAbsolutePath());
                FileReader read = new FileReader(f);
                Scanner scan = new Scanner(read);
                while (scan.hasNextLine()) {
                    String line = scan.nextLine() + "\n";
                    fileContent.append(line);
                }
                scan.close();
            }
        } catch (FileNotFoundException | NullPointerException | IllegalStateException ex) {
            this.displayError("Apologies, an error occurred while processing the file.", "File Opening Error");
            ex.printStackTrace();
        }
        //  Render the file content into the editor's textarea
        Editor.txtArea.setText(fileContent.toString());
    }

    /**
     * Saves the text within editor textarea into a file.
     */
    public void saveFile() {
        try {
            //  Set the title for JFileChooser window
            this.jfc.setDialogTitle("Choose a destination.");

            //  Pops up an "Save File" file chooser dialog
            int chooserOption = jfc.showSaveDialog(null);

            //  If approve (yes, ok, open) is chosen
            if(chooserOption == JFileChooser.APPROVE_OPTION) {
                File f = new File(jfc.getSelectedFile().getAbsolutePath());
                FileWriter out = new FileWriter(f);
                out.write(Editor.txtArea.getText());
                out.close();
            }
        } catch (IOException | NullPointerException ex) {
            this.displayError("Apologies, an error occurred while saving the file.", "File Saving Error");
            ex.printStackTrace();
        }
    }

    /**
     * Renders the 'edit manager' view.
     */
    public void editManager() {
        try {
            ArrayList<EditGroupModel> edits = EditManagerModel.getEditGroups();
            if (edits.get(0).getEdits().size() > 0) {
                EditManager em = new EditManager(edits);
                em.setSize(640, 480);
                em.setLocationRelativeTo(null);
                em.setVisible(true);
            } else {
                this.displayInfo("Please type something or open a file.", "No Edits");
            }
        } catch (Exception e) {
            this.displayError("Apologies, an error occurred while opening the 'edit manager' window.", "Edit Manager Window Error");
            e.printStackTrace();
        }
    }

    /**
     * Renders the 'help' view.
     */
    public void help() {
        try {
            HTMLViewer h = new HTMLViewer("Help", Paths.get("src/main/resources/text/help.html"));
            h.setSize(640, 480);
            h.setLocationRelativeTo(null);
            h.setVisible(true);
        } catch (Exception e) {
            this.displayError("Apologies, an error occurred while opening the 'help' view.", "Help View Error");
            e.printStackTrace();
        }
    }

    /**
     * Renders the 'about us' view.
     */
    public void about() {
        try {
            HTMLViewer h = new HTMLViewer("About", Paths.get("src/main/resources/text/aboutUs.html"));
            h.setSize(640, 480);
            h.setLocationRelativeTo(null);
            h.setVisible(true);
        } catch (Exception e) {
            this.displayError("Apologies, an error occurred while opening the 'about us' view.", "About Us View Error");
            e.printStackTrace();
        }
    }

    /**
     * Terminates the running application.
     */
    public void exitApp() {
        try {
            System.exit(0);
        } catch (Exception e) {
            this.displayError("Apologies, an error occurred while trying to exit the application.", "Exit App Error");
            e.printStackTrace();
        }
    }
}
