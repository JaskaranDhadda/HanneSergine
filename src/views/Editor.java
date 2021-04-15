package views;

import controllers.EditController;
import controllers.EditorController;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Editor extends JFrame {

    private final EditorController editorControllerObj = new EditorController();
    private final EditController editControllerObj =  new EditController();
    public static JTextArea txtArea;

    public void init() {
        String applicationName = "Hanne Sergine";

        JFrame frame = new JFrame(applicationName);

        //	Set the look-and-feel (LNF) of the application
        //	Try to default to whatever the host system prefers
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Editor.class.getName()).log(Level.SEVERE, null, ex);
        }

        //	Set attributes of the app window
        ImageIcon editorIcon = new ImageIcon("src/resources/images/editor.png");
        frame.setIconImage(editorIcon.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        txtArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(txtArea);
        frame.add(scrollPane);
        frame.setSize(640, 480);

        txtArea.getDocument().addDocumentListener(editControllerObj);

        //	Build the menu
        JMenuBar mainMenu = new JMenuBar();

        // File Menu
        JMenu fileMenu = new JMenu("File");
        mainMenu.add(fileMenu);

        ImageIcon newIcon = new ImageIcon("src/resources/images/new.png");
        JMenuItem menuItemNew = new JMenuItem("New", newIcon);
        menuItemNew.setToolTipText("Create a new file");
        KeyStroke ctrlN = KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK);
        menuItemNew.setAccelerator(ctrlN);
        menuItemNew.addActionListener(evt -> editorControllerObj.newFile());
        fileMenu.add(menuItemNew);

        ImageIcon openIcon = new ImageIcon("src/resources/images/open.png");
        JMenuItem menuItemOpen = new JMenuItem("Open", openIcon);
        menuItemOpen.setToolTipText("Open a new file");
        KeyStroke ctrlO = KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK);
        menuItemOpen.setAccelerator(ctrlO);
        menuItemOpen.addActionListener(evt -> editorControllerObj.openFile());
        fileMenu.add(menuItemOpen);

        ImageIcon saveIcon = new ImageIcon("src/resources/images/save.png");
        JMenuItem menuItemSave = new JMenuItem("Save", saveIcon);
        menuItemSave.setToolTipText("Save file");
        KeyStroke ctrlS = KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK);
        menuItemSave.setAccelerator(ctrlS);
        menuItemSave.addActionListener(evt -> editorControllerObj.saveFile());
        fileMenu.add(menuItemSave);

        ImageIcon exitIcon = new ImageIcon("src/resources/images/exit.png");
        JMenuItem menuItemExit = new JMenuItem("Exit", exitIcon);
        menuItemExit.setToolTipText("Exit application");
        KeyStroke ctrlE = KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.CTRL_DOWN_MASK);
        menuItemExit.setAccelerator(ctrlE);
        menuItemExit.addActionListener(evt -> editorControllerObj.exitApp());
        fileMenu.add(menuItemExit);

        // Edit Menu
        JMenu editMenu = new JMenu("Edit");
        mainMenu.add(editMenu);

        ImageIcon editManagerIcon = new ImageIcon("src/resources/images/history.png");
        JMenuItem menuItemEditManager = new JMenuItem("Edit Manager", editManagerIcon);
        menuItemEditManager.setToolTipText("Manage edits");
        KeyStroke ctrlM = KeyStroke.getKeyStroke(KeyEvent.VK_M, KeyEvent.CTRL_DOWN_MASK);
        menuItemEditManager.setAccelerator(ctrlM);
        menuItemEditManager.addActionListener(evt -> editorControllerObj.editManager());
        editMenu.add(menuItemEditManager);

        // Help Menu
        JMenu helpMenu = new JMenu("Help");
        mainMenu.add(helpMenu);

        ImageIcon helpIcon = new ImageIcon("src/resources/images/help.png");
        JMenuItem menuItemHelp = new JMenuItem("Help", helpIcon);
        menuItemHelp.setToolTipText("Documentation and FAQ");
        menuItemHelp.addActionListener(evt -> editorControllerObj.help());
        helpMenu.add(menuItemHelp);

        ImageIcon aboutIcon = new ImageIcon("src/resources/images/about.png");
        JMenuItem menuItemAbout = new JMenuItem("About", aboutIcon);
        menuItemAbout.setToolTipText("More about us");
        menuItemAbout.addActionListener(evt -> editorControllerObj.about());
        helpMenu.add(menuItemAbout);

        frame.setJMenuBar(mainMenu);
        frame.setVisible(true);
    }
}
