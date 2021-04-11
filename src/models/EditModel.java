package models;

public class EditModel {

    private String content;
    private Boolean undone;
    private Boolean selected;

    /**
     * Constructor.
     *
     * @param content The initial content of the edit.
     */
    public EditModel(String content) {
        this.content = content;
        this.undone = false;
        this.selected = false;
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
        this.content = content;
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
     * @return the select state of the edit.
     */
    public Boolean getSelected() {
        return this.selected;
    }

    /**
     * Updates the state the edit's select.
     *
     * @param selected the new state.
     */
    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    /**
     * Changes the edit's status from done to undone and vice-versa.
     */
    public void toggleStatus() {
        this.undone = !this.undone;
    }

    /**
     * Changes he state the edit's select from unselected to selected and vice-versa.
     */
    public void toggleSelect() {
        this.selected = !this.selected;
    }
}
