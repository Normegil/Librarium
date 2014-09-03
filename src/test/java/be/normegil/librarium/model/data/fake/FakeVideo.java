package be.normegil.librarium.model.data.fake;

import be.normegil.librarium.model.data.video.Video;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@javax.persistence.Entity
@Access(AccessType.FIELD)
public class FakeVideo extends Video implements Comparable<Video>{

	public FakeVideo(@NotNull @Valid final Video entity) {
		super(entity);
	}

	public FakeVideo(@NotNull @Valid final Init<?> init) {
		super(init);
	}

	public static Builder builder() {
		return new FakeVideo.Builder();
	}

	public abstract static class Init<E extends Init<E>> extends Video.Init<E> {

		public Video build() {
			return new FakeVideo(this);
		}

	}

	public static class Builder extends FakeVideo.Init<Builder> {
		@Override
		protected Builder self() {
			return this;
		}
	}
}
