package be.normegil.librarium.model.data.book;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.model.data.fake.FakeAbstractBD;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UTAbstractBD {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<AbstractBD> FACTORY = FactoryRepository.get(AbstractBD.class);
	private AbstractBD entity;

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
		AbstractBD copy = new FakeAbstractBD(entity);
		assertEquals(entity, copy);
	}

	@Test
	public void testSetIssueNumber() throws Exception {
		long issueNumber = entity.getIssueNumber() + 1;
		entity.setIssueNumber(issueNumber);
		assertEquals(issueNumber, (Object) entity.getIssueNumber());
	}
}
