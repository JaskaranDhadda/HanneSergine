package main.views;

import main.controllers.EditorController;
import main.controllers.ManagerController;
import main.models.EditGroupModel;
import main.models.EditModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

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

	private JComboBox editGroupsComboBox;
	private JList groupList;
	private JList stagingAreaList;

	public EditManager(ArrayList<EditGroupModel> listEditGroups) {
		this.setTitle("Edit Manager");
		Image editManagerIcon = new ImageIcon("src/resources/images/history.png").getImage();
		this.setIconImage(editManagerIcon);
		this.setContentPane(contentPane);
		this.setModal(true);

		DefaultListModel<EditModel> stageListModel = new DefaultListModel<>();

		//	Fill the edit groups combo box with the list groups
		for (EditGroupModel editGroup : listEditGroups) {
			editGroupsComboBox.addItem(editGroup);
		}

		//	Remove selected item (-1 indicates no selection)
		editGroupsComboBox.setSelectedIndex(-1);

		//	Load edits belonging to the selected edit group
		editGroupsComboBox.addActionListener(e -> {
			if (e.getActionCommand().equals("comboBoxChanged")) {
				groupList.setModel(managerController.getGroupEdits(editGroupsComboBox.getSelectedItem()));
			}
		});

		//	Select all edits displayed within list component
		selectAllButton.addActionListener(e -> {
			int start = 0;
			int end = groupList.getModel().getSize() - 1;
			if (end >= 0) {
				groupList.setSelectionInterval(start, end);
			}
		});

		//	Deselect all edits previous selected edits within list component
		unselectAllButton.addActionListener(e -> groupList.clearSelection());

		//	Deletes the selected group after confirmation
		deleteGroupButton.addActionListener(e -> {
			EditGroupModel selectedEditGroup = (EditGroupModel) editGroupsComboBox.getSelectedItem();

			if (selectedEditGroup != null) {
				String groupName = selectedEditGroup.getName();
				int nbEdits = selectedEditGroup.getEdits().size();

				if(managerController.getConfirmation("Are you sure you want to delete the '" + selectedEditGroup.getName() + "' group?", "Confirm Group Deletion")) {
					managerController.deleteEditGroup(selectedEditGroup);
					managerController.displayInfo("The group '" + groupName + "' has been successfully deleted along with its " + nbEdits + " edit(s)!", "Group Deletion Successful");
					dispose();
					editorControllerObj.renderContent();
				}
			} else {
				managerController.displayInfo("Please select a group from the drop down.", "No Group Selected");
			}
		});

		//	Undo one or more edits selected from the staging area
		undoButton.addActionListener(e -> {
			int selectCount = stagingAreaList.getSelectedIndices().length;
			if (selectCount > 0) {
				for (Object o : stagingAreaList.getSelectedValuesList()) {
					EditModel selectedEdit = (EditModel) o;
					selectedEdit.toggleStatus();
				}
				managerController.displayInfo(selectCount + " edit(s) undone!", "Undo Successful");
				dispose();
				editorControllerObj.renderContent();
			} else {
				managerController.displayInfo("Please select some edits from the stating area.", "No Edits Selected");
			}
		});

		//	Deletes one or more edits selected from the staging area
		deleteButton.addActionListener(e -> {
			int selectCount = stagingAreaList.getSelectedIndices().length;
			if (selectCount > 0) {
				managerController.deleteEdits(stagingAreaList.getSelectedValuesList());
				managerController.displayInfo(selectCount + " edit(s) deleted!", "Delete Successful");
				dispose();
				editorControllerObj.renderContent();
			} else {
				managerController.displayInfo("Please select some edits from the stating area.", "No Edits Selected");
			}
		});

		//	Creates a group of edits with the given name and given staged edits
		createGroupButton.addActionListener(e -> {
			if (stageListModel.getSize() > 0) {
				String groupName = managerController.getUserInput("Please provide a name for the group: ");
				if (!groupName.isBlank()) {
					managerController.createGroup(groupName, stageListModel);
					managerController.displayInfo("The group '" + groupName + "' was successfully created with " + stageListModel.getSize()+ " edit(s)!", "Group Creation Successful");
					dispose();
				}
			} else {
				managerController.displayInfo("Please stage some edits.", "No Edits Staged");
			}
		});

		//	Copies the selected edits from the given group to staging area
		stageEditsButton.addActionListener(e -> {
			for (Object o : groupList.getSelectedValuesList()) {
				EditModel selectedEdit = (EditModel) o;
				if (!stageListModel.contains(selectedEdit)) {
					stageListModel.addElement(selectedEdit);
				}
			}
			stagingAreaList.setModel(stageListModel);
		});

		//	Removes the selected edits from the stating area
		unstageEditsButton.addActionListener(e -> {
			for (Object o : stagingAreaList.getSelectedValuesList()) {
				EditModel selectedEdit = (EditModel) o;
				if (stageListModel.contains(selectedEdit)) {
					stageListModel.removeElement(selectedEdit);
				}
			}
		});
	}
}
