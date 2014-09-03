package be.normegil.librarium.model.data.people;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.model.data.video.Video;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UTRole {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Role> FACTORY = FactoryRepository.get(Role.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Person> PERSON_FACTORY = FactoryRepository.get(Person.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Video> VIDEO_FACTORY = FactoryRepository.get(Video.class);
	private Role entity;

	@Before
	public void setUp() throws Exception {
		entity = FACTORY.getNew();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test
	public void testCopyConstructor() throws Exception {
		Role copy = new Role(entity);
		assertEquals(entity, copy);
	}

	@Test
	public void testSetRole() throws Exception {
		Person person = PERSON_FACTORY.getNext();
		entity.setRole(person);
		assertEquals(person, entity.getRole());
	}

	@Test
	public void testSetActor() throws Exception {
		Person person = PERSON_FACTORY.getNext();
		entity.setActor(person);
		assertEquals(person, entity.getActor());
	}

	@Test
	public void testSetVideo() throws Exception {
		Video video = VIDEO_FACTORY.getNext();
		entity.setVideo(video);
		assertEquals(video, entity.getVideo());
	}
}
