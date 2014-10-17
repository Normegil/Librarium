package be.normegil.librarium.validation.constraint;

import be.normegil.librarium.Constants;
import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.UUID;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class UTURIWithID {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<URL> URL_FACTORY = FactoryRepository.get(URL.class);
	private URIWithIDValidator entity = new URIWithIDValidator();
	@Mock
	private ConstraintValidatorContextImpl context;

	@Test
	public void testNullURL() throws Exception {
		assertFalse(entity.isValid(null, context));
	}

	@Test
	public void testWithoutID() throws Exception {
		URL url = URL_FACTORY.getNew();
		URL urlWithoutID = url.addToPath(Constants.URL.PATH_SEPARATOR);
		assertFalse(entity.isValid(urlWithoutID.toURI(), context));
	}

	@Test
	public void testWithIDNotUUID() throws Exception {
		URL url = URL_FACTORY.getNew();
		URL urlWithoutUUID = url.addToPath(Constants.URL.PATH_SEPARATOR + "test");
		assertFalse(entity.isValid(urlWithoutUUID.toURI(), context));
	}

	@Test
	public void testWithID() throws Exception {
		URL url = URL_FACTORY.getNew();
		URL urlWithUUID = url.addToPath(Constants.URL.PATH_SEPARATOR + UUID.randomUUID());
		assertTrue(entity.isValid(urlWithUUID.toURI(), context));
	}
}
