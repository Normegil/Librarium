package be.normegil.librarium.validation.constraint;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UTHttpUrlValidator {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<URL> URL_FACTORY = FactoryRepository.get(URL.class);
	private static final String ALTERNATIVE_PROTOCOL = "ftp";
	private static final String HTTP_PROTOCOL = "http";
	private HttpUrlValidator entity = new HttpUrlValidator();
	private ConstraintValidatorContextImpl context = new ConstraintValidatorContextImpl(null, null, null);

	@Test
	public void testNullURL() throws Exception {
		assertFalse(entity.isValid(null, context));
	}

	@Test
	public void testWrongProtocol() throws Exception {
		URL url = URL_FACTORY.getNew();
		URL alternativeUrl = new URL(ALTERNATIVE_PROTOCOL, url.getHost(), url.getPort(), url.getFilePath());
		assertFalse(entity.isValid(alternativeUrl, context));
	}

	@Test
	public void testHttpProtocol() throws Exception {
		URL url = URL_FACTORY.getNew();
		URL alternativeUrl = new URL(HTTP_PROTOCOL, url.getHost(), url.getPort(), url.getFilePath());
		assertTrue(entity.isValid(alternativeUrl, context));
	}
}
