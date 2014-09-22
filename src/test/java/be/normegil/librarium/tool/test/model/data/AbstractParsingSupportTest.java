package be.normegil.librarium.tool.test.model.data;

import be.normegil.librarium.util.parser.Parser;
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

public abstract class AbstractParsingSupportTest<E> {
	protected final Logger log;
	private final Class<E> entityClass;
	private final Parser.DocumentType documentType;
	private final Parser<E> parser;
	private E entity;

	protected AbstractParsingSupportTest(Parser.DocumentType documentType, Class<E> entityClass, Class<?>... otherClasses) {
		this.documentType = documentType;
		this.entityClass = entityClass;
		log = LoggerFactory.getLogger(entityClass);
		parser = new Parser<>(entityClass, otherClasses);
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
		Parser<E> parser = getParser();
		File temporaryFile = File.createTempFile(entityClass.getSimpleName() + "-Marshalling", "." + documentType.toString().toLowerCase());

		FileOutputStream outputStream = new FileOutputStream(temporaryFile);
		parser.to(entity, documentType, outputStream);
		outputStream.close();
		log.info("Generated " + documentType + " File in : " + temporaryFile.getAbsolutePath());

		FileInputStream inputStream = new FileInputStream(temporaryFile);
		E toTestEntity = parser.from(documentType, inputStream);
		inputStream.close();

		assertEquals(this.entity, toTestEntity);
	}

	@Test
	public void testUnMarshaller() throws Exception {
		Parser<E> parser = getParser();
		String testFileName = getTestFileName();
		InputStream inputStream = getClass().getResourceAsStream(testFileName);

		log.info("Searched " + documentType + " File in : " + testFileName);

		E toTestEntity = parser.from(documentType, inputStream);

		assertEquals(entity, toTestEntity);
	}

	protected String getTestFileName() {
		return entityClass.getSimpleName() + "Reference." + documentType.toString().toLowerCase();
	}

	private Parser<E> getParser() {
		return parser;
	}
}
