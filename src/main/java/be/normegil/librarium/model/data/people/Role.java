package be.normegil.librarium.model.data.people;

import be.normegil.librarium.ApplicationProperties;
import be.normegil.librarium.Constants;
import be.normegil.librarium.model.data.Entity;
import be.normegil.librarium.model.data.video.Video;
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
public class Role extends Entity implements Comparable<Role>, Serializable {

	@NotNull
	@Valid
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE})
	@JoinColumn(name = "id_role")
	private Person role;
	@NotNull
	@Valid
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE})
	@JoinColumn(name = "id_actor")
	private Person actor;
	@NotNull
	@Valid
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE})
	@JoinColumn(name = "id_video")
	private Video video;

	public Role(@NotNull @Valid Role entity) {
		super();

		setRole(entity.getRole());
		setActor(entity.getActor());
		setVideo(entity.getVideo());
	}

	protected Role(@NotNull @Valid Init<?> init) {
		super();

		setRole(init.role);
		setActor(init.actor);
		setVideo(init.video);
	}

	//For JAXB
	private Role() {
		super();
	}

	public static Builder builder() {
		return new Builder();
	}

	public Person getRole() {
		return role;
	}

	public void setRole(@NotNull @Valid final Person role) {
		this.role = role;
	}

	public Person getActor() {
		return actor;
	}

	public void setActor(@NotNull @Valid final Person actor) {
		this.actor = actor;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(@NotNull @Valid final Video video) {
		this.video = video;
	}

	@Override
	public int compareTo(final Role o) {
		if (o != null) {
			return new CompareToBuilder()
					.append(getVideo(), o.getVideo())
					.append(getRole(), o.getRole())
					.append(getActor(), o.getActor())
					.toComparison();
		} else {
			return Constants.Comparator.PRIORITY_SECOND;
		}
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ApplicationProperties.TO_STRING_STYLE)
				.appendSuper(super.toString())
				.append("role", role)
				.append("actor", actor)
				.append("video", video)
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
		Role rhs = (Role) obj;
		return new EqualsBuilder()
				.append(this.role, rhs.role)
				.append(this.actor, rhs.actor)
				.append(this.video, rhs.video)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.appendSuper(super.hashCode())
				.append(role)
				.append(actor)
				.append(video)
				.toHashCode();
	}

	public abstract static class Init<E extends Init<E>> {

		private Person role;
		private Person actor;
		private Video video;

		protected abstract E self();

		public E from(@NotNull @Valid Role entity) {
			setRole(entity.getRole());
			setActor(entity.getActor());
			setVideo(entity.getVideo());
			return self();
		}

		public E setRole(@NotNull @Valid final Person role) {
			this.role = role;
			return self();
		}

		public E setActor(@NotNull @Valid final Person actor) {
			this.actor = actor;
			return self();
		}

		public E setVideo(@NotNull @Valid final Video video) {
			this.video = video;
			return self();
		}

		public
		@Valid
		Role build() {
			return new Role(this);
		}
	}

	protected static class Builder extends Init<Builder> {
		@Override
		protected Builder self() {
			return this;
		}
	}
}
