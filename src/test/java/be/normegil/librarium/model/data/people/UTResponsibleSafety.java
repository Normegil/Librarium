package be.normegil.librarium.model.data.people;

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

public class UTResponsibleSafety {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Responsible> FACTORY = FactoryRepository.get(Responsible.class);
	private static final ClassWrapper<Responsible> CLASS = new ClassWrapper<>(Responsible.class);
	private static final String EMPTY_STRING = "";
	private Responsible entity;

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
		Validator.validate(CLASS.getConstructor(Responsible.Init.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testCopyConstructor_Null() throws Exception {
		Validator.validate(CLASS.getConstructor(Responsible.class), new Object[]{null});
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
	public void testSetWikipediaPage_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("setWikipediaPage", URL.class), new Object[]{null});
	}
}