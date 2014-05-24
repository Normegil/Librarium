package be.normegil.librarium.rest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        UTGameREST_Safety.class,
        UTGameREST.class,
        UTGameREST_BorderCases.class
})
public class GameRESTTestSuite {
}
