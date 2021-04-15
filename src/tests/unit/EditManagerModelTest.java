package tests.unit;

import main.models.EditGroupModel;
import main.models.EditManagerModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EditManagerModelTest {

	@BeforeEach
	void setUp() {
		EditManagerModel.editGroups.clear();
	}

	@Test
	void getEditGroups() {
		assert EditManagerModel.editGroups.size() == 0;
		EditManagerModel.createGroup("Test Group #1");
		assert EditManagerModel.getEditGroups().size() == 1;
	}

	@Test
	void createGroup() {
		assert EditManagerModel.editGroups.size() == 0;
		EditManagerModel.createGroup("Test Group #2");
		assert EditManagerModel.editGroups.size() == 1;
	}

	@Test
	void addGroup() {
		assert EditManagerModel.editGroups.size() == 0;
		EditGroupModel editGroup = new EditGroupModel("Test Group #3");
		EditManagerModel.addGroup(editGroup);
		assert EditManagerModel.editGroups.size() == 1;
	}
}