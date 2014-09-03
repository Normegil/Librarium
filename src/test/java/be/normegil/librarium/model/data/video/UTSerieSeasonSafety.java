package be.normegil.librarium.model.data.video;

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

public class UTSerieSeasonSafety {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<SerieSeason> FACTORY = FactoryRepository.get(SerieSeason.class);
	private static final Class<SerieSeason> CLASS = new Class<>(SerieSeason.class);
	private SerieSeason entity;

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
		Validator.validate(CLASS.getConstructor(SerieSeason.Init.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testCopyConstructor_Null() throws Exception {
		Validator.validate(CLASS.getConstructor(SerieSeason.class), new Object[]{null});
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

	@Test(expected = ConstraintViolationException.class)
	public void testRemoveAllEpisodes_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("removeAllEpisodes", Collection.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testRemoveEpisode_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("removeEpisode", EpisodeSerie.class), new Object[]{null});
	}
}