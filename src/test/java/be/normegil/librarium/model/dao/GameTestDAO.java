package be.normegil.librarium.model.dao;

import be.normegil.librarium.model.data.game.Game;
import be.normegil.librarium.tool.EntityHelper;
import org.apache.commons.lang3.Validate;

import javax.ejb.Stateless;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Stateless
public class GameTestDAO implements DAO<Game> {

	public static final String NAME = "Name";

	private Collection<Game> games = new ArrayList<>();

	public GameTestDAO() {
		Game game1 = Game.builder().setTitle(NAME).build();
		Game game2 = Game.builder().setTitle(NAME + 2).build();
		Game game3 = Game.builder().setTitle(NAME + 3).build();

		EntityHelper entityHelper = new EntityHelper();
		entityHelper.setId(game1, UUID.fromString("a32bacaa-3c33-4435-a9b2-4ae7d13617f8"));
		entityHelper.setId(game2, UUID.fromString("40d46c43-0700-4f38-8f4a-dcfa8186195e"));
		entityHelper.setId(game3, UUID.fromString("72e608ea-202c-44aa-ae42-699130d8367c"));

		games.add(game1);
		games.add(game2);
		games.add(game3);
	}

	@Override
	public Collection<Game> getAll() {
		return new ArrayList<>(games);
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
