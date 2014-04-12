package be.normegil.librarium.model.data;

import be.normegil.librarium.ApplicationProperty;
import org.apache.commons.lang3.Validate;
import org.junit.Before;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.InputStream;

public class GameTestXmlSupport {

    public static final String DEFAULT_NAME = "DefaultName";
    public static final long DEFAULT_ID = 0L;
    public static final String FILE_NAME = "GameReference.xml";
    public static final String LINE_END = "\n";
    public static final String INDENTATION = "\t";

    private Game game;
    private Unmarshaller unmarshaller;
    private Marshaller marshaller;

    @Before
    public void setUp() throws Exception {
        game = new Game(DEFAULT_ID, DEFAULT_NAME);

        JAXBContext jaxbContext = JAXBContext.newInstance(Game.class);
        marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, ApplicationProperty.ENCOODING);
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

    private void assertGamePropertiesEquals(Game expectedGame, Game toTestGame) {
        Validate.notNull(expectedGame);
        Validate.notNull(toTestGame);

        boolean areEquals = true;
        StringBuilder errorMessage = new StringBuilder();

        Long expectedGameId = expectedGame.getId();
        Long toTestGameId = toTestGame.getId();
        if (!expectedGameId.equals(toTestGameId)) {
            areEquals = false;
            errorMessage.append("Different IDs").append(LINE_END)
                    .append(INDENTATION).append("Expected=").append(expectedGameId).append(LINE_END)
                    .append(INDENTATION).append("Actual=").append(toTestGameId).append(LINE_END)
                    .append(LINE_END);
        }

        String expectedGameName = expectedGame.getName();
        String toTestGameName = toTestGame.getName();
        if (!expectedGameName.equals(toTestGameName)) {
            areEquals = false;
            errorMessage.append("Different Names").append(LINE_END)
                    .append(INDENTATION).append("Expected=").append(expectedGameName).append(LINE_END)
                    .append(INDENTATION).append("Actual=").append(toTestGameName).append(LINE_END)
                    .append(LINE_END);
        }

        if (!areEquals) {
            throw new AssertionError(errorMessage.toString());
        }
    }
}
