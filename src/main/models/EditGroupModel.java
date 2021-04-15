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

    @Override
    public String toString() {
        return "Name: " + this.name + " | Size: " + this.edits.size();
    }
}
