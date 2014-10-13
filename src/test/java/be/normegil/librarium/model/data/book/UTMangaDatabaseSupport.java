package be.normegil.librarium.model.data.book;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.model.data.AbstractEntityDatabaseSupportTest;

public class UTMangaDatabaseSupport extends AbstractEntityDatabaseSupportTest<Manga> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Manga> FACTORY = FactoryRepository.get(Manga.class);

	public UTMangaDatabaseSupport() {
		super(Manga.class);
	}

	@Override
	protected Manga initEntity() {
		return FACTORY.getNew();
	}

	@Override
	protected Manga changeEntity(final Manga entity) {
		entity.setTitle(entity.getTitle() + 1);
		return entity;
	}
}