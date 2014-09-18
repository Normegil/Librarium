package be.normegil.librarium.tool;

import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.model.data.*;
import be.normegil.librarium.model.data.book.*;
import be.normegil.librarium.model.data.game.Game;
import be.normegil.librarium.model.data.game.GameSerie;
import be.normegil.librarium.model.data.game.GameSerieTestSuite;
import be.normegil.librarium.model.data.game.GameTestSuite;
import be.normegil.librarium.model.data.people.*;
import be.normegil.librarium.model.data.video.*;
import be.normegil.librarium.model.rest.CollectionResource;
import be.normegil.librarium.model.rest.CollectionResourceTestSuite;
import be.normegil.librarium.model.rest.exception.RESTError;
import be.normegil.librarium.model.rest.exception.RESTErrorTestSuite;
import be.normegil.librarium.util.jaxb.JAXBHelperTestSuite;

import java.util.HashMap;
import java.util.Map;

public class FactoryRepository {

	private static Map<Class, DataFactory> factories = new HashMap<>();
	private static boolean isInitialized = false;

	public static DataFactory get(Class aClass) {

		if (!isInitialized) {
			synchronized (FactoryRepository.class) {
				if (!isInitialized) {
					initialize();
					isInitialized = true;
				}
			}
		}

		DataFactory factory = factories.get(aClass);
		if (factory == null) {
			throw new UnsupportedOperationException("Factory not found for type [Type=" + aClass + "]");
		} else {
			return factory;
		}
	}

	private static void initialize() {
		// Framework classes
		factories.put(URL.class, new URLFactory());
		factories.put(CollectionResource.class, new CollectionResourceTestSuite());
		factories.put(RESTError.class, new RESTErrorTestSuite());

		// Model Classes
		factories.put(AbstractBD.class, new AbstractBDTestSuite());
		factories.put(BaseMedia.class, new BaseMediaTestSuite());
		factories.put(BD.class, new BDTestSuite());
		factories.put(Book.class, new BookTestSuite());
		factories.put(BookSerie.class, new BookSerieTestSuite());
		factories.put(Comic.class, new ComicTestSuite());
		factories.put(DownloadLink.class, new DownloadLinkTestSuite());
		factories.put(Enterprise.class, new EnterpriseTestSuite());
		factories.put(Entity.class, new EntityTestSuite());
		factories.put(EpisodeSerie.class, new EpisodeSerieTestSuite());
		factories.put(Game.class, new GameTestSuite());
		factories.put(GameSerie.class, new GameSerieTestSuite());
		factories.put(Manga.class, new MangaTestSuite());
		factories.put(Media.class, new MediaTestSuite());
		factories.put(Movie.class, new MovieTestSuite());
		factories.put(MovieSerie.class, new MovieSerieTestSuite());
		factories.put(Novel.class, new NovelTestSuite());
		factories.put(Person.class, new PersonTestSuite());
		factories.put(ReleaseDate.class, new ReleaseDateTestSuite());
		factories.put(Responsible.class, new ResponsibleTestSuite());
		factories.put(Role.class, new RoleTestSuite());
		factories.put(Serie.class, new SerieTestSuite());
		factories.put(SerieSeason.class, new SerieSeasonTestSuite());
		factories.put(StaffMember.class, new StaffMemberTestSuite());
		factories.put(Support.class, new SupportTestSuite());
		factories.put(Universe.class, new UniverseTestSuite());
		factories.put(Video.class, new VideoTestSuite());

		//Test Classes
		factories.put(JAXBHelperTestSuite.JAXBTestClass.class, new JAXBHelperTestSuite());
	}
}
