package be.normegil.librarium.model.data.book;

import be.normegil.librarium.ApplicationProperties;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@javax.persistence.Entity
@Access(AccessType.FIELD)
public class Comic extends AbstractBD implements Comparable<Comic>, Serializable {

	public Comic(@NotNull @Valid Comic entity) {
		super(entity);
	}

	protected Comic(@NotNull @Valid Init<?> init) {
		super(init);
	}

	//For JAXB
	private Comic() {
		super();
	}

	public static Builder builder() {
		return new Builder();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ApplicationProperties.TO_STRING_STYLE)
				.appendSuper(super.toString())
				.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}
		Comic rhs = (Comic) obj;
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
	public int compareTo(final Comic o) {
		return new CompareToBuilder()
				.appendSuper(super.compareTo(o))
				.toComparison();
	}

	public abstract static class Init<E extends Init<E>> extends AbstractBD.Init<E> {

		protected abstract E self();

		public E from(@NotNull @Valid Comic entity) {
			super.from(entity);
			return self();
		}

		public
		@Valid
		Comic build() {
			return new Comic(this);
		}
	}

	protected static class Builder extends Init<Builder> {
		@Override
		protected Builder self() {
			return this;
		}
	}
}
