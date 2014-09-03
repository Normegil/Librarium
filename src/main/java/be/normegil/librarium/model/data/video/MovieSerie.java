package be.normegil.librarium.model.data.video;

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
import java.util.TreeSet;

@javax.persistence.Entity
@Access(AccessType.FIELD)
public class MovieSerie extends BaseMedia implements Comparable<MovieSerie>, Serializable {

	private static final CollectionComparator COLLECTION_COMPARATOR = new CollectionComparator();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "serie")
	private Collection<Movie> movies = new TreeSet<>();

	public MovieSerie(@NotNull @Valid MovieSerie entity) {
		super(entity);
		addAllMovies(entity.getMovies());
	}

	protected MovieSerie(@NotNull @Valid Init<?> init) {
		super(init);
		addAllMovies(init.movies);
	}

	//For JAXB
	private MovieSerie() {
		super();
	}

	public static Builder builder() {
		return new Builder();
	}

	public Collection<Movie> getMovies() {
		return new TreeSet<>(movies);
	}

	public void addAllMovies(@NotNull final Collection<Movie> movies) {
		for (Movie movie : movies) {
			addMovie(movie);
		}
	}

	public void addMovie(@NotNull final Movie movie) {
		movies.add(movie);
	}

	public void removeAllMovies(@NotNull final Collection<Movie> movies) {
		for (Movie movie : movies) {
			removeMovie(movie);
		}
	}

	public void removeMovie(@NotNull final Movie movie) {
		movies.remove(movie);
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ApplicationProperties.TO_STRING_STYLE)
				.appendSuper(super.toString())
				.append("movies", movies)
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
		MovieSerie rhs = (MovieSerie) obj;
		return new EqualsBuilder()
				.appendSuper(super.equals(obj))
				.append(this.movies, rhs.movies)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.appendSuper(super.hashCode())
				.append(movies)
				.toHashCode();
	}

	@Override
	public int compareTo(final MovieSerie o) {
		if (o != null) {
			return new CompareToBuilder()
					.appendSuper(super.compareTo(o))
					.appendSuper(COLLECTION_COMPARATOR.compare(getMovies(), o.getMovies()))
					.toComparison();
		} else {
			return Constants.Comparator.PRIORITY_SECOND;
		}
	}


	public abstract static class Init<E extends Init<E>> extends BaseMedia.Init<E> {

		private Collection<Movie> movies = new TreeSet<>();

		protected abstract E self();

		public E from(@NotNull @Valid MovieSerie entity) {
			super.from(entity);
			addAllMovies(entity.getMovies());
			return self();
		}

		public E addAllMovies(@NotNull final Collection<Movie> movies) {
			for (Movie movie : movies) {
				addMovie(movie);
			}
			return self();
		}

		public E addMovie(@NotNull @Valid final Movie movie) {
			movies.add(movie);
			return self();
		}

		public
		@Valid
		MovieSerie build() {
			return new MovieSerie(this);
		}
	}

	protected static class Builder extends Init<Builder> {
		@Override
		protected Builder self() {
			return this;
		}
	}
}
