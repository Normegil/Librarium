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
public class EpisodeSerie extends Video implements Comparable<EpisodeSerie>, Serializable {

	private Long episodeNumber;
	@ManyToOne(cascade = {
			CascadeType.PERSIST,
			CascadeType.REFRESH,
			CascadeType.DETACH,
			CascadeType.MERGE
	})
	@JoinColumn(name = "id_season")
	private SerieSeason season;

	public EpisodeSerie(@NotNull @Valid EpisodeSerie entity) {
		super(entity);
		setEpisodeNumber(entity.getEpisodeNumber());
		setSeason(entity.getSeason());
	}

	protected EpisodeSerie(@NotNull @Valid Init<?> init) {
		super(init);
		setEpisodeNumber(init.episodeNumber);
		setSeason(init.season);
	}

	//For JAXB
	private EpisodeSerie() {
		super();
	}

	public static Builder builder() {
		return new Builder();
	}

	public Long getEpisodeNumber() {
		return episodeNumber;
	}

	public void setEpisodeNumber(@NotNull @Valid final Long episodeNumber) {
		this.episodeNumber = episodeNumber;
	}

	public SerieSeason getSeason() {
		return season;
	}

	public void setSeason(@NotNull @Valid final SerieSeason season) {
		this.season = season;
	}

	@Override
	public int compareTo(final EpisodeSerie o) {
		if (o != null) {
			return new CompareToBuilder()
					.append(getSeason(), o.getSeason())
					.append(getEpisodeNumber(), o.getEpisodeNumber())
					.appendSuper(super.compareTo(o))
					.toComparison();
		} else {
			return Constants.Comparator.PRIORITY_SECOND;
		}
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ApplicationProperties.TO_STRING_STYLE)
				.appendSuper(super.toString())
				.append("episodeNumber", episodeNumber)
				.append("season", season)
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
		EpisodeSerie rhs = (EpisodeSerie) obj;
		return new EqualsBuilder()
				.appendSuper(super.equals(obj))
				.append(this.episodeNumber, rhs.episodeNumber)
				.append(this.season, rhs.season)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.appendSuper(super.hashCode())
				.append(episodeNumber)
				.append(season)
				.toHashCode();
	}

	public abstract static class Init<E extends Init<E>> extends Video.Init<E> {

		private SerieSeason season;
		private Long episodeNumber;

		protected abstract E self();

		public E from(@NotNull @Valid EpisodeSerie entity) {
			super.from(entity);
			setEpisodeNumber(entity.getEpisodeNumber());
			setSeason(entity.getSeason());
			return self();
		}

		public E setEpisodeNumber(@NotNull @Valid final Long episodeNumber) {
			this.episodeNumber = episodeNumber;
			return self();
		}

		public E setSeason(@NotNull @Valid final SerieSeason season) {
			this.season = season;
			return self();
		}

		public
		@Valid
		EpisodeSerie build() {
			return new EpisodeSerie(this);
		}
	}

	protected static class Builder extends Init<Builder> {
		@Override
		protected Builder self() {
			return this;
		}
	}
}
