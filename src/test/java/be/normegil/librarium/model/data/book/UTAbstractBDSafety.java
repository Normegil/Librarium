package be.normegil.librarium.model.data.book;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.libraries.Class;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.validation.Validator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolationException;

public class UTAbstractBDSafety {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<AbstractBD> FACTORY = FactoryRepository.get(AbstractBD.class);
	private static final Class<AbstractBD> CLASS = new Class<>(AbstractBD.class);
	private AbstractBD entity;

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
		Validator.validate(CLASS.getConstructor(AbstractBD.Init.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testCopyConstructor_Null() throws Exception {
		Validator.validate(CLASS.getConstructor(AbstractBD.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testSetIssueNumber_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("setIssueNumber", Long.class), new Object[]{null});
	}
}