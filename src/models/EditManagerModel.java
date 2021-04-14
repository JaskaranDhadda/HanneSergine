package models;

import java.util.ArrayList;

public class EditManagerModel {

    public static ArrayList<EditGroupModel> editGroups = new ArrayList<>();

    /**
     * @return The list existing of groups.
     */
    public static ArrayList<EditGroupModel> getEditGroups() {
        return editGroups;
    }

    /**
     * Creates a new edit group and adds it to the list of groups.
     *
     * @param name The name of the given edit group.
     *
     * @return The created group.
     */
    public static EditGroupModel createGroup(String name) {
        EditGroupModel editGroupObj = new EditGroupModel(name);
        editGroups.add(editGroupObj);
        return editGroupObj;
    }

    /**
     * Adds the given group to the list of groups.
     *
     * @param newGroup The new group to add.
     */
    public static void addGroup(EditGroupModel newGroup) {
        editGroups.add(newGroup);
    }

    /**
     * Deletes an edit group from the list of groups.
     *
     * @param groupIndex The index of the group to delete.
     */
    public static void deleteGroupByIndex(int groupIndex) {
        editGroups.remove(groupIndex);
    }
}
