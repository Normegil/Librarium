package be.normegil.librarium.model.data.video;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.model.data.AbstractDataEqualityTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class UTSerieEquality extends AbstractDataEqualityTest<Serie> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Serie> FACTORY = FactoryRepository.get(Serie.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<SerieSeason> SERIE_SEASON_FACTORY = FactoryRepository.get(SerieSeason.class);

	@Override
	protected Serie getNewEntity() {
		return FACTORY.getDefault();
	}

	@Test
	public void testUnchanged() throws Exception {
		Serie entity = getEntity();
		Serie copy = new Serie(entity);
		assertEquals(entity, copy);
	}

	@Test
	public void testDifferentSeason() throws Exception {
		Serie entity = getEntity();
		Serie copy = new Serie(entity);
		entity.addSeason(SERIE_SEASON_FACTORY.getNew());
		assertEquals(entity, copy);
	}
}