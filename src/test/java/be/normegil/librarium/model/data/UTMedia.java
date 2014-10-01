package be.normegil.librarium.model.data;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.model.data.fake.FakeMedia;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UTMedia {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Media> FACTORY = FactoryRepository.get(Media.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Universe> UNIVERSE_FACTORY = FactoryRepository.get(Universe.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Support> SUPPORT_FACTORY = FactoryRepository.get(Support.class);
	private Media entity;
	private Collection<Universe> universes = new ArrayList<>();
	private Collection<Support> supports = new ArrayList<>();
	private Map<Support, LocalDate> releaseDates = new HashMap<>();

	@Before
	public void setUp() throws Exception {
		entity = FACTORY.getNew();
		universes = entity.getUniverses();
		supports = entity.getSupports();
		releaseDates = entity.getReleaseDates();
	}

	@After
	public void tearDown() throws Exception {
		releaseDates = null;
		supports = null;
		universes = null;
		entity = null;
	}

	@Test
	public void testCopyConstructor() throws Exception {
		Media copy = new FakeMedia(entity);
		assertEquals(entity, copy);
	}

	@Test
	public void testAddAllUniverses() throws Exception {
		Collection<Universe> toAdd = new HashSet<>();
		toAdd.add(UNIVERSE_FACTORY.getNext());
		toAdd.add(UNIVERSE_FACTORY.getNext());
		toAdd.add(UNIVERSE_FACTORY.getNext());

		universes.addAll(toAdd);
		entity.addAllUniverses(toAdd);
		assertEquals(universes, entity.getUniverses());
	}

	@Test
	public void testAddUniverse() throws Exception {
		Universe toAdd = UNIVERSE_FACTORY.getNext();
		universes.add(toAdd);
		entity.addUniverse(toAdd);
		assertEquals(universes, entity.getUniverses());
	}

	@Test
	public void testRemoveAllUniverses() throws Exception {
		Universe base = UNIVERSE_FACTORY.getNext();
		Universe second = UNIVERSE_FACTORY.getNext();
		Universe third = UNIVERSE_FACTORY.getNext();

		Collection<Universe> toAdd = new HashSet<>();
		toAdd.add(base);
		toAdd.add(second);
		toAdd.add(third);

		Collection<Universe> toRemove = new HashSet<>();
		toRemove.add(second);
		toRemove.add(third);

		universes.addAll(toAdd);
		entity.addAllUniverses(toAdd);

		universes.removeAll(toRemove);
		entity.removeAllUniverses(toRemove);

		assertEquals(universes, entity.getUniverses());
	}

	@Test
	public void testRemoveUniverse() throws Exception {
		Universe toRemove = entity.getUniverses().iterator().next();
		universes.remove(toRemove);
		entity.removeUniverse(toRemove);

		assertEquals(universes, entity.getUniverses());
	}

	@Test
	public void testClearUniverses() throws Exception {
		entity.addUniverse(UNIVERSE_FACTORY.getNext());
		entity.clearUniverses();
		assertTrue(entity.getUniverses().isEmpty());
	}

	@Test
	public void testAddAllSupports() throws Exception {
		Collection<Support> toAdd = new HashSet<>();
		toAdd.add(SUPPORT_FACTORY.getNext());
		toAdd.add(SUPPORT_FACTORY.getNext());
		toAdd.add(SUPPORT_FACTORY.getNext());

		supports.addAll(toAdd);
		entity.addAllSupports(toAdd);
		assertEquals(supports, entity.getSupports());
	}

	@Test
	public void testAddSupport() throws Exception {
		Support toAdd = SUPPORT_FACTORY.getNext();
		supports.add(toAdd);
		entity.addSupport(toAdd);
		assertEquals(supports, entity.getSupports());
	}

	@Test
	public void testRemoveAllSupports() throws Exception {
		Support base = SUPPORT_FACTORY.getNext();
		Support second = SUPPORT_FACTORY.getNext();
		Support third = SUPPORT_FACTORY.getNext();

		Collection<Support> toAdd = new HashSet<>();
		toAdd.add(base);
		toAdd.add(second);
		toAdd.add(third);

		Collection<Support> toRemove = new HashSet<>();
		toRemove.add(second);
		toRemove.add(third);

		supports.addAll(toAdd);
		entity.addAllSupports(toAdd);

		supports.removeAll(toRemove);
		entity.removeAllSupports(toRemove);

		assertEquals(supports, entity.getSupports());
	}

	@Test
	public void testRemoveSupport() throws Exception {
		Support toRemove = entity.getSupports().iterator().next();
		supports.remove(toRemove);
		entity.removeSupport(toRemove);

		assertEquals(supports, entity.getSupports());
	}

	@Test
	public void testClearSupports() throws Exception {
		entity.addSupport(SUPPORT_FACTORY.getNext());
		entity.clearSupports();
		assertTrue(entity.getSupports().isEmpty());
	}

	@Test
	public void testGetReleaseDate() throws Exception {
		Support support = SUPPORT_FACTORY.getNext();
		LocalDate date = LocalDate.of(1990, 01, 01);

		entity.addReleaseDate(support, date);
		assertEquals(date, entity.getReleaseDate(support));
	}

	@Test
	public void testAddAllReleaseDates() throws Exception {
		Map<Support, LocalDate> toAdd = new HashMap<>();
		toAdd.put(SUPPORT_FACTORY.getNext(), LocalDate.now());
		toAdd.put(SUPPORT_FACTORY.getNext(), LocalDate.now());
		toAdd.put(SUPPORT_FACTORY.getNext(), LocalDate.now());

		releaseDates.putAll(toAdd);
		entity.addAllReleaseDates(toAdd);
		assertEquals(releaseDates, entity.getReleaseDates());
	}

	@Test
	public void testAddReleaseDate() throws Exception {
		Support toAddSupport = SUPPORT_FACTORY.getNext();
		LocalDate toAddDate = LocalDate.now();
		releaseDates.put(toAddSupport, toAddDate);
		entity.addReleaseDate(toAddSupport, toAddDate);
		assertEquals(releaseDates, entity.getReleaseDates());
	}

	@Test
	public void testRemoveAllReleaseDates() throws Exception {
		Support base = SUPPORT_FACTORY.getNext();
		Support second = SUPPORT_FACTORY.getNext();
		Support third = SUPPORT_FACTORY.getNext();
		LocalDate date = LocalDate.now();

		Map<Support, LocalDate> toAdd = new HashMap<>();
		toAdd.put(base, date);
		toAdd.put(second, date);
		toAdd.put(third, date);

		Collection<Support> toRemove = new HashSet<>();
		toRemove.add(second);
		toRemove.add(third);

		releaseDates.putAll(toAdd);
		entity.addAllReleaseDates(toAdd);

		for (Support support : toRemove) {
			releaseDates.remove(support);
		}
		entity.removeAllReleaseDates(toRemove);

		assertEquals(releaseDates, entity.getReleaseDates());
	}

	@Test
	public void testRemoveReleaseDate() throws Exception {
		Support support = entity.getReleaseDates().keySet().iterator().next();
		releaseDates.remove(support);
		entity.removeReleaseDate(support);

		assertEquals(releaseDates, entity.getReleaseDates());
	}

	@Test
	public void testClearReleaseDate() throws Exception {
		entity.addReleaseDate(SUPPORT_FACTORY.getNext(), LocalDate.now());
		entity.clearReleaseDates();
		assertTrue(entity.getReleaseDates().isEmpty());
	}
}
