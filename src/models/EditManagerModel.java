package models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

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
     * Deletes the given edits.
     *
     * @param edits The list of edits to delete.
     */
    public static void deleteEdits(List edits) {
        Iterator<EditGroupModel> it = editGroups.iterator();

        while (it.hasNext()) {
            EditGroupModel editGroup = it.next();
            editGroup.getEdits().removeAll(edits);
            //  Remove empty group
            if(editGroup.getEdits().size() == 0) {
                it.remove();
            }
        }
    }

    /**
     * Deletes an edit group from the list of groups along with all its edits.
     *
     * @param editGroup The group to delete.
     */
    public static void deleteGroup(EditGroupModel editGroup) {
        Iterator<EditGroupModel> it = editGroups.iterator();
        while (it.hasNext()) {
            EditGroupModel editGroupTemp = it.next();
            editGroupTemp.getEdits().removeAll(editGroup.getEdits());
            //  Remove empty group
            if(editGroupTemp.getEdits().size() == 0) {
                it.remove();
            }
        }
    }
}
