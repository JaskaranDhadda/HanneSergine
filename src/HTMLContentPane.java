import javax.swing.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class HTMLContentPane extends JDialog {

	private final Path documentPath;
	private static JTextPane textPane;
	private static JScrollPane scrollPane;

	public HTMLContentPane(JFrame frame, String title, Boolean modal, Path documentPath) {
		super(frame, title, modal);

		this.documentPath = documentPath;

		textPane = new JTextPane();
		textPane.setContentType("text/html");
		textPane.setText(getHTMLContent());
		textPane.setEnabled(false);

		scrollPane = new JScrollPane(textPane);
		add(scrollPane);
	}

	private String getHTMLContent() {
		String content = "";
		try {
			content = Files.readString(documentPath, StandardCharsets.US_ASCII);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content;
	}
}
