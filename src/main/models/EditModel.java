package main.models;

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
     * @return The id of the group.
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * Sets the id of the group.
     */
    public void setId() {
        this.id = idIndex++;
    }

    /**
     * @return the edit content.
     */
    public String getContent() {
        return this.content;
    }

    /**
     * Updates the edit content.
     *
     * @param content the edit content.
     */
    public void setContent(String content) {
        this.content = this.getUndone() ? "" : content;
    }

    /**
     * @return the status of the given edit (marked as undone or not).
     */
    public Boolean getUndone() {
        return this.undone;
    }

    /**
     * Updates the edit's status.
     *
     * @param undone the new status.
     */
    public void setUndone(Boolean undone) {
        this.undone = undone;
    }

    /**
     * Changes the edit's status from done to undone and vice-versa.
     */
    public void toggleStatus() {
        this.undone = !this.undone;
    }


    @Override
    public String toString() {
        String status = this.getUndone() ? " | hidden" : "";
        return "ID = " + id + " | Content = '" + content + "'" + status;
    }
}
