package be.normegil.librarium.model.data.book;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.libraries.ClassWrapper;
import be.normegil.librarium.model.data.people.Responsible;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.validation.Validator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolationException;
import java.util.Collection;

public class UTBookSafety {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Book> FACTORY = FactoryRepository.get(Book.class);
	private static final ClassWrapper<Book> CLASS = new ClassWrapper<>(Book.class);
	private Book entity;

	@Before
	public void setUp() throws Exception {
		entity = FACTORY.getNew();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test(expected = ConstraintViolationException.class)
	public void testBuilderConstructor_Null() throws Exception {
		Validator.validate(CLASS.getConstructor(Book.Init.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testCopyConstructor_Null() throws Exception {
		Validator.validate(CLASS.getConstructor(Book.class), new Object[]{null});
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
	public void testRemoveAllAuthors_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("removeAllAuthors", Collection.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testRemoveAuthor_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("removeAuthor", Responsible.class), new Object[]{null});
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
	public void testRemoveAllEditors_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("removeAllEditors", Collection.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testRemoveEditor_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("removeEditor", Responsible.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testSetSerie_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("setSerie", BookSerie.class), new Object[]{null});
	}
}