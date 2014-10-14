package be.normegil.librarium.model.data;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.model.data.fake.FakeMedia;
import be.normegil.librarium.model.data.game.Game;
import be.normegil.librarium.model.data.people.StaffMember;
import be.normegil.librarium.model.rest.RESTHelper;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.MemoryTestDAO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.URI;
import java.util.Collection;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;

public class UTMediaDigest {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Game> GAME_FACTORY = FactoryRepository.get(Game.class);
	private static final String REST_URI = "http://localhost:8080/rest";

	private Media.MediaDigest entity;
	private MemoryTestDAO<DownloadLink> downloadLinkDAO;
	private MemoryTestDAO<Universe> universeDAO;
	private MemoryTestDAO<ReleaseDate> releaseDateDAO;
	private MemoryTestDAO<StaffMember> staffMemberDAO;

	@Before
	public void setUp() throws Exception {
		entity = new Media.MediaDigest();

		downloadLinkDAO = new MemoryTestDAO<>(DownloadLink.class);
		universeDAO = new MemoryTestDAO<>(Universe.class);
		releaseDateDAO = new MemoryTestDAO<>(ReleaseDate.class);
		staffMemberDAO = new MemoryTestDAO<>(StaffMember.class);

		entity.setDownloadLinkDAO(downloadLinkDAO);
		entity.setUniverseDAO(universeDAO);
		entity.setReleaseDateDAO(releaseDateDAO);
		entity.setStaffMembersDAO(staffMemberDAO);
	}

	@After
	public void tearDown() throws Exception {
		downloadLinkDAO = null;
		universeDAO = null;
		releaseDateDAO = null;
		staffMemberDAO = null;

		entity = null;
	}

	@Test
	public void testFromBase_Universes() throws Exception {
		Media media = callFromBase();
		Collection<URI> expected = new TreeSet<>();
		for (Universe universe : media.getUniverses()) {
			expected.add(new RESTHelper().getRESTUri(URI.create(REST_URI), Universe.class, universe));
		}
		assertEquals(expected, entity.universes);
	}

	@Test
	public void testFromBase_StaffMembers() throws Exception {
		Media media = callFromBase();
		Collection<URI> expected = new TreeSet<>();
		for (StaffMember staffMember : media.getStaffMembers()) {
			expected.add(new RESTHelper().getRESTUri(URI.create(REST_URI), StaffMember.class, staffMember));
		}
		assertEquals(expected, entity.staffMembers);
	}

	@Test
	public void testFromBase_ReleaseDate() throws Exception {
		Media media = callFromBase();
		Collection<URI> expected = new TreeSet<>();
		for (ReleaseDate date : media.releaseDates) {
			expected.add(new RESTHelper().getRESTUri(URI.create(REST_URI), ReleaseDate.class, date));
		}
		assertEquals(expected, entity.releaseDates);
	}

	@Test
	public void testToBase_Universes() throws Exception {
		Media media = callToBase();
		Collection<URI> toTest = new TreeSet<>();
		for (Universe universe : media.getUniverses()) {
			toTest.add(new RESTHelper().getRESTUri(URI.create(REST_URI), Universe.class, universe));
		}
		assertEquals(entity.universes, toTest);
	}

	@Test
	public void testToBase_StaffMembers() throws Exception {
		Media media = callToBase();
		Collection<URI> toTest = new TreeSet<>();
		for (StaffMember staffMember : media.getStaffMembers()) {
			toTest.add(new RESTHelper().getRESTUri(URI.create(REST_URI), StaffMember.class, staffMember));
		}
		assertEquals(entity.staffMembers, toTest);
	}

	@Test
	public void testToBase_ReleaseDate() throws Exception {
		Media media = callToBase();
		Collection<URI> toTest = new TreeSet<>();
		for (ReleaseDate date : media.releaseDates) {
			toTest.add(new RESTHelper().getRESTUri(URI.create(REST_URI), ReleaseDate.class, date));
		}
		assertEquals(entity.releaseDates, toTest);
	}

	private Media callFromBase() {
		URI baseUri = URI.create(REST_URI);
		Media media = GAME_FACTORY.getNew(true, true);
		entity.fromBase(baseUri, media);
		return media;
	}

	public Media callToBase() throws Exception {
		FakeMedia.Builder builder = FakeMedia.builder();
		Media media = GAME_FACTORY.getNew(true, true);

		for (DownloadLink downloadLink : media.getDownloadLinks()) {
			downloadLinkDAO.create(downloadLink);
		}

		for (Universe universe : media.getUniverses()) {
			universeDAO.create(universe);
		}

		for (ReleaseDate releaseDate : media.releaseDates) {
			releaseDateDAO.create(releaseDate);
		}

		for (StaffMember staffMember : media.getStaffMembers()) {
			staffMemberDAO.create(staffMember);
		}

		entity.fromBase(URI.create(REST_URI), media);
		entity.toBase(builder);
		return builder.build();
	}
}
