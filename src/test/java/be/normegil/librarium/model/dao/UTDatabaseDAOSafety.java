package be.normegil.librarium.model.dao;

import be.normegil.librarium.tool.test.model.dao.AbstractDAOSafetyTest;

public class UTDatabaseDAOSafety extends AbstractDAOSafetyTest<DatabaseDAO> {

	@Override
	protected DatabaseDAO initDAO() {
		return new DatabaseDAO();
	}
}
