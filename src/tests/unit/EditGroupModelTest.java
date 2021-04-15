package tests.unit;

import main.models.EditGroupModel;
import main.models.EditModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EditGroupModelTest {

	private EditGroupModel group;

	@BeforeEach
	void setUp() {
		group = new EditGroupModel("Test Edit Group");
	}

	@Test
	void getEdits() {
		assert group.getEdits().size() == 0;
	}

	@Test
	void addEdit() {
		assert group.getEdits().size() == 0;
		for (int i = 0; i < 10; i++) {
			group.addEdit(new EditModel("Test Edit #" + i));
		}
		assert group.getEdits().size() == 10;
	}

	@Test
	void removeEditByID() {
		assert group.getEdits().size() == 0;
		for (int i = 0; i < 10; i++) {
			group.addEdit(new EditModel("Test Edit #" + i));
		}
		var edits = group.getEdits();
		assert edits.size() == 10;

		int id = edits.get(3).getId();
		group.removeEditByID(id);
		assert group.getEdits().size() == 9;
	}
}