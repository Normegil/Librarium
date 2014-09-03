package be.normegil.librarium.model.data;

import be.normegil.librarium.libraries.Class;
import be.normegil.librarium.model.data.fake.FakeMedia;
import be.normegil.librarium.tool.validation.Validator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;

public class UTMediaBuilderSafety {

	private static final Class<FakeMedia.Builder> CLASS = new Class<>(FakeMedia.Builder.class);
	private FakeMedia.Builder entity;

	@Before
	public void setUp() throws Exception {
		entity = FakeMedia.builder();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test(expected = ConstraintViolationException.class)
	public void testFrom_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("from", Media.class), new Object[]{null});
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
	public void testAddAllSupports_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("addAllSupports", Collection.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testAddSupport_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("addSupport", Support.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testAddAllReleaseDates_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("addAllReleaseDates", Map.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testAddReleaseDate_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("addReleaseDate", Support.class, LocalDate.class), null, LocalDate.now());
	}

}