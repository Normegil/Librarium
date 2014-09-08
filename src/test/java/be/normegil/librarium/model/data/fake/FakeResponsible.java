package be.normegil.librarium.model.data.fake;

import be.normegil.librarium.model.data.people.Responsible;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@javax.persistence.Entity
@Access(AccessType.FIELD)
public class FakeResponsible extends Responsible implements Comparable<Responsible> {
	public FakeResponsible(@NotNull @Valid final Responsible entity) {
		super(entity);
	}

	protected FakeResponsible(@NotNull @Valid final Init<?> init) {
		super(init);
	}

	private FakeResponsible() {
		super();
	}

	public static Builder builder() {
		return new FakeResponsible.Builder();
	}

	public abstract static class Init<E extends Init<E>> extends Responsible.Init<E> {

		public Responsible build() {
			return new FakeResponsible(this);
		}

	}

	public static class Builder extends FakeResponsible.Init<Builder> {
		@Override
		protected Builder self() {
			return this;
		}
	}
}
