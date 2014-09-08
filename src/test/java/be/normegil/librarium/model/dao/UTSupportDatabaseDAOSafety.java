package be.normegil.librarium.model.dao;

import be.normegil.librarium.tool.test.model.dao.AbstractDAOSafetyTest;

public class UTSupportDatabaseDAOSafety extends AbstractDAOSafetyTest<SupportDatabaseDAO> {
	public UTSupportDatabaseDAOSafety() {
		super(SupportDatabaseDAO.class);
	}

	@Override
	protected SupportDatabaseDAO initDAO() {
		return new SupportDatabaseDAO();
	}
}
