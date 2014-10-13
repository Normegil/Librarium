package be.normegil.librarium.model.data;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.model.data.fake.FakeBaseMedia;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		UTBaseMediaSafety.class,
		UTBaseMedia.class,
		UTBaseMediaEquality.class,
		UTBaseMediaComparator.class,
		UTBaseMediaBuilderSafety.class,
		UTBaseMediaBuilder.class,
		UTBaseMediaDigestSafety.class,
		UTBaseMediaDigest.class,
		UTBaseMediaDigestEquality.class
})
public class BaseMediaTestSuite implements DataFactory<BaseMedia> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<URL> URL_FACTORY = FactoryRepository.get(URL.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<DownloadLink> DOWNLOAD_LINK_FACTORY = FactoryRepository.get(DownloadLink.class);
	private static final String TITLE = "Title";
	private static final String DESCRIPTION = "Description";
	private static final String TAG = "Tag";
	private static long index = 0L;

	@Override
	public BaseMedia getNew() {
		return getNew(true);
	}

	@Override
	public BaseMedia getNext() {
		return getNext(true);
	}

	@Override
	public BaseMedia getNew(boolean withLink) {
		FakeBaseMedia.Builder builder = FakeBaseMedia.builder()
				.setTitle(TITLE)
				.setDescription(DESCRIPTION)
				.addTag(TAG)
				.addTag(TAG + 1)
				.setOfficialWebsite(URL_FACTORY.getNew())
				.setWikipediaPage(URL_FACTORY.getNew())
				.addStore(URL_FACTORY.getNew());

		if (withLink) {
			builder.addDownloadLink(DOWNLOAD_LINK_FACTORY.getNew());
		}

		return builder.build();
	}

	@Override
	public BaseMedia getNext(boolean withLink) {
		FakeBaseMedia.Builder builder = FakeBaseMedia.builder()
				.setTitle(TITLE + index)
				.setDescription(DESCRIPTION + index)
				.setOfficialWebsite(URL_FACTORY.getNext())
				.setWikipediaPage(URL_FACTORY.getNext())
				.addTag(TAG + index)
				.addTag(TAG + index + 1)
				.addStore(URL_FACTORY.getNext())
				.addStore(URL_FACTORY.getNext());

		if (withLink) {
			builder.addDownloadLink(DOWNLOAD_LINK_FACTORY.getNext(false))
					.addDownloadLink(DOWNLOAD_LINK_FACTORY.getNext(false));
		}

		index += 1;
		return builder.build();
	}
}