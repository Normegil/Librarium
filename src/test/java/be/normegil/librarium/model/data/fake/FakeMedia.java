package be.normegil.librarium.model.data.fake;

import be.normegil.librarium.ApplicationProperties;
import be.normegil.librarium.model.data.Media;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@javax.persistence.Entity
@Access(AccessType.FIELD)
public class FakeMedia extends Media implements Comparable<Media> {
	public FakeMedia(@NotNull @Valid final Media entity) {
		super(entity);
	}

	protected FakeMedia(@NotNull @Valid final Init<?> init) {
		super(init);
	}

	public static Builder builder() {
		return new FakeMedia.Builder();
	}

	@Override
	public boolean equals(Object obj) {
		return new EqualsBuilder()
				.appendSuper(super.equals(obj))
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.appendSuper(super.hashCode())
				.toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ApplicationProperties.TO_STRING_STYLE)
				.appendSuper(super.toString())
				.toString();
	}

	public abstract static class Init<E extends Init<E>> extends Media.Init<E> {

		public Media build() {
			return new FakeMedia(this);
		}

	}

	public static class Builder extends FakeMedia.Init<Builder> {
		@Override
		protected Builder self() {
			return this;
		}
	}
}
