package be.normegil.librarium.model.data.book;

import be.normegil.librarium.ApplicationProperties;
import be.normegil.librarium.Constants;
import be.normegil.librarium.model.data.BaseMedia;
import be.normegil.librarium.util.CollectionComparator;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;
import java.util.SortedSet;
import java.util.TreeSet;

@javax.persistence.Entity
@Access(AccessType.FIELD)
public class BookSerie extends BaseMedia implements Comparable<BookSerie>, Serializable {

	private static final CollectionComparator COLLECTION_COMPARATOR = new CollectionComparator();
	@NotNull
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "serie")
	private Collection<Book> books = new TreeSet<>();

	public BookSerie(@NotNull @Valid BookSerie entity) {
		super(entity);
		addAllBooks(entity.getBooks());
	}

	protected BookSerie(@NotNull @Valid Init<?> init) {
		super(init);
		addAllBooks(init.books);
	}

	//For JAXB
	private BookSerie() {
		super();
	}

	public static Builder builder() {
		return new Builder();
	}

	public SortedSet<Book> getBooks() {
		return new TreeSet<>(books);
	}

	public void addAllBooks(@NotNull final Collection<Book> books) {
		for (Book book : books) {
			addBook(book);
		}
	}

	public void addBook(@NotNull @Valid final Book book) {
		books.add(book);
	}

	public void removeAllBooks(@NotNull final Collection<Book> books) {
		for (Book book : books) {
			removeBook(book);
		}
	}

	public void removeBook(@NotNull @Valid final Book book) {
		books.remove(book);
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ApplicationProperties.TO_STRING_STYLE)
				.appendSuper(super.toString())
				.append("books", books)
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
		BookSerie rhs = (BookSerie) obj;
		return new EqualsBuilder()
				.appendSuper(super.equals(obj))
				.append(this.books, rhs.books)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.appendSuper(super.hashCode())
				.append(books)
				.toHashCode();
	}

	@Override
	public int compareTo(final BookSerie o) {
		if (o != null) {
			return new CompareToBuilder()
					.appendSuper(COLLECTION_COMPARATOR.compare(getBooks(), o.getBooks()))
					.toComparison();
		} else {
			return Constants.Comparator.PRIORITY_SECOND;
		}
	}

	public abstract static class Init<E extends Init<E>> extends BaseMedia.Init<E> {

		private Collection<Book> books = new TreeSet<>();

		protected abstract E self();

		public E from(@NotNull @Valid BookSerie entity) {
			super.from(entity);
			addAllBooks(entity.getBooks());
			return self();
		}

		public E addAllBooks(@NotNull final Collection<Book> books) {
			for (Book book : books) {
				addBook(book);
			}
			return self();
		}

		public E addBook(@NotNull @Valid final Book book) {
			books.add(book);
			return self();
		}

		public
		@Valid
		BookSerie build() {
			return new BookSerie(this);
		}
	}

	protected static class Builder extends Init<Builder> {
		@Override
		protected Builder self() {
			return this;
		}
	}
}
