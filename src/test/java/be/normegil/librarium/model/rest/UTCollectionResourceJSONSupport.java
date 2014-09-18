package be.normegil.librarium.model.rest;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.model.data.AbstractParsingSupportTest;
import be.normegil.librarium.util.parser.JAXBHelper;
import be.normegil.librarium.util.parser.Parser;

public class UTCollectionResourceJSONSupport extends AbstractParsingSupportTest<CollectionResource> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<CollectionResource> FACTORY = FactoryRepository.get(CollectionResource.class);

	public UTCollectionResourceJSONSupport() {
		super(Parser.DocumentType.JSON, CollectionResource.class);
	}

	@Override
	protected CollectionResource initEntity() {
		return FACTORY.getNew();
	}
}
