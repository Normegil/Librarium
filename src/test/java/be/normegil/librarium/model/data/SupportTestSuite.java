package be.normegil.librarium.model.data;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.TestResult;
import be.normegil.librarium.tool.comparator.PropertyComparatorHelper;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		UTSupportSafety.class,
		UTSupport.class,
		UTSupportEquality.class,
		UTSupportComparator.class,
		UTSupportBuilderSafety.class,
		UTSupportBuilder.class,
		UTSupportDatabaseSupport.class
})
public class SupportTestSuite implements DataFactory<Support> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Media> MEDIA_FACTORY = FactoryRepository.get(Media.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<URL> URL_FACTORY = FactoryRepository.get(URL.class);
	private static final String NAME = "Name";
	private static Long index = 1L;

	@Override
	public Support getNew() {
		return getNew(true);
	}

	@Override
	public Support getNext() {
		return getNext(true);
	}

	@Override
	public Support getNew(boolean withLink) {
		Support.Builder builder = Support.builder()
				.setName(NAME)
				.setWikipediaPage(URL_FACTORY.getNew());

		if (withLink) {
			builder.addMedia(MEDIA_FACTORY.getNew(false));
		}

		return builder.build();
	}

	@Override
	public Support getNext(boolean withLink) {
		Support.Builder builder = Support.builder()
				.setName(NAME + index)
				.setWikipediaPage(URL_FACTORY.getNext());

		if (withLink) {
			builder.addMedia(MEDIA_FACTORY.getNext(false))
					.addMedia(MEDIA_FACTORY.getNext(false));
		}

		index += 1;

		return builder.build();
	}
}