package be.normegil.librarium.model.rest;

import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.test.model.data.AbstractParsingSupportTest;
import be.normegil.librarium.util.parser.Parser;

public class UTCollectionResourceXMLSupport extends AbstractParsingSupportTest<CollectionResource> {

	private static final DataFactory<CollectionResource> FACTORY = new CollectionResourceTestSuite();

	public UTCollectionResourceXMLSupport() {
		super(Parser.DocumentType.XML, CollectionResource.class);
	}

	@Override
	protected CollectionResource initEntity() {
		return FACTORY.getDefault();
	}
}
