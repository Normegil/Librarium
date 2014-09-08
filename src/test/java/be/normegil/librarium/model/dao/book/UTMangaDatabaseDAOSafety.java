package be.normegil.librarium.model.dao.book;

import be.normegil.librarium.tool.test.model.dao.AbstractDAOSafetyTest;

public class UTMangaDatabaseDAOSafety extends AbstractDAOSafetyTest<MangaDatabaseDAO> {
	public UTMangaDatabaseDAOSafety() {
		super(MangaDatabaseDAO.class);
	}

	@Override
	protected MangaDatabaseDAO initDAO() {
		return new MangaDatabaseDAO();
	}
}
