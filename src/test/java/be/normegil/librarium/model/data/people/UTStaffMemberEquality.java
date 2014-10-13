package be.normegil.librarium.model.data.people;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.model.data.video.Video;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.model.data.AbstractDataEqualityTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class UTStaffMemberEquality extends AbstractDataEqualityTest<StaffMember> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<StaffMember> FACTORY = FactoryRepository.get(StaffMember.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Responsible> RESPONSIBLE_FACTORY = FactoryRepository.get(Responsible.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Video> VIDEO_FACTORY = FactoryRepository.get(Video.class);

	@Override
	protected StaffMember getNewEntity() {
		return FACTORY.getDefault();
	}

	@Test
	public void testUnchanged() throws Exception {
		StaffMember entity = getEntity();
		StaffMember copy = new StaffMember(entity);
		assertEquals(entity, copy);
	}

	@Test
	public void testDifferentRole() throws Exception {
		StaffMember entity = getEntity();
		StaffMember copy = new StaffMember(entity);
		entity.setRole(StaffRole.COMPOSER);
		assertNotEquals(entity, copy);
	}

	@Test
	public void testDifferentResponsible() throws Exception {
		StaffMember entity = getEntity();
		StaffMember copy = new StaffMember(entity);
		entity.setResponsible(RESPONSIBLE_FACTORY.getNew());
		assertNotEquals(entity, copy);
	}

	@Test
	public void testDifferentVideo() throws Exception {
		StaffMember entity = getEntity();
		StaffMember copy = new StaffMember(entity);
		entity.setMedia(VIDEO_FACTORY.getNew());
		assertNotEquals(entity, copy);
	}
}