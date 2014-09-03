package be.normegil.librarium.model.data;

import be.normegil.librarium.libraries.Class;
import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.model.data.fake.FakeBaseMedia;
import be.normegil.librarium.tool.validation.Validator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolationException;
import java.util.Collection;

public class UTBaseMediaBuilderSafety {

	private static final Class<FakeBaseMedia.Builder> CLASS = new Class<>(FakeBaseMedia.Builder.class);
	private static final String EMPTY_STRING = "";
	private FakeBaseMedia.Builder entity;

	@Before
	public void setUp() throws Exception {
		entity = FakeBaseMedia.builder();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
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

	@Test(expected = ConstraintViolationException.class)
	public void testSetDescription_Empty() throws Exception {
		Validator.validate(entity, CLASS.getMethod("setDescription", String.class), EMPTY_STRING);
	}

	@Test(expected = ConstraintViolationException.class)
	public void testAddAllTags_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("addAllTags", Collection.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testAddTag_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("addTag", String.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testSetOfficialWebsite_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("setOfficialWebsite", URL.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testSetWikipediaPage_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("setWikipediaPage", URL.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testAddAllStores_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("addAllStores", Collection.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testAddStore_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("addStore", URL.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testAddAllDownloadLinks_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("addAllDownloadLinks", Collection.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testAddDownloadLink_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("addDownloadLink", DownloadLink.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testFrom_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("from", BaseMedia.class), new Object[]{null});
	}
}