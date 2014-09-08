package be.normegil.librarium.model.dao.video;

import be.normegil.librarium.tool.test.model.dao.AbstractDAOSafetyTest;

public class UTMovieSerieDatabaseDAOSafety extends AbstractDAOSafetyTest<MovieSerieDatabaseDAO> {
	public UTMovieSerieDatabaseDAOSafety() {
		super(MovieSerieDatabaseDAO.class);
	}

	@Override
	protected MovieSerieDatabaseDAO initDAO() {
		return new MovieSerieDatabaseDAO();
	}
}
