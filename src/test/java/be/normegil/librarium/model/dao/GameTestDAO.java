package be.normegil.librarium.model.dao;

import be.normegil.librarium.model.data.Game;
import org.apache.commons.lang3.Validate;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.Collection;

@Stateless
public class GameTestDAO implements GameDAO {

    public static final String NAME = "Name";

    private Collection<Game> games = new ArrayList<Game>();

    public GameTestDAO() {
        games.add(new Game(1L, NAME));
        games.add(new Game(2L, NAME + 2));
        games.add(new Game(3L, NAME + 3));
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
