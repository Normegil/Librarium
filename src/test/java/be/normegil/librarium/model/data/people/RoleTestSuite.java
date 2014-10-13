package be.normegil.librarium.model.data.people;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.model.data.video.Video;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.EntityHelper;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import java.util.UUID;

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
	private static final UUID DEFAULT_ID = UUID.fromString("3873812f-a42d-43a1-83a3-974266ab7ef9");

	@Override
	public Role getDefault() {
		return getDefault(true, false);
	}

	@Override
	public Role getNew() {
		return getNew(true, false);
	}

	@Override
	public Role getDefault(final boolean withLink, final boolean withIds) {
		Role.Builder builder = Role.builder()
				.setRole(PERSON_FACTORY.getDefault(false, withIds))
				.setActor(PERSON_FACTORY.getDefault(false, withIds))
				.setVideo(VIDEO_FACTORY.getDefault(false, withIds));
		Role role = builder.build();
		if (withIds) {
			new EntityHelper().setId(role, DEFAULT_ID);
		}
		return role;
	}

	@Override
	public Role getNew(final boolean withLink, final boolean withIds) {
		Role.Builder builder = Role.builder()
				.setRole(PERSON_FACTORY.getNew(false, withIds))
				.setActor(PERSON_FACTORY.getNew(false, withIds))
				.setVideo(VIDEO_FACTORY.getNew(false, withIds));
		Role role = builder.build();
		if (withIds) {
			new EntityHelper().setId(role, DEFAULT_ID);
		}
		return role;
	}
}