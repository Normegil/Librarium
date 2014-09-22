package be.normegil.librarium.libraries;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.validation.Validator;
import be.normegil.librarium.util.exception.MalformedURLException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolationException;

public class UTURLSafety {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<URL> FACTORY = FactoryRepository.get(URL.class);
	private static final ClassWrapper<URL> CLASS = new ClassWrapper<>(URL.class);
	private static final String EMPTY_STRING = "";
	private static final String HOST = "Host";
	private static final int PORT = 42;
	private static final String FILE = "file";
	private static final String PROTOCOL = "http";
	private static final String WRONG_FORMAT_URL = "te:st///qovsvosdv-4631csdcs5/78csd:87";
	private URL entity;

	@Before
	public void setUp() throws Exception {
		entity = FACTORY.getNew();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test(expected = ConstraintViolationException.class)
	public void testConstructor_String_Null() throws Exception {
		Validator.validate(CLASS.getConstructor(String.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testConstructor_String_Empty() throws Exception {
		Validator.validate(CLASS.getConstructor(String.class), EMPTY_STRING);
	}

	@Test(expected = MalformedURLException.class)
	public void testConstructor_String_WrongRepresentation() throws Exception {
		new URL(WRONG_FORMAT_URL);
	}

	@Test(expected = ConstraintViolationException.class)
	public void testCopyConstructor_Null() throws Exception {
		Validator.validate(CLASS.getConstructor(URL.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testConstructor_URL_Null() throws Exception {
		Validator.validate(CLASS.getConstructor(java.net.URL.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testConstructor_ProtocolHostPortFile_NullProtocol() throws Exception {
		Validator.validate(CLASS.getConstructor(String.class, String.class, Integer.class, String.class), new Object[]{null, HOST, PORT, FILE});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testConstructor_ProtocolHostPortFile_EmptyProtocol() throws Exception {
		Validator.validate(CLASS.getConstructor(String.class, String.class, Integer.class, String.class), EMPTY_STRING, HOST, PORT, FILE);
	}

	@Test(expected = ConstraintViolationException.class)
	public void testConstructor_ProtocolHostPortFile_NullHost() throws Exception {
		Validator.validate(CLASS.getConstructor(String.class, String.class, Integer.class, String.class), PROTOCOL, null, PORT, FILE);
	}

	@Test(expected = ConstraintViolationException.class)
	public void testConstructor_ProtocolHostPortFile_EmptyHost() throws Exception {
		Validator.validate(CLASS.getConstructor(String.class, String.class, Integer.class, String.class), PROTOCOL, EMPTY_STRING, PORT, FILE);
	}

	@Test(expected = ConstraintViolationException.class)
	public void testConstructor_ProtocolHostPortFile_NullPort() throws Exception {
		Validator.validate(CLASS.getConstructor(String.class, String.class, Integer.class, String.class), PROTOCOL, HOST, null, FILE);
	}

	@Test(expected = ConstraintViolationException.class)
	public void testConstructor_ProtocolHostPortFile_NullFile() throws Exception {
		Validator.validate(CLASS.getConstructor(String.class, String.class, Integer.class, String.class), PROTOCOL, HOST, PORT, null);
	}
}