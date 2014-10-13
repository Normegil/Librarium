package be.normegil.librarium.util.parser;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

public class UTJAXBHelper {

	private static final Logger LOG = LoggerFactory.getLogger(UTJAXBHelper.class);

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<JAXBHelperTestSuite.JAXBTestClass> FACTORY = FactoryRepository.get(JAXBHelperTestSuite.JAXBTestClass.class);
	private JAXBHelper<JAXBHelperTestSuite.JAXBTestClass> entity;

	@Before
	public void setUp() throws Exception {
		entity = new JAXBHelper<JAXBHelperTestSuite.JAXBTestClass>(JAXBHelperTestSuite.JAXBTestClass.class);
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test
	public void testTo_XML() throws Exception {
		JAXBHelperTestSuite.JAXBTestClass expected = FACTORY.getDefault();

		File file = File.createTempFile("JAXBHelperTest-", ".xml");
		FileOutputStream outputStream = new FileOutputStream(file);
		entity.to(expected, outputStream);
		outputStream.close();
		LOG.info("XML File Location : " + file.getAbsolutePath());

		FileInputStream inputStream = new FileInputStream(file);
		JAXBHelperTestSuite.JAXBTestClass toTest = entity.from(inputStream);
		inputStream.close();

		assertEquals(expected, toTest);
	}

	@Test
	public void testTo_JSON() throws Exception {
		JAXBHelperTestSuite.JAXBTestClass expected = FACTORY.getDefault();

		File file = File.createTempFile("JAXBHelperTest-", ".json");
		FileOutputStream outputStream = new FileOutputStream(file);
		entity.to(expected, outputStream);
		outputStream.close();
		LOG.info("JSON File Location : " + file.getAbsolutePath());

		FileInputStream inputStream = new FileInputStream(file);
		JAXBHelperTestSuite.JAXBTestClass toTest = entity.from(inputStream);
		inputStream.close();

		assertEquals(expected, toTest);
	}

	@Test
	public void testFrom_XML() throws Exception {
		JAXBHelperTestSuite.JAXBTestClass expected = FACTORY.getDefault();

		InputStream inputStream = getClass().getResourceAsStream("JAXBTestClass.xml");
		JAXBHelperTestSuite.JAXBTestClass toTest = entity.from(inputStream);
		inputStream.close();

		assertEquals(expected, toTest);
	}

	@Test
	public void testFrom_JSON() throws Exception {
		JAXBHelperTestSuite.JAXBTestClass expected = FACTORY.getDefault();

		InputStream inputStream = getClass().getResourceAsStream("JAXBTestClass.json");
		JAXBHelperTestSuite.JAXBTestClass toTest = entity.from(inputStream);
		inputStream.close();

		assertEquals(expected, toTest);
	}
}
