package be.normegil.librarium.model.dao.book;

import be.normegil.librarium.tool.test.model.dao.AbstractDAOSafetyTest;

public class UTBDDatabaseDAOSafety extends AbstractDAOSafetyTest<BDDatabaseDAO> {
	public UTBDDatabaseDAOSafety() {
		super(BDDatabaseDAO.class);
	}

	@Override
	protected BDDatabaseDAO initDAO() {
		return new BDDatabaseDAO();
	}
}
