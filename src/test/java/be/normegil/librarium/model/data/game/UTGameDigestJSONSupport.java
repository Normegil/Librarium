package be.normegil.librarium.model.data.game;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.model.data.AbstractParsingSupportTest;
import be.normegil.librarium.util.parser.Parser;

import java.net.URI;

public class UTGameDigestJSONSupport extends AbstractParsingSupportTest<Game.GameDigest> {

	public static final URI REST_URI = URI.create("http://localhost:9999/rest");
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Game> GAME_FACTORY = FactoryRepository.get(Game.class);

	public UTGameDigestJSONSupport() {
		super(Parser.DocumentType.JSON, Game.GameDigest.class);
	}

	@Override
	protected Game.GameDigest initEntity() {
		Game game = GAME_FACTORY.getNew();
		Game.GameDigest digest = new Game.GameDigest();
		digest.fromBase(REST_URI, game);
		return digest;
	}
}
