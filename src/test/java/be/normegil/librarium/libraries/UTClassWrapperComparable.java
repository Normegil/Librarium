package be.normegil.librarium.libraries;

import be.normegil.librarium.Constants;
import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.model.data.Media;
import be.normegil.librarium.model.data.video.Video;
import be.normegil.librarium.tool.test.model.data.AbstractDataComparableTest;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class UTClassWrapperComparable extends AbstractDataComparableTest<ClassWrapper> {

	private static final java.lang.Class<Media> BASE_CLASS = Media.class;

	@Override
	protected ClassWrapper getNewEntity() {
		return new ClassWrapper<>(BASE_CLASS);
	}

	@Override
	@SuppressWarnings(WarningTypes.UNCHECKED_CALL)
	protected int compare(final ClassWrapper entity1, final ClassWrapper entity2) {
		return entity1.compareTo(entity2);
	}

	@Override
	public void testEquality() throws Exception {
		ClassWrapper<Media> otherEntity = new ClassWrapper<>(BASE_CLASS);
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.EQUALS, compare(getEntity(), otherEntity)));
	}

	@Test
	public void testSimpleName_First() throws Exception {
		ClassWrapper entity = getEntity();
		ClassWrapper<Video> otherEntity = new ClassWrapper<>(Video.class);
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_FIRST, compare(entity, otherEntity)));
	}

	@Test
	public void testSimpleName_Second() throws Exception {
		ClassWrapper entity = getEntity();
		ClassWrapper<Video> otherEntity = new ClassWrapper<>(Video.class);
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_SECOND, compare(otherEntity, entity)));
	}

	@Test
	public void testCanonicalName_First() throws Exception {
		ClassWrapper entity = new ClassWrapper<>(java.sql.Date.class);
		ClassWrapper otherEntity = new ClassWrapper<>(java.util.Date.class);
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_FIRST, compare(entity, otherEntity)));
	}

	@Test
	public void testCanonicalName_Second() throws Exception {
		ClassWrapper entity = new ClassWrapper<>(java.sql.Date.class);
		ClassWrapper otherEntity = new ClassWrapper<>(java.util.Date.class);
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_SECOND, compare(otherEntity, entity)));
	}
}
