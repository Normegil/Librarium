package be.normegil.librarium.model.rest.exception;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.model.data.AbstractJAXBSupportTest;
import be.normegil.librarium.util.jaxb.JAXBHelper;

public class UTRESTErrorJSONSupport extends AbstractJAXBSupportTest<RESTError> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<RESTError> FACTORY = FactoryRepository.get(RESTError.class);

	public UTRESTErrorJSONSupport() {
		super(JAXBHelper.DocumentType.JSON, RESTError.class);
	}

	@Override
	protected RESTError initEntity() {
		return FACTORY.getNew();
	}
}
