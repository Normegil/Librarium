package be.normegil.librarium.util.parser;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.libraries.Class;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.UnitTestXMLValidationEventHandler;
import be.normegil.librarium.tool.validation.Validator;
import be.normegil.librarium.util.XSDUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.validation.ConstraintViolationException;
import javax.xml.XMLConstants;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class UTJAXBHelperSafety {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<JAXBHelperTestSuite.JAXBTestClass> FACTORY = FactoryRepository.get(JAXBHelperTestSuite.JAXBTestClass.class);
	private static final Class<JAXBHelper> CLASS = new Class<>(JAXBHelper.class);
	private JAXBHelper<JAXBHelperTestSuite.JAXBTestClass> entity;

	@Before
	public void setUp() throws Exception {
		entity = new JAXBHelper<JAXBHelperTestSuite.JAXBTestClass>(JAXBHelperTestSuite.JAXBTestClass.class);
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test(expected = ConstraintViolationException.class)
	public void testConstructor_NullSchema() throws Exception {
		java.lang.Class[] classes = {};
		Validator.validate(CLASS.getConstructor(Schema.class, ValidationEventHandler.class, classes.getClass()), new Object[]{null, new UnitTestXMLValidationEventHandler(), classes});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testConstructor_NullValidationEventHandler() throws Exception {
		java.lang.Class[] classes = {};
		Validator.validate(CLASS.getConstructor(Schema.class, ValidationEventHandler.class, classes.getClass()), getValidationSchema(JAXBHelperTestSuite.JAXBTestClass.class), null, classes);
	}

	@Test(expected = ConstraintViolationException.class)
	public void testFrom_NullDocumentType() throws Exception {
		Validator.validate(entity, CLASS.getMethod("from", Parser.DocumentType.class, InputStream.class), null, new ByteArrayInputStream("Stream".getBytes()));
	}

	@Test(expected = ConstraintViolationException.class)
	public void testFrom_NullInputStream() throws Exception {
		Validator.validate(entity, CLASS.getMethod("from", Parser.DocumentType.class, InputStream.class), Parser.DocumentType.JSON, null);
	}

	@Test(expected = ConstraintViolationException.class)
	public void testTo_NullObject() throws Exception {
		Validator.validate(entity, CLASS.getMethod("to", Object.class, Parser.DocumentType.class, OutputStream.class), null, Parser.DocumentType.JSON, System.out);
	}

	@Test(expected = ConstraintViolationException.class)
	public void testTo_NullDocumentType() throws Exception {
		Validator.validate(entity, CLASS.getMethod("to", Object.class, Parser.DocumentType.class, OutputStream.class), FACTORY.getNext(), null, System.out);
	}

	@Test(expected = ConstraintViolationException.class)
	public void testTo_NullOutputStream() throws Exception {
		Validator.validate(entity, CLASS.getMethod("to", Object.class, Parser.DocumentType.class, OutputStream.class), FACTORY.getNext(), Parser.DocumentType.JSON, null);
	}

	private Schema getValidationSchema(java.lang.Class<?> aClass) throws SAXException {
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		XSDUtil xsdUtil = new XSDUtil();
		return schemaFactory.newSchema(xsdUtil.getSchema(aClass));
	}
}