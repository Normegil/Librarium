package be.normegil.librarium.model.data.people;

import org.apache.commons.lang3.builder.CompareToBuilder;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@javax.persistence.Entity
@Access(AccessType.FIELD)
public class Enterprise extends Responsible implements Comparable<Enterprise>, Serializable {

	public Enterprise(@NotNull @Valid Enterprise entity) {
		super(entity);
	}

	protected Enterprise(@NotNull @Valid Init<?> init) {
		super(init);
	}

	//For JAXB
	private Enterprise() {
		super();
	}

	public static Builder builder() {
		return new Builder();
	}

	@Override
	public int compareTo(final Enterprise o) {
		return new CompareToBuilder()
				.appendSuper(super.compareTo(o))
				.toComparison();
	}

	public abstract static class Init<E extends Init<E>> extends Responsible.Init<E> {

		protected abstract E self();

		public E from(@NotNull @Valid Enterprise entity) {
			super.from(entity);
			return self();
		}

		public
		@Valid
		Enterprise build() {
			return new Enterprise(this);
		}
	}

	protected static class Builder extends Init<Builder> {
		@Override
		protected Builder self() {
			return this;
		}
	}
}
