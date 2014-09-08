package be.normegil.librarium.model.data.book;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.model.data.fake.FakeBook;
import be.normegil.librarium.model.data.people.Responsible;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.model.data.AbstractDataEqualityTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class UTBookEquality extends AbstractDataEqualityTest<Book> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Book> FACTORY = FactoryRepository.get(Book.class);
	                        @SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	                        private static final DataFactory<Responsible> RESPONSIBLE_FACTORY = FactoryRepository.get(Responsible.class);
	@Override
	protected Book getNewEntity() {
		return FACTORY.getNew();
	}

	@Test
	public void testUnchanged() throws Exception {
		Book entity = getEntity();
		Book copy = new FakeBook(entity);
		assertEquals(entity, copy);
	}

	@Test
	public void testDifferentAuthor() throws Exception {
		Book entity = getEntity();
		Book copy = new FakeBook(entity);
		entity.addAuthor(RESPONSIBLE_FACTORY.getNext());
		assertNotEquals(entity, copy);
	}
}