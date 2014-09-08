package be.normegil.librarium.model.dao.video;

import be.normegil.librarium.tool.test.model.dao.AbstractDAOSafetyTest;

public class UTEpisodeSerieDatabaseDAOSafety extends AbstractDAOSafetyTest<EpisodeSerieDatabaseDAO> {
	public UTEpisodeSerieDatabaseDAOSafety() {
		super(EpisodeSerieDatabaseDAO.class);
	}

	@Override
	protected EpisodeSerieDatabaseDAO initDAO() {
		return new EpisodeSerieDatabaseDAO();
	}
}
