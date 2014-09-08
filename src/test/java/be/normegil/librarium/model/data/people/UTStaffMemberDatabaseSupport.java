package be.normegil.librarium.model.data.people;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.model.data.AbstractEntityDatabaseSupportTest;

public class UTStaffMemberDatabaseSupport extends AbstractEntityDatabaseSupportTest<StaffMember> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<StaffMember> FACTORY = FactoryRepository.get(StaffMember.class);

	public UTStaffMemberDatabaseSupport() {
		super(StaffMember.class);
	}

	@Override
	protected StaffMember initEntity() {
		return FACTORY.getNext();
	}

	@Override
	protected StaffMember changeEntity(final StaffMember entity) {
		entity.setRole(StaffRole.COMPOSER);
		return entity;
	}
}