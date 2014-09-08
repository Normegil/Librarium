package be.normegil.librarium.model.dao.people;

import be.normegil.librarium.tool.test.model.dao.AbstractDAOSafetyTest;

public class UTEnterpriseDatabaseDAOSafety extends AbstractDAOSafetyTest<EnterpriseDatabaseDAO> {
	public UTEnterpriseDatabaseDAOSafety() {
		super(EnterpriseDatabaseDAO.class);
	}

	@Override
	protected EnterpriseDatabaseDAO initDAO() {
		return new EnterpriseDatabaseDAO();
	}
}
