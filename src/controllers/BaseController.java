package controllers;

import views.Editor;

public class BaseController {

    /**
     * Bootstraps the application.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        Editor editorObj = new Editor();
        editorObj.init();
    }
}
