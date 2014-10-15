package be.normegil.librarium.util.parser;

import be.normegil.librarium.libraries.URL;
import org.eclipse.sisu.Parameters;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class UTParser {

	private String documentExtention;
	private Parser.DocumentType documentType;
	private Parser<ParserTestSuite.ParserExample> entity;

	public UTParser(final Parser.DocumentType documentType, final String documentExtention) {
		this.documentExtention = documentExtention;
		this.documentType = documentType;
	}

	@Parameters
	public static Collection<Object[]> generateData() {
		List<Object[]> parameters = new ArrayList<>();
		for (Parser.DocumentType documentType : Parser.DocumentType.values()) {
			parameters.add(new Object[]{
					documentType,
					documentType.toString().toLowerCase()
			});
		}
		return parameters;
	}

	@Before
	public void setUp() throws Exception {
		entity = new Parser<>(ParserTestSuite.ParserExample.class);
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test
	public void testTo() throws Exception {
		ParserTestSuite.ParserExample expected = new ParserTestSuite.ParserExample(UUID.randomUUID(), "test");
		File file = File.createTempFile("ParserExample-TestTo", "." + documentExtention);

		OutputStream outputStream = new FileOutputStream(file);
		entity.to(expected, documentType, outputStream);
		outputStream.close();

		InputStream inputStream = new FileInputStream(file);
		ParserTestSuite.ParserExample toTest = entity.from(documentType, inputStream);
		inputStream.close();

		assertEquals(expected, toTest);
	}

	@Test
	public void testFrom() throws Exception {
		ParserTestSuite.ParserExample expected = new ParserTestSuite.ParserExample(UUID.fromString("9d7f528b-90e0-42cd-96ed-19a723161bf8"), "Test");

		URL url = new URL(getClass().getResource("ParserExample." + documentExtention));
		InputStream inputStream = new FileInputStream(url.toFile());
		ParserTestSuite.ParserExample toTest = entity.from(documentType, inputStream);
		inputStream.close();

		assertEquals(expected, toTest);
	}
}
