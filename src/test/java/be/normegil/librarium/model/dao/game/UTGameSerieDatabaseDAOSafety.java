package be.normegil.librarium.model.dao.game;

import be.normegil.librarium.tool.test.model.dao.AbstractDAOSafetyTest;

public class UTGameSerieDatabaseDAOSafety extends AbstractDAOSafetyTest<GameSerieDatabaseDAO> {
	public UTGameSerieDatabaseDAOSafety() {
		super(GameSerieDatabaseDAO.class);
	}

	@Override
	protected GameSerieDatabaseDAO initDAO() {
		return new GameSerieDatabaseDAO();
	}
}
