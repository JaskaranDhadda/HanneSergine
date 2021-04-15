package main.models;

import java.util.LinkedList;

public class EditGroupModel {

    public static int idIndex = 1;

    private Integer id;

    private String name;

    private LinkedList<EditModel> edits;

    /**
     * Constructor.
     *
     * @param name The name of the given group.
     */
	public EditGroupModel(String name) {
        this.setId();
        this.name = name;
        this.edits = new LinkedList<>();
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
     * @return The name of the group.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the group.
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
     * @param editId The id of the edit to remove.
     */
    public void removeEditByID(int editId) {
        edits.removeIf(e -> e.getId() == editId);
        var v = 0;
    }

    @Override
    public String toString() {
        /*StringBuilder result = new StringBuilder("EditGroup: " + name);

        for (EditModel edit : this.edits) {
            result.append("\nEdit content: '").append(edit.getContent()).append("'\nUndone?: ").append(edit.getUndone());
        }
        return result.toString();*/
        return this.name;
    }
}
