package be.normegil.librarium.model.data;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.EntityHelper;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import java.util.UUID;

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
	private static final UUID DEFAULT_ID = UUID.fromString("");

	private static long index = 0L;

	@Override
	public DownloadLink getDefault() {
		return getDefault(true, false);
	}

	@Override
	public DownloadLink getNew() {
		return getNew(true, false);
	}

	@Override
	public DownloadLink getDefault(final boolean withLink, final boolean withIds) {
		DownloadLink.Builder builder = DownloadLink.builder()
				.setTitle(TITLE)
				.setDescription(DESCRIPTION)
				.setLink(URL_FACTORY.getDefault());

		if (withLink) {
			builder.setMedia(BASE_MEDIA_FACTORY.getDefault(false, withIds));
		}

		DownloadLink downloadLink = builder.build();
		if (withIds) {
			new EntityHelper().setId(downloadLink, DEFAULT_ID);
		}
		return downloadLink;
	}

	@Override
	public DownloadLink getNew(final boolean withLink, final boolean withIds) {
		DownloadLink.Builder builder = DownloadLink.builder()
				.setTitle(TITLE + index)
				.setDescription(DESCRIPTION + index)
				.setLink(URL_FACTORY.getNew());

		if (withLink) {
			builder.setMedia(BASE_MEDIA_FACTORY.getNew());
		}

		index += 1;
		DownloadLink downloadLink = builder.build();
		if (withIds) {
			new EntityHelper().setId(downloadLink, DEFAULT_ID);
		}
		return downloadLink;
	}
}