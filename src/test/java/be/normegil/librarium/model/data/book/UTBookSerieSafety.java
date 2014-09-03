package be.normegil.librarium.model.data.book;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.libraries.Class;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.validation.Validator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolationException;
import java.util.Collection;

public class UTBookSerieSafety {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<BookSerie> FACTORY = FactoryRepository.get(BookSerie.class);
	private static final Class<BookSerie> CLASS = new Class<>(BookSerie.class);
	private BookSerie entity;

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
		Validator.validate(CLASS.getConstructor(BookSerie.Init.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testCopyConstructor_Null() throws Exception {
		Validator.validate(CLASS.getConstructor(BookSerie.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testAddAllBooks_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("addAllBooks", Collection.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testAddBook_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("addBook", Book.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testRemoveAllBooks_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("removeAllBooks", Collection.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testRemoveBook_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("removeBook", Book.class), new Object[]{null});
	}
}