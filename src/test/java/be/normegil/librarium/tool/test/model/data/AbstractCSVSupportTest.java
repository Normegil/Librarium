package be.normegil.librarium.tool.test.model.data;

import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.util.parser.CsvParser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertEquals;

public abstract class AbstractCSVSupportTest<E> {
	protected final Logger log;
	private final Class<E> entityClass;
	private List<E> entities;

	protected AbstractCSVSupportTest(Class<E> entityClass) {
		this.entityClass = entityClass;
		log = LoggerFactory.getLogger(entityClass);
	}

	protected abstract List<E> initEntity();

	@Before
	public void setUp() throws Exception {
		entities = initEntity();
	}

	@After
	public void tearDown() throws Exception {
		entities = null;
	}

	@Test
	public void testMarshaller() throws Exception {
		File temporaryFile = File.createTempFile(entityClass.getSimpleName() + "-Marshalling", ".csv");

		CsvParser<E> parser = new CsvParser<>(entityClass);
		parser.to(entities, temporaryFile);
		log.info("Generated CSV File in : " + temporaryFile.getAbsolutePath());
		List<E> toTestEntity = parser.from(temporaryFile);

		testEquality(entities, toTestEntity);
	}

	@Test
	public void testUnMarshaller() throws Exception {
		String testFileName = getTestFileName();
		URL url = new URL(getClass().getResource(testFileName));
		File referenceFile = url.toFile();

		log.info("Searched CSV File in : " + referenceFile.getAbsolutePath());

		List<E> toTestEntity = getParser().from(referenceFile);

		testEquality(entities, toTestEntity);
	}

	protected void testEquality(final List<E> expected, final List<E> toTest) {
		assertEquals(expected, toTest);
	}

	private CsvParser<E> getParser() {
		return new CsvParser<>(entityClass);
	}

	protected String getTestFileName() {
		return entityClass.getSimpleName() + "Reference.csv";
	}
}
