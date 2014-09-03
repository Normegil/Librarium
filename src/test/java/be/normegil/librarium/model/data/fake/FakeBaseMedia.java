package be.normegil.librarium.model.data.fake;

import be.normegil.librarium.model.data.BaseMedia;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@javax.persistence.Entity
@Access(AccessType.FIELD)
public class FakeBaseMedia extends BaseMedia implements Comparable<BaseMedia> {
	public FakeBaseMedia(@NotNull @Valid final BaseMedia entity) {
		super(entity);
	}

	protected FakeBaseMedia(@NotNull @Valid final Init<?> init) {
		super(init);
	}

	public static Builder builder() {
		return new FakeBaseMedia.Builder();
	}

	public abstract static class Init<E extends Init<E>> extends BaseMedia.Init<E> {

		public BaseMedia build() {
			return new FakeBaseMedia(this);
		}

	}

	public static class Builder extends FakeBaseMedia.Init<Builder> {
		@Override
		protected Builder self() {
			return this;
		}
	}
}
