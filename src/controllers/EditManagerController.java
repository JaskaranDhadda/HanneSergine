package controllers;

import models.EditorModel;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class EditManagerController extends BaseController implements DocumentListener {

    private EditorModel editorModelObj;

    public EditManagerController(){
         this.editorModelObj = new EditorModel();
    }

    @Override
    public void insertUpdate(DocumentEvent documentEvent) {
        this.editorModelObj.trackChange(documentEvent);
    }

    @Override
    public void removeUpdate(DocumentEvent documentEvent) {
        this.editorModelObj.trackChange(documentEvent);
    }

    @Override
    public void changedUpdate(DocumentEvent documentEvent) {}
}
