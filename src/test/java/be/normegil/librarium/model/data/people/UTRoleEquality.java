package be.normegil.librarium.model.data.people;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.model.data.video.Video;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.AbstractDataEqualityTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class UTRoleEquality extends AbstractDataEqualityTest<Role> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Role> FACTORY = FactoryRepository.get(Role.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Person> PERSON_FACTORY = FactoryRepository.get(Person.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Video> VIDEO_FACTORY = FactoryRepository.get(Video.class);

	@Override
	protected Role getNewEntity() {
		return FACTORY.getNew();
	}

	@Test
	public void testUnchanged() throws Exception {
		Role entity = getEntity();
		Role copy = new Role(entity);
		assertEquals(entity, copy);
	}

	@Test
	public void testDifferentRole() throws Exception {
		Role entity = getEntity();
		Role copy = new Role(entity);
		entity.setRole(PERSON_FACTORY.getNext());
		assertNotEquals(entity, copy);
	}

	@Test
	public void testDifferentActor() throws Exception {
		Role entity = getEntity();
		Role copy = new Role(entity);
		entity.setActor(PERSON_FACTORY.getNext());
		assertNotEquals(entity, copy);
	}

	@Test
	public void testDifferentVideo() throws Exception {
		Role entity = getEntity();
		Role copy = new Role(entity);
		entity.setVideo(VIDEO_FACTORY.getNext());
		assertNotEquals(entity, copy);
	}
}