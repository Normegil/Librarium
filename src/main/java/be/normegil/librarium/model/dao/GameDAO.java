package be.normegil.librarium.model.dao;

import be.normegil.librarium.model.data.Game;

import java.util.Collection;

public interface GameDAO {

    Collection<Game> getAll();

    Game get(Long id);

    void save(Game game);

    void remove(Game game);

}
