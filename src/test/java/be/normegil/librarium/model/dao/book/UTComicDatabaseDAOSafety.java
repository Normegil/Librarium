package be.normegil.librarium.model.dao.book;

import be.normegil.librarium.tool.test.model.dao.AbstractDAOSafetyTest;

public class UTComicDatabaseDAOSafety extends AbstractDAOSafetyTest<ComicDatabaseDAO> {
	public UTComicDatabaseDAOSafety() {
		super(ComicDatabaseDAO.class);
	}

	@Override
	protected ComicDatabaseDAO initDAO() {
		return new ComicDatabaseDAO();
	}
}
