package be.normegil.librarium.model.data.people;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.Constants;
import be.normegil.librarium.model.data.Media;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.model.data.AbstractDataComparableTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UTStaffMemberComparator extends AbstractDataComparableTest<StaffMember> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<StaffMember> FACTORY = FactoryRepository.get(StaffMember.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Responsible> RESPONSIBLE_FACTORY = FactoryRepository.get(Responsible.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Media> MEDIA_FACTORY = FactoryRepository.get(Media.class);

	@Override
	protected StaffMember getNewEntity() {
		return FACTORY.getDefault();
	}

	@Override
	protected int compare(final StaffMember entity1, final StaffMember entity2) {
		return entity1.compareTo(entity2);
	}

	@Override
	public void testEquality() throws Exception {
		StaffMember entity = getEntity();
		StaffMember copy = new StaffMember(entity);
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.EQUALS, compare(entity, copy)));
		assertEquals(compare(entity, copy), entity.compareTo(copy));
	}

	@Test
	public void testRole_First() throws Exception {
		StaffMember entity = getEntity();
		StaffMember copy = new StaffMember(entity);
		copy.setRole(StaffRole.COMPOSER);
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_FIRST, compare(entity, copy)));
	}

	@Test
	public void testRole_Second() throws Exception {
		StaffMember entity = getEntity();
		StaffMember copy = new StaffMember(entity);
		copy.setRole(StaffRole.COMPOSER);
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_SECOND, compare(copy, entity)));
	}

	@Test
	public void testResponsible_First() throws Exception {
		StaffMember entity = getEntity();
		StaffMember copy = new StaffMember(entity);
		copy.setResponsible(RESPONSIBLE_FACTORY.getNew());
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_FIRST, compare(entity, copy)));
	}

	@Test
	public void testResponsible_Second() throws Exception {
		StaffMember entity = getEntity();
		StaffMember copy = new StaffMember(entity);
		copy.setResponsible(RESPONSIBLE_FACTORY.getNew());
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_SECOND, compare(copy, entity)));
	}

	@Test
	public void testMedia_First() throws Exception {
		StaffMember entity = getEntity();
		StaffMember copy = new StaffMember(entity);
		copy.setMedia(MEDIA_FACTORY.getNew());
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_FIRST, compare(entity, copy)));
	}

	@Test
	public void testMedia_Second() throws Exception {
		StaffMember entity = getEntity();
		StaffMember copy = new StaffMember(entity);
		copy.setMedia(MEDIA_FACTORY.getNew());
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_SECOND, compare(copy, entity)));
	}
}
