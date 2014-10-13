package be.normegil.librarium.model.data.video;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.libraries.ClassWrapper;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.validation.Validator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolationException;
import java.util.Collection;

public class UTMovieSerieSafety {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<MovieSerie> FACTORY = FactoryRepository.get(MovieSerie.class);
	private static final ClassWrapper<MovieSerie> CLASS = new ClassWrapper<>(MovieSerie.class);
	private MovieSerie entity;

	@Before
	public void setUp() throws Exception {
		entity = FACTORY.getDefault();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test(expected = ConstraintViolationException.class)
	public void testBuilderConstructor_Null() throws Exception {
		Validator.validate(CLASS.getConstructor(MovieSerie.Init.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testCopyConstructor_Null() throws Exception {
		Validator.validate(CLASS.getConstructor(MovieSerie.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testAddAllMovies_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("addAllMovies", Collection.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testAddMovie_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("addMovie", Movie.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testRemoveAllMovies_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("removeAllMovies", Collection.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testRemoveMovie_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("removeMovie", Movie.class), new Object[]{null});
	}
}