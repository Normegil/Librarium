package be.normegil.librarium.model.data.video;

import be.normegil.librarium.ApplicationProperties;
import be.normegil.librarium.Constants;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@javax.persistence.Entity
@Access(AccessType.FIELD)
public class Movie extends Video implements Comparable<Movie>, Serializable {

	@ManyToOne(cascade = {
			CascadeType.PERSIST,
			CascadeType.REFRESH,
			CascadeType.DETACH,
			CascadeType.MERGE
	})
	@JoinColumn(name = "id_movieserie")
	private MovieSerie serie;

	public Movie(@NotNull @Valid Movie entity) {
		super(entity);
		setSerie(entity.getSerie());
	}

	protected Movie(@NotNull @Valid Init<?> init) {
		super(init);
		setSerie(init.serie);
	}

	//For JAXB
	private Movie() {
		super();
	}

	public static Builder builder() {
		return new Builder();
	}

	public MovieSerie getSerie() {
		return serie;
	}

	public void setSerie(@NotNull @Valid final MovieSerie serie) {
		this.serie = serie;
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
		Movie rhs = (Movie) obj;
		return new EqualsBuilder()
				.appendSuper(super.equals(obj))
				.append(this.serie, rhs.serie)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.appendSuper(super.hashCode())
				.append(serie)
				.toHashCode();
	}

	@Override
	public int compareTo(final Movie o) {
		if (o != null) {
			return new CompareToBuilder()
					.append(getSerie(), o.getSerie())
					.appendSuper(super.compareTo(o))
					.toComparison();
		} else {
			return Constants.Comparator.PRIORITY_SECOND;
		}
	}

	public abstract static class Init<E extends Init<E>> extends Video.Init<E> {

		private MovieSerie serie;

		protected abstract E self();

		public E from(@NotNull @Valid Movie entity) {
			super.from(entity);
			setSerie(entity.getSerie());
			return self();
		}

		public E setSerie(@NotNull @Valid final MovieSerie serie) {
			this.serie = serie;
			return self();
		}

		public
		@Valid
		Movie build() {
			return new Movie(this);
		}
	}

	protected static class Builder extends Init<Builder> {
		@Override
		protected Builder self() {
			return this;
		}
	}
}
