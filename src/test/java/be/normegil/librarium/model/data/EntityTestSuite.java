package be.normegil.librarium.model.data;

import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.EntityHelper;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import java.util.UUID;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		UTEntityComparator.class,
})
public class EntityTestSuite implements DataFactory<Entity> {

	private static final UUID ID = UUID.fromString("40d46c43-0700-4f38-8f4a-dcfa8186195e");

	@Override
	public Entity getNew() {
		return getNew(true);
	}

	@Override
	public Entity getNext() {
		return getNext(true);
	}

	@Override
	public Entity getNew(final boolean withLink) {
		Entity entity = new Entity();
		new EntityHelper().setId(entity, ID);
		return entity;
	}

	@Override
	public Entity getNext(final boolean withLink) {
		Entity entity = new Entity();
		new EntityHelper().setId(entity, UUID.randomUUID());
		return entity;
	}
}