package be.normegil.librarium.model.data.people;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.model.data.fake.FakeResponsible;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

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
	private static long index = 0L;

	@Override
	public Responsible getDefault() {
		return getDefault(true);
	}

	@Override
	public Responsible getNew() {
		return getNew(true);
	}

	@Override
	public Responsible getDefault(boolean withLink) {
		return FakeResponsible.builder()
				.setName(NAME)
				.setWikipediaPage(URL_FACTORY.getDefault())
				.build();
	}

	@Override
	public Responsible getNew(boolean withLink) {
		Responsible entity = FakeResponsible.builder()
				.setName(NAME + index)
				.setWikipediaPage(URL_FACTORY.getNew())
				.build();

		index += 1;
		return entity;
	}
}