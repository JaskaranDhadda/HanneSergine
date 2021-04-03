import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class HTMLViewer extends JDialog {
	private JPanel contentPane;
	private JButton buttonCancel;
	private JTextPane textPane1;

	public HTMLViewer(String title, Path documentPath) {
		setTitle(title);
		setContentPane(contentPane);
		setModal(true);

		try {
			String content = Files.readString(documentPath, StandardCharsets.US_ASCII);
			textPane1.setText(content);
		} catch (IOException e) {
			e.printStackTrace();
		}

		buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onCancel();
			}
		});

		// call onCancel() when cross is clicked
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				onCancel();
			}
		});

		// call onCancel() on ESCAPE
		contentPane.registerKeyboardAction(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onCancel();
			}
		}, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
	}

	private void onCancel() {
		// add your code here if necessary
		dispose();
	}
}
