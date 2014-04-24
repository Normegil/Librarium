package be.normegil.librarium.model.data;

import be.normegil.librarium.ApplicationProperties;
import org.apache.commons.lang3.Validate;
import org.junit.Before;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.InputStream;

import static be.normegil.librarium.model.data.GameTestHelper.assertGamePropertiesEquals;

public class GameTestXmlSupport {

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

    @Test
    public void testXmlMarshaller() throws Exception {
        File temporaryFile = File.createTempFile("GameTestXmlSupport-Marshalling", ".xml");
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
}
