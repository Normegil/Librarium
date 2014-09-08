package be.normegil.librarium.model.dao.video;

import be.normegil.librarium.tool.test.model.dao.AbstractDAOSafetyTest;

public class UTMovieDatabaseDAOSafety extends AbstractDAOSafetyTest<MovieDatabaseDAO> {
	public UTMovieDatabaseDAOSafety() {
		super(MovieDatabaseDAO.class);
	}

	@Override
	protected MovieDatabaseDAO initDAO() {
		return new MovieDatabaseDAO();
	}
}
