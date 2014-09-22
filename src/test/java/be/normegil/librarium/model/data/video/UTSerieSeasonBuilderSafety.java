package be.normegil.librarium.model.data.video;

import be.normegil.librarium.libraries.ClassWrapper;
import be.normegil.librarium.tool.validation.Validator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolationException;
import java.util.Collection;

public class UTSerieSeasonBuilderSafety {

	private static final ClassWrapper<SerieSeason.Builder> CLASS = new ClassWrapper<>(SerieSeason.Builder.class);
	private SerieSeason.Builder entity;

	@Before
	public void setUp() throws Exception {
		entity = SerieSeason.builder();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test(expected = ConstraintViolationException.class)
	public void testFrom_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("from", SerieSeason.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testEmptyBuildReturnValue() throws Exception {
		Validator.validate(entity, CLASS.getMethod("build"));
	}

	@Test(expected = ConstraintViolationException.class)
	public void testSetSerie_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("setSerie", Serie.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testSetSeasonNumber_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("setSeasonNumber", Long.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testAddAllEpisodes_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("addAllEpisodes", Collection.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testAddEpisode_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("addEpisode", EpisodeSerie.class), new Object[]{null});
	}
}