package be.normegil.librarium.model.data.book;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.model.data.fake.FakeAbstractBD;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		UTAbstractBDSafety.class,
		UTAbstractBD.class,
		UTAbstractBDEquality.class,
		UTAbstractBDComparator.class,
		UTAbstractBDBuilderSafety.class,
		UTAbstractBDBuilder.class,
})
public class AbstractBDTestSuite implements DataFactory<AbstractBD> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Book> BOOK_FACTORY = FactoryRepository.get(Book.class);
	private static long index = 0L;

	@Override
	public AbstractBD getDefault() {
		return getDefault(true);
	}

	@Override
	public AbstractBD getNew() {
		return getNew(true);
	}

	@Override
	public AbstractBD getDefault(boolean withLink) {
		return FakeAbstractBD.builder()
				.from(BOOK_FACTORY.getDefault(withLink))
				.setIssueNumber(1L)
				.build();
	}

	@Override
	public AbstractBD getNew(boolean withLink) {
		AbstractBD entity = FakeAbstractBD.builder()
				.from(BOOK_FACTORY.getNew(withLink))
				.setIssueNumber(index)
				.build();
		index += 1;
		return entity;
	}
}