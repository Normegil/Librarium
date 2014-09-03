package be.normegil.librarium.model.data.book;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.model.data.fake.FakeAbstractBD;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UTAbstractBDBuilder {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<AbstractBD> ABSTRACT_BD_FACTORY = FactoryRepository.get(AbstractBD.class);
	private FakeAbstractBD.Builder entity;

	@Before
	public void setUp() throws Exception {
		entity = FakeAbstractBD.builder();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test
	public void testFrom() throws Exception {
		AbstractBD abstractBD = ABSTRACT_BD_FACTORY.getNext();
		AbstractBD copy = entity.from(abstractBD).build();
		assertEquals(abstractBD, copy);
	}

	@Test
	public void testSetIssueNumber() throws Exception {
		long issueNumber = 42L;
		AbstractBD abstractBD = entity
				.setIssueNumber(issueNumber)
				.build();
		assertEquals(issueNumber, (Object) abstractBD.getIssueNumber());
	}
}