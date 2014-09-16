package be.normegil.librarium.model.data;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.libraries.Class;
import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.validation.Validator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

public class UTEntityHelperSafety {

	private static final Class<Entity.Helper> CLASS = new Class<>(Entity.Helper.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<URL> URL_FACTORY = FactoryRepository.get(URL.class);
	private static final java.lang.reflect.Method CONVERT_TO_URL_METHOD = CLASS.getMethod("convertToURLs", List.class, URL.class);
	private Entity.Helper entity;

	@Before
	public void setUp() throws Exception {
		entity = Entity.helper();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test(expected = ConstraintViolationException.class)
	public void testConvertToURLs_NullList() throws Exception {
		Validator.validate(entity, CONVERT_TO_URL_METHOD, null, URL_FACTORY.getNext());
	}

	@Test(expected = ConstraintViolationException.class)
	public void testConvertToURLs_NullURL() throws Exception {
		Validator.validate(entity, CONVERT_TO_URL_METHOD, new ArrayList<>(), null);
	}
}
