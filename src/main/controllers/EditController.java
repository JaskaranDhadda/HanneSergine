package main.controllers;

import main.models.EditorModel;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class EditController extends BaseController implements DocumentListener {

    /**
     * An instance of the editor model to tracks changes in the textarea of the editor.
     */
    private final EditorModel editorModelObj;

    /**
     * Constructor.
     */
    public EditController(){
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
