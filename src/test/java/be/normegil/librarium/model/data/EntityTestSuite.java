package be.normegil.librarium.model.data;

import be.normegil.librarium.model.data.fake.FakeEntity;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.EntityHelper;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import java.util.UUID;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		UTEntityComparator.class,
		UTEntityHelperSafety.class,
		UTEntityHelper.class,
		UTEntityDigestSafety.class,
		UTEntityDigest.class,
		UTEntityDigestEquality.class
})
public class EntityTestSuite implements DataFactory<Entity> {

	public static final UUID ID = UUID.fromString("40d46c43-0700-4f38-8f4a-dcfa8186195e");

	@Override
	public Entity getDefault() {
		return getDefault(true, false);
	}

	@Override
	public Entity getNew() {
		return getNew(true, false);
	}

	@Override
	public Entity getDefault(final boolean withLink, final boolean withIds) {
		Entity entity = new FakeEntity();
		if (withIds) {
			new EntityHelper().setId(entity, ID);
		}
		return entity;
	}

	@Override
	public Entity getNew(final boolean withLink, final boolean withIds) {
		Entity entity = new FakeEntity();
		if (withIds) {
			new EntityHelper().setId(entity, UUID.randomUUID());
		}
		return entity;
	}
}