package be.normegil.librarium.model.data.book;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.AbstractEntityDatabaseSupportTest;

public class UTBookSerieDatabaseSupport extends AbstractEntityDatabaseSupportTest<BookSerie> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<BookSerie> FACTORY = FactoryRepository.get(BookSerie.class);

	public UTBookSerieDatabaseSupport() {
		super(BookSerie.class);
	}

	@Override
	protected BookSerie initEntity() {
		return FACTORY.getNext();
	}

	@Override
	protected BookSerie changeEntity(final BookSerie entity) {
		entity.setTitle(entity.getTitle() + 1);
		return entity;
	}
}