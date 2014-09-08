package be.normegil.librarium.model.data;

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
import java.time.LocalDate;
import java.util.UUID;

@javax.persistence.Entity
@Access(AccessType.FIELD)
public class ReleaseDate extends Entity implements Comparable<ReleaseDate>, Serializable {

	@ManyToOne(cascade = {
			CascadeType.PERSIST,
			CascadeType.REFRESH,
			CascadeType.DETACH,
			CascadeType.MERGE
	})
	@JoinColumn(name = "id_media")
	private Media media;

	@ManyToOne(cascade = {
			CascadeType.PERSIST,
			CascadeType.REFRESH,
			CascadeType.DETACH,
			CascadeType.MERGE
	})
	@JoinColumn(name = "id_support")
	private Support support;
	private LocalDate date;

	protected ReleaseDate(@NotNull @Valid Media media, @NotNull @Valid Support support, LocalDate date) {
		this.media = media;
		this.support = support;
		setDate(date);
	}

	public ReleaseDate(@NotNull @Valid ReleaseDate entity) {
		super();
		this.media = entity.media;
		this.support = entity.support;
		setDate(entity.getDate());
	}

	//For JAXB
	private ReleaseDate() {
		super();
	}

	public Media getMedia() {
		return media;
	}

	public Support getSupport() {
		return support;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(@NotNull final LocalDate date) {
		this.date = date;
	}

	@Override
	public int compareTo(final ReleaseDate o) {
		if (o != null) {
			return new CompareToBuilder()
					.append(getMedia(), o.getMedia())
					.append(getSupport(), o.getSupport())
					.append(getDate(), o.getDate())
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
		ReleaseDate rhs = (ReleaseDate) obj;
		return new EqualsBuilder()
				.append(this.media, rhs.media)
				.append(this.support, rhs.support)
				.append(this.date, rhs.date)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(media)
				.append(support)
				.append(date)
				.toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ApplicationProperties.TO_STRING_STYLE)
				.append("media", media)
				.append("support", support)
				.append("date", date)
				.toString();
	}
}
