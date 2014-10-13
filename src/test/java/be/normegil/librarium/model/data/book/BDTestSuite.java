package be.normegil.librarium.model.data.book;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		UTBDSafety.class,
		UTBD.class,
		UTBDEquality.class,
		UTBDComparator.class,
		UTBDBuilderSafety.class,
		UTBDBuilder.class,
		UTBDDatabaseSupport.class
})
public class BDTestSuite implements DataFactory<BD> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<AbstractBD> ABSTRACT_BD_FACTORY = FactoryRepository.get(AbstractBD.class);

	@Override
	public BD getDefault() {
		return getDefault(true);
	}

	@Override
	public BD getNew() {
		return getNew(true);
	}

	@Override
	public BD getDefault(boolean withLink) {
		return BD.builder()
				.from(ABSTRACT_BD_FACTORY.getDefault(withLink))
				.build();
	}

	@Override
	public BD getNew(boolean withLink) {
		BD entity = BD.builder()
				.from(ABSTRACT_BD_FACTORY.getNew(withLink))
				.build();
		return entity;
	}
}