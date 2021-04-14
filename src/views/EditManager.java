package views;

import controllers.EditController;
import controllers.EditorController;
import controllers.ManagerController;
import models.EditGroupModel;
import models.EditModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Iterator;

public class EditManager extends JDialog {
	private final ManagerController managerController =  new ManagerController();
	private final EditorController editorControllerObj = new EditorController();

	private JPanel contentPane;
	private JButton stageEditsButton;
	private JButton unstageEditsButton;
	private JButton selectAllButton;
	private JButton unselectAllButton;
	private JButton deleteGroupButton;
	private JButton undoButton;
	private JButton deleteButton;
	private JButton createGroupButton;
	private JList groupList;
	private JList stageAreaList;
	private JComboBox editGroups;

	private static final String title = "Edit Manager";

	public EditManager(ArrayList<EditGroupModel> edits) {
		setTitle(title);
		Image editManagerIcon = new ImageIcon("src/resources/images/history.png").getImage();
		setIconImage(editManagerIcon);
		setContentPane(contentPane);
		setModal(true);

		DefaultListModel<EditModel> stageListModel = new DefaultListModel();

		for (EditGroupModel editGroup : edits) {
			editGroups.addItem(editGroup);
		}

		editGroups.setSelectedIndex(-1);
		editGroups.addActionListener(e -> {
			if (e.getActionCommand().equals("comboBoxChanged")) {
//				System.out.println("Edits " + managerController.getGroupEdits(editGroups.getSelectedItem()).toString());
				groupList.setModel(managerController.getGroupEdits(editGroups.getSelectedItem()));
			}
		});

		selectAllButton.addActionListener(e -> {
			int start = 0;
			int end = groupList.getModel().getSize() - 1;
			if (end >= 0) {
				groupList.setSelectionInterval(start, end);
			}
		});

		unselectAllButton.addActionListener(e -> {
				groupList.clearSelection();
		});

		deleteGroupButton.addActionListener(e -> {
			EditGroupModel selectedEditGroups = (EditGroupModel) editGroups.getSelectedItem();
			if(selectedEditGroups != null){
				int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the '" + selectedEditGroups.getName() + "' group?", "Warning", JOptionPane.YES_NO_OPTION );
				if(dialogResult == JOptionPane.YES_OPTION){
					// TODO implement the group delete
					System.out.println("Alright mate we'll delete it!");
				}
			}
		});

		undoButton.addActionListener(e -> {
			for (Iterator it = stageAreaList.getSelectedValuesList().iterator(); it.hasNext();) {
				EditModel selectedEdit = (EditModel) it.next();
				selectedEdit.toggleStatus();
			}
			dispose();
			editorControllerObj.rerender();
		});

		deleteButton.addActionListener(e -> {
			for (Iterator it = stageAreaList.getSelectedValuesList().iterator(); it.hasNext();) {
				EditModel selectedEdit = (EditModel) it.next();
				managerController.deleteEditFromGroup(selectedEdit.getId());
			}
			dispose();
			editorControllerObj.rerender();
		});

		createGroupButton.addActionListener(e -> {
			if (stageListModel.getSize() > 0) {
				String groupName = JOptionPane.showInputDialog("Please provide a name for the group: ");;

				if (groupName != null && !groupName.isBlank()) {
					managerController.createGroup(groupName, stageListModel);
					dispose();
				}
			} else {
				JOptionPane.showMessageDialog(null, "Please stage some edits.");
			}
		});

		stageEditsButton.addActionListener(e -> {
			for (Iterator it = groupList.getSelectedValuesList().iterator(); it.hasNext();) {
				EditModel selectedEdit = (EditModel) it.next();
				if (!stageListModel.contains(selectedEdit)) {
					stageListModel.addElement(selectedEdit);
				}
			}
			stageAreaList.setModel(stageListModel);
		});

		unstageEditsButton.addActionListener(e -> {
			for (Iterator it = stageAreaList.getSelectedValuesList().iterator(); it.hasNext();) {
				EditModel selectedEdit = (EditModel) it.next();
				if (stageListModel.contains(selectedEdit)) {
					stageListModel.removeElement(selectedEdit);
				}
			}
		});
	}
}
