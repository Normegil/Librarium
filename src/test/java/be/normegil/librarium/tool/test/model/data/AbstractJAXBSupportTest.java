package be.normegil.librarium.tool.test.model.data;

import be.normegil.librarium.tool.UnitTestXMLValidationEventHandler;
import be.normegil.librarium.util.XSDUtil;
import be.normegil.librarium.util.jaxb.JAXBHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;

public abstract class AbstractJAXBSupportTest<E> {
	protected final Logger log;
	private final Class<E> entityClass;
	private final JAXBHelper.DocumentType documentType;
	private final Class<?>[] classedToBoundToContext;
	private E entity;

	protected AbstractJAXBSupportTest(JAXBHelper.DocumentType documentType, Class<E> entityClass, Class<?>... otherClasses) {
		this.documentType = documentType;

		this.entityClass = entityClass;

		Collection<Class<?>> classes = new HashSet<>();
		classes.add(entityClass);
		classes.addAll(Arrays.asList(otherClasses));
		classedToBoundToContext = classes.toArray(new Class<?>[classes.size()]);

		log = LoggerFactory.getLogger(entityClass);
	}

	protected abstract E initEntity();

	@Before
	public void setUp() throws Exception {
		entity = initEntity();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test
	public void testMarshaller() throws Exception {
		JAXBHelper<E> jaxbHelper = getJAXBHelper();
		File temporaryFile = File.createTempFile(entityClass.getSimpleName() + "-Marshalling", "." + documentType.toString().toLowerCase());

		FileOutputStream outputStream = new FileOutputStream(temporaryFile);
		jaxbHelper.to(entity, documentType, outputStream);
		outputStream.close();
		log.info("Generated " + documentType + " File in : " + temporaryFile.getAbsolutePath());

		FileInputStream inputStream = new FileInputStream(temporaryFile);
		E toTestEntity = jaxbHelper.from(documentType, inputStream);
		inputStream.close();

		assertEquals(this.entity, toTestEntity);
	}

	@Test
	public void testXmlUnMarshaller() throws Exception {
		JAXBHelper<E> jaxbHelper = getJAXBHelper();
		InputStream inputStream = getClass().getResourceAsStream(getTestFileName());

		E toTestEntity = jaxbHelper.from(documentType, inputStream);

		assertEquals(entity, toTestEntity);
	}

	@Test
	public void testXSDValidation_Marshaller() throws Exception {
		JAXBHelper<E> jaxbHelper = getJAXBHelper(getValidationSchema(entityClass));

		File temporaryFile = File.createTempFile(entityClass.getSimpleName() + "XmlSupport-Marshalling", ".xml");

		jaxbHelper.to(entity, documentType, new FileOutputStream(temporaryFile));
	}

	@Test
	public void testXSDValidation_Unmarshaller() throws Exception {
		JAXBHelper<E> jaxbHelper = getJAXBHelper(getValidationSchema(entityClass));

		InputStream inputStream = getClass().getResourceAsStream(getTestFileName());

		jaxbHelper.from(documentType, inputStream);
	}

	protected String getTestFileName() {
		return entityClass.getSimpleName() + "Reference." + documentType.toString().toLowerCase();
	}

	private Schema getValidationSchema(Class<?> aClass) throws SAXException {
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		XSDUtil xsdUtil = new XSDUtil();
		return schemaFactory.newSchema(xsdUtil.getSchema(aClass));
	}

	private JAXBHelper<E> getJAXBHelper() {
		return new JAXBHelper<>(classedToBoundToContext);
	}

	private JAXBHelper<E> getJAXBHelper(Schema schema) {
		return new JAXBHelper<>(schema, new UnitTestXMLValidationEventHandler(), classedToBoundToContext);
	}
}
