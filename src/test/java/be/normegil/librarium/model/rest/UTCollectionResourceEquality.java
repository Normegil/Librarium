package be.normegil.librarium.model.rest;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.libraries.ClassWrapper;
import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.test.model.data.AbstractDataEqualityTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class UTCollectionResourceEquality extends AbstractDataEqualityTest<CollectionResource> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<CollectionResource> FACTORY = FactoryRepository.get(CollectionResource.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<URL> URL_FACTORY = FactoryRepository.get(URL.class);
	private static final ClassWrapper<CollectionResource> CLASS = new ClassWrapper<>(CollectionResource.class);

	@Override
	protected CollectionResource getNewEntity() {
		return FACTORY.getDefault();
	}

	@Test
	public void testUnchanged() throws Exception {
		CollectionResource entity = getEntity();
		CollectionResource copy = new CollectionResource(entity);
		assertEquals(entity, copy);
	}

	@Test
	public void testDifferentOffset() throws Exception {
		CollectionResource entity = getEntity();
		CollectionResource copy = CollectionResource.builder()
				.from(entity)
				.setOffset(entity.getOffset() + 1)
				.build();
		assertNotEquals(entity, copy);
	}

	@Test
	public void testDifferentLimit() throws Exception {
		CollectionResource entity = getEntity();
		CollectionResource copy = CollectionResource.builder()
				.from(entity)
				.setLimit(entity.getLimit() + 1)
				.build();
		assertNotEquals(entity, copy);
	}

	@Test
	public void testDifferentFirstLink() throws Exception {
		CollectionResource entity = getEntity();
		CollectionResource copy = new CollectionResource(entity);
		CLASS.getField("first").set(copy, URL_FACTORY.getNew());
		assertNotEquals(entity, copy);
	}

	@Test
	public void testDifferentLastLink() throws Exception {
		CollectionResource entity = getEntity();
		CollectionResource copy = new CollectionResource(entity);
		CLASS.getField("last").set(copy, URL_FACTORY.getNew());
		assertNotEquals(entity, copy);
	}

	@Test
	public void testDifferentPreviousLink() throws Exception {
		CollectionResource entity = getEntity();
		CollectionResource copy = new CollectionResource(entity);
		CLASS.getField("previous").set(copy, URL_FACTORY.getNew());
		assertNotEquals(entity, copy);
	}

	@Test
	public void testDifferentNextLink() throws Exception {
		CollectionResource entity = getEntity();
		CollectionResource copy = new CollectionResource(entity);
		CLASS.getField("next").set(copy, URL_FACTORY.getNew());
		assertNotEquals(entity, copy);
	}

	@Test
	public void testDifferentItems() throws Exception {
		CollectionResource entity = getEntity();
		CollectionResource copy = CollectionResource.builder()
				.from(entity)
				.addItem(URL_FACTORY.getNew())
				.build();
		assertNotEquals(entity, copy);
	}
}