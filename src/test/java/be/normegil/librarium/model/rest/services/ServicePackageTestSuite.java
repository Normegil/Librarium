package be.normegil.librarium.model.rest.services;

import be.normegil.librarium.model.rest.services.game.GamePackageTestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		AbstractBaseMediaRestServiceTestSuite.class,
		//AbstractMediaRestServiceTestSuite.class,
		BasicRESTServiceTestSuite.class,
		//RESTServiceHelperTestSuite.class,

		//DownloadLinkRESTTestSuite.class,
		//ReleaseDateRESTTestSuite.class,
		//UniverseRESTTestSuite.class,

		GamePackageTestSuite.class,
		//PeoplePackageTestSuite.class
})
public class ServicePackageTestSuite {
}