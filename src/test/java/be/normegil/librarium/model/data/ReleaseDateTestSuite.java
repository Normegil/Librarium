package be.normegil.librarium.model.data;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import java.time.LocalDate;
import java.time.Month;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		UTReleaseDateSafety.class,
		UTReleaseDate.class,
		UTReleaseDateEquality.class,
		UTReleaseDateComparator.class,
		UTReleaseDateDatabaseSupport.class
})
public class ReleaseDateTestSuite implements DataFactory<ReleaseDate> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Media> MEDIA_FACTORY = FactoryRepository.get(Media.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Support> SUPPORT_FACTORY = FactoryRepository.get(Support.class);

	@Override
	public ReleaseDate getDefault() {
		return getDefault(true);
	}

	@Override
	public ReleaseDate getNew() {
		return getNew(true);
	}

	@Override
	public ReleaseDate getDefault(final boolean withLink) {
		return new ReleaseDate(
				MEDIA_FACTORY.getDefault(),
				SUPPORT_FACTORY.getDefault(),
				LocalDate.of(2014, Month.AUGUST, 30)
		);
	}

	@Override
	public ReleaseDate getNew(final boolean withLink) {
		ReleaseDate entity = new ReleaseDate(
				MEDIA_FACTORY.getNew(),
				SUPPORT_FACTORY.getNew(),
				LocalDate.now()
		);
		return entity;
	}
}