package be.normegil.librarium.model.data;

import be.normegil.librarium.tool.test.AbstractDataEqualityTest;
import org.junit.Test;

import static org.junit.Assert.assertNotEquals;

public class UTReleaseDatePKEquality extends AbstractDataEqualityTest<ReleaseDate.ReleaseDatePK> {

	private long index = 0L;

	@Override
	protected ReleaseDate.ReleaseDatePK getNewEntity() {
		ReleaseDate.ReleaseDatePK releaseDatePK = new ReleaseDate.ReleaseDatePK(index, index + 1);
		index += 1;
		return releaseDatePK;
	}

	@Test
	public void testDifferentMediaID() throws Exception {
		ReleaseDate.ReleaseDatePK entity = getNewEntity();
		ReleaseDate.ReleaseDatePK otherPK = new ReleaseDate.ReleaseDatePK(entity.getMediaID() + 1, entity.getSupportID());
		assertNotEquals(entity, otherPK);
	}

	@Test
	public void testDifferentSupportID() throws Exception {
		ReleaseDate.ReleaseDatePK entity = getNewEntity();
		ReleaseDate.ReleaseDatePK otherPK = new ReleaseDate.ReleaseDatePK(entity.getMediaID(), entity.getSupportID() + 1);
		assertNotEquals(entity, otherPK);
	}
}
