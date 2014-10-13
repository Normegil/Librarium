package be.normegil.librarium.model.data;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.model.data.fake.FakeBaseMedia;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.EntityHelper;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import java.util.UUID;

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

	public static final UUID DEFAULT_ID = UUID.fromString("3b482e5c-ade3-410e-978c-37b1a0a959ec");
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<URL> URL_FACTORY = FactoryRepository.get(URL.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<DownloadLink> DOWNLOAD_LINK_FACTORY = FactoryRepository.get(DownloadLink.class);
	private static final String TITLE = "Title";
	private static final String DESCRIPTION = "Description";
	private static final String TAG = "Tag";
	private static long index = 0L;

	@Override
	public BaseMedia getDefault() {
		return getDefault(true, false);
	}

	@Override
	public BaseMedia getNew() {
		return getNew(true, false);
	}

	@Override
	public BaseMedia getDefault(final boolean withLink, final boolean withIds) {
		FakeBaseMedia.Builder builder = FakeBaseMedia.builder()
				.setTitle(TITLE)
				.setDescription(DESCRIPTION)
				.addTag(TAG)
				.addTag(TAG + 1)
				.setOfficialWebsite(URL_FACTORY.getDefault())
				.setWikipediaPage(URL_FACTORY.getDefault())
				.addStore(URL_FACTORY.getDefault());

		if (withLink) {
			builder.addDownloadLink(DOWNLOAD_LINK_FACTORY.getDefault(false, withIds));
		}

		BaseMedia media = builder.build();
		if (withIds) {
			new EntityHelper().setId(media, DEFAULT_ID);
		}
		return media;
	}

	@Override
	public BaseMedia getNew(final boolean withLink, final boolean withIds) {
		FakeBaseMedia.Builder builder = FakeBaseMedia.builder()
				.setTitle(TITLE + index)
				.setDescription(DESCRIPTION + index)
				.setOfficialWebsite(URL_FACTORY.getNew())
				.setWikipediaPage(URL_FACTORY.getNew())
				.addTag(TAG + index)
				.addTag(TAG + index + 1)
				.addStore(URL_FACTORY.getNew())
				.addStore(URL_FACTORY.getNew());

		if (withLink) {
			builder.addDownloadLink(DOWNLOAD_LINK_FACTORY.getNew(false, withIds))
					.addDownloadLink(DOWNLOAD_LINK_FACTORY.getNew(false, withIds));
		}

		index += 1;
		BaseMedia media = builder.build();
		if (withIds) {
			new EntityHelper().setId(media, DEFAULT_ID);
		}
		return media;
	}
}