package be.normegil.librarium.model.data;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.model.data.fake.FakeMedia;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.model.data.AbstractDataEqualityTest;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class UTMediaEquality extends AbstractDataEqualityTest<Media> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Media> FACTORY = FactoryRepository.get(Media.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Universe> UNIVERSE_FACTORY = FactoryRepository.get(Universe.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Support> SUPPORT_FACTORY = FactoryRepository.get(Support.class);

	@Override
	protected Media getNewEntity() {
		return FACTORY.getDefault();
	}

	@Test
	public void testUnchanged() throws Exception {
		Media entity = getEntity();
		Media copy = new FakeMedia(entity);
		assertEquals(entity, copy);
	}

	@Test
	public void testDifferentUniverse() throws Exception {
		Media entity = getEntity();
		Media copy = new FakeMedia(entity);
		entity.addUniverse(UNIVERSE_FACTORY.getNew());
		assertNotEquals(entity, copy);
	}

	@Test
	public void testDifferentReleaseDate() throws Exception {
		Media entity = getEntity();
		Media copy = new FakeMedia(entity);
		entity.addReleaseDate(SUPPORT_FACTORY.getNew(), LocalDate.now());
		assertEquals(entity, copy);
	}
}