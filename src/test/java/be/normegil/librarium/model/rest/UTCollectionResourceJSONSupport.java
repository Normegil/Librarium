package be.normegil.librarium.model.rest;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.model.data.AbstractJAXBSupportTest;
import be.normegil.librarium.util.jaxb.JAXBHelper;

public class UTCollectionResourceJSONSupport extends AbstractJAXBSupportTest<CollectionResource> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<CollectionResource> FACTORY = FactoryRepository.get(CollectionResource.class);

	public UTCollectionResourceJSONSupport() {
		super(JAXBHelper.DocumentType.JSON, CollectionResource.class);
	}

	@Override
	protected CollectionResource initEntity() {
		return FACTORY.getNew();
	}
}
