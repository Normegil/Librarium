package be.normegil.librarium.model.data.fake;

import be.normegil.librarium.model.data.book.Book;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@javax.persistence.Entity
@Access(AccessType.FIELD)
public class FakeBook extends Book implements Comparable<Book>{
	public FakeBook(@NotNull @Valid final Book entity) {
		super(entity);
	}

	protected FakeBook(@NotNull @Valid final Init<?> init) {
		super(init);
	}

	public static Builder builder() {
		return new FakeBook.Builder();
	}

	public abstract static class Init<E extends Init<E>> extends Book.Init<E> {

		public Book build() {
			return new FakeBook(this);
		}

	}

	public static class Builder extends FakeBook.Init<Builder> {
		@Override
		protected Builder self() {
			return this;
		}
	}
}
