package be.normegil.librarium.model.data.people;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		ResponsibleTestSuite.class,

		EnterpriseTestSuite.class,
		PersonTestSuite.class,

		RoleTestSuite.class,
		StaffMemberTestSuite.class
})
public class PeoplePackageTestSuite {
}
