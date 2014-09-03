package be.normegil.librarium.model.data.video;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.model.data.fake.FakeVideo;
import be.normegil.librarium.model.data.people.Responsible;
import be.normegil.librarium.model.data.people.Role;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.Duration;
import java.util.Collection;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UTVideoBuilder {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Video> VIDEO_FACTORY = FactoryRepository.get(Video.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Role> ROLE_FACTORY = FactoryRepository.get(Role.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Responsible> RESPONSIBLE_FACTORY = FactoryRepository.get(Responsible.class);

	private FakeVideo.Builder entity;

	@Before
	public void setUp() throws Exception {
		entity = FakeVideo.builder();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test
	public void testFrom() throws Exception {
		Video video = VIDEO_FACTORY.getNext();
		Video copy = entity.from(video).build();
		assertEquals(video, copy);
	}

	@Test
	public void testSetDuration() throws Exception {
		Video video = entity
				.setDuration(Duration.ofMinutes(1))
				.build();
		assertEquals(Duration.ofMinutes(1), video.getDuration());
	}

	@Test
	public void testAddAllProducers() throws Exception {
		Collection<Responsible> toAdd = new HashSet<>();
		toAdd.add(RESPONSIBLE_FACTORY.getNext());
		toAdd.add(RESPONSIBLE_FACTORY.getNext());
		toAdd.add(RESPONSIBLE_FACTORY.getNext());

		Video video = entity
				.addAllProducers(toAdd)
				.build();

		assertTrue(video.getProducers().containsAll(toAdd));
	}

	@Test
	public void testAddProducer() throws Exception {
		Responsible responsible = RESPONSIBLE_FACTORY.getNext();
		Video video = entity
				.addProducer(responsible)
				.build();

		assertTrue(video.getProducers().contains(responsible));
	}

	@Test
	public void testAddAllDirectors() throws Exception {
		Collection<Responsible> toAdd = new HashSet<>();
		toAdd.add(RESPONSIBLE_FACTORY.getNext());
		toAdd.add(RESPONSIBLE_FACTORY.getNext());
		toAdd.add(RESPONSIBLE_FACTORY.getNext());

		Video video = entity
				.addAllDirectors(toAdd)
				.build();

		assertTrue(video.getDirectors().containsAll(toAdd));
	}

	@Test
	public void testAddDirector() throws Exception {
		Responsible responsible = RESPONSIBLE_FACTORY.getNext();
		Video video = entity
				.addDirector(responsible)
				.build();

		assertTrue(video.getDirectors().contains(responsible));
	}

	@Test
	public void testAddAllComposers() throws Exception {
		Collection<Responsible> toAdd = new HashSet<>();
		toAdd.add(RESPONSIBLE_FACTORY.getNext());
		toAdd.add(RESPONSIBLE_FACTORY.getNext());
		toAdd.add(RESPONSIBLE_FACTORY.getNext());

		Video video = entity
				.addAllComposers(toAdd)
				.build();

		assertTrue(video.getComposers().containsAll(toAdd));
	}

	@Test
	public void testAddComposer() throws Exception {
		Responsible responsible = RESPONSIBLE_FACTORY.getNext();
		Video video = entity
				.addComposer(responsible)
				.build();

		assertTrue(video.getComposers().contains(responsible));
	}

	@Test
	public void testAddAllScenarists() throws Exception {
		Collection<Responsible> toAdd = new HashSet<>();
		toAdd.add(RESPONSIBLE_FACTORY.getNext());
		toAdd.add(RESPONSIBLE_FACTORY.getNext());
		toAdd.add(RESPONSIBLE_FACTORY.getNext());

		Video video = entity
				.addAllScenarists(toAdd)
				.build();

		assertTrue(video.getScenarists().containsAll(toAdd));
	}

	@Test
	public void testAddScenarist() throws Exception {
		Responsible responsible = RESPONSIBLE_FACTORY.getNext();
		Video video = entity
				.addScenarist(responsible)
				.build();

		assertTrue(video.getScenarists().contains(responsible));
	}

	@Test
	public void testAddAllOtherStaffMembers() throws Exception {
		Collection<Responsible> toAdd = new HashSet<>();
		toAdd.add(RESPONSIBLE_FACTORY.getNext());
		toAdd.add(RESPONSIBLE_FACTORY.getNext());
		toAdd.add(RESPONSIBLE_FACTORY.getNext());

		Video video = entity
				.addAllOtherStaffMembers(toAdd)
				.build();

		assertTrue(video.getOtherStaffMembers().containsAll(toAdd));
	}

	@Test
	public void testAddOtherStaffMember() throws Exception {
		Responsible responsible = RESPONSIBLE_FACTORY.getNext();
		Video video = entity
				.addOtherStaffMember(responsible)
				.build();

		assertTrue(video.getOtherStaffMembers().contains(responsible));
	}

	@Test
	public void testAddAllActors() throws Exception {
		Collection<Role> toAdd = new HashSet<>();
		toAdd.add(ROLE_FACTORY.getNext());
		toAdd.add(ROLE_FACTORY.getNext());
		toAdd.add(ROLE_FACTORY.getNext());

		Video video = entity
				.addAllActors(toAdd)
				.build();

		assertTrue(video.getActors().containsAll(toAdd));
	}

	@Test
	public void testAddActor() throws Exception {
		Role role = ROLE_FACTORY.getNext();
		Video video = entity
				.addActor(role)
				.build();

		assertTrue(video.getActors().contains(role));
	}
}