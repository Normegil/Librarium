package be.normegil.librarium.model.dao;

import be.normegil.librarium.tool.test.model.dao.AbstractDAOSafetyTest;

public class UTUniverseDatabaseDAOSafety extends AbstractDAOSafetyTest<UniverseDatabaseDAO> {
	public UTUniverseDatabaseDAOSafety() {
		super(UniverseDatabaseDAO.class);
	}

	@Override
	protected UniverseDatabaseDAO initDAO() {
		return new UniverseDatabaseDAO();
	}
}
