package be.normegil.librarium.model.data;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.model.data.AbstractDataEqualityTest;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class UTReleaseDateEquality extends AbstractDataEqualityTest<ReleaseDate> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<ReleaseDate> FACTORY = FactoryRepository.get(ReleaseDate.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Media> MEDIA_FACTORY = FactoryRepository.get(Media.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Support> SUPPORT_FACTORY = FactoryRepository.get(Support.class);

	@Override
	protected ReleaseDate getNewEntity() {
		return FACTORY.getDefault();
	}

	@Test
	public void testUnchanged() throws Exception {
		ReleaseDate entity = getEntity();
		ReleaseDate copy = new ReleaseDate(entity);
		assertEquals(entity, copy);
	}

	@Test
	public void testDifferentMedia() throws Exception {
		ReleaseDate entity = getEntity();
		ReleaseDate copy = new ReleaseDate(MEDIA_FACTORY.getNew(), entity.getSupport(), entity.getDate());
		assertNotEquals(entity, copy);
	}

	@Test
	public void testDifferentSupport() throws Exception {
		ReleaseDate entity = getEntity();
		ReleaseDate copy = new ReleaseDate(entity.getMedia(), SUPPORT_FACTORY.getNew(), entity.getDate());
		assertNotEquals(entity, copy);
	}

	@Test
	public void testDifferentDate() throws Exception {
		ReleaseDate entity = getEntity();
		ReleaseDate copy = new ReleaseDate(entity);
		entity.setDate(LocalDate.of(1990, Month.AUGUST, 1));
		assertNotEquals(entity, copy);
	}
}