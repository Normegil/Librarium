package be.normegil.librarium.model.data.book;

import be.normegil.librarium.ApplicationProperties;
import be.normegil.librarium.Constants;
import be.normegil.librarium.model.data.Media;
import be.normegil.librarium.model.data.people.Responsible;
import be.normegil.librarium.model.data.people.StaffRole;
import be.normegil.librarium.util.CollectionComparator;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

@javax.persistence.Entity
@Access(AccessType.FIELD)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Book extends Media {

	private static final CollectionComparator COLLECTION_COMPARATOR = new CollectionComparator();

	@Valid
	@ManyToOne(cascade = {
			CascadeType.PERSIST,
			CascadeType.REFRESH,
			CascadeType.DETACH,
			CascadeType.MERGE
	})
	@JoinColumn(name = "id_bookserie")
	private BookSerie serie;

	protected Book(@NotNull @Valid Book entity) {
		super(entity);
		addAllAuthors(entity.getAuthors());
		addAllEditors(entity.getEditors());
		setSerie(entity.getSerie());
	}

	protected Book(@NotNull @Valid Init<?> init) {
		super(init);
		addAllAuthors(init.authors);
		addAllEditors(init.editors);
		setSerie(init.serie);
	}

	//For JAXB
	protected Book() {
		super();
	}

	public Set<Responsible> getAuthors() {
		return getResponsibleFor(StaffRole.AUTHOR);
	}

	public void addAllAuthors(@NotNull final Collection<Responsible> authors) {
		for (Responsible author : authors) {
			addAuthor(author);
		}
	}

	public void addAuthor(@NotNull @Valid final Responsible author) {
		addStaffMember(StaffRole.AUTHOR, author);
	}

	public void removeAllAuthors(@NotNull final Collection<Responsible> authors) {
		for (Responsible author : authors) {
			removeAuthor(author);
		}
	}

	public void removeAuthor(@NotNull @Valid final Responsible author) {
		removeStaffMember(StaffRole.AUTHOR, author);
	}

	public Set<Responsible> getEditors() {
		return getResponsibleFor(StaffRole.EDITOR);
	}

	public void addAllEditors(@NotNull final Collection<Responsible> editors) {
		for (Responsible editor : editors) {
			addEditor(editor);
		}
	}

	public void addEditor(@NotNull @Valid final Responsible editor) {
		addStaffMember(StaffRole.EDITOR, editor);
	}

	public void removeAllEditors(@NotNull final Collection<Responsible> editors) {
		for (Responsible editor : editors) {
			removeEditor(editor);
		}
	}

	public void removeEditor(@NotNull @Valid final Responsible editor) {
		removeStaffMember(StaffRole.EDITOR, editor);
	}

	public BookSerie getSerie() {
		return serie;
	}

	public void setSerie(@NotNull @Valid final BookSerie serie) {
		this.serie = serie;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ApplicationProperties.TO_STRING_STYLE)
				.appendSuper(super.toString())
				.append("serie", serie)
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
		Book rhs = (Book) obj;
		return new EqualsBuilder()
				.appendSuper(super.equals(obj))
				.append(this.getAuthors(), rhs.getAuthors())
				.append(this.getEditors(), rhs.getEditors())
				.append(this.serie, rhs.serie)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.appendSuper(super.hashCode())
				.append(getAuthors())
				.append(getEditors())
				.append(serie)
				.toHashCode();
	}

	public int compareTo(final Book o) {
		if (o != null) {
			return new CompareToBuilder()
					.appendSuper(super.compareTo(o))
					.append(getSerie(), o.getSerie())
					.appendSuper(COLLECTION_COMPARATOR.compare(getEditors(), o.getEditors()))
					.appendSuper(COLLECTION_COMPARATOR.compare(getAuthors(), o.getAuthors()))
					.toComparison();
		} else {
			return Constants.Comparator.PRIORITY_SECOND;
		}
	}

	public abstract static class Init<E extends Init<E>> extends Media.Init<E> {

		private Collection<Responsible> editors = new TreeSet<>();
		private Collection<Responsible> authors = new TreeSet<>();
		private BookSerie serie;

		protected abstract E self();

		public E from(@NotNull @Valid Book entity) {
			super.from(entity);
			addAllAuthors(entity.getAuthors());
			addAllEditors(entity.getEditors());
			setSerie(entity.getSerie());
			return self();
		}

		public E addAllAuthors(@NotNull final Collection<Responsible> authors) {
			for (Responsible author : authors) {
				addAuthor(author);
			}
			return self();
		}

		public E addAuthor(@NotNull @Valid final Responsible author) {
			authors.add(author);
			return self();
		}

		public E addAllEditors(@NotNull final Collection<Responsible> editors) {
			for (Responsible editor : editors) {
				addEditor(editor);
			}
			return self();
		}

		public E addEditor(@NotNull @Valid final Responsible editor) {
			editors.add(editor);
			return self();
		}

		public E setSerie(@NotNull @Valid final BookSerie serie) {
			this.serie = serie;
			return self();
		}

	}
}
