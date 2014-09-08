package be.normegil.librarium.model.dao.book;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.model.data.book.Novel;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.model.dao.AbstractDAOTest;

import static org.junit.Assert.assertEquals;

public class UTNovelDatabaseDAO extends AbstractDAOTest<NovelDatabaseDAO, Novel> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Novel> FACTORY = FactoryRepository.get(Novel.class);
	private static final String ALTERNATIVE_TITLE = "AlternativeTitle";

	@Override
	protected NovelDatabaseDAO initDAO() {
		return new NovelDatabaseDAO();
	}

	@Override
	protected Object getEntityId(final Novel entity) {
		return entity.getId();
	}

	@Override
	protected void changeEntity(final Novel entity) {
		entity.setTitle(ALTERNATIVE_TITLE);
	}

	@Override
	protected void assertChangedPropertyEquals(final Novel foundEntity) {
		assertEquals(ALTERNATIVE_TITLE, foundEntity.getTitle());
	}

	@Override
	protected Novel getNewData() {
		return FACTORY.getNext();
	}
}
