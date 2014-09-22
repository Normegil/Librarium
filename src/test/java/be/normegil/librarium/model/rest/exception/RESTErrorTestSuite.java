package be.normegil.librarium.model.rest.exception;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.model.rest.HttpStatus;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import java.time.LocalDateTime;
import java.time.Month;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		UTRESTErrorSafety.class,
		UTRESTError.class,
		UTRESTErrorEquality.class,
		UTRESTErrorBuilderSafety.class,
		UTRESTErrorBuilder.class,
		UTRESTErrorJSONSupport.class,
		UTRESTErrorXMLSupport.class,
		UTRESTErrorCSVSupport.class
})
public class RESTErrorTestSuite implements DataFactory<RESTError> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<URL> URL_FACTORY = FactoryRepository.get(URL.class);
	private static final int FAKE_CODE = 40449;
	private static final String MESSAGE = "Message";
	private static final String DEVELOPPER_MESSAGE = "DevelopperMessage";
	private static final LocalDateTime DEFAULT_TIME = LocalDateTime.of(2014, Month.SEPTEMBER, 19, 18, 20);
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
		return RESTError.builder()
				.setHttpStatus(HttpStatus.NOT_FOUND)
				.setCode(FAKE_CODE)
				.setMessage(MESSAGE)
				.setDeveloperMessage(DEVELOPPER_MESSAGE)
				.setMoreInfoURL(URL_FACTORY.getNew())
				.setTime(DEFAULT_TIME)
				.build();
	}

	@Override
	public RESTError getNext(final boolean withLink) {
		RESTError entity = RESTError.builder()
				.setHttpStatus(HttpStatus.NOT_FOUND)
				.setCode((int) (FAKE_CODE + index))
				.setMessage(MESSAGE + index)
				.setDeveloperMessage(DEVELOPPER_MESSAGE + index)
				.setMoreInfoURL(URL_FACTORY.getNext())
				.setTime(LocalDateTime.now())
				.build();

		index += 1;
		return entity;
	}
}