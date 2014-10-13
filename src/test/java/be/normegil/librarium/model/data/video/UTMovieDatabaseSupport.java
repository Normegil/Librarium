package be.normegil.librarium.model.data.video;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.model.data.AbstractEntityDatabaseSupportTest;

public class UTMovieDatabaseSupport extends AbstractEntityDatabaseSupportTest<Movie> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Movie> FACTORY = FactoryRepository.get(Movie.class);

	public UTMovieDatabaseSupport() {
		super(Movie.class);
	}

	@Override
	protected Movie initEntity() {
		return FACTORY.getNew();
	}

	@Override
	protected Movie changeEntity(final Movie entity) {
		entity.setTitle(entity.getTitle() + 1);
		return entity;
	}
}