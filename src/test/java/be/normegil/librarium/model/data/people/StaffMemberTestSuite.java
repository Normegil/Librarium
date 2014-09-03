package be.normegil.librarium.model.data.people;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.model.data.Media;
import be.normegil.librarium.model.data.video.Video;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

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

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Responsible> RESPONSIBLE_FACTORY = FactoryRepository.get(Responsible.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Media> MEDIA_FACTORY = FactoryRepository.get(Media.class);

	@Override
	public StaffMember getNew() {
		return getNew(true);
	}

	@Override
	public StaffMember getNext() {
		return getNext(true);
	}

	@Override
	public StaffMember getNew(boolean withLink) {
		StaffMember.Builder builder = StaffMember.builder()
				.setRole(StaffRole.PRODUCER)
				.setResponsible(RESPONSIBLE_FACTORY.getNew(false))
				.setMedia(MEDIA_FACTORY.getNew(false));

		return builder.build();
	}

	@Override
	public StaffMember getNext(boolean withLink) {
		StaffMember.Builder builder = StaffMember.builder()
				.setRole(StaffRole.PRODUCER)
				.setResponsible(RESPONSIBLE_FACTORY.getNext(false))
				.setMedia(MEDIA_FACTORY.getNext(false));

		return builder.build();
	}
}