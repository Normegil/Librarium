package be.normegil.librarium.model.data;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.libraries.ClassWrapper;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.validation.Validator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;

public class UTMediaSafety {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Media> FACTORY = FactoryRepository.get(Media.class);
	private static final ClassWrapper<Media> CLASS = new ClassWrapper<>(Media.class);
	private Media entity;

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
		Validator.validate(CLASS.getConstructor(Media.Init.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testCopyConstructor_Null() throws Exception {
		Validator.validate(CLASS.getConstructor(Media.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testAddAllUniverses_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("addAllUniverses", Collection.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testAddUniverse_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("addUniverse", Universe.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testRemoveAllUniverses_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("removeAllUniverses", Collection.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testRemoveUniverse_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("removeUniverse", Universe.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testAddAllSupports_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("addAllSupports", Collection.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testAddSupport_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("addSupport", Support.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testRemoveAllSupports_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("removeAllSupports", Collection.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testRemoveSupport_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("removeSupport", Support.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testGetReleaseDate_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("getReleaseDate", Support.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testAddAllReleaseDates_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("addAllReleaseDates", Map.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testAddReleaseDate_NullSupport() throws Exception {
		Validator.validate(entity, CLASS.getMethod("addReleaseDate", Support.class, LocalDate.class), null, LocalDate.now());
	}

	@Test(expected = ConstraintViolationException.class)
	public void testRemoveAllReleaseDates_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("removeAllReleaseDates", Collection.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testRemoveReleaseDate_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("removeReleaseDate", Support.class), new Object[]{null});
	}
}