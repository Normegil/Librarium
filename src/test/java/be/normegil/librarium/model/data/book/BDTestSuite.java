package be.normegil.librarium.model.data.book;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.EntityHelper;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import java.util.UUID;

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
	private static final UUID DEFAULT_ID = UUID.fromString("");

	@Override
	public BD getDefault() {
		return getDefault(true, false);
	}

	@Override
	public BD getNew() {
		return getNew(true, false);
	}

	@Override
	public BD getDefault(final boolean withLink, final boolean withIds) {
		BD bd = BD.builder()
				.from(ABSTRACT_BD_FACTORY.getDefault(withLink, withIds))
				.build();
		if (withIds) {
			new EntityHelper().setId(bd, DEFAULT_ID);
		}
		return bd;
	}

	@Override
	public BD getNew(final boolean withLink, final boolean withIds) {
		BD entity = BD.builder()
				.from(ABSTRACT_BD_FACTORY.getNew(withLink, withIds))
				.build();
		if (withIds) {
			new EntityHelper().setId(entity, DEFAULT_ID);
		}
		return entity;
	}
}