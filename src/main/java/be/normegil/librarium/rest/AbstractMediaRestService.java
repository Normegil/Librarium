package be.normegil.librarium.rest;

import be.normegil.librarium.model.data.Media;
import be.normegil.librarium.model.data.Support;
import be.normegil.librarium.model.data.Universe;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

public abstract class AbstractMediaRestService<E extends Media> extends AbstractBaseMediaRestService<E> {

	@Override
	protected void update(final Media loadedEntity, final Media entity) {
		super.update(loadedEntity, entity);

		loadedEntity.clearUniverses();
		loadedEntity.addAllUniverses(entity.getUniverses());

		loadedEntity.clearReleaseDates();
		loadedEntity.addAllReleaseDates(entity.getReleaseDates());
	}

	@Override
	protected void updateNullCheck(final Media loadedEntity, final Media entity) {
		super.updateNullCheck(loadedEntity, entity);

		Set<Universe> universes = entity.getUniverses();
		if (universes != null) {
			loadedEntity.clearUniverses();
			loadedEntity.addAllUniverses(universes);
		}

		Map<Support, LocalDate> releaseDates = entity.getReleaseDates();
		if (releaseDates != null) {
			loadedEntity.clearReleaseDates();
			loadedEntity.addAllReleaseDates(releaseDates);
		}
	}
}
