package be.normegil.librarium.model.data;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.test.model.data.AbstractEntityDatabaseSupportTest;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;

public class UTDownloadLinkDatabaseSupport extends AbstractEntityDatabaseSupportTest<DownloadLink> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<DownloadLink> FACTORY = FactoryRepository.get(DownloadLink.class);
	private static final String ALTERNATIVE_TITLE = "AlternativeTitle";

	public UTDownloadLinkDatabaseSupport() {
		super(DownloadLink.class);
	}

	@Override
	protected DownloadLink initEntity() {
		return FACTORY.getNext();
	}

	@Override
	protected DownloadLink changeEntity(final DownloadLink entity) {
		entity.setTitle(ALTERNATIVE_TITLE);
		return entity;
	}
}