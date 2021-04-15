package main;

import main.views.Editor;

public class App {

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
