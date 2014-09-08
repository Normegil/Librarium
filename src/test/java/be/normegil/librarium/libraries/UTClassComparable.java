package be.normegil.librarium.libraries;

import be.normegil.librarium.Constants;
import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.model.data.Media;
import be.normegil.librarium.model.data.video.Video;
import be.normegil.librarium.tool.test.model.data.AbstractDataComparableTest;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class UTClassComparable extends AbstractDataComparableTest<Class> {

	private static final java.lang.Class<Media> BASE_CLASS = Media.class;

	@Override
	protected Class getNewEntity() {
		return new Class<>(BASE_CLASS);
	}

	@Override
	@SuppressWarnings(WarningTypes.UNCHECKED_CALL)
	protected int compare(final Class entity1, final Class entity2) {
		return entity1.compareTo(entity2);
	}

	@Override
	public void testEquality() throws Exception {
		Class<Media> otherEntity = new Class<>(BASE_CLASS);
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.EQUALS, compare(getEntity(), otherEntity)));
	}

	@Test
	public void testSimpleName_First() throws Exception {
		Class entity = getEntity();
		Class<Video> otherEntity = new Class<>(Video.class);
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_FIRST, compare(entity, otherEntity)));
	}

	@Test
	public void testSimpleName_Second() throws Exception {
		Class entity = getEntity();
		Class<Video> otherEntity = new Class<>(Video.class);
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_SECOND, compare(otherEntity, entity)));
	}

	@Test
	public void testCanonicalName_First() throws Exception {
		Class entity = new Class<>(Class.class);
		Class<java.lang.Class> otherEntity = new Class<>(java.lang.Class.class);
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_FIRST, compare(entity, otherEntity)));
	}

	@Test
	public void testCanonicalName_Second() throws Exception {
		Class entity = new Class<>(Class.class);
		Class<java.lang.Class> otherEntity = new Class<>(java.lang.Class.class);
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_SECOND, compare(otherEntity, entity)));
	}
}
