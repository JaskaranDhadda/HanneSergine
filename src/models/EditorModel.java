package models;

import javax.swing.event.DocumentEvent;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.util.Arrays;
import java.util.StringTokenizer;

public class EditorModel {

    private EditGroupModel defaultEditGroup;
    private String inputBuffer = "";

    public EditorModel() {
        this.defaultEditGroup = new EditGroupModel("Default");
        EditManagerModel.addGroup(this.defaultEditGroup);
    }

    /**
     * Pushes the given edit to the default edit group.
     *
     * @param content the edit
     */
    public void pushEdit(String content) {
        EditModel editModelObj = new EditModel(content);
        this.defaultEditGroup.addEdit(editModelObj);
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
     * @param documentEvent
     */
    public void trackChange(DocumentEvent documentEvent) {
        // Gets the type of event
        DocumentEvent.EventType type = documentEvent.getType();
        // System.out.println("Type: " + type.toString());

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
                StringTokenizer st = new StringTokenizer(inputString, Arrays.toString(editMarkers),true);

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
