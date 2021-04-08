package models;

import java.util.LinkedList;

public class EditGroupModel {

    private String name;

    private LinkedList<EditModel> edits;

    /**
     * Constructor.
     *
     * @param name The name of the given group.
     */
    EditGroupModel(String name) {
        this.name = name;
        this.edits = new LinkedList<>();
    }

    /**
     * @return The name of the group.
     */
    public String getName() {
        return name;
    }

    /**
     * Updates the name of the group.
     *
     * @param name The new name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The list of edits within the group.
     */
    public LinkedList<EditModel> getEdits() {
        return this.edits;
    }

    /**
     * Adds a new edit to the given group.
     *
     * @param edit The new edit.
     */
    public void addEdit(EditModel edit) {
        this.edits.add(edit);
    }

    /**
     * Removes an edit to the given group by its index.
     *
     * @param editIndex The index of the dit to remove.
     */
    public void removeEditByIndex(int editIndex) {
        this.edits.remove(editIndex);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("EditGroup: " + name);

        for (EditModel edit : this.edits) {
            result.append("\nEdit content: '").append(edit.getContent()).append("'\nUndone?: ").append(edit.getUndone());
        }
        return result.toString();
    }
}
