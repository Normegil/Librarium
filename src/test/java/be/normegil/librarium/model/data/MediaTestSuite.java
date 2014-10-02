package be.normegil.librarium.model.data;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.model.data.fake.FakeMedia;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import java.time.LocalDate;
import java.time.Month;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		UTMediaSafety.class,
		UTMedia.class,
		UTMediaEquality.class,
		UTMediaComparator.class,
		UTMediaBuilderSafety.class,
		UTMediaBuilder.class,
		UTMediaDigestSafety.class,
		UTMediaDigest.class
})
public class MediaTestSuite implements DataFactory<Media> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<BaseMedia> BASE_MEDIA_FACTORY = FactoryRepository.get(BaseMedia.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Universe> UNIVERSE_FACTORY = FactoryRepository.get(Universe.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Support> SUPPORT_FACTORY = FactoryRepository.get(Support.class);

	@Override
	public Media getNew() {
		return getNew(true);
	}

	@Override
	public Media getNext() {
		return getNext(true);
	}

	@Override
	public Media getNew(boolean withLink) {
		FakeMedia.Builder builder = FakeMedia.builder()
				.from(BASE_MEDIA_FACTORY.getNew(withLink));

		if (withLink) {
			builder.addUniverse(UNIVERSE_FACTORY.getNew(false))
					.addReleaseDate(SUPPORT_FACTORY.getNew(false), LocalDate.of(2014, Month.AUGUST, 20));
		}

		return builder.build();
	}

	@Override
	public Media getNext(boolean withLink) {
		FakeMedia.Builder builder = FakeMedia.builder()
				.from(BASE_MEDIA_FACTORY.getNext(withLink));

		if (withLink) {
			builder.addUniverse(UNIVERSE_FACTORY.getNext(false))
					.addUniverse(UNIVERSE_FACTORY.getNext(false))
					.addSupport(SUPPORT_FACTORY.getNext(false))
					.addSupport(SUPPORT_FACTORY.getNext(false))
					.addReleaseDate(SUPPORT_FACTORY.getNext(false), LocalDate.of(2014, Month.AUGUST, 20))
					.addReleaseDate(SUPPORT_FACTORY.getNext(false), LocalDate.now());
		}

		return builder.build();
	}
}