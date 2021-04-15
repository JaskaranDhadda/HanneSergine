# Hanne Sergine

A simple text editor with smart-undo capabilities.

## Contributors
* Jaskaran Dhadda
* Kiana Nezami
* Dominic Morin

### Requirements
* JavaSE-15 (version 16)
* Junit5.4

### Folder structure
```
src # The core code of the application
├───main
│   │   App.java # The entry point of the application 
│   │
│   ├───controllers # The controllers (the logic to handle requests entering the application)
│   │       BaseController.java
│   │       EditController.java
│   │       EditorController.java
│   │       ManagerController.java
│   │
│   ├───models # The logic to handle the data within the app
│   │       EditGroupModel.java
│   │       EditManagerModel.java
│   │       EditModel.java
│   │       EditorModel.java
│   │
│   ├───resources
│   │   ├───images # The logos and icons
│   │   │       about.png
│   │   │       ...
│   │   │
│   │   └───text # The static textual content
│   │           aboutUs.html
│   │           help.html
│   │
│   └───views # The user interface (UI)
│           EditManager.form
│           EditManager.java
│           Editor.java
│           HTMLViewer.form
│           HTMLViewer.java
│
└───tests # The automated tests
    └───unit # The unitary test for models
            EditGroupModelTest.java
            EditManagerModelTest.java
            EditModelTest.java
            EditorModelTest.java
```