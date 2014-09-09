package be.normegil.librarium;

import be.normegil.librarium.rest.game.ITGameREST_RESTReponses;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ITGameREST_RESTReponses.class
})
public class TestLibrarium_IntegrationTest {
    public static final String LOG_MANAGER_PROPERTY_KEY = "java.util.logging.manager";
    public static final String LOG_MANAGER_PROPERTY_JBOSS_VALUE = "org.jboss.logmanager.LogManager";
    private static String logManagerProperty;

    @BeforeClass
    public static void setupClass() {
        logManagerProperty = System.getProperty(LOG_MANAGER_PROPERTY_KEY);
        System.setProperty(LOG_MANAGER_PROPERTY_KEY, LOG_MANAGER_PROPERTY_JBOSS_VALUE);
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        System.setProperty(LOG_MANAGER_PROPERTY_KEY, logManagerProperty);
    }
}
