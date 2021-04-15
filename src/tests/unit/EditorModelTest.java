package tests.unit;

import main.models.EditorModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class EditorModelTest {

	private EditorModel em;

	@BeforeEach
	void setUp() {
		em = new EditorModel();
	}

	@Test @Disabled("Private variables, can't test to see if boolean is set to true")
	void setTrackChanges() {
	}

	@Test @Disabled("Private variables, can't test to see edit is pushed")
	void pushEdit() {
	}

	@Test @Disabled("Private variables, need an editable document")
	void processBufferedInput() {

	}

	@Test @Disabled("Private variables, need an editable document")
	void trackChange() {

	}

	@Test
	void getChangedContent() {
		assert EditorModel.getChangedContent().equals("");
		em.pushEdit("aaa");
		em.processBufferedInput();
		assert EditorModel.getChangedContent().equals("aaa");
	}

	@Test
	void deleteEdit() {
		em.pushEdit("aaa");
		em.processBufferedInput();
		EditorModel.deleteEdit(2);
	}
}