package be.normegil.librarium.util.parser;

import be.normegil.librarium.libraries.URL;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class UTJacksonParser {

	private JacksonParser entity;

	@Before
	public void setUp() throws Exception {
		entity = new JacksonParser<>(JacksonParserTestSuite.JacksonParserExample.class);
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test
	public void testTo() throws Exception {
		JacksonParserTestSuite.JacksonParserExample expected = new JacksonParserTestSuite.JacksonParserExample(UUID.randomUUID(), "test");
		File file = File.createTempFile("JacksonParserExample-TestTo", ".json");

		OutputStream outputStream = new FileOutputStream(file);
		entity.to(expected, outputStream);
		outputStream.close();

		InputStream inputStream = new FileInputStream(file);
		JacksonParserTestSuite.JacksonParserExample toTest = (JacksonParserTestSuite.JacksonParserExample) entity.from(inputStream);
		inputStream.close();

		assertEquals(expected, toTest);
	}

	@Test
	public void testFrom() throws Exception {
		JacksonParserTestSuite.JacksonParserExample expected = new JacksonParserTestSuite.JacksonParserExample(UUID.fromString("c886f5d4-5754-4061-9027-7d6b34e9d96e"), "test1");

		URL url = new URL(getClass().getResource("JacksonParserExample.json"));
		InputStream inputStream = new FileInputStream(url.toFile());
		JacksonParserTestSuite.JacksonParserExample toTest = (JacksonParserTestSuite.JacksonParserExample) entity.from(inputStream);
		inputStream.close();

		assertEquals(expected, toTest);
	}
}
