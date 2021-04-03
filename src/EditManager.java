import javax.swing.*;

public class EditManager extends JDialog {
	private JPanel contentPane;
	private JButton stageEditsButton;
	private JButton unstageEditsButton;
	private JButton selectAllButton;
	private JButton unselectAllButton;
	private JButton deleteGroupButton;
	private JButton undoButton;
	private JButton deleteButton;
	private JButton clearAllButton;
	private JList list1;
	private JList list2;
	private JComboBox comboBox1;

	private static final String title = "Edit Manager";

	public EditManager() {
		setTitle(title);
		setContentPane(contentPane);
		setModal(true);
	}
}
