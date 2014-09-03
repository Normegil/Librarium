package be.normegil.librarium.model.data.video;

import be.normegil.librarium.model.data.BaseMedia;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.SortNatural;

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
public class Serie extends BaseMedia implements Comparable<Serie>, Serializable {

	@SortNatural
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "serie")
	private SortedSet<SerieSeason> seasons = new TreeSet<>();

	public Serie(@NotNull @Valid Serie entity) {
		super(entity);
		addAllSeasons(entity.getSeasons());
	}

	protected Serie(@NotNull @Valid Init<?> init) {
		super(init);
		addAllSeasons(init.seasons);
	}

	//For JAXB
	private Serie() {
		super();
	}

	public static Builder builder() {
		return new Builder();
	}

	public SortedSet<SerieSeason> getSeasons() {
		return new TreeSet<>(seasons);
	}

	public void addAllSeasons(@NotNull final Collection<SerieSeason> seasons) {
		for (SerieSeason season : seasons) {
			addSeason(season);
		}
	}

	public void addSeason(@NotNull @Valid final SerieSeason season) {
		seasons.add(season);
	}

	public void removeAllSeasons(@NotNull final Collection<SerieSeason> seasons) {
		for (SerieSeason season : seasons) {
			removeSeason(season);
		}
	}

	public void removeSeason(@NotNull @Valid final SerieSeason season) {
		seasons.remove(season);
	}

	@Override
	public int compareTo(final Serie o) {
		return new CompareToBuilder()
				.appendSuper(super.compareTo(o))
				.toComparison();
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
		Serie rhs = (Serie) obj;
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

	public abstract static class Init<E extends Init<E>> extends BaseMedia.Init<E> {

		private Collection<SerieSeason> seasons = new TreeSet<>();

		protected abstract E self();

		public E from(@NotNull @Valid Serie entity) {
			super.from(entity);
			addAllSeasons(entity.getSeasons());
			return self();
		}

		public E addAllSeasons(@NotNull final Collection<SerieSeason> seasons) {
			for (SerieSeason season : seasons) {
				addSeason(season);
			}
			return self();
		}

		public E addSeason(@NotNull @Valid final SerieSeason season) {
			seasons.add(season);
			return self();
		}

		public
		@Valid
		Serie build() {
			return new Serie(this);
		}
	}

	protected static class Builder extends Init<Builder> {
		@Override
		protected Builder self() {
			return this;
		}
	}
}
