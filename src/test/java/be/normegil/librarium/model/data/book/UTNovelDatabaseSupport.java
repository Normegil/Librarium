package be.normegil.librarium.model.data.book;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.AbstractEntityDatabaseSupportTest;

public class UTNovelDatabaseSupport extends AbstractEntityDatabaseSupportTest<Novel> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Novel> FACTORY = FactoryRepository.get(Novel.class);

	public UTNovelDatabaseSupport() {
		super(Novel.class);
	}

	@Override
	protected Novel initEntity() {
		return FACTORY.getNext();
	}

	@Override
	protected Novel changeEntity(final Novel entity) {
		entity.setTitle(entity.getTitle() + 1);
		return entity;
	}
}