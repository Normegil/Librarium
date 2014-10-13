package be.normegil.librarium.model.data;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.model.data.game.Game;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.EntityHelper;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.model.data.AbstractDataEqualityTest;
import org.junit.Test;

import java.net.URI;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class UTEntityDigestEquality extends AbstractDataEqualityTest<Entity.EntityDigest> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<URL> URL_FACTORY = FactoryRepository.get(URL.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Game> ENTITY_FACTORY = FactoryRepository.get(Game.class);

	@Override
	protected Entity.EntityDigest getNewEntity() {
		return new Entity.EntityDigest();
	}

	@Test
	public void testUnchanged() throws Exception {
		Entity entity = ENTITY_FACTORY.getNew();
		URL url = URL_FACTORY.getNew();
		URI baseURI = url.toURI();
		Entity.EntityDigest digest1 = new Entity.EntityDigest();
		Entity.EntityDigest digest2 = new Entity.EntityDigest();
		digest1.fromBase(baseURI, entity);
		digest2.fromBase(baseURI, entity);
		assertEquals(digest1, digest2);
	}

	@Test
	public void testDifferentID() throws Exception {
		URL url = URL_FACTORY.getNew();
		URI baseURI = url.toURI();
		Entity.EntityDigest digest1 = new Entity.EntityDigest();
		Entity.EntityDigest digest2 = new Entity.EntityDigest();
		Entity entity = ENTITY_FACTORY.getNew();
		digest1.fromBase(baseURI, entity);
		new EntityHelper().setId(entity, UUID.randomUUID());
		digest2.fromBase(baseURI, entity);
		assertNotEquals(digest1, digest2);
	}

	@Test
	public void testDifferentURL() throws Exception {
		Entity.EntityDigest digest1 = new Entity.EntityDigest();
		Entity.EntityDigest digest2 = new Entity.EntityDigest();
		Entity entity = ENTITY_FACTORY.getNew();
		digest1.fromBase(URL_FACTORY.getNew().toURI(), entity);
		digest2.fromBase(URL_FACTORY.getNew().toURI(), entity);
		assertNotEquals(digest1, digest2);
	}
}