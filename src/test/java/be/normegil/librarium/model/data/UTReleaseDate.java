package be.normegil.librarium.model.data;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class UTReleaseDate {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<ReleaseDate> FACTORY = FactoryRepository.get(ReleaseDate.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Media> MEDIA_FACTORY = FactoryRepository.get(Media.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Support> SUPPORT_FACTORY = FactoryRepository.get(Support.class);
	private static final int DAYS_TO_ADD = 1;
	private ReleaseDate entity;

	@Before
	public void setUp() throws Exception {
		entity = FACTORY.getDefault();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test
	public void testConstructor_Media() throws Exception {
		Media media = MEDIA_FACTORY.getNew();
		Support support = SUPPORT_FACTORY.getNew();
		ReleaseDate copy = new ReleaseDate(media, support, LocalDate.now());
		assertEquals(media, copy.getMedia());
	}

	@Test
	public void testConstructor_Support() throws Exception {
		Media media = MEDIA_FACTORY.getNew();
		Support support = SUPPORT_FACTORY.getNew();
		ReleaseDate copy = new ReleaseDate(media, support, LocalDate.now());
		assertEquals(support, copy.getSupport());
	}

	@Test
	public void testCopyConstructor() throws Exception {
		ReleaseDate copy = new ReleaseDate(entity);
		assertEquals(entity, copy);
	}

	@Test
	public void testSetDate() throws Exception {
		LocalDate date = entity.getDate().plusDays(DAYS_TO_ADD);
		entity.setDate(date);
		assertEquals(date, entity.getDate());
	}

	@Test
	public void testToString() throws Exception {
		ReleaseDate releaseDate = FACTORY.getDefault();
		assertEquals("ReleaseDate[media=FakeMedia[id=<null>,title=Title],support=Support[id=<null>,name=Name],date=2014-08-30]", releaseDate.toString());
	}
}