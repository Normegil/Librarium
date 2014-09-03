package be.normegil.librarium.model.data.book;

import be.normegil.librarium.libraries.Class;
import be.normegil.librarium.model.data.fake.FakeBook;
import be.normegil.librarium.model.data.people.Responsible;
import be.normegil.librarium.tool.validation.Validator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolationException;
import java.util.Collection;

public class UTBookBuilderSafety {

	private static final Class<FakeBook.Builder> CLASS = new Class<>(FakeBook.Builder.class);
	private FakeBook.Builder entity;

	@Before
	public void setUp() throws Exception {
		entity = FakeBook.builder();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test(expected = ConstraintViolationException.class)
	public void testFrom_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("from", Book.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testAddAllAuthors_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("addAllAuthors", Collection.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testAddAuthor_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("addAuthor", Responsible.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testAddAllEditors_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("addAllEditors", Collection.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testAddEditor_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("addEditor", Responsible.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testSetSerie_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("setSerie", BookSerie.class), new Object[]{null});
	}
}