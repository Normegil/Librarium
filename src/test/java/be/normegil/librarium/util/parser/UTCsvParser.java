package be.normegil.librarium.util.parser;

import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.util.exception.ReadOnlyRuntimeException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class UTCsvParser {

	private CsvParser entity;

	@Before
	public void setUp() throws Exception {
		entity = new CsvParser<>(CsvParserTestSuite.CsvParserExample.class);
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test(expected = ReadOnlyRuntimeException.class)
	public void testTo_ReadOnly() throws Exception {
		File file = File.createTempFile("CsvParser-TestTo-ReadOnly", ".csv");
		new CsvParser<>(CsvParserTestSuite.CsvParserReadOnlyExample.class).to(new ArrayList<>(), file);
	}

	@Test
	public void testTo() throws Exception {
		List<CsvParserTestSuite.CsvParserExample> entities = new ArrayList<>();
		entities.add(new CsvParserTestSuite.CsvParserExample(UUID.randomUUID(), "Test1"));
		entities.add(new CsvParserTestSuite.CsvParserExample(UUID.randomUUID(), "Test2"));
		entities.add(new CsvParserTestSuite.CsvParserExample(UUID.randomUUID(), "Test3"));
		entities.add(new CsvParserTestSuite.CsvParserExample(UUID.randomUUID(), "Test4"));

		File file = File.createTempFile("CsvParser-TestTo-ReadOnly", ".csv");
		entity.to(entities, file);

		List toTest = entity.from(file);
		assertEquals(entities, toTest);
	}

	@Test
	public void testFrom() throws Exception {
		List<CsvParserTestSuite.CsvParserExample> expected = new ArrayList<>();
		expected.add(new CsvParserTestSuite.CsvParserExample(UUID.fromString("21bf83f5-ffda-4ce3-a30f-02c231cbf6cb"), "Test1"));
		expected.add(new CsvParserTestSuite.CsvParserExample(UUID.fromString("33d743f0-b45a-457a-80ab-85833d129922"), "Test2"));
		expected.add(new CsvParserTestSuite.CsvParserExample(UUID.fromString("a434728a-4d23-4af4-8450-5617647a8874"), "Test3"));
		expected.add(new CsvParserTestSuite.CsvParserExample(UUID.fromString("46261527-c6a2-47a3-a14f-431fce5dc93c"), "Test4"));

		URL urlToTestFile = new URL(getClass().getResource("CsvParserExamples.csv"));
		File file = urlToTestFile.toFile();
		List toTest = entity.from(file);

		assertEquals(expected, toTest);
	}
}
