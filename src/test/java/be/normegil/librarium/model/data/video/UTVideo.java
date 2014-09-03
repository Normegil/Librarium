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

public class UTVideo {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Video> FACTORY = FactoryRepository.get(Video.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Role> ROLE_FACTORY = FactoryRepository.get(Role.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Responsible> RESPONSIBLE_FACTORY = FactoryRepository.get(Responsible.class);
	private Video entity;
	private Collection<Role> actors = new HashSet<>();
	private Collection<Responsible> producers;
	private Collection<Responsible> directors;
	private Collection<Responsible> composers;
	private Collection<Responsible> scenarists;
	private Collection<Responsible> otherStaffMembers;

	@Before
	public void setUp() throws Exception {
		entity = FACTORY.getNext();
		actors = entity.getActors();
		producers = entity.getProducers();
		directors = entity.getDirectors();
		composers = entity.getComposers();
		scenarists = entity.getScenarists();
		otherStaffMembers = entity.getOtherStaffMembers();
	}

	@After
	public void tearDown() throws Exception {
		otherStaffMembers = null;
		scenarists = null;
		composers = null;
		directors = null;
		producers = null;
		actors = null;
		entity = null;
	}

	@Test
	public void testCopyConstructor() throws Exception {
		Video copy = new FakeVideo(entity);
		assertEquals(entity, copy);
	}

	@Test
	public void testSetDuration() throws Exception {
		entity.setDuration(Duration.ofMinutes(90));
		assertEquals(Duration.ofMinutes(90), entity.getDuration());
	}

	@Test
	public void testAddAllProducers() throws Exception {
		Collection<Responsible> toAdd = new HashSet<>();
		toAdd.add(RESPONSIBLE_FACTORY.getNext());
		toAdd.add(RESPONSIBLE_FACTORY.getNext());
		toAdd.add(RESPONSIBLE_FACTORY.getNext());

		producers.addAll(toAdd);
		entity.addAllProducers(toAdd);
		assertEquals(producers, entity.getProducers());
	}

	@Test
	public void testAddProducer() throws Exception {
		Responsible toAdd = RESPONSIBLE_FACTORY.getNext();
		producers.add(toAdd);
		entity.addProducer(toAdd);
		assertEquals(producers, entity.getProducers());
	}

	@Test
	public void testRemoveAllProducers() throws Exception {
		Responsible base = RESPONSIBLE_FACTORY.getNext();
		Responsible second = RESPONSIBLE_FACTORY.getNext();
		Responsible third = RESPONSIBLE_FACTORY.getNext();

		Collection<Responsible> toAdd = new HashSet<>();
		toAdd.add(base);
		toAdd.add(second);
		toAdd.add(third);

		Collection<Responsible> toRemove = new HashSet<>();
		toRemove.add(second);
		toRemove.add(third);

		producers.addAll(toAdd);
		entity.addAllProducers(toAdd);

		producers.removeAll(toRemove);
		entity.removeAllProducers(toRemove);

		assertEquals(producers, entity.getProducers());
	}

	@Test
	public void testRemoveProducer() throws Exception {
		Responsible toRemove = entity.getProducers().iterator().next();
		producers.remove(toRemove);
		entity.removeProducer(toRemove);

		assertEquals(producers, entity.getProducers());
	}

	@Test
	public void testAddAllDirectors() throws Exception {
		Collection<Responsible> toAdd = new HashSet<>();
		toAdd.add(RESPONSIBLE_FACTORY.getNext());
		toAdd.add(RESPONSIBLE_FACTORY.getNext());
		toAdd.add(RESPONSIBLE_FACTORY.getNext());

		directors.addAll(toAdd);
		entity.addAllDirectors(toAdd);
		assertEquals(directors, entity.getDirectors());
	}

	@Test
	public void testAddDirector() throws Exception {
		Responsible toAdd = RESPONSIBLE_FACTORY.getNext();
		directors.add(toAdd);
		entity.addDirector(toAdd);
		assertEquals(directors, entity.getDirectors());
	}

	@Test
	public void testRemoveAllDirectors() throws Exception {
		Responsible base = RESPONSIBLE_FACTORY.getNext();
		Responsible second = RESPONSIBLE_FACTORY.getNext();
		Responsible third = RESPONSIBLE_FACTORY.getNext();

		Collection<Responsible> toAdd = new HashSet<>();
		toAdd.add(base);
		toAdd.add(second);
		toAdd.add(third);

		Collection<Responsible> toRemove = new HashSet<>();
		toRemove.add(second);
		toRemove.add(third);

		directors.addAll(toAdd);
		entity.addAllDirectors(toAdd);

		directors.removeAll(toRemove);
		entity.removeAllDirectors(toRemove);

		assertEquals(directors, entity.getDirectors());
	}

	@Test
	public void testRemoveDirector() throws Exception {
		Responsible toRemove = entity.getDirectors().iterator().next();
		directors.remove(toRemove);
		entity.removeDirector(toRemove);

		assertEquals(directors, entity.getDirectors());
	}

	@Test
	public void testAddAllComposers() throws Exception {
		Collection<Responsible> toAdd = new HashSet<>();
		toAdd.add(RESPONSIBLE_FACTORY.getNext());
		toAdd.add(RESPONSIBLE_FACTORY.getNext());
		toAdd.add(RESPONSIBLE_FACTORY.getNext());

		composers.addAll(toAdd);
		entity.addAllComposers(toAdd);
		assertEquals(composers, entity.getComposers());
	}

	@Test
	public void testAddComposer() throws Exception {
		Responsible toAdd = RESPONSIBLE_FACTORY.getNext();
		composers.add(toAdd);
		entity.addComposer(toAdd);
		assertEquals(composers, entity.getComposers());
	}

	@Test
	public void testRemoveAllComposers() throws Exception {
		Responsible base = RESPONSIBLE_FACTORY.getNext();
		Responsible second = RESPONSIBLE_FACTORY.getNext();
		Responsible third = RESPONSIBLE_FACTORY.getNext();

		Collection<Responsible> toAdd = new HashSet<>();
		toAdd.add(base);
		toAdd.add(second);
		toAdd.add(third);

		Collection<Responsible> toRemove = new HashSet<>();
		toRemove.add(second);
		toRemove.add(third);

		composers.addAll(toAdd);
		entity.addAllComposers(toAdd);

		composers.removeAll(toRemove);
		entity.removeAllComposers(toRemove);

		assertEquals(composers, entity.getComposers());
	}

	@Test
	public void testRemoveComposer() throws Exception {
		Responsible toRemove = entity.getComposers().iterator().next();
		composers.remove(toRemove);
		entity.removeComposer(toRemove);

		assertEquals(composers, entity.getComposers());
	}

	@Test
	public void testAddAllScenarists() throws Exception {
		Collection<Responsible> toAdd = new HashSet<>();
		toAdd.add(RESPONSIBLE_FACTORY.getNext());
		toAdd.add(RESPONSIBLE_FACTORY.getNext());
		toAdd.add(RESPONSIBLE_FACTORY.getNext());

		scenarists.addAll(toAdd);
		entity.addAllScenarists(toAdd);
		assertEquals(scenarists, entity.getScenarists());
	}

	@Test
	public void testAddScenarist() throws Exception {
		Responsible toAdd = RESPONSIBLE_FACTORY.getNext();
		scenarists.add(toAdd);
		entity.addScenarist(toAdd);
		assertEquals(scenarists, entity.getScenarists());
	}

	@Test
	public void testRemoveAllScenarists() throws Exception {
		Responsible base = RESPONSIBLE_FACTORY.getNext();
		Responsible second = RESPONSIBLE_FACTORY.getNext();
		Responsible third = RESPONSIBLE_FACTORY.getNext();

		Collection<Responsible> toAdd = new HashSet<>();
		toAdd.add(base);
		toAdd.add(second);
		toAdd.add(third);

		Collection<Responsible> toRemove = new HashSet<>();
		toRemove.add(second);
		toRemove.add(third);

		scenarists.addAll(toAdd);
		entity.addAllScenarists(toAdd);

		scenarists.removeAll(toRemove);
		entity.removeAllScenarists(toRemove);

		assertEquals(scenarists, entity.getScenarists());
	}

	@Test
	public void testRemoveScenarist() throws Exception {
		Responsible toRemove = entity.getScenarists().iterator().next();
		scenarists.remove(toRemove);
		entity.removeScenarist(toRemove);

		assertEquals(scenarists, entity.getScenarists());
	}

	@Test
	public void testAddAllOtherStaffMembers() throws Exception {
		Collection<Responsible> toAdd = new HashSet<>();
		toAdd.add(RESPONSIBLE_FACTORY.getNext());
		toAdd.add(RESPONSIBLE_FACTORY.getNext());
		toAdd.add(RESPONSIBLE_FACTORY.getNext());

		otherStaffMembers.addAll(toAdd);
		entity.addAllOtherStaffMembers(toAdd);
		assertEquals(otherStaffMembers, entity.getOtherStaffMembers());
	}

	@Test
	public void testAddOtherStaffMember() throws Exception {
		Responsible toAdd = RESPONSIBLE_FACTORY.getNext();
		otherStaffMembers.add(toAdd);
		entity.addOtherStaffMember(toAdd);
		assertEquals(otherStaffMembers, entity.getOtherStaffMembers());
	}

	@Test
	public void testRemoveAllOtherStaffMembers() throws Exception {
		Responsible base = RESPONSIBLE_FACTORY.getNext();
		Responsible second = RESPONSIBLE_FACTORY.getNext();
		Responsible third = RESPONSIBLE_FACTORY.getNext();

		Collection<Responsible> toAdd = new HashSet<>();
		toAdd.add(base);
		toAdd.add(second);
		toAdd.add(third);

		Collection<Responsible> toRemove = new HashSet<>();
		toRemove.add(second);
		toRemove.add(third);

		otherStaffMembers.addAll(toAdd);
		entity.addAllOtherStaffMembers(toAdd);

		otherStaffMembers.removeAll(toRemove);
		entity.removeAllOtherStaffMembers(toRemove);

		assertEquals(otherStaffMembers, entity.getOtherStaffMembers());
	}

	@Test
	public void testRemoveOtherStaffMember() throws Exception {
		Responsible toRemove = entity.getOtherStaffMembers().iterator().next();
		otherStaffMembers.remove(toRemove);
		entity.removeOtherStaffMember(toRemove);

		assertEquals(otherStaffMembers, entity.getOtherStaffMembers());
	}

	@Test
	public void testGetRoleForActor() throws Exception {
		Role role = ROLE_FACTORY.getNext();
		entity.addActor(role);
		assertTrue(entity.getRoleForActor(role.getActor()).contains(role.getRole()));
	}

	@Test
	public void testGetActorsForRole() throws Exception {
		Role role = ROLE_FACTORY.getNext();
		entity.addActor(role);
		assertTrue(entity.getActorsForRole(role.getRole()).contains(role.getActor()));
	}

	@Test
	public void testAddAllActors() throws Exception {
		Collection<Role> toAdd = new HashSet<>();
		toAdd.add(ROLE_FACTORY.getNext());
		toAdd.add(ROLE_FACTORY.getNext());
		toAdd.add(ROLE_FACTORY.getNext());

		actors.addAll(toAdd);
		entity.addAllActors(toAdd);
		assertEquals(actors, entity.getActors());
	}

	@Test
	public void testAddActor() throws Exception {
		Role toAdd = ROLE_FACTORY.getNext();
		actors.add(toAdd);
		entity.addActor(toAdd);
		assertEquals(actors, entity.getActors());
	}

	@Test
	public void testRemoveAllActors() throws Exception {
		Role base = ROLE_FACTORY.getNext();
		Role second = ROLE_FACTORY.getNext();
		Role third = ROLE_FACTORY.getNext();

		Collection<Role> toAdd = new HashSet<>();
		toAdd.add(base);
		toAdd.add(second);
		toAdd.add(third);

		Collection<Role> toRemove = new HashSet<>();
		toRemove.add(second);
		toRemove.add(third);

		actors.addAll(toAdd);
		entity.addAllActors(toAdd);

		actors.removeAll(toRemove);
		entity.removeAllActors(toRemove);

		assertEquals(actors, entity.getActors());
	}

	@Test
	public void testRemoveActor() throws Exception {
		Role toRemove = entity.getActors().iterator().next();
		actors.remove(toRemove);
		entity.removeActor(toRemove.getActor());

		assertEquals(actors, entity.getActors());
	}

	@Test
	public void testRemoveRole() throws Exception {
		Role toRemove = entity.getActors().iterator().next();
		actors.remove(toRemove);
		entity.removeRole(toRemove.getRole());

		assertEquals(actors, entity.getActors());
	}
}
