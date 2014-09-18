package be.normegil.librarium.model.rest.exception;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import org.apache.http.HttpStatus;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		UTRESTErrorSafety.class,
		UTRESTError.class,
		UTRESTErrorEquality.class,
		UTRESTErrorBuilderSafety.class,
		UTRESTErrorBuilder.class,
		UTRESTErrorJSONSupport.class
})
public class RESTErrorTestSuite implements DataFactory<RESTError> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<URL> URL_FACTORY = FactoryRepository.get(URL.class);
	private static final int FAKE_CODE = 40449;
	private static final String MESSAGE = "Message";
	private static final String DEVELOPPER_MESSAGE = "DevelopperMessage";
	private static long index = 0L;

	@Override
	public RESTError getNew() {
		return getNew(true);
	}

	@Override
	public RESTError getNext() {
		return getNext(true);
	}

	@Override
	public RESTError getNew(final boolean withLink) {
		RESTError entity = RESTError.builder()
				.setHttpStatus(HttpStatus.SC_NOT_FOUND)
				.setCode(FAKE_CODE)
				.setMessage(MESSAGE)
				.setDeveloperMessage(DEVELOPPER_MESSAGE)
				.setMoreInfoURL(URL_FACTORY.getNew())
				.setThrowable(new NullPointerException())
				.build();
		return entity;
	}

	@Override
	public RESTError getNext(final boolean withLink) {
		RESTError entity = RESTError.builder()
				.setHttpStatus(HttpStatus.SC_NOT_FOUND)
				.setCode((int) (FAKE_CODE + index))
				.setMessage(MESSAGE + index)
				.setDeveloperMessage(DEVELOPPER_MESSAGE + index)
				.setMoreInfoURL(URL_FACTORY.getNext())
				.setThrowable(new NullPointerException())
				.build();

		index += 1;
		return entity;
	}
}