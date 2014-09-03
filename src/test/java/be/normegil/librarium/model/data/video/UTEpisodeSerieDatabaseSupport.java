package be.normegil.librarium.model.data.video;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.AbstractEntityDatabaseSupportTest;

public class UTEpisodeSerieDatabaseSupport extends AbstractEntityDatabaseSupportTest<EpisodeSerie> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<EpisodeSerie> FACTORY = FactoryRepository.get(EpisodeSerie.class);

	public UTEpisodeSerieDatabaseSupport() {
		super(EpisodeSerie.class);
	}

	@Override
	protected EpisodeSerie initEntity() {
		return FACTORY.getNext();
	}

	@Override
	protected EpisodeSerie changeEntity(final EpisodeSerie entity) {
		entity.setTitle(entity.getTitle() + 1);
		return entity;
	}
}