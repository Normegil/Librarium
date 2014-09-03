package be.normegil.librarium.model.data;

import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.EntityHelper;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		UTEntityComparator.class,
})
public class EntityTestSuite implements DataFactory<Entity> {

	private static Long index = 1L;

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
		new EntityHelper().setId(entity, 0L);
		return entity;
	}

	@Override
	public Entity getNext(final boolean withLink) {
		Entity entity = new Entity();
		new EntityHelper().setId(entity, index);
		index += 1;
		return entity;
	}
}