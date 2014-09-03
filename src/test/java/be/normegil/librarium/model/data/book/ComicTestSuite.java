package be.normegil.librarium.model.data.book;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.TestResult;
import be.normegil.librarium.tool.comparator.PropertyComparatorHelper;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		UTComicSafety.class,
		UTComic.class,
		UTComicEquality.class,
		UTComicComparator.class,
		UTComicBuilderSafety.class,
		UTComicBuilder.class,
		UTComicDatabaseSupport.class
})
public class ComicTestSuite implements DataFactory<Comic> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<AbstractBD> ABSTRACT_BD_FACTORY = FactoryRepository.get(AbstractBD.class);

	@Override
	public Comic getNew() {
		return getNew(true);
	}

	@Override
	public Comic getNext() {
		return getNext(true);
	}

	@Override
	public Comic getNew(boolean withLink) {
		return Comic.builder()
				.from(ABSTRACT_BD_FACTORY.getNew(withLink))
				.build();
	}

	@Override
	public Comic getNext(boolean withLink) {
		return Comic.builder()
				.from(ABSTRACT_BD_FACTORY.getNext(withLink))
				.build();
	}
}