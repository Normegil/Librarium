package be.normegil.librarium.model.dao;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.model.data.game.Game;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.EntityHelper;
import be.normegil.librarium.tool.FactoryRepository;
import org.apache.commons.lang3.Validate;

import javax.ejb.Stateless;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Stateless
public class GameTestDAO implements DAO<Game> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Game> GAME_FACTORY = FactoryRepository.get(Game.class);
	private static final int DEFAULT_NUMBER_OF_GAMES = 5;
	private Collection<Game> games = new ArrayList<>();

	public GameTestDAO() {
		this(DEFAULT_NUMBER_OF_GAMES);
	}

	public GameTestDAO(int numberOfGamesToAdd) {
		addGame(numberOfGamesToAdd);
	}

	private EntityHelper addGame(int numberOfGames) {
		EntityHelper entityHelper = new EntityHelper();
		for (int i = 0; i < numberOfGames; i++) {
			Game game = GAME_FACTORY.getNext();
			entityHelper.setId(game, UUID.randomUUID());
			games.add(game);
		}
		return entityHelper;
	}

	@Override
	public Collection<Game> getAll() {
		return new ArrayList<>(games);
	}

	@Override
	public List<Game> getAll(final long offset, final int limit) {
		return null;
	}

	@Override
	public long getNumberOfElements() {
		return games.size();
	}

	@Override
	public Game get(final Object id) {
		Validate.notNull(id);
		for (Game game : games) {
			if (game.getId().equals(id)) {
				return game;
			}
		}
		return null;
	}

	@Override
	public void create(@NotNull @Valid final Game entity) {
		games.add(entity);
	}

	@Override
	public Game update(@NotNull @Valid final Game entity) {
		remove(entity);
		create(entity);
		return entity;
	}

	@Override
	public void remove(final Game game) {
		Validate.notNull(game);
		Game foundGame = null;
		for (Game gameInMemory : games) {
			if (gameInMemory.getId().equals(game.getId())) {
				foundGame = gameInMemory;
				break;
			}
		}
		games.remove(foundGame);
	}
}
