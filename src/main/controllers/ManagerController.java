package main.controllers;

import main.models.EditGroupModel;
import main.models.EditManagerModel;
import main.models.EditModel;
import main.models.EditorModel;

import javax.swing.*;
import java.util.LinkedList;

public class ManagerController extends BaseController {

    public DefaultListModel<EditModel> getGroupEdits(Object selectedItem) {
        LinkedList<EditModel> groupEdits;
        DefaultListModel<EditModel> listModel = new DefaultListModel<>();

        try {
            EditGroupModel editGroup = (EditGroupModel) selectedItem;
            System.out.println("ID: " + editGroup.getId() + " | Name: " + editGroup.getName());
            groupEdits = editGroup.getEdits();

            for (EditModel edit : groupEdits) {
                listModel.addElement(edit);
            }
        } catch (Exception e) {
            e.getStackTrace();
        }

        return listModel;
    }

    public void createGroup(String name, DefaultListModel<EditModel> edits) {
        EditGroupModel editGroup = EditManagerModel.createGroup(name);
        for(int i = 0; i < edits.getSize(); i++){
            editGroup.addEdit(edits.getElementAt(i));
        }
    }

    public void deleteEditFromGroup(int editId) {
        EditorModel.deleteEdit(editId);
    }
}
