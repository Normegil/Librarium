package be.normegil.librarium.model.rest;

import be.normegil.librarium.ApplicationProperties;
import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.model.data.game.Game;
import be.normegil.librarium.rest.RESTCollectionHelper;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import java.util.ArrayList;
import java.util.List;

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
	private static final long FIRST_OFFSET = 0L;
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
		List<URL> urls = helper.convertToURLs(games, url);
		return CollectionResource.builder()
				.addAllItems(urls)
				.setOffset(DEFAULT_OFFSET)
				.setLimit(DEFAULT_LIMIT)
				.setFirst(helper.getCollectionURL(url, FIRST_OFFSET, DEFAULT_LIMIT))
				.setLast(helper.getCollectionURL(url, helper.getLastOffset(urls.size(), DEFAULT_LIMIT), DEFAULT_LIMIT))
				.build();
	}

	@Override
	public CollectionResource getNext(final boolean withLink) {
		URL url = URL_FACTORY.getNext();
		List<Game> games = new ArrayList<>();
		games.add(GAME_FACTORY.getNext(false));
		games.add(GAME_FACTORY.getNext(false));
		games.add(GAME_FACTORY.getNext(false));
		games.add(GAME_FACTORY.getNext(false));
		RESTCollectionHelper helper = new RESTCollectionHelper();
		List<URL> urls = helper.convertToURLs(games, url);
		return CollectionResource.builder()
				.addAllItems(urls)
				.setOffset(DEFAULT_OFFSET)
				.setLimit(DEFAULT_LIMIT)
				.setFirst(helper.getCollectionURL(url, FIRST_OFFSET, DEFAULT_LIMIT))
				.setLast(helper.getCollectionURL(url, helper.getLastOffset(urls.size(), DEFAULT_LIMIT), DEFAULT_LIMIT))
				.build();
	}
}