package be.normegil.librarium.model.data.people;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.model.data.Media;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.EntityHelper;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import java.util.UUID;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		UTStaffMemberSafety.class,
		UTStaffMember.class,
		UTStaffMemberEquality.class,
		UTStaffMemberComparator.class,
		UTStaffMemberBuilderSafety.class,
		UTStaffMemberBuilder.class,
		UTStaffMemberDatabaseSupport.class
})
public class StaffMemberTestSuite implements DataFactory<StaffMember> {

	public static final UUID DEFAULT_ID = UUID.fromString("");
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Responsible> RESPONSIBLE_FACTORY = FactoryRepository.get(Responsible.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Media> MEDIA_FACTORY = FactoryRepository.get(Media.class);

	@Override
	public StaffMember getDefault() {
		return getDefault(true, false);
	}

	@Override
	public StaffMember getNew() {
		return getNew(true, false);
	}

	@Override
	public StaffMember getDefault(final boolean withLink, final boolean withIds) {
		StaffMember.Builder builder = StaffMember.builder()
				.setRole(StaffRole.PRODUCER)
				.setResponsible(RESPONSIBLE_FACTORY.getDefault(false, withIds))
				.setMedia(MEDIA_FACTORY.getDefault(false, true));

		StaffMember member = builder.build();
		if (withIds) {
			new EntityHelper().setId(member, DEFAULT_ID);
		}
		return member;
	}

	@Override
	public StaffMember getNew(final boolean withLink, final boolean withIds) {
		StaffMember.Builder builder = StaffMember.builder()
				.setRole(StaffRole.PRODUCER)
				.setResponsible(RESPONSIBLE_FACTORY.getNew(false, withIds))
				.setMedia(MEDIA_FACTORY.getNew(false, withIds));

		StaffMember member = builder.build();
		if (withIds) {
			new EntityHelper().setId(member, UUID.randomUUID());
		}
		return member;
	}
}