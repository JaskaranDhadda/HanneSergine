import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileSystemView;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TextEditor extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static JTextArea  txtArea;
	private static JFrame frame;
	private static JScrollPane scrollPane;
	private static int returnValue = 0;
	private static String applicationName = "Hanne Sergine";

	public void run() {
		frame = new JFrame(applicationName);

		//	Set the look-and-feel (LNF) of the application
		//	Try to default to whatever the host system prefers
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
			Logger.getLogger(TextEditor.class.getName()).log(Level.SEVERE, null, ex);
		}

		//	Set attributes of the app window
		ImageIcon editorIcon = new ImageIcon("src/resources/images/editor.png");
		
		txtArea = new JTextArea();
		frame.setIconImage(editorIcon.getImage());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		scrollPane = new JScrollPane(txtArea);
		frame.add(scrollPane);
		frame.setSize(640, 480);
		frame.setVisible(true);

		//	Build the menu
		JMenuBar mainMenu = new JMenuBar();

		// File Menu
		JMenu fileMenu = new JMenu("File");

		ImageIcon newIcon = new ImageIcon("src/resources/images/new.png");
		JMenuItem menuItemNew = new JMenuItem("New", newIcon);
		menuItemNew.addActionListener(this);
		menuItemNew.setToolTipText("Create a new file");
		
		ImageIcon openIcon = new ImageIcon("src/resources/images/open.png");
		JMenuItem menuItemOpen = new JMenuItem("Open", openIcon);
		menuItemOpen.addActionListener(this);
		menuItemOpen.setToolTipText("Open a new file");
		
		ImageIcon saveIcon = new ImageIcon("src/resources/images/save.png");
		JMenuItem menuItemSave = new JMenuItem("Save", saveIcon);
		menuItemSave.addActionListener(this);
		menuItemSave.setToolTipText("Save file");
        KeyStroke ctrlS = KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK);
		menuItemSave.setAccelerator(ctrlS);
		
		ImageIcon exitIcon = new ImageIcon("src/resources/images/exit.png");
		JMenuItem menuItemExit = new JMenuItem("Exit", exitIcon);
		menuItemExit.addActionListener(this);
		menuItemExit.setToolTipText("Exit application");

		mainMenu.add(fileMenu);

		fileMenu.add(menuItemNew);
		fileMenu.add(menuItemOpen);
		fileMenu.add(menuItemSave);
		fileMenu.add(menuItemExit);

		// Edit Menu
		JMenu editMenu = new JMenu("Edit");

		ImageIcon editManagerIcon = new ImageIcon();
		JMenuItem menuItemEditManager = new JMenuItem("Edit Manager", editManagerIcon);
		menuItemEditManager.addActionListener(this);
		menuItemEditManager.setToolTipText("Manage edits");

		mainMenu.add(editMenu);

		editMenu.add(menuItemEditManager);

		// Help Menu
		JMenu helpMenu = new JMenu("Help");

		ImageIcon viewHelpIcon = new ImageIcon();
		JMenuItem menuItemViewHelp = new JMenuItem("View Help", viewHelpIcon);
		menuItemViewHelp.addActionListener(this);
		menuItemViewHelp.setToolTipText("Documentation and FAQ");

		ImageIcon aboutIcon = new ImageIcon();
		JMenuItem menuItemAbout = new JMenuItem("About " + applicationName, aboutIcon);
		menuItemAbout.addActionListener(this);
		menuItemAbout.setToolTipText("More about us");

		mainMenu.add(helpMenu);

		helpMenu.add(menuItemViewHelp);
		helpMenu.add(menuItemAbout);

		frame.setJMenuBar(mainMenu);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String ingest = null;
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		jfc.setDialogTitle("Choose destination.");
		jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

		String ae = e.getActionCommand();
		
		if (ae.equals("Open")) {
			returnValue = jfc.showOpenDialog(null);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				File f = new File(jfc.getSelectedFile().getAbsolutePath());
				try {
					FileReader read = new FileReader(f);
					Scanner scan = new Scanner(read);
					while (scan.hasNextLine()) {
						String line = scan.nextLine() + "\n";
						ingest = ingest + line;
					}
					txtArea.setText(ingest);
					scan.close();
				} catch (FileNotFoundException ex) {
					ex.printStackTrace();
				}
			}
		// SAVE
		} else if (ae.equals("Save")) {
			returnValue = jfc.showSaveDialog(null);
			
			try {
				File f = new File(jfc.getSelectedFile().getAbsolutePath());
				FileWriter out = new FileWriter(f);
				out.write(txtArea.getText());
				out.close();
			} catch (FileNotFoundException ex) {
				Component f = null;
				JOptionPane.showMessageDialog(f, "File not found.");
			} catch (IOException ex) {
				Component f = null;
				JOptionPane.showMessageDialog(f, "Error.");
			}
		} else if (ae.equals("New")) {
			txtArea.setText("");
		} else if (ae.equals("Exit")) {
			System.exit(0);
		}
	}
}
