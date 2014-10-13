package be.normegil.librarium.model.data;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.EntityHelper;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import java.util.UUID;

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
	private static final String NAME = "Name";
	private static final String DESCRIPTION = "Description";
	private static final UUID DEFAULT_ID = UUID.fromString("");
	private static long index = 0L;

	@Override
	public Universe getDefault() {
		return getDefault(true, false);
	}

	@Override
	public Universe getNew() {
		return getNew(true, false);
	}

	@Override
	public Universe getDefault(final boolean withLink, final boolean withIds) {
		Universe.Builder builder = Universe.builder()
				.setName(NAME)
				.setDescription(DESCRIPTION);

		if (withLink) {
			builder.addMedia(MEDIA_FACTORY.getDefault(false, withIds));
		}

		Universe universe = builder.build();
		if (withIds) {
			new EntityHelper().setId(universe, DEFAULT_ID);
		}
		return universe;
	}

	@Override
	public Universe getNew(final boolean withLink, final boolean withIds) {
		Universe.Builder builder = Universe.builder()
				.setName(NAME + index)
				.setDescription(DESCRIPTION + index);

		if (withLink) {
			builder.addMedia(MEDIA_FACTORY.getNew(false, withIds))
					.addMedia(MEDIA_FACTORY.getNew(false, withIds));

		}
		index += 1;
		Universe universe = builder.build();
		if (withIds) {
			new EntityHelper().setId(universe, DEFAULT_ID);
		}
		return universe;
	}
}