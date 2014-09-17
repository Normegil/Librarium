package be.normegil.librarium.rest;

import be.normegil.librarium.rest.game.GamePackageTestSuite;
import be.normegil.librarium.rest.game.GameRESTTestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		RESTHelperTestSuite.class,

        GamePackageTestSuite.class
})
public class RESTPackageTestSuite {
}
