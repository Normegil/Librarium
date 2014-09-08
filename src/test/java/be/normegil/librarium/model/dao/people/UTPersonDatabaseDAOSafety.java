package be.normegil.librarium.model.dao.people;

import be.normegil.librarium.tool.test.model.dao.AbstractDAOSafetyTest;

public class UTPersonDatabaseDAOSafety extends AbstractDAOSafetyTest<PersonDatabaseDAO> {
	public UTPersonDatabaseDAOSafety() {
		super(PersonDatabaseDAO.class);
	}

	@Override
	protected PersonDatabaseDAO initDAO() {
		return new PersonDatabaseDAO();
	}
}
