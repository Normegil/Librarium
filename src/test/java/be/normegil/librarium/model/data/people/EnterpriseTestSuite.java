package be.normegil.librarium.model.data.people;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.EntityHelper;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import java.util.UUID;

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
	private static final UUID DEFAULT_ID = UUID.fromString("7930d837-f0a6-427f-9958-57e16ee24d1d");

	@Override
	public Enterprise getDefault() {
		return getDefault(true, false);
	}

	@Override
	public Enterprise getNew() {
		return getNew(true, false);
	}

	@Override
	public Enterprise getDefault(final boolean withLink, final boolean withIds) {
		Enterprise enterprise = Enterprise.builder()
				.from(RESPONSIBLE_FACTORY.getDefault(withLink, withIds))
				.build();
		if (withIds) {
			new EntityHelper().setId(enterprise, DEFAULT_ID);
		}
		return enterprise;
	}

	@Override
	public Enterprise getNew(final boolean withLink, final boolean withIds) {
		Enterprise entity = Enterprise.builder()
				.from(RESPONSIBLE_FACTORY.getDefault(withLink, withIds))
				.build();
		if (withIds) {
			new EntityHelper().setId(entity, DEFAULT_ID);
		}
		return entity;
	}
}