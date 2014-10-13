package be.normegil.librarium.model.data.people;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.model.data.video.Video;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UTRoleBuilder {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Role> ROLE_FACTORY = FactoryRepository.get(Role.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Person> PERSON_FACTORY = FactoryRepository.get(Person.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Video> VIDEO_FACTORY = FactoryRepository.get(Video.class);
	private Role.Builder entity;

	@Before
	public void setUp() throws Exception {
		entity = Role.builder();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test
	public void testFrom() throws Exception {
		Role role = ROLE_FACTORY.getNew();
		Role copy = entity.from(role).build();
		assertEquals(role, copy);
	}

	@Test
	public void testSetRole() throws Exception {
		Person person = PERSON_FACTORY.getNew();
		Role role = entity
				.setRole(person)
				.build();
		assertEquals(person, role.getRole());
	}

	@Test
	public void testSetActor() throws Exception {
		Person person = PERSON_FACTORY.getNew();
		Role role = entity
				.setActor(person)
				.build();
		assertEquals(person, role.getActor());
	}

	@Test
	public void testSetVideo() throws Exception {
		Video video = VIDEO_FACTORY.getNew();
		Role role = entity
				.setVideo(video)
				.build();
		assertEquals(video, role.getVideo());
	}
}