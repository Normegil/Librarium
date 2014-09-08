package be.normegil.librarium.model.data.video;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.model.data.AbstractEntityDatabaseSupportTest;

public class UTMovieSerieDatabaseSupport extends AbstractEntityDatabaseSupportTest<MovieSerie> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<MovieSerie> FACTORY = FactoryRepository.get(MovieSerie.class);

	public UTMovieSerieDatabaseSupport() {
		super(MovieSerie.class);
	}

	@Override
	protected MovieSerie initEntity() {
		return FACTORY.getNext();
	}

	@Override
	protected MovieSerie changeEntity(final MovieSerie entity) {
		entity.setTitle(entity.getTitle() + 1);
		return entity;
	}
}