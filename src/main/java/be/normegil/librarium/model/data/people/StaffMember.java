package be.normegil.librarium.model.data.people;

import be.normegil.librarium.ApplicationProperties;
import be.normegil.librarium.Constants;
import be.normegil.librarium.model.data.Entity;
import be.normegil.librarium.model.data.Media;
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
public class StaffMember extends Entity implements Comparable<StaffMember>, Serializable {

	@NotNull
	@Valid
	private StaffRole role;
	@NotNull
	@Valid
	@ManyToOne(cascade = {
			CascadeType.PERSIST,
			CascadeType.REFRESH,
			CascadeType.MERGE,
			CascadeType.DETACH,
	})
	@JoinColumn(name = "id_responsible")
	private Responsible responsible;
	@NotNull
	@Valid
	@ManyToOne(cascade = {
			CascadeType.PERSIST,
			CascadeType.REFRESH,
			CascadeType.MERGE,
			CascadeType.DETACH,
	})
	@JoinColumn(name = "id_media")
	private Media media;

	public StaffMember(@NotNull @Valid StaffMember entity) {
		super();

		setRole(entity.getRole());
		setResponsible(entity.getResponsible());
		setMedia(entity.getMedia());
	}

	protected StaffMember(@NotNull @Valid Init<?> init) {
		super();

		setRole(init.role);
		setResponsible(init.responsible);
		setMedia(init.media);
	}

	//For JAXB
	private StaffMember() {
		super();
	}

	public static Builder builder() {
		return new Builder();
	}

	public StaffRole getRole() {
		return role;
	}

	public void setRole(@NotNull @Valid final StaffRole role) {
		this.role = role;
	}

	public Responsible getResponsible() {
		return responsible;
	}

	public void setResponsible(@NotNull @Valid final Responsible responsible) {
		this.responsible = responsible;
	}

	public Media getMedia() {
		return media;
	}

	public void setMedia(@NotNull @Valid final Media media) {
		this.media = media;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ApplicationProperties.TO_STRING_STYLE)
				.appendSuper(super.toString())
				.append("role", role)
				.append("responsible", responsible)
				.append("media", media)
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
		StaffMember rhs = (StaffMember) obj;
		return new EqualsBuilder()
				.append(this.role, rhs.role)
				.append(this.responsible, rhs.responsible)
				.append(this.media, rhs.media)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.appendSuper(super.hashCode())
				.append(role)
				.append(responsible)
				.append(media)
				.toHashCode();
	}

	@Override
	public int compareTo(final StaffMember o) {
		if (o != null) {
			return new CompareToBuilder()
					.append(getMedia(), o.getMedia())
					.append(getRole(), o.getRole())
					.append(getResponsible(), o.getResponsible())
					.toComparison();
		} else {
			return Constants.Comparator.PRIORITY_SECOND;
		}
	}

	public abstract static class Init<E extends Init<E>> {

		private StaffRole role;
		private Responsible responsible;
		private Media media;

		protected abstract E self();

		public E from(@NotNull @Valid StaffMember entity) {
			setRole(entity.getRole());
			setResponsible(entity.getResponsible());
			setMedia(entity.getMedia());
			return self();
		}

		public E setRole(@NotNull @Valid final StaffRole role) {
			this.role = role;
			return self();
		}

		public E setResponsible(@NotNull @Valid final Responsible responsible) {
			this.responsible = responsible;
			return self();
		}

		public E setMedia(@NotNull @Valid final Media media) {
			this.media = media;
			return self();
		}

		public
		@Valid
		StaffMember build() {
			return new StaffMember(this);
		}
	}

	public static class Builder extends Init<Builder> {
		@Override
		protected Builder self() {
			return this;
		}
	}
}
