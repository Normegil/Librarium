package be.normegil.librarium.model.data;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.model.data.AbstractDataEqualityTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class UTUniverseEquality extends AbstractDataEqualityTest<Universe> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Universe> FACTORY = FactoryRepository.get(Universe.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Media> MEDIA_FACTORY = FactoryRepository.get(Media.class);

	@Override
	protected Universe getNewEntity() {
		return FACTORY.getDefault();
	}

	@Test
	public void testUnchanged() throws Exception {
		Universe entity = getEntity();
		Universe copy = new Universe(entity);
		assertEquals(entity, copy);
	}

	@Test
	public void testDifferentName() throws Exception {
		Universe entity = getEntity();
		Universe copy = new Universe(entity);
		entity.setName(entity.getName() + 1);
		assertNotEquals(entity, copy);
	}

	@Test
	public void testDifferentDescription() throws Exception {
		Universe entity = getEntity();
		Universe copy = new Universe(entity);
		entity.setDescription(entity.getDescription() + 1);
		assertNotEquals(entity, copy);
	}

	@Test
	public void testDifferentMedia() throws Exception {
		Universe entity = getEntity();
		Universe copy = new Universe(entity);
		entity.addMedia(MEDIA_FACTORY.getNew());
		assertEquals(entity, copy);
	}
}