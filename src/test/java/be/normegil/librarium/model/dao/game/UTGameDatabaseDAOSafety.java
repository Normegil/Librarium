package be.normegil.librarium.model.dao.game;

import be.normegil.librarium.tool.test.model.dao.AbstractDAOSafetyTest;

public class UTGameDatabaseDAOSafety extends AbstractDAOSafetyTest<GameDatabaseDAO> {
	public UTGameDatabaseDAOSafety() {
		super(GameDatabaseDAO.class);
	}

	@Override
	protected GameDatabaseDAO initDAO() {
		return new GameDatabaseDAO();
	}
}
