package be.normegil.librarium.model.data.book;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.model.data.fake.FakeAbstractBD;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.EntityHelper;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import java.util.UUID;

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
	private static final UUID DEFAULT_ID = UUID.fromString("4a4876c6-3caa-42d1-a726-782a9da9036d");
	private static long index = 0L;

	@Override
	public AbstractBD getDefault() {
		return getDefault(true, false);
	}

	@Override
	public AbstractBD getNew() {
		return getNew(true, false);
	}

	@Override
	public AbstractBD getDefault(final boolean withLink, final boolean withIds) {
		AbstractBD bd = FakeAbstractBD.builder()
				.from(BOOK_FACTORY.getDefault(withLink, withIds))
				.setIssueNumber(1L)
				.build();
		if (withIds) {
			new EntityHelper().setId(bd, DEFAULT_ID);
		}
		return bd;
	}

	@Override
	public AbstractBD getNew(final boolean withLink, final boolean withIds) {
		AbstractBD entity = FakeAbstractBD.builder()
				.from(BOOK_FACTORY.getNew(withLink, withIds))
				.setIssueNumber(index)
				.build();
		index += 1;
		if (withIds) {
			new EntityHelper().setId(entity, DEFAULT_ID);
		}
		return entity;
	}
}