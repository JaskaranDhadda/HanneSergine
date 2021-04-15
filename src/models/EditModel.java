package models;

import java.util.Arrays;

public class EditModel {

    public static int idIndex = 1;
    private Integer id;
    private String content;
    private Boolean undone;

    /**
     * Constructor.
     *
     * @param content The initial content of the edit.
     */
    public EditModel(String content) {
        this.setId();
        this.content = content;
        this.undone = false;
    }

    /**
     * Sets the id of the group.
     */
    public void setId() {
        this.id = idIndex++;
    }

    /**
     * @return The edit content.
     */
    public String getContent() {
        return this.content;
    }

    /**
     * @return the status of the given edit (marked as undone or not).
     */
    public Boolean getUndone() {
        return this.undone;
    }

    /**
     * Changes the edit's status from done to undone and vice-versa.
     */
    public void toggleStatus() {
        this.undone = !this.undone;
    }

    @Override
    public String toString() {
        String status = this.getUndone() ? " | HIDDEN" : "";
        String tmpContent = this.content;

        //  If the edit contains a line separator
        if (Arrays.stream(new String[]{"\n", "\r", "\f"}).anyMatch(this.content::contains)) {
            tmpContent = "LINE_SEPARATOR";
        } else if (this.content.isBlank()) { //  If the edit contains a white space
            tmpContent = "WHITE_SPACE";
        }

        return "ID = " + this.id + " | Content = '" + tmpContent + "'" + status;
    }
}
