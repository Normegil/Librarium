package be.normegil.librarium.model.data.video;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.model.data.fake.FakeVideo;
import be.normegil.librarium.model.data.people.Responsible;
import be.normegil.librarium.model.data.people.Role;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.model.data.AbstractDataEqualityTest;
import org.junit.Test;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class UTVideoEquality extends AbstractDataEqualityTest<Video> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Video> FACTORY = FactoryRepository.get(Video.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Role> ROLE_FACTORY = FactoryRepository.get(Role.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Responsible> RESPONSIBLE_FACTORY = FactoryRepository.get(Responsible.class);

	@Override
	protected Video getNewEntity() {
		return FACTORY.getDefault();
	}

	@Test
	public void testUnchanged() throws Exception {
		Video entity = getEntity();
		Video copy = new FakeVideo(entity);
		assertEquals(entity, copy);
	}

	@Test
	public void testDifferentDuration() throws Exception {
		Video entity = getEntity();
		Video copy = new FakeVideo(entity);
		entity.setDuration(entity.getDuration().plus(Duration.ofMinutes(1)));
		assertNotEquals(entity, copy);
	}

	@Test
	public void testDifferentProducer() throws Exception {
		Video entity = getEntity();
		Video copy = new FakeVideo(entity);
		entity.addProducer(RESPONSIBLE_FACTORY.getNew());
		assertEquals(entity, copy);
	}

	@Test
	public void testDifferentDirector() throws Exception {
		Video entity = getEntity();
		Video copy = new FakeVideo(entity);
		entity.addDirector(RESPONSIBLE_FACTORY.getNew());
		assertEquals(entity, copy);
	}

	@Test
	public void testDifferentComposer() throws Exception {
		Video entity = getEntity();
		Video copy = new FakeVideo(entity);
		entity.addComposer(RESPONSIBLE_FACTORY.getNew());
		assertEquals(entity, copy);
	}

	@Test
	public void testDifferentScenarist() throws Exception {
		Video entity = getEntity();
		Video copy = new FakeVideo(entity);
		entity.addScenarist(RESPONSIBLE_FACTORY.getNew());
		assertEquals(entity, copy);
	}

	@Test
	public void testDifferentOther() throws Exception {
		Video entity = getEntity();
		Video copy = new FakeVideo(entity);
		entity.addOtherStaffMember(RESPONSIBLE_FACTORY.getNew());
		assertEquals(entity, copy);
	}

	@Test
	public void testDifferentActor() throws Exception {
		Video entity = getEntity();
		Video copy = new FakeVideo(entity);
		entity.addActor(ROLE_FACTORY.getNew());
		assertEquals(entity, copy);
	}
}