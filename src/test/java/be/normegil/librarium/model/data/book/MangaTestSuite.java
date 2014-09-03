package be.normegil.librarium.model.data.book;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.model.data.DownloadLink;
import be.normegil.librarium.model.data.Support;
import be.normegil.librarium.model.data.Universe;
import be.normegil.librarium.model.data.people.Responsible;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.TestResult;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import java.time.LocalDate;
import java.time.Month;

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
	private static final DataFactory<Responsible> RESPONSIBLE_FACTORY = FactoryRepository.get(Responsible.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<BookSerie> BOOK_SERIE_FACTORY = FactoryRepository.get(BookSerie.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<URL> URL_FACTORY = FactoryRepository.get(URL.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<DownloadLink> DOWNLOAD_LINK_FACTORY = FactoryRepository.get(DownloadLink.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Universe> UNIVERSE_FACTORY = FactoryRepository.get(Universe.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Support> SUPPORT_FACTORY = FactoryRepository.get(Support.class);
	private static final String TITLE = "Title";
	private static final String DESCRIPTION = "Description";
	private static final String TAG = "Tag";
	private static long index = 0L;

	@Override
	public Manga getNew() {
		return getNew(true);
	}

	@Override
	public Manga getNext() {
		return getNext(true);
	}

	@Override
	public Manga getNew(boolean withLink) {
		Manga.Builder builder = Manga.builder()
				.setTitle(TITLE)
				.setDescription(DESCRIPTION)
				.addTag(TAG)
				.addTag(TAG + 1)
				.setOfficialWebsite(URL_FACTORY.getNew(false))
				.setWikipediaPage(URL_FACTORY.getNew(false))
				.addStore(URL_FACTORY.getNew(false))
				.addDownloadLink(DOWNLOAD_LINK_FACTORY.getNew(false))
				.setIssueNumber(1L);

		if (withLink) {
			builder.addUniverse(UNIVERSE_FACTORY.getNew(false))
					.addReleaseDate(SUPPORT_FACTORY.getNew(false), LocalDate.of(2014, Month.AUGUST, 20))
					.addAuthor(RESPONSIBLE_FACTORY.getNew(false))
					.addEditor(RESPONSIBLE_FACTORY.getNew(false))
					.setSerie(BOOK_SERIE_FACTORY.getNew(false));
		}

		return builder.build();

	}

	@Override
	public Manga getNext(boolean withLink) {
		Manga.Builder builder = Manga.builder()
				.setTitle(TITLE + index)
				.setDescription(DESCRIPTION + index)
				.addTag(TAG + index)
				.addTag(TAG + index + 1)
				.setOfficialWebsite(URL_FACTORY.getNext(true))
				.setWikipediaPage(URL_FACTORY.getNext(true))
				.addStore(URL_FACTORY.getNext(true))
				.addStore(URL_FACTORY.getNext(true))
				.setIssueNumber(index);

		if (withLink) {
			builder.addDownloadLink(DOWNLOAD_LINK_FACTORY.getNext(false))
					.addDownloadLink(DOWNLOAD_LINK_FACTORY.getNext(false))
					.addUniverse(UNIVERSE_FACTORY.getNext(false))
					.addUniverse(UNIVERSE_FACTORY.getNext(false))
					.addSupport(SUPPORT_FACTORY.getNext(false))
					.addSupport(SUPPORT_FACTORY.getNext(false))
					.addReleaseDate(SUPPORT_FACTORY.getNext(false), LocalDate.of(2014, Month.AUGUST, 20))
					.addReleaseDate(SUPPORT_FACTORY.getNext(false), LocalDate.now())
					.addAuthor(RESPONSIBLE_FACTORY.getNext(false))
					.addAuthor(RESPONSIBLE_FACTORY.getNext(false))
					.addEditor(RESPONSIBLE_FACTORY.getNext(false))
					.addEditor(RESPONSIBLE_FACTORY.getNext(false))
					.setSerie(BOOK_SERIE_FACTORY.getNext(false));
		}

		index += 1;
		return builder.build();
	}
}