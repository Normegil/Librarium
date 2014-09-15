package be.normegil.librarium.model.rest;

import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.test.model.data.AbstractJAXBSupportTest;
import be.normegil.librarium.util.jaxb.JAXBHelper;

public class UTCollectionResourceXMLSupport extends AbstractJAXBSupportTest<CollectionResource> {

	private static final DataFactory<CollectionResource> FACTORY = new CollectionResourceTestSuite();

	public UTCollectionResourceXMLSupport() {
		super(JAXBHelper.DocumentType.XML, CollectionResource.class);
	}

	@Override
	protected CollectionResource initEntity() {
		CollectionResource resource = FACTORY.getNew();
		for (URL url : resource.getItems()) {

		}
		return resource;
	}
}
