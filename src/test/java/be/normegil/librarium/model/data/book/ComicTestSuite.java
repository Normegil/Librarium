package be.normegil.librarium.model.data.book;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.EntityHelper;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import java.util.UUID;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		UTComicSafety.class,
		UTComic.class,
		UTComicEquality.class,
		UTComicComparator.class,
		UTComicBuilderSafety.class,
		UTComicBuilder.class,
		UTComicDatabaseSupport.class
})
public class ComicTestSuite implements DataFactory<Comic> {

	public static final UUID DEFAULT_ID = UUID.fromString("");
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<AbstractBD> ABSTRACT_BD_FACTORY = FactoryRepository.get(AbstractBD.class);

	@Override
	public Comic getDefault() {
		return getDefault(true, false);
	}

	@Override
	public Comic getNew() {
		return getNew(true, false);
	}

	@Override
	public Comic getDefault(final boolean withLink, final boolean withIds) {
		Comic comic = Comic.builder()
				.from(ABSTRACT_BD_FACTORY.getDefault(withLink, withIds))
				.build();
		if (withIds) {
			new EntityHelper().setId(comic, DEFAULT_ID);
		}
		return comic;
	}

	@Override
	public Comic getNew(final boolean withLink, final boolean withIds) {
		Comic comic = Comic.builder()
				.from(ABSTRACT_BD_FACTORY.getNew(withLink, withIds))
				.build();
		if (withIds) {
			new EntityHelper().setId(comic, UUID.randomUUID());
		}
		return comic;
	}
}