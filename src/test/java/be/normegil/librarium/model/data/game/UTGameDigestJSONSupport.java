package be.normegil.librarium.model.data.game;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.EntityHelper;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.model.data.AbstractParsingSupportTest;
import be.normegil.librarium.util.parser.Parser;

import java.net.URI;
import java.util.UUID;

public class UTGameDigestJSONSupport extends AbstractParsingSupportTest<Game.GameDigest> {

	public static final URI REST_URI = URI.create("http://localhost:9999/rest");
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Game> GAME_FACTORY = FactoryRepository.get(Game.class);
	public static final UUID GAME_ID = UUID.fromString("1399d978-3d3d-451f-bdb2-9cd88701e96c");

	public UTGameDigestJSONSupport() {
		super(Parser.DocumentType.JSON, Game.GameDigest.class);
	}

	@Override
	protected Game.GameDigest initEntity() {
		Game game = GAME_FACTORY.getNew();
		EntityHelper helper = new EntityHelper();
		helper.setId(game, GAME_ID);

		Game.GameDigest digest = new Game.GameDigest();
		digest.fromBase(REST_URI, game);
		return digest;
	}
}
