package be.normegil.librarium.model.dao.book;

import be.normegil.librarium.tool.test.model.dao.AbstractDAOSafetyTest;

public class UTBookSerieDatabaseDAOSafety extends AbstractDAOSafetyTest<BookSerieDatabaseDAO> {
	public UTBookSerieDatabaseDAOSafety() {
		super(BookSerieDatabaseDAO.class);
	}

	@Override
	protected BookSerieDatabaseDAO initDAO() {
		return new BookSerieDatabaseDAO();
	}
}
