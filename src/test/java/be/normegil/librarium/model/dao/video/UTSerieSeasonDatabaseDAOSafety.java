package be.normegil.librarium.model.dao.video;

import be.normegil.librarium.tool.test.model.dao.AbstractDAOSafetyTest;

public class UTSerieSeasonDatabaseDAOSafety extends AbstractDAOSafetyTest<SerieSeasonDatabaseDAO> {
	public UTSerieSeasonDatabaseDAOSafety() {
		super(SerieSeasonDatabaseDAO.class);
	}

	@Override
	protected SerieSeasonDatabaseDAO initDAO() {
		return new SerieSeasonDatabaseDAO();
	}
}
