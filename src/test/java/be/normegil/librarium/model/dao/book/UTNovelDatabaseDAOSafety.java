package be.normegil.librarium.model.dao.book;

import be.normegil.librarium.tool.test.model.dao.AbstractDAOSafetyTest;

public class UTNovelDatabaseDAOSafety extends AbstractDAOSafetyTest<NovelDatabaseDAO> {
	public UTNovelDatabaseDAOSafety() {
		super(NovelDatabaseDAO.class);
	}

	@Override
	protected NovelDatabaseDAO initDAO() {
		return new NovelDatabaseDAO();
	}
}
