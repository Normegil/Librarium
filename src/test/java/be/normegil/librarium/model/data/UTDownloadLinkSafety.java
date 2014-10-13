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

import static org.junit.Assert.assertEquals;

public class UTDownloadLinkSafety {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<DownloadLink> FACTORY = FactoryRepository.get(DownloadLink.class);
	private static final ClassWrapper<DownloadLink> CLASS = new ClassWrapper<>(DownloadLink.class);
	private static final String EMPTY_STRING = "";
	private DownloadLink entity;

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
		Validator.validate(CLASS.getConstructor(DownloadLink.Init.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testCopyConstructor_Null() throws Exception {
		Validator.validate(CLASS.getConstructor(DownloadLink.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testSetTitle_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("setTitle", String.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testSetTitle_Empty() throws Exception {
		Validator.validate(entity, CLASS.getMethod("setTitle", String.class), EMPTY_STRING);
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
	public void testSetLink_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("setLink", URL.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testSetMedia_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("setMedia", BaseMedia.class), new Object[]{null});
	}
}