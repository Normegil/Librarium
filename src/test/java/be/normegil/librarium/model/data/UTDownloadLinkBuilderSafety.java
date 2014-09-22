package be.normegil.librarium.model.data;

import be.normegil.librarium.libraries.ClassWrapper;
import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.tool.validation.Validator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolationException;

import static org.junit.Assert.assertEquals;

public class UTDownloadLinkBuilderSafety {

	private static final ClassWrapper<DownloadLink.Builder> CLASS = new ClassWrapper<>(DownloadLink.Builder.class);
	private static final String EMPTY_STRING = "";
	private DownloadLink.Builder entity;

	@Before
	public void setUp() throws Exception {
		entity = DownloadLink.builder();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test(expected = ConstraintViolationException.class)
	public void testFrom_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("from", DownloadLink.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testEmptyBuildReturnValue() throws Exception {
		Validator.validate(entity, CLASS.getMethod("build"));
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
		assertEquals(EMPTY_STRING, entity.build().getDescription());
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