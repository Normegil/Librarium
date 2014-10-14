package be.normegil.librarium.model.data.game;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.libraries.ClassWrapper;
import be.normegil.librarium.libraries.FieldWrapper;
import be.normegil.librarium.model.data.DownloadLink;
import be.normegil.librarium.model.data.ReleaseDate;
import be.normegil.librarium.model.data.Universe;
import be.normegil.librarium.model.data.people.StaffMember;
import be.normegil.librarium.model.rest.RESTHelper;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.EntityHelper;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.MemoryTestDAO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.URI;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

public class UTGameDigest {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Game> GAME_FACTORY = FactoryRepository.get(Game.class);
	private static final String REST_URI = "http://localhost:8080/rest";

	private Game.GameDigest entity;
	private MemoryTestDAO<DownloadLink> downloadLinkDAO;
	private MemoryTestDAO<ReleaseDate> releaseDateDAO;
	private MemoryTestDAO<Universe> universeDAO;
	private MemoryTestDAO<GameSerie> gameSerieDAO;
	private MemoryTestDAO<StaffMember> staffMemberDAO;

	@Before
	public void setUp() throws Exception {
		entity = new Game.GameDigest();
		downloadLinkDAO = new MemoryTestDAO<>(DownloadLink.class);
		releaseDateDAO = new MemoryTestDAO<>(ReleaseDate.class);
		universeDAO = new MemoryTestDAO<>(Universe.class);
		gameSerieDAO = new MemoryTestDAO<>(GameSerie.class);
		staffMemberDAO = new MemoryTestDAO<>(StaffMember.class);
		entity.setDownloadLinkDAO(downloadLinkDAO);
		entity.setReleaseDateDAO(releaseDateDAO);
		entity.setUniverseDAO(universeDAO);
		entity.setGameSerieDAO(gameSerieDAO);
		entity.setStaffMembersDAO(staffMemberDAO);
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test
	public void testFromBase_Serie() throws Exception {
		Game game = callFromBase();
		URI expected = new RESTHelper().getRESTUri(URI.create(REST_URI), GameSerie.class, game.getSerie());
		assertEquals(expected, entity.serie);
	}

	@Test
	public void testToBase_Serie() throws Exception {
		Game game = callToBase();
		URI toTest = new RESTHelper().getRESTUri(URI.create(REST_URI), GameSerie.class, game.getSerie());
		assertEquals(entity.serie, toTest);
	}

	private Game callFromBase() {
		URI baseUri = URI.create(REST_URI);
		Game game = GAME_FACTORY.getNew();
		entity.fromBase(baseUri, game);
		return game;
	}

	public Game callToBase() throws Exception {
		Game game = GAME_FACTORY.getNew(true, true);

		ClassWrapper<? extends Game> gameClass = new ClassWrapper<>(game.getClass());

		FieldWrapper releaseDatesField = gameClass.getField("releaseDates");
		FieldWrapper staffMembersField = gameClass.getField("staffMembers");
		Collection<ReleaseDate> releaseDates = (Collection<ReleaseDate>) releaseDatesField.get(game);
		Collection<StaffMember> staffMembers = (Collection<StaffMember>) staffMembersField.get(game);

		new EntityHelper().assignIdTo(game.getSerie());

		for (DownloadLink downloadLink : game.getDownloadLinks()) {
			downloadLinkDAO.create(downloadLink);
		}

		for (Universe universe : game.getUniverses()) {
			universeDAO.create(universe);
		}

		for (ReleaseDate releaseDate : releaseDates) {
			releaseDateDAO.create(releaseDate);
		}

		for (StaffMember staffMember : staffMembers) {
			staffMemberDAO.create(staffMember);
		}

		gameSerieDAO.create(game.getSerie());

		entity.fromBase(URI.create(REST_URI), game);
		return entity.toBase();
	}

}
