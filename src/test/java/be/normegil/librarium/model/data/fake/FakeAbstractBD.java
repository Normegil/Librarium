package be.normegil.librarium.model.data.fake;

import be.normegil.librarium.model.data.book.AbstractBD;
import be.normegil.librarium.model.data.book.Book;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@javax.persistence.Entity
@Access(AccessType.FIELD)
public class FakeAbstractBD extends AbstractBD {

	public FakeAbstractBD(@NotNull @Valid final AbstractBD entity) {
		super(entity);
	}

	public FakeAbstractBD(@NotNull @Valid final Init<?> init) {
		super(init);
	}

	public static Builder builder() {
		return new FakeAbstractBD.Builder();
	}

	public abstract static class Init<E extends Init<E>> extends AbstractBD.Init<E> {

		public AbstractBD build() {
			return new FakeAbstractBD(this);
		}

	}

	public static class Builder extends FakeAbstractBD.Init<Builder> {
		@Override
		protected Builder self() {
			return this;
		}
	}
}
