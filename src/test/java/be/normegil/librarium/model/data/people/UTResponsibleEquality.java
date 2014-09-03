package be.normegil.librarium.model.data.people;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.model.data.fake.FakeResponsible;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.AbstractDataEqualityTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class UTResponsibleEquality extends AbstractDataEqualityTest<Responsible> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Responsible> FACTORY = FactoryRepository.get(Responsible.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<URL> URL_FACTORY = FactoryRepository.get(URL.class);

	@Override
	protected Responsible getNewEntity() {
		return FACTORY.getNew();
	}

	@Test
	public void testUnchanged() throws Exception {
		Responsible entity = getEntity();
		Responsible copy = new FakeResponsible(entity);
		assertEquals(entity, copy);
	}

	@Test
	public void testDifferentName() throws Exception {
		Responsible entity = getEntity();
		Responsible copy = new FakeResponsible(entity);
		entity.setName(entity.getName() + 1);
		assertNotEquals(entity, copy);
	}

	@Test
	public void testDifferentWikipediaPage() throws Exception {
		Responsible entity = getEntity();
		Responsible copy = new FakeResponsible(entity);
		entity.setWikipediaPage(URL_FACTORY.getNext());
		assertNotEquals(entity, copy);
	}
}