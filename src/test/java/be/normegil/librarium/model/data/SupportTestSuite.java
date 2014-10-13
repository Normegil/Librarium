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
		UTSupportSafety.class,
		UTSupport.class,
		UTSupportEquality.class,
		UTSupportComparator.class,
		UTSupportBuilderSafety.class,
		UTSupportBuilder.class,
		UTSupportDatabaseSupport.class
})
public class SupportTestSuite implements DataFactory<Support> {

	public static final UUID DEFAULT_ID = UUID.fromString("dc8924df-9cf2-46e5-9fb8-a8cb5850f646");
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Media> MEDIA_FACTORY = FactoryRepository.get(Media.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<URL> URL_FACTORY = FactoryRepository.get(URL.class);
	private static final String NAME = "Name";
	private static Long index = 1L;

	@Override
	public Support getDefault() {
		return getDefault(true, false);
	}

	@Override
	public Support getNew() {
		return getNew(true, false);
	}

	@Override
	public Support getDefault(final boolean withLink, final boolean withIds) {
		Support.Builder builder = Support.builder()
				.setName(NAME)
				.setWikipediaPage(URL_FACTORY.getDefault());

		if (withLink) {
			builder.addMedia(MEDIA_FACTORY.getDefault(false, withIds));
		}

		Support support = builder.build();
		if (withIds) {
			new EntityHelper().setId(support, DEFAULT_ID);
		}
		return support;
	}

	@Override
	public Support getNew(final boolean withLink, final boolean withIds) {
		Support.Builder builder = Support.builder()
				.setName(NAME + index)
				.setWikipediaPage(URL_FACTORY.getNew());

		if (withLink) {
			builder.addMedia(MEDIA_FACTORY.getNew(false, withIds))
					.addMedia(MEDIA_FACTORY.getNew(false, withIds));
		}

		index += 1;
		Support support = builder.build();
		if (withIds) {
			new EntityHelper().setId(support, DEFAULT_ID);
		}
		return support;
	}
}