package controllers;

import models.EditGroupModel;
import models.EditManagerModel;
import models.EditModel;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;

public class ManagerController extends BaseController {

    /**
     * Returns a list edits belongs the given group.
     * 
     * @param selectedEditGroup The selected edit Group
     *
     * @return The list of the edits within the group.
     */
    public DefaultListModel<EditModel> getGroupEdits(Object selectedEditGroup) {
        DefaultListModel<EditModel> listModel = new DefaultListModel<>();

        try {
            EditGroupModel editGroup = (EditGroupModel) selectedEditGroup;
            // System.out.println("ID: " + editGroup.getId() + " | Name: " + editGroup.getName());
            LinkedList<EditModel> groupEdits = editGroup.getEdits();

            for (EditModel edit : groupEdits) {
                listModel.addElement(edit);
            }
        } catch (Exception e) {
            this.displayError("Apologies, an error occurred while retrieving the edits.", "Edits Fetch Error");
            e.getStackTrace();
        }

        return listModel;
    }

    /**
     * Creates a new group of edits.
     *
     * @param name The name of the group.
     * @param edits The list of edits to put in the group.
     */
    public void createGroup(String name, DefaultListModel<EditModel> edits) {
        try {
            EditGroupModel editGroup = EditManagerModel.createGroup(name);
            for(int i = 0; i < edits.getSize(); i++){
                editGroup.addEdit(edits.getElementAt(i));
            }
        } catch (Exception e) {
            this.displayError("Apologies, an error occurred while creating a group.", "Create Group Error");
            e.printStackTrace();
        }
    }

    /**
     * Deletes edits.
     *
     * @param edits The list the edit to delete.
     */
    public void deleteEdits(List edits) {
        try {
            EditManagerModel.deleteEdits(edits);
        } catch (Exception e) {
            this.displayError("Apologies, an error occurred while deleting edit(s).", "Delete Edit Error");
            e.printStackTrace();
        }
    }

    /**
     * Deletes an edit group.
     *
     * @param editGroup The edit group to delete.
     */
    public void deleteEditGroup(EditGroupModel editGroup) {
        try {
            EditManagerModel.deleteGroup(editGroup);
        } catch (Exception e) {
            this.displayError("Apologies, an error occurred while deleting an edit group.", "Delete Edit Group Error");
            e.printStackTrace();
        }
    }
}
