package be.normegil.librarium.model.data.video;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.model.data.AbstractEntityDatabaseSupportTest;

public class UTSerieSeasonDatabaseSupport extends AbstractEntityDatabaseSupportTest<SerieSeason> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<SerieSeason> FACTORY = FactoryRepository.get(SerieSeason.class);

	public UTSerieSeasonDatabaseSupport() {
		super(SerieSeason.class);
	}

	@Override
	protected SerieSeason initEntity() {
		return FACTORY.getNew();
	}

	@Override
	protected SerieSeason changeEntity(final SerieSeason entity) {
		entity.setTitle(entity.getTitle() + 1);
		return entity;
	}
}