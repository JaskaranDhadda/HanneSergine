package tests.unit;

import main.models.EditModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EditModelTest {

	private EditModel em;
	private String content = "Edit Test Content";

	@BeforeEach
	void setUp() {
		em = new EditModel(content);
	}

	@Test
	void getContent() {
		assert em.getContent().equals(content);
	}

	@Test
	void getUndone() {
		assert !em.getUndone();
	}

	@Test
	void toggleStatus() {
		assert !em.getUndone();
		em.toggleStatus();
		assert em.getUndone();
		em.toggleStatus();
		assert !em.getUndone();
	}
}