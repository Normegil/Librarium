package be.normegil.librarium.util;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

public class UTInputStreamHelper {

	private InputStreamHelper entity;

	@Before
	public void setUp() throws Exception {
		entity = new InputStreamHelper();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test
	public void testInputStreamToString() throws Exception {
		String expected = "This is a test ! \n\tA second line for the test with tabulation";
		InputStream inputStream = new ByteArrayInputStream(expected.getBytes());
		assertEquals(expected, entity.toString(inputStream));
	}
}
