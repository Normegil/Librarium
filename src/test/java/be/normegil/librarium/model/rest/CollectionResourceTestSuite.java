package be.normegil.librarium.model.rest;

import be.normegil.librarium.ApplicationProperties;
import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.model.dao.DAO;
import be.normegil.librarium.model.dao.GameTestDAO;
import be.normegil.librarium.model.data.game.Game;
import be.normegil.librarium.rest.RESTCollectionHelper;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.EntityHelper;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		UTCollectionResourceBuilderSafety.class,
		UTCollectionResourceBuilder.class,
		UTCollectionResourceXMLSupport.class,
		UTCollectionResourceJSONSupport.class
})
public class CollectionResourceTestSuite implements DataFactory<CollectionResource> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Game> GAME_FACTORY = FactoryRepository.get(Game.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<URL> URL_FACTORY = FactoryRepository.get(URL.class);
	private static final long DEFAULT_OFFSET = 0L;
	private static final int DEFAULT_LIMIT = ApplicationProperties.REST.DEFAULT_LIMIT;

	@Override
	public CollectionResource getNew() {
		return getNew(true);
	}

	@Override
	public CollectionResource getNext() {
		return getNext(true);
	}

	@Override
	public CollectionResource getNew(final boolean withLink) {
		URL url = URL_FACTORY.getNew();
		List<Game> games = new ArrayList<>();
		games.add(GAME_FACTORY.getNew());
		RESTCollectionHelper helper = new RESTCollectionHelper();
		EntityHelper entityHelper = new EntityHelper();
		for (Game game : games) {
			entityHelper.setId(game, UUID.randomUUID());
		}
		List<URL> urls = helper.convertToURLs(games, url);
		return CollectionResource.builder()
				.addAllItems(urls)
				.setOffset(DEFAULT_OFFSET)
				.setLimit(urls.size())
				.setBaseURL(url)
				.setTotalNumberOfItems(50)
				.build();
	}

	@Override
	public CollectionResource getNext(final boolean withLink) {
		URL url = URL_FACTORY.getNext();
		DAO<Game> dao = new GameTestDAO(DEFAULT_LIMIT * 2);
		RESTCollectionHelper helper = new RESTCollectionHelper();
		List<URL> urls = helper.convertToURLs(dao.getAll(DEFAULT_OFFSET, DEFAULT_LIMIT), url);
		return CollectionResource.builder()
				.addAllItems(urls)
				.setOffset(DEFAULT_OFFSET)
				.setLimit(DEFAULT_LIMIT)
				.setBaseURL(url)
				.setTotalNumberOfItems(dao.getNumberOfElements())
				.build();
	}
}