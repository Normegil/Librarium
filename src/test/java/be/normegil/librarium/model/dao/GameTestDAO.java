package be.normegil.librarium.model.dao;

import be.normegil.librarium.model.data.game.Game;
import be.normegil.librarium.tool.EntityHelper;
import org.apache.commons.lang3.Validate;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.Collection;

@Stateless
public class GameTestDAO implements GameDAO {

	public static final String NAME = "Name";

	private Collection<Game> games = new ArrayList<Game>();

	public GameTestDAO() {
		Game game1 = Game.builder().setTitle(NAME).build();
		Game game2 = Game.builder().setTitle(NAME + 2).build();
		Game game3 = Game.builder().setTitle(NAME + 3).build();

		EntityHelper entityHelper = new EntityHelper();
		entityHelper.setId(game1, 1L);
		entityHelper.setId(game2, 2L);
		entityHelper.setId(game3, 3L);

		games.add(game1);
		games.add(game2);
		games.add(game3);
	}

	@Override
	public Collection<Game> getAll() {
		return new ArrayList<Game>(games);
	}

	@Override
	public Game get(final Long id) {
		Validate.notNull(id);
		for (Game game : games) {
			if (game.getId().equals(id)) {
				return game;
			}
		}
		return null;
	}

	@Override
	public void save(final Game game) {
		remove(game);
		games.add(game);
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
