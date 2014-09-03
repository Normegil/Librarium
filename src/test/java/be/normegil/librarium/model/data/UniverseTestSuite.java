package be.normegil.librarium.model.data;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		UTUniverseSafety.class,
		UTUniverse.class,
		UTUniverseEquality.class,
		UTUniverseComparator.class,
		UTUniverseBuilderSafety.class,
		UTUniverseBuilder.class,
		UTUniverseDatabaseSupport.class
})
public class UniverseTestSuite implements DataFactory<Universe> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Media> MEDIA_FACTORY = FactoryRepository.get(Media.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<URL> URL_FACTORY = FactoryRepository.get(URL.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<DownloadLink> DOWNLOAD_LINK_FACTORY = FactoryRepository.get(DownloadLink.class);
	private static final String NAME = "Name";
	private static final String TITLE = "Title";
	private static final String DESCRIPTION = "Description";
	private static final String TAG = "Tag";
	private static long index = 0L;

	@Override
	public Universe getNew() {
		return getNew(true);
	}

	@Override
	public Universe getNext() {
		return getNext(true);
	}

	@Override
	public Universe getNew(boolean withLink) {
		Universe.Builder builder = Universe.builder()
				.setName(NAME)
				.setDescription(DESCRIPTION);

		if (withLink) {
			builder.addMedia(MEDIA_FACTORY.getNew(false));
		}

		return builder.build();
	}

	@Override
	public Universe getNext(boolean withLink) {
		Universe.Builder builder = Universe.builder()
				.setName(NAME + index)
				.setDescription(DESCRIPTION + index);

		if (withLink) {
			builder.addMedia(MEDIA_FACTORY.getNext(false))
					.addMedia(MEDIA_FACTORY.getNext(false));

		}
		index += 1;
		return builder.build();
	}
}