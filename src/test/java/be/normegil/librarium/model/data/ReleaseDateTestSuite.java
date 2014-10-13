package be.normegil.librarium.model.data;

import be.normegil.librarium.WarningTypes;
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
	private static final UUID DEFAULT_ID = UUID.fromString("");

	@Override
	public ReleaseDate getDefault() {
		return getDefault(true, false);
	}

	@Override
	public ReleaseDate getNew() {
		return getNew(true, false);
	}

	@Override
	public ReleaseDate getDefault(final boolean withLink, final boolean withIds) {
		ReleaseDate releaseDate = new ReleaseDate(
				MEDIA_FACTORY.getDefault(false, withIds),
				SUPPORT_FACTORY.getDefault(false, withIds),
				LocalDate.of(2014, Month.AUGUST, 30)
		);
		if(withIds){
			new EntityHelper().setId(releaseDate, DEFAULT_ID);
		}
		return releaseDate;
	}

	@Override
	public ReleaseDate getNew(final boolean withLink, final boolean withIds) {
		ReleaseDate releaseDate = new ReleaseDate(
				MEDIA_FACTORY.getNew(false, withIds),
				SUPPORT_FACTORY.getNew(false, withIds),
				LocalDate.now()
		);
		if(withIds){
			new EntityHelper().setId(releaseDate, DEFAULT_ID);
		}
		return releaseDate;
	}
}