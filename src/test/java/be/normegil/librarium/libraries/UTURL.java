package be.normegil.librarium.libraries;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.URI;
import java.nio.file.Path;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UTURL {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<URL> FACTORY = FactoryRepository.get(URL.class);
	private static final String URL_REPRESENTATION = "http://Host:42/File";
	private URL entity;

	@Before
	public void setUp() throws Exception {
		entity = FACTORY.getNew();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test
	public void testConstructor_String() throws Exception {
		URL otherUrl = new URL(entity.toRepresentation());
		assertEquals(entity, otherUrl);
	}

	@Test
	public void testConstructor_ProtocolHostPortFile() throws Exception {
		URL otherUrl = new URL(entity.getProtocol(), entity.getHost(), entity.getPort(), entity.getFilePath());
		assertEquals(entity, otherUrl);
	}

	@Test
	public void testCopyConstructor() throws Exception {
		URL copy = new URL(entity);
		assertEquals(entity, copy);
	}

	@Test
	public void testConstructor_JavaURL() throws Exception {
		URL copy = new URL(new java.net.URL(entity.getProtocol(), entity.getHost(), entity.getPort(), entity.getFilePath()));
		assertEquals(entity.getProtocol(), copy.getProtocol());
		assertEquals(entity.getHost(), copy.getHost());
		assertEquals(entity.getPort(), copy.getPort());
		assertEquals(entity.getFilePath(), copy.getFilePath());
	}

	@Test
	public void testToURI() throws Exception {
		java.net.URL url = new java.net.URL(entity.getProtocol(), entity.getHost(), entity.getPort(), entity.getFilePath());
		URI expected = url.toURI();
		URI toTest = new URL(url).toURI();
		assertEquals(expected, toTest);
	}

	@Test
	public void testToRepresentation() throws Exception {
		assertEquals(URL_REPRESENTATION, entity.toRepresentation());
	}

	@Test
	public void testGetOriginalURL() throws Exception {
		java.net.URL expected = new java.net.URL(entity.getProtocol(), entity.getHost(), entity.getPort(), entity.getFilePath());
		java.net.URL toTest = new URL(expected).getOriginalURL();
		assertTrue(expected == toTest);
	}
}
