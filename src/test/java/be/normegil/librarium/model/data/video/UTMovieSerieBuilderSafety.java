package be.normegil.librarium.model.data.video;

import be.normegil.librarium.libraries.Class;
import be.normegil.librarium.tool.validation.Validator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolationException;
import java.util.Collection;

public class UTMovieSerieBuilderSafety {

	private static final Class<MovieSerie.Builder> CLASS = new Class<>(MovieSerie.Builder.class);
	private MovieSerie.Builder entity;

	@Before
	public void setUp() throws Exception {
		entity = MovieSerie.builder();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test(expected = ConstraintViolationException.class)
	public void testFrom_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("from", MovieSerie.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testEmptyBuildReturnValue() throws Exception {
		Validator.validate(entity, CLASS.getMethod("build"));
	}

	@Test(expected = ConstraintViolationException.class)
	public void testAddAllMovies_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("addAllMovies", Collection.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testAddMovie_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("addMovie", Movie.class), new Object[]{null});
	}
}