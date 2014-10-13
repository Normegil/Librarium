package be.normegil.librarium.model.data.people;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		UTEnterpriseSafety.class,
		UTEnterprise.class,
		UTEnterpriseEquality.class,
		UTEnterpriseComparator.class,
		UTEnterpriseBuilderSafety.class,
		UTEnterpriseBuilder.class,
		UTEnterpriseDatabaseSupport.class
})
public class EnterpriseTestSuite implements DataFactory<Enterprise> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Responsible> RESPONSIBLE_FACTORY = FactoryRepository.get(Responsible.class);

	@Override
	public Enterprise getDefault() {
		return getDefault(true);
	}

	@Override
	public Enterprise getNew() {
		return getNew(true);
	}

	@Override
	public Enterprise getDefault(boolean withLink) {
		return Enterprise.builder()
				.from(RESPONSIBLE_FACTORY.getDefault(withLink))
				.build();
	}

	@Override
	public Enterprise getNew(boolean withLink) {
		Enterprise entity = Enterprise.builder()
				.from(RESPONSIBLE_FACTORY.getDefault(withLink))
				.build();

		return entity;
	}
}