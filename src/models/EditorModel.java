package models;

import javax.swing.event.DocumentEvent;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.util.Arrays;
import java.util.StringTokenizer;

public class EditorModel {

    private static EditGroupModel defaultEditGroup;

    private String inputBuffer = "";

    private static boolean trackChanges = true;

    public EditorModel() {
        defaultEditGroup = new EditGroupModel("Default");
        EditManagerModel.addGroup(defaultEditGroup);
    }

    /**
     * Sets the value of the track changes variable.
     *
     * @param trackChanges True or false
     */
    public static void setTrackChanges(boolean trackChanges) {
        EditorModel.trackChanges = trackChanges;
    }

    /**
     * Pushes the given edit to the default edit group.
     *
     * @param content the edit
     */
    public void pushEdit(String content) {
        EditModel editModelObj = new EditModel(content);
        defaultEditGroup.addEdit(editModelObj);
    }

    /**
     * Processes the buffered input if required.
     */
    public void processBufferedInput() {
        // If the buffer contains something
        if(this.inputBuffer.length() > 0) {
            // Push it to the default edit group
            this.pushEdit(this.inputBuffer);

            // Reset the buffer
            this.inputBuffer = "";
        }
    }

    /**
     * Tracks the changes within the text area.
     *
     * @param documentEvent The document event.
     */
    public void trackChange(DocumentEvent documentEvent) {
        if(trackChanges) {
            // Returns the offset within the document of the start of the change
            int offset = documentEvent.getOffset();
            // System.out.println("Offset: " + offset);

            // Returns the length of the change
            int length = documentEvent.getLength();
            // System.out.println("Length : " + length);

            try {
                // Gets the document that sourced the change event
                Document doc = documentEvent.getDocument();

                // Fetches the text contained within the given portion of the document (aka the input string)
                String inputString = doc.getText(offset, length);

                // System.out.println("Current input: " + inputString);

                String[] editMarkers = {".", ",", ":", ";", "!", "?", " ", "\t", "\n", "\r", "\f"};

                // If the new input string contains any of the edit makers
                if (Arrays.stream(editMarkers).anyMatch(inputString::contains)) {
                    // Break the given string into tokens taking the edit makers as delimiters
                    StringTokenizer st = new StringTokenizer(inputString, Arrays.toString(editMarkers), true);

                    if (st.countTokens() > 1) {
                        while (st.hasMoreTokens()) {
                            String strToken = st.nextToken();
                            // System.out.println("Edit: '" + strToken + "'");
                            this.processBufferedInput();
                            this.pushEdit(strToken);
                        }
                    } else {
                        this.processBufferedInput();
                        // Push the edit maker to the default edit group
                        this.pushEdit(inputString);
                    }
                } else {
                    this.inputBuffer += inputString;
                    // System.out.println("Word so far: " + this.inputBuffer);
                }
            } catch (BadLocationException e) {
                e.printStackTrace();
            }

            // System.out.println("\n");
            // System.out.println(this.defaultEditGroup.toString());
        }
    }

    public static String getChangedContent() {
        StringBuilder editGroupContent = new StringBuilder();

        for(EditModel edit: defaultEditGroup.getEdits()) {
            if (!edit.getUndone()) {
                editGroupContent.append(edit.getContent());
            }
        }

        return editGroupContent.toString();
    }


}
