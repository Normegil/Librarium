package be.normegil.librarium.model.dao;

import be.normegil.librarium.model.data.game.Game;
import be.normegil.librarium.tool.test.model.dao.AbstractDAOSafetyTest;

public class UTDatabaseDAOSafety extends AbstractDAOSafetyTest<DatabaseDAO> {

	public UTDatabaseDAOSafety() {
		super(DatabaseDAO.class);
	}

	@Override
	protected DatabaseDAO initDAO() {
		return new DatabaseDAO<>(Game.class);
	}
}
