package be.normegil.librarium.model.data.book;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.model.data.fake.FakeAbstractBD;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.AbstractDataEqualityTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class UTAbstractBDEquality extends AbstractDataEqualityTest<AbstractBD> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<AbstractBD> FACTORY = FactoryRepository.get(AbstractBD.class);

	@Override
	protected AbstractBD getNewEntity() {
		return FACTORY.getNew();
	}

	@Test
	public void testUnchanged() throws Exception {
		AbstractBD entity = getEntity();
		AbstractBD copy = new FakeAbstractBD(entity);
		assertEquals(entity, copy);
	}

	@Test
	public void testDifferentIssueNumber() throws Exception {
		AbstractBD entity = getEntity();
		AbstractBD copy = new FakeAbstractBD(entity);
		entity.setIssueNumber(entity.getIssueNumber() + 1);
		assertNotEquals(entity, copy);
	}
}