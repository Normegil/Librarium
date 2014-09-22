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

import static org.junit.Assert.assertEquals;

public class UTBaseMediaSafety {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<BaseMedia> FACTORY = FactoryRepository.get(BaseMedia.class);
	private static final ClassWrapper<BaseMedia> CLASS = new ClassWrapper<>(BaseMedia.class);
	private static final String EMPTY_STRING = "";
	private BaseMedia entity;

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
		Validator.validate(CLASS.getConstructor(BaseMedia.Init.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testCopyConstructor_Null() throws Exception {
		Validator.validate(CLASS.getConstructor(BaseMedia.class), new Object[]{null});
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
	public void testAddAllTags_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("addAllTags", Collection.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testAddTag_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("addTag", String.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testRemoveAllTags_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("removeAllTags", Collection.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testRemoveTag_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("removeTag", String.class), new Object[]{null});
	}

	@Test
	public void testSetOfficialWebsite_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("setOfficialWebsite", URL.class), new Object[]{null});
		assertEquals(null, entity.getOfficialWebsite());
	}

	@Test
	public void testSetWikipediaPage_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("setWikipediaPage", URL.class), new Object[]{null});
		assertEquals(null, entity.getWikipediaPage());
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
	public void testRemoveAllStores_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("removeAllStores", Collection.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testRemoveStore_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("removeStore", URL.class), new Object[]{null});
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
	public void testRemoveAllDownloadLinks_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("removeAllDownloadLinks", Collection.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testRemoveDownloadLink_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("removeDownloadLink", DownloadLink.class), new Object[]{null});
	}
}