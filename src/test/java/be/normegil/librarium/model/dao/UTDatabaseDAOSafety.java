package be.normegil.librarium.model.dao;

import be.normegil.librarium.tool.test.model.dao.AbstractDAOSafetyTest;

public class UTDatabaseDAOSafety extends AbstractDAOSafetyTest<DatabaseDAO> {

	protected UTDatabaseDAOSafety(final Class<DatabaseDAO> daoClassSupported) {
		super(daoClassSupported);
	}

	@Override
	protected DatabaseDAO initDAO() {
		return new DatabaseDAO();
	}
}
