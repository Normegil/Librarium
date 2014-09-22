package be.normegil.librarium.model.data.video;

import be.normegil.librarium.libraries.ClassWrapper;
import be.normegil.librarium.tool.validation.Validator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolationException;

public class UTEpisodeSerieBuilderSafety {

	private static final ClassWrapper<EpisodeSerie.Builder> CLASS = new ClassWrapper<>(EpisodeSerie.Builder.class);
	private EpisodeSerie.Builder entity;

	@Before
	public void setUp() throws Exception {
		entity = EpisodeSerie.builder();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test(expected = ConstraintViolationException.class)
	public void testFrom_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("from", EpisodeSerie.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testEmptyBuildReturnValue() throws Exception {
		Validator.validate(entity, CLASS.getMethod("build"));
	}

	@Test(expected = ConstraintViolationException.class)
	public void testSetEpisodeNumber_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("setEpisodeNumber", Long.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testSetSeason_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("setSeason", SerieSeason.class), new Object[]{null});
	}
}