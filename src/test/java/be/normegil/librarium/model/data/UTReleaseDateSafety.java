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
import java.time.LocalDate;

public class UTReleaseDateSafety {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<ReleaseDate> FACTORY = FactoryRepository.get(ReleaseDate.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Media> MEDIA_FACTORY = FactoryRepository.get(Media.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Support> SUPPORT_FACTORY = FactoryRepository.get(Support.class);
	private static final Class<ReleaseDate> CLASS = new Class<>(ReleaseDate.class);
	private ReleaseDate entity;

	@Before
	public void setUp() throws Exception {
		entity = FACTORY.getNew();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test(expected = ConstraintViolationException.class)
	public void testConstructor_NullMedia() throws Exception {
		Validator.validate(CLASS.getConstructor(Media.class, Support.class, LocalDate.class), new Object[]{null, SUPPORT_FACTORY.getNext(), LocalDate.now()});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testConstructor_NullSupport() throws Exception {
		Validator.validate(CLASS.getConstructor(Media.class, Support.class, LocalDate.class), MEDIA_FACTORY.getNext(), null, LocalDate.now());
	}

	@Test(expected = ConstraintViolationException.class)
	public void testCopyConstructor_Null() throws Exception {
		Validator.validate(CLASS.getConstructor(ReleaseDate.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testSetDate_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("setDate", LocalDate.class), new Object[]{null});
	}
}