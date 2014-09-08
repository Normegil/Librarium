package be.normegil.librarium.model.dao.book;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.model.data.book.Manga;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.model.dao.AbstractDAOTest;

import static org.junit.Assert.assertEquals;

public class UTMangaDatabaseDAO extends AbstractDAOTest<MangaDatabaseDAO, Manga> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Manga> FACTORY = FactoryRepository.get(Manga.class);
	private static final String ALTERNATIVE_TITLE = "AlternativeTitle";

	@Override
	protected MangaDatabaseDAO initDAO() {
		return new MangaDatabaseDAO();
	}

	@Override
	protected Object getEntityId(final Manga entity) {
		return entity.getId();
	}

	@Override
	protected void changeEntity(final Manga entity) {
		entity.setTitle(ALTERNATIVE_TITLE);
	}

	@Override
	protected void assertChangedPropertyEquals(final Manga foundEntity) {
		assertEquals(ALTERNATIVE_TITLE, foundEntity.getTitle());
	}

	@Override
	protected Manga getNewData() {
		return FACTORY.getNext();
	}
}
