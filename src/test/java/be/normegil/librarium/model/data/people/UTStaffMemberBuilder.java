package be.normegil.librarium.model.data.people;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.model.data.Media;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UTStaffMemberBuilder {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<StaffMember> STAFF_MEMBER_FACTORY = FactoryRepository.get(StaffMember.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Responsible> RESPONSIBLE_FACTORY = FactoryRepository.get(Responsible.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Media> MEDIA_FACTORY = FactoryRepository.get(Media.class);
	private StaffMember.Builder entity;

	@Before
	public void setUp() throws Exception {
		entity = StaffMember.builder();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test
	public void testFrom() throws Exception {
		StaffMember staffMember = STAFF_MEMBER_FACTORY.getNew();
		StaffMember copy = entity.from(staffMember).build();
		assertEquals(staffMember, copy);
	}

	@Test
	public void testSetRole() throws Exception {
		StaffMember staffMember = entity
				.setRole(StaffRole.COMPOSER)
				.build();
		assertEquals(StaffRole.COMPOSER, staffMember.getRole());
	}

	@Test
	public void testSetResponsible() throws Exception {
		Responsible responsible = RESPONSIBLE_FACTORY.getNew();
		StaffMember staffMember = entity
				.setResponsible(responsible)
				.build();
		assertEquals(responsible, staffMember.getResponsible());
	}

	@Test
	public void testSetMedia() throws Exception {
		Media media = MEDIA_FACTORY.getNew();
		StaffMember staffMember = entity
				.setMedia(media)
				.build();
		assertEquals(media, staffMember.getMedia());
	}
}