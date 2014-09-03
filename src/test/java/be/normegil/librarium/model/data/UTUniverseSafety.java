package be.normegil.librarium.model.data;

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

import static org.junit.Assert.assertEquals;

public class UTUniverseSafety {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Universe> FACTORY = FactoryRepository.get(Universe.class);
	private static final Class<Universe> CLASS = new Class<>(Universe.class);
	private static final String EMPTY_STRING = "";
	private Universe entity;

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
		Validator.validate(CLASS.getConstructor(Universe.Init.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testCopyConstructor_Null() throws Exception {
		Validator.validate(CLASS.getConstructor(Universe.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testSetName_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("setName", String.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testSetName_Empty() throws Exception {
		Validator.validate(entity, CLASS.getMethod("setName", String.class), EMPTY_STRING);
	}

	@Test(expected = ConstraintViolationException.class)
	public void testSetDescription_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("setDescription", String.class), new Object[]{null});
	}

	@Test
	public void testSetDescription_Empty() throws Exception {
		Validator.validate(entity, CLASS.getMethod("setDescription", String.class), EMPTY_STRING);
		assertEquals(EMPTY_STRING, entity.getDescription());
	}

	@Test(expected = ConstraintViolationException.class)
	public void testAddAllMedias_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("addAllMedias", Collection.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testAddMedia_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("addMedia", Media.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testRemoveAllMedias_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("removeAllMedias", Collection.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testRemoveMedia_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("removeMedia", Media.class), new Object[]{null});
	}
}