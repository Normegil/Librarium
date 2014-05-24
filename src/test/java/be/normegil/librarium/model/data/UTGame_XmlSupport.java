package be.normegil.librarium.model.data;

import be.normegil.librarium.ApplicationProperties;
import be.normegil.librarium.UnitTestXMLValidationEventHandler;
import be.normegil.librarium.util.XSDUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.InputStream;

import static be.normegil.librarium.model.data.GameTestHelper.assertGamePropertiesEquals;

public class UTGame_XmlSupport {

    public static final String DEFAULT_NAME = "DefaultName";
    public static final long DEFAULT_ID = 0L;
    public static final String FILE_NAME = "GameReference.xml";

    private Game game;
    private Unmarshaller unmarshaller;
    private Marshaller marshaller;

    @Before
    public void setUp() throws Exception {
        game = new Game(DEFAULT_ID, DEFAULT_NAME);

        JAXBContext jaxbContext = JAXBContext.newInstance(Game.class);
        marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, ApplicationProperties.ENCOODING);
        unmarshaller = jaxbContext.createUnmarshaller();
    }

    @After
    public void tearDown() throws Exception {
        game = null;
        marshaller = null;
        unmarshaller = null;
    }

    @Test
    public void testXmlMarshaller() throws Exception {
        File temporaryFile = File.createTempFile("GameXmlSupport-Marshalling", ".xml");
        marshaller.marshal(game, temporaryFile);
        Game toTestGame = (Game) unmarshaller.unmarshal(temporaryFile);
        assertGamePropertiesEquals(game, toTestGame);
    }

    @Test
    public void testXmlUnMarshaller() throws Exception {
        InputStream inputStream = getClass().getResourceAsStream(FILE_NAME);
        Game toTestGame = (Game) unmarshaller.unmarshal(inputStream);
        assertGamePropertiesEquals(game, toTestGame);
    }

    @Test
    public void testXSDValidation_Marshaller() throws Exception {
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        XSDUtil xsdUtil = new XSDUtil();
        Schema schema = schemaFactory.newSchema(xsdUtil.getSchema(Game.class));

        File temporaryFile = File.createTempFile("GameXmlSupport-Marshalling", ".xml");
        marshaller.setSchema(schema);
        marshaller.setEventHandler(new UnitTestXMLValidationEventHandler());
        marshaller.marshal(game, temporaryFile);
    }

    @Test
    public void testXSDValidation_Unmarshaller() throws Exception {
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        XSDUtil xsdUtil = new XSDUtil();
        Schema schema = schemaFactory.newSchema(xsdUtil.getSchema(Game.class));

        InputStream inputStream = getClass().getResourceAsStream(FILE_NAME);
        unmarshaller.setSchema(schema);
        unmarshaller.setEventHandler(new UnitTestXMLValidationEventHandler());
        unmarshaller.unmarshal(inputStream);
    }
}
