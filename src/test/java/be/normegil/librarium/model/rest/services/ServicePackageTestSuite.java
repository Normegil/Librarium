package be.normegil.librarium.model.rest.services;

import be.normegil.librarium.model.rest.services.game.GamePackageTestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		GamePackageTestSuite.class
})
public class ServicePackageTestSuite {
}