package be.normegil.librarium.model.data.book;

import be.normegil.librarium.libraries.Class;
import be.normegil.librarium.tool.validation.Validator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolationException;
import java.util.Collection;

public class UTBookSerieBuilderSafety {

	private static final Class<BookSerie.Builder> CLASS = new Class<>(BookSerie.Builder.class);
	private BookSerie.Builder entity;

	@Before
	public void setUp() throws Exception {
		entity = BookSerie.builder();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test(expected = ConstraintViolationException.class)
	public void testFrom_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("from", BookSerie.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testEmptyBuildReturnValue() throws Exception {
		Validator.validate(entity, CLASS.getMethod("build"));
	}

	@Test(expected = ConstraintViolationException.class)
	public void testAddAllBooks_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("addAllBooks", Collection.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testAddBook_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("addBook", Book.class), new Object[]{null});
	}
}