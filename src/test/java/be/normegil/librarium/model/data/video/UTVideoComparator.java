package be.normegil.librarium.model.data.video;

import be.normegil.librarium.Constants;
import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.model.data.fake.FakeVideo;
import be.normegil.librarium.model.data.people.Responsible;
import be.normegil.librarium.model.data.people.Role;
import be.normegil.librarium.model.data.people.StaffMember;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.AbstractDataComparableTest;
import org.junit.Test;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UTVideoComparator extends AbstractDataComparableTest<Video> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Video> FACTORY = FactoryRepository.get(Video.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Role> ROLE_FACTORY = FactoryRepository.get(Role.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Responsible> RESPONSIBLE_FACTORY = FactoryRepository.get(Responsible.class);

	@Override
	protected Video getNewEntity() {
		return FACTORY.getNew();
	}

	@Override
	protected int compare(final Video entity1, final Video entity2) {
		return entity1.compareTo(entity2);
	}

	@Override
	public void testEquality() throws Exception {
		Video entity = getEntity();
		Video copy = new FakeVideo(entity);
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.EQUALS, compare(entity, copy)));
		assertEquals(compare(entity, copy), entity.compareTo(copy));
	}

	@Test
	public void testDuration_First() throws Exception {
		Video entity = getEntity();
		Video copy = new FakeVideo(entity);
		copy.setDuration(entity.getDuration().plus(Duration.ofMinutes(1)));
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_FIRST, compare(entity, copy)));
	}

	@Test
	public void testDuration_Second() throws Exception {
		Video entity = getEntity();
		Video copy = new FakeVideo(entity);
		copy.setDuration(entity.getDuration().plus(Duration.ofMinutes(1)));
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_SECOND, compare(copy, entity)));
	}

	@Test
	public void testProducer_Equality() throws Exception {
		Video entity = getEntity();
		Video copy = new FakeVideo(entity);
		copy.addProducer(RESPONSIBLE_FACTORY.getNext());
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.EQUALS, compare(entity, copy)));
	}

	@Test
	public void testDirector_Equality() throws Exception {
		Video entity = getEntity();
		Video copy = new FakeVideo(entity);
		copy.addDirector(RESPONSIBLE_FACTORY.getNext());
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.EQUALS, compare(entity, copy)));
	}

	@Test
	public void testComposer_Equality() throws Exception {
		Video entity = getEntity();
		Video copy = new FakeVideo(entity);
		copy.addComposer(RESPONSIBLE_FACTORY.getNext());
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.EQUALS, compare(entity, copy)));
	}

	@Test
	public void testScenarist_Equality() throws Exception {
		Video entity = getEntity();
		Video copy = new FakeVideo(entity);
		copy.addScenarist(RESPONSIBLE_FACTORY.getNext());
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.EQUALS, compare(entity, copy)));
	}

	@Test
	public void testOtherStaffMember_Equality() throws Exception {
		Video entity = getEntity();
		Video copy = new FakeVideo(entity);
		copy.addOtherStaffMember(RESPONSIBLE_FACTORY.getNext());
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.EQUALS, compare(entity, copy)));
	}

	@Test
	public void testActor_Equality() throws Exception {
		Video entity = getEntity();
		Video copy = new FakeVideo(entity);
		copy.addActor(ROLE_FACTORY.getNext());
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.EQUALS, compare(entity, copy)));
	}
}
