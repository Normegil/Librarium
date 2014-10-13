package be.normegil.librarium.model.data;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.model.data.AbstractDatabaseSupportTest;

public class UTReleaseDateDatabaseSupport extends AbstractDatabaseSupportTest<ReleaseDate> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<ReleaseDate> FACTORY = FactoryRepository.get(ReleaseDate.class);
	private static final int DAYS_TO_ADD = 1;

	public UTReleaseDateDatabaseSupport() {
		super(ReleaseDate.class);
	}

	@Override
	protected ReleaseDate initEntity() {
		return FACTORY.getNew();
	}

	@Override
	protected ReleaseDate changeEntity(final ReleaseDate entity) {
		entity.setDate(entity.getDate().plusDays(DAYS_TO_ADD));
		return entity;
	}

	@Override
	protected Object getId(final ReleaseDate entity) {
		return entity.getId();
	}
}