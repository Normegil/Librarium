package be.normegil.librarium.util;

import be.normegil.librarium.util.parser.JAXBHelperTestSuite;
import org.junit.Before;
import org.junit.Test;

import java.net.URL;

import static org.junit.Assert.assertEquals;

public class UTXSDUtil {

	private XSDUtil xsdUtil;

	@Before
	public void setUp() throws Exception {
		xsdUtil = new XSDUtil();
	}

	@Test
	public void testGame() throws Exception {
		URL schemeURL = getClass().getResource(XSDUtil.XSD_FOLDER_PATH + "JAXBTestClass.xsd");
		URL schemaURLToTest = xsdUtil.getSchema(JAXBHelperTestSuite.JAXBTestClass.class);
		assertEquals(schemeURL, schemaURLToTest);
	}
}
