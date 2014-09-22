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

public class UTEpisodeSerieSafety {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<EpisodeSerie> FACTORY = FactoryRepository.get(EpisodeSerie.class);
	private static final ClassWrapper<EpisodeSerie> CLASS = new ClassWrapper<>(EpisodeSerie.class);
	private EpisodeSerie entity;

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
		Validator.validate(CLASS.getConstructor(EpisodeSerie.Init.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testCopyConstructor_Null() throws Exception {
		Validator.validate(CLASS.getConstructor(EpisodeSerie.class), new Object[]{null});
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