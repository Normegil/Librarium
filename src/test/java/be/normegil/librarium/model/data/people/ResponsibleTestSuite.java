package be.normegil.librarium.model.data.people;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.model.data.fake.FakeResponsible;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.EntityHelper;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import java.util.UUID;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		UTResponsibleSafety.class,
		UTResponsible.class,
		UTResponsibleEquality.class,
		UTResponsibleComparator.class,
		UTResponsibleBuilderSafety.class,
		UTResponsibleBuilder.class,
})
public class ResponsibleTestSuite implements DataFactory<Responsible> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<URL> URL_FACTORY = FactoryRepository.get(URL.class);
	private static final String NAME = "Name";
	private static final UUID DEFAULT_ID = UUID.fromString("33701a05-ce59-46b0-8bbb-7d5f7aec773b");
	private static long index = 0L;

	@Override
	public Responsible getDefault() {
		return getDefault(true, false);
	}

	@Override
	public Responsible getNew() {
		return getNew(true, false);
	}

	@Override
	public Responsible getDefault(final boolean withLink, final boolean withIds) {
		Responsible responsible = FakeResponsible.builder()
				.setName(NAME)
				.setWikipediaPage(URL_FACTORY.getDefault())
				.build();
		if (withIds) {
			new EntityHelper().setId(responsible, DEFAULT_ID);
		}
		return responsible;
	}

	@Override
	public Responsible getNew(final boolean withLink, final boolean withIds) {
		Responsible entity = FakeResponsible.builder()
				.setName(NAME + index)
				.setWikipediaPage(URL_FACTORY.getNew())
				.build();

		index += 1;
		if (withIds) {
			new EntityHelper().setId(entity, DEFAULT_ID);
		}
		return entity;
	}
}