package be.normegil.librarium.model.data;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.model.data.fake.FakeMedia;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.EntityHelper;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import java.time.LocalDate;
import java.time.Month;
import java.util.UUID;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		UTMediaSafety.class,
		UTMedia.class,
		UTMediaEquality.class,
		UTMediaComparator.class,
		UTMediaBuilderSafety.class,
		UTMediaBuilder.class,
		UTMediaDigestSafety.class,
		UTMediaDigest.class,
		UTMediaDigestEquality.class
})
public class MediaTestSuite implements DataFactory<Media> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<BaseMedia> BASE_MEDIA_FACTORY = FactoryRepository.get(BaseMedia.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Universe> UNIVERSE_FACTORY = FactoryRepository.get(Universe.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Support> SUPPORT_FACTORY = FactoryRepository.get(Support.class);
	private static final UUID DEFAULT_ID = UUID.fromString("");

	@Override
	public Media getDefault() {
		return getDefault(true, false);
	}

	@Override
	public Media getNew() {
		return getNew(true, false);
	}

	@Override
	public Media getDefault(final boolean withLink, final boolean withIds) {
		FakeMedia.Builder builder = FakeMedia.builder()
				.from(BASE_MEDIA_FACTORY.getDefault(withLink, withIds));

		if (withLink) {
			builder.addUniverse(UNIVERSE_FACTORY.getDefault(false, withIds))
					.addReleaseDate(SUPPORT_FACTORY.getDefault(false, withIds), LocalDate.of(2014, Month.AUGUST, 20));
		}

		Media media = builder.build();
		if(withIds){
			new EntityHelper().setId(media, DEFAULT_ID);
		}
		return media;
	}

	@Override
	public Media getNew(final boolean withLink, final boolean withIds) {
		FakeMedia.Builder builder = FakeMedia.builder()
				.from(BASE_MEDIA_FACTORY.getNew(withLink, withIds));

		if (withLink) {
			builder.addUniverse(UNIVERSE_FACTORY.getNew(false, withIds))
					.addUniverse(UNIVERSE_FACTORY.getNew(false, withIds))
					.addSupport(SUPPORT_FACTORY.getNew(false, withIds))
					.addSupport(SUPPORT_FACTORY.getNew(false, withIds))
					.addReleaseDate(SUPPORT_FACTORY.getNew(false, withIds), LocalDate.of(2014, Month.AUGUST, 20))
					.addReleaseDate(SUPPORT_FACTORY.getNew(false, withIds), LocalDate.now());
		}

		Media media = builder.build();
		if(withIds){
			new EntityHelper().setId(media, DEFAULT_ID);
		}
		return media;
	}
}