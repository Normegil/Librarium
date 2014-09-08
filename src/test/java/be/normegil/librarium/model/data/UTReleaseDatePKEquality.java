package be.normegil.librarium.model.data;

import be.normegil.librarium.tool.test.model.data.AbstractDataEqualityTest;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertNotEquals;

public class UTReleaseDatePKEquality extends AbstractDataEqualityTest<ReleaseDate.ReleaseDatePK> {

	private static final UUID MEDIA_ID = UUID.fromString("40d46c43-0700-4f38-8f4a-dcfa8186195e");
	private static final UUID SUPPORT_ID = UUID.fromString("72e608ea-202c-44aa-ae42-699130d8367c");

	@Override
	protected ReleaseDate.ReleaseDatePK getNewEntity() {
		return new ReleaseDate.ReleaseDatePK(MEDIA_ID, SUPPORT_ID);
	}

	@Test
	public void testDifferentMediaID() throws Exception {
		ReleaseDate.ReleaseDatePK entity = getNewEntity();
		ReleaseDate.ReleaseDatePK otherPK = new ReleaseDate.ReleaseDatePK(UUID.randomUUID(), entity.getSupportID());
		assertNotEquals(entity, otherPK);
	}

	@Test
	public void testDifferentSupportID() throws Exception {
		ReleaseDate.ReleaseDatePK entity = getNewEntity();
		ReleaseDate.ReleaseDatePK otherPK = new ReleaseDate.ReleaseDatePK(entity.getMediaID(), UUID.randomUUID());
		assertNotEquals(entity, otherPK);
	}
}
