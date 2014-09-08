package be.normegil.librarium.model.dao.video;

import be.normegil.librarium.tool.test.model.dao.AbstractDAOSafetyTest;

public class UTSerieDatabaseDAOSafety extends AbstractDAOSafetyTest<SerieDatabaseDAO> {
	public UTSerieDatabaseDAOSafety() {
		super(SerieDatabaseDAO.class);
	}

	@Override
	protected SerieDatabaseDAO initDAO() {
		return new SerieDatabaseDAO();
	}
}
