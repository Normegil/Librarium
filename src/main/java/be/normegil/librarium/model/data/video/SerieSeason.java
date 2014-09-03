package be.normegil.librarium.model.data.video;

import be.normegil.librarium.Constants;
import be.normegil.librarium.model.data.BaseMedia;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.SortNatural;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;
import java.util.SortedSet;
import java.util.TreeSet;

@javax.persistence.Entity
@Access(AccessType.FIELD)
public class SerieSeason extends BaseMedia implements Comparable<SerieSeason>, Serializable {

	@ManyToOne(cascade = {
			CascadeType.PERSIST,
			CascadeType.REFRESH,
			CascadeType.DETACH,
			CascadeType.MERGE
	})
	@JoinColumn(name = "id_serie")
	private Serie serie;
	private Long seasonNumber;
	@SortNatural
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "season")
	private SortedSet<EpisodeSerie> episodes = new TreeSet<>();

	public SerieSeason(@NotNull @Valid SerieSeason entity) {
		super(entity);
		setSeasonNumber(entity.getSeasonNumber());
		setSerie(entity.getSerie());
		addAllEpisodes(entity.getEpisodes());
	}

	protected SerieSeason(@NotNull @Valid Init<?> init) {
		super(init);
		setSerie(init.serie);
		setSeasonNumber(init.seasonNumber);
		addAllEpisodes(init.episodes);
	}

	//For JAXB
	private SerieSeason() {
		super();
	}

	public static Builder builder() {
		return new Builder();
	}

	public Serie getSerie() {
		return serie;
	}

	public void setSerie(@NotNull @Valid final Serie serie) {
		this.serie = serie;
	}

	public Long getSeasonNumber() {
		return seasonNumber;
	}

	public void setSeasonNumber(@NotNull @Valid final Long seasonNumber) {
		this.seasonNumber = seasonNumber;
	}

	public SortedSet<EpisodeSerie> getEpisodes() {
		return new TreeSet<>(episodes);
	}

	public void addAllEpisodes(@NotNull final Collection<EpisodeSerie> episodes) {
		for (EpisodeSerie episode : episodes) {
			addEpisode(episode);
		}
	}

	public void addEpisode(@NotNull @Valid final EpisodeSerie episode) {
		episodes.add(episode);
	}

	public void removeAllEpisodes(@NotNull final Collection<EpisodeSerie> episodes) {
		for (EpisodeSerie episode : episodes) {
			removeEpisode(episode);
		}
	}

	public void removeEpisode(@NotNull @Valid final EpisodeSerie episode) {
		episodes.remove(episode);
	}

	@Override
	public int compareTo(final SerieSeason o) {
		if (o != null) {
			return new CompareToBuilder()
					.append(getSerie(), o.getSerie())
					.append(getSeasonNumber(), o.getSeasonNumber())
					.appendSuper(super.compareTo(o))
					.toComparison();
		} else {
			return Constants.Comparator.PRIORITY_SECOND;
		}
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
		SerieSeason rhs = (SerieSeason) obj;
		return new EqualsBuilder()
				.appendSuper(super.equals(obj))
				.append(this.serie, rhs.serie)
				.append(this.seasonNumber, rhs.seasonNumber)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.appendSuper(super.hashCode())
				.append(serie)
				.append(seasonNumber)
				.toHashCode();
	}

	public abstract static class Init<E extends Init<E>> extends BaseMedia.Init<E> {

		private Collection<EpisodeSerie> episodes = new TreeSet<>();
		private Serie serie;
		private Long seasonNumber;

		protected abstract E self();

		public E from(@NotNull @Valid SerieSeason entity) {
			super.from(entity);
			setSeasonNumber(entity.getSeasonNumber());
			setSerie(entity.getSerie());
			addAllEpisodes(entity.getEpisodes());
			return self();
		}

		public E setSerie(@NotNull @Valid final Serie serie) {
			this.serie = serie;
			return self();
		}

		public E setSeasonNumber(@NotNull @Valid final Long seasonNumber) {
			this.seasonNumber = seasonNumber;
			return self();
		}

		public E addAllEpisodes(@NotNull final Collection<EpisodeSerie> episodes) {
			for (EpisodeSerie episode : episodes) {
				addEpisode(episode);
			}
			return self();
		}

		public E addEpisode(@NotNull @Valid final EpisodeSerie episode) {
			episodes.add(episode);
			return self();
		}

		public
		@Valid
		SerieSeason build() {
			return new SerieSeason(this);
		}
	}

	protected static class Builder extends Init<Builder> {
		@Override
		protected Builder self() {
			return this;
		}
	}
}
