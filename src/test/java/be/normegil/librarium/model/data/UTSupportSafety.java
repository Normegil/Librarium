package be.normegil.librarium.model.data;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.libraries.ClassWrapper;
import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.validation.Validator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolationException;
import java.util.Collection;

public class UTSupportSafety {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Support> FACTORY = FactoryRepository.get(Support.class);
	private static final ClassWrapper<Support> CLASS = new ClassWrapper<>(Support.class);
	private Support entity;

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
		Validator.validate(CLASS.getConstructor(Support.Init.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testCopyConstructor_Null() throws Exception {
		Validator.validate(CLASS.getConstructor(Support.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testSetName_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("setName", String.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testSetWikipediaPage_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("setWikipediaPage", URL.class), new Object[]{null});
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