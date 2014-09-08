package be.normegil.librarium.libraries;

import be.normegil.librarium.Constants;
import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.model.data.AbstractDataComparatorTest;
import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.assertTrue;

public class UTURLComparator extends AbstractDataComparatorTest<URL> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<URL> FACTORY = FactoryRepository.get(URL.class);
	private static final Comparator<URL> COMPARATOR = new URL.Comparator();
	private static final String ALTERNATIVE_PROTOCOL = "ftp";

	@Override
	protected URL getNewEntity() {
		return FACTORY.getNew();
	}

	@Override
	protected Comparator<URL> getComparator() {
		return new URL.Comparator();
	}

	@Override
	protected int compare(final URL entity1, final URL entity2) {
		return COMPARATOR.compare(entity1, entity2);
	}

	@Override
	public void testEquality() throws Exception {
		URL entity = getEntity();
		URL copy = new URL(entity);
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.EQUALS, compare(entity, copy)));
	}

	@Test
	public void testProtocol_First() throws Exception {
		URL entity = getEntity();
		URL otherEntity = new URL(ALTERNATIVE_PROTOCOL, entity.getHost(), entity.getPort(), entity.getFilePath());
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_FIRST, compare(otherEntity, entity)));
	}

	@Test
	public void testProtocol_Second() throws Exception {
		URL entity = getEntity();
		URL otherEntity = new URL(ALTERNATIVE_PROTOCOL, entity.getHost(), entity.getPort(), entity.getFilePath());
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_SECOND, compare(entity, otherEntity)));
	}

	@Test
	public void testHost_First() throws Exception {
		URL entity = getEntity();
		URL otherEntity = new URL(entity.getProtocol(), entity.getHost() + 1, entity.getPort(), entity.getFilePath());
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_FIRST, compare(entity, otherEntity)));
	}

	@Test
	public void testHost_Second() throws Exception {
		URL entity = getEntity();
		URL otherEntity = new URL(entity.getProtocol(), entity.getHost() + 1, entity.getPort(), entity.getFilePath());
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_SECOND, compare(otherEntity, entity)));
	}

	@Test
	public void testPort_First() throws Exception {
		URL entity = getEntity();
		URL otherEntity = new URL(entity.getProtocol(), entity.getHost(), entity.getPort() + 1, entity.getFilePath());
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_FIRST, compare(entity, otherEntity)));
	}

	@Test
	public void testPort_Second() throws Exception {
		URL entity = getEntity();
		URL otherEntity = new URL(entity.getProtocol(), entity.getHost(), entity.getPort() + 1, entity.getFilePath());
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_SECOND, compare(otherEntity, entity)));
	}

	@Test
	public void testFilePath_First() throws Exception {
		URL entity = getEntity();
		URL otherEntity = new URL(entity.getProtocol(), entity.getHost(), entity.getPort(), entity.getFilePath() + 1);
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_FIRST, compare(entity, otherEntity)));
	}

	@Test
	public void testFilePath_Second() throws Exception {
		URL entity = getEntity();
		URL otherEntity = new URL(entity.getProtocol(), entity.getHost(), entity.getPort(), entity.getFilePath() + 1);
		assertTrue(getComparatorHelper().testComparatorResult(Constants.Comparator.PRIORITY_SECOND, compare(otherEntity, entity)));
	}
}
