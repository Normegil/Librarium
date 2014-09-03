package be.normegil.librarium.model.dao;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.model.data.game.Game;
import org.apache.commons.lang3.Validate;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Stateless
public class GameDatabaseDAO implements GameDAO {

	public static final String GET_ALL_QUERY = "select g from Game g";
	@PersistenceContext(unitName = "MainPU")
	EntityManager entityManager;

	@Override
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	public Collection<Game> getAll() {
		return entityManager.createQuery(GET_ALL_QUERY).getResultList();
	}

	@Override
	public Game get(final Long id) {
		Validate.notNull(id);
		return entityManager.find(Game.class, id);
	}

	@Override
	public void save(final Game game) {
		Validate.notNull(game);
		entityManager.persist(game);
	}

	@Override
	public void remove(final Game game) {
		Validate.notNull(game);
		entityManager.remove(game);
	}
}
