package be.normegil.librarium.model.data.book;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.model.data.DownloadLink;
import be.normegil.librarium.model.data.Support;
import be.normegil.librarium.model.data.Universe;
import be.normegil.librarium.model.data.people.Responsible;
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
		UTMangaSafety.class,
		UTManga.class,
		UTMangaEquality.class,
		UTMangaComparator.class,
		UTMangaBuilderSafety.class,
		UTMangaBuilder.class,
		UTMangaDatabaseSupport.class
})
public class MangaTestSuite implements DataFactory<Manga> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<AbstractBD> ABSTRACT_BD_FACTORY = FactoryRepository.get(AbstractBD.class);
	public static final UUID DEFAULT_ID = UUID.fromString("8fa0c58d-204a-4bf5-93bc-cfc2e906ee15");

	@Override
	public Manga getDefault() {
		return getDefault(true, false);
	}

	@Override
	public Manga getNew() {
		return getNew(true, false);
	}

	@Override
	public Manga getDefault(final boolean withLink, final boolean withIds) {
		Manga.Builder builder = Manga.builder()
				.from(ABSTRACT_BD_FACTORY.getDefault(withLink, withIds));

		Manga manga = builder.build();
		if(withIds){
			new EntityHelper().setId(manga, DEFAULT_ID);
		}
		return manga;
	}

	@Override
	public Manga getNew(final boolean withLink, final boolean withIds) {
		Manga.Builder builder = Manga.builder()
				.from(ABSTRACT_BD_FACTORY.getNew(withLink, withIds));

		Manga manga = builder.build();
		if(withIds){
			new EntityHelper().setId(manga, DEFAULT_ID);
		}
		return manga;
	}
}