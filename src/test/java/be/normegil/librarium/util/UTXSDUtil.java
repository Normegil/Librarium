package be.normegil.librarium.util;

import be.normegil.librarium.model.data.Game;
import org.junit.Before;
import org.junit.Test;

import java.net.URL;

import static org.junit.Assert.assertEquals;

public class UTXSDUtil {

    private XSDUtil xsdUtil;

    @Before
    public void setUp() throws Exception {
        xsdUtil = new XSDUtil();
    }

    @Test
    public void testGame() throws Exception {
        URL schemeURL = getClass().getResource(XSDUtil.XSD_FOLDER_PATH + "game.xsd");
        URL schemaURLToTest = xsdUtil.getSchema(Game.class);
        assertEquals(schemeURL, schemaURLToTest);
    }
}
