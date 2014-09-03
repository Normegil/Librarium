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
		UTDownloadLinkSafety.class,
		UTDownloadLink.class,
		UTDownloadLinkEquality.class,
		UTDownloadLinkComparator.class,
		UTDownloadLinkBuilderSafety.class,
		UTDownloadLinkBuilder.class,
		UTDownloadLinkDatabaseSupport.class
})
public class DownloadLinkTestSuite implements DataFactory<DownloadLink> {

	private static final String DESCRIPTION = "Description";
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<URL> URL_FACTORY = FactoryRepository.get(URL.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<BaseMedia> BASE_MEDIA_FACTORY = FactoryRepository.get(BaseMedia.class);
	private static final String TITLE = "Title";

	private static long index = 0L;

	@Override
	public DownloadLink getNew() {
		return getNew(true);
	}

	@Override
	public DownloadLink getNext() {
		return getNext(true);
	}

	@Override
	public DownloadLink getNew(boolean withLink) {
		DownloadLink.Builder builder = DownloadLink.builder()
				.setTitle(TITLE)
				.setDescription(DESCRIPTION)
				.setLink(URL_FACTORY.getNew());

		if (withLink) {
			builder.setMedia(BASE_MEDIA_FACTORY.getNew(false));
		}

		return builder.build();
	}

	@Override
	public DownloadLink getNext(boolean withLink) {
		DownloadLink.Builder builder = DownloadLink.builder()
				.setTitle(TITLE + index)
				.setDescription(DESCRIPTION + index)
				.setLink(URL_FACTORY.getNext());

		if (withLink) {
			builder.setMedia(BASE_MEDIA_FACTORY.getNext());
		}

		index += 1;
		return builder.build();
	}
}