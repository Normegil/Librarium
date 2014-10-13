package be.normegil.librarium.model.data.people;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.model.data.video.Video;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UTStaffMember {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<StaffMember> FACTORY = FactoryRepository.get(StaffMember.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Responsible> RESPONSIBLE_FACTORY = FactoryRepository.get(Responsible.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Video> VIDEO_FACTORY = FactoryRepository.get(Video.class);
	private StaffMember entity;

	@Before
	public void setUp() throws Exception {
		entity = FACTORY.getDefault();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test
	public void testCopyConstructor() throws Exception {
		StaffMember copy = new StaffMember(entity);
		assertEquals(entity, copy);
	}

	@Test
	public void testSetRole() throws Exception {
		entity.setRole(StaffRole.COMPOSER);
		assertEquals(StaffRole.COMPOSER, entity.getRole());
	}

	@Test
	public void testSetResponsible() throws Exception {
		Responsible responsible = RESPONSIBLE_FACTORY.getNew();
		entity.setResponsible(responsible);
		assertEquals(responsible, entity.getResponsible());
	}

	@Test
	public void testSetVideo() throws Exception {
		Video video = VIDEO_FACTORY.getNew();
		entity.setMedia(video);
		assertEquals(video, entity.getMedia());
	}
}
