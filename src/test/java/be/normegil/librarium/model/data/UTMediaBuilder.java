package be.normegil.librarium.model.data;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.model.data.fake.FakeMedia;
import be.normegil.librarium.model.data.people.StaffMember;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UTMediaBuilder {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Media> FACTORY = FactoryRepository.get(Media.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Universe> UNIVERSE_FACTORY = FactoryRepository.get(Universe.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Support> SUPPORT_FACTORY = FactoryRepository.get(Support.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<ReleaseDate> RELEASE_DATE_FACTORY = FactoryRepository.get(ReleaseDate.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<StaffMember> STAFF_MEMBER_FACTORY = FactoryRepository.get(StaffMember.class);
	private FakeMedia.Builder entity;

	@Before
	public void setUp() throws Exception {
		entity = FakeMedia.builder();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test
	public void testFrom() throws Exception {
		Media media = FACTORY.getNew();
		Media copy = entity.from(media).build();
		assertEquals(media, copy);
	}

	@Test
	public void testAddUniverse() throws Exception {
		Universe universe = UNIVERSE_FACTORY.getNew();
		Media media = entity
				.addUniverse(universe)
				.build();
		assertTrue(media.getUniverses().contains(universe));
	}

	@Test
	public void testAddAllUniverses() throws Exception {
		Collection<Universe> toAdd = new HashSet<>();
		toAdd.add(UNIVERSE_FACTORY.getNew());
		toAdd.add(UNIVERSE_FACTORY.getNew());

		Media media = entity
				.addAllUniverses(toAdd)
				.build();
		assertTrue(media.getUniverses().containsAll(toAdd));
	}

	@Test
	public void testAddSupport() throws Exception {
		Support support = SUPPORT_FACTORY.getNew();
		Media media = entity
				.addSupport(support)
				.build();
		assertTrue(media.getSupports().contains(support));
	}

	@Test
	public void testAddAllSupports() throws Exception {
		Collection<Support> toAdd = new HashSet<>();
		toAdd.add(SUPPORT_FACTORY.getNew());
		toAdd.add(SUPPORT_FACTORY.getNew());

		Media media = entity
				.addAllSupports(toAdd)
				.build();
		assertTrue(media.getSupports().containsAll(toAdd));
	}

	@Test
	public void testAddReleaseDate() throws Exception {
		Support support = SUPPORT_FACTORY.getNew();
		LocalDate date = LocalDate.now();
		Media media = entity
				.addReleaseDate(support, date)
				.build();
		assertEquals(date, media.getReleaseDate(support));
	}

	@Test
	public void testAddAllReleaseDates() throws Exception {
		Map<Support, LocalDate> toAdd = new HashMap<>();
		toAdd.put(SUPPORT_FACTORY.getNew(), LocalDate.now());
		toAdd.put(SUPPORT_FACTORY.getNew(), LocalDate.now());

		Media media = entity
				.addAllReleaseDates(toAdd)
				.build();
		for (Map.Entry<Support, LocalDate> releaseDate : toAdd.entrySet()) {
			assertEquals(releaseDate.getValue(), media.getReleaseDate(releaseDate.getKey()));
		}
	}

	@Test
	public void testAddReleaseDate_Full() throws Exception {
		ReleaseDate releaseDate = RELEASE_DATE_FACTORY.getNew();
		Media media = entity
				.addReleaseDate(releaseDate)
				.build();
		assertEquals(releaseDate.getDate(), media.getReleaseDate(releaseDate.getSupport()));
	}

	@Test
	public void testAddStaffMember() throws Exception {
		StaffMember staffMember = STAFF_MEMBER_FACTORY.getNew();
		Media media = entity
				.addStaffMember(staffMember)
				.build();
		assertTrue(media.getStaffMembers().contains(staffMember));
	}
}