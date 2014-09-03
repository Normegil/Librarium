package be.normegil.librarium.model.data.people;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.model.data.video.Video;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		UTRoleSafety.class,
		UTRole.class,
		UTRoleEquality.class,
		UTRoleComparator.class,
		UTRoleBuilderSafety.class,
		UTRoleBuilder.class,
		UTRoleDatabaseSupport.class
})
public class RoleTestSuite implements DataFactory<Role> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Person> PERSON_FACTORY = FactoryRepository.get(Person.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Video> VIDEO_FACTORY = FactoryRepository.get(Video.class);

	@Override
	public Role getNew() {
		return getNew(true);
	}

	@Override
	public Role getNext() {
		return getNext(true);
	}

	@Override
	public Role getNew(boolean withLink) {
		Role.Builder builder = Role.builder()
				.setRole(PERSON_FACTORY.getNew(false))
				.setActor(PERSON_FACTORY.getNew(false))
				.setVideo(VIDEO_FACTORY.getNew(false));
		return builder.build();
	}

	@Override
	public Role getNext(boolean withLink) {
		Role.Builder builder = Role.builder()
				.setRole(PERSON_FACTORY.getNext(false))
				.setActor(PERSON_FACTORY.getNext(false))
				.setVideo(VIDEO_FACTORY.getNext(false));
		return builder.build();
	}
}