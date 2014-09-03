package be.normegil.librarium.model.data.people;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.AbstractDataEqualityTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UTEnterpriseEquality extends AbstractDataEqualityTest<Enterprise> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Enterprise> FACTORY = FactoryRepository.get(Enterprise.class);

	@Override
	protected Enterprise getNewEntity() {
		return FACTORY.getNew();
	}

	@Test
	public void testUnchanged() throws Exception {
		Enterprise entity = getEntity();
		Enterprise copy = new Enterprise(entity);
		assertEquals(entity, copy);
	}
}