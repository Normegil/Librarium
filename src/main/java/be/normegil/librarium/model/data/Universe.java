package be.normegil.librarium.model.data;

import be.normegil.librarium.ApplicationProperties;
import be.normegil.librarium.Constants;
import be.normegil.librarium.validation.constraint.NotEmpty;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

@javax.persistence.Entity
@Access(AccessType.FIELD)
public class Universe extends Entity implements Comparable<Universe>, Serializable {

	@NotEmpty
	private String name;
	@NotNull
	private String description;
	@NotNull
	@ManyToMany(cascade = {
			CascadeType.PERSIST,
			CascadeType.REFRESH,
			CascadeType.DETACH,
			CascadeType.MERGE
	})
	private Collection<Media> medias = new TreeSet<>();

	public Universe(@NotNull @Valid Universe entity) {
		super();

		setName(entity.getName());
		setDescription(entity.getDescription());
		addAllMedias(entity.getMedias());
	}

	protected Universe(@NotNull @Valid Init<?> init) {
		super();

		setName(init.name);
		setDescription(init.description);
		addAllMedias(init.medias);
	}

	//For JAXB
	private Universe() {
		super();
	}

	public static Builder builder() {
		return new Builder();
	}

	public String getName() {
		return name;
	}

	public void setName(@NotEmpty final String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(@NotNull final String description) {
		this.description = description;
	}

	public Set<Media> getMedias() {
		return new TreeSet<>(medias);
	}

	public void addAllMedias(@NotNull final Collection<Media> medias) {
		for (Media media : medias) {
			addMedia(media);
		}
	}

	public void addMedia(@NotNull @Valid final Media media) {
		medias.add(media);
	}

	public void removeAllMedias(@NotNull final Collection<Media> medias) {
		for (Media media : medias) {
			removeMedia(media);
		}
	}

	public void removeMedia(@NotNull @Valid final Media media) {
		medias.remove(media);
	}

	@Override
	public int compareTo(final Universe o) {
		if (o != null) {
			return new CompareToBuilder()
					.append(getName(), o.getName())
					.append(getDescription(), o.getDescription())
					.toComparison();
		} else {
			return Constants.Comparator.PRIORITY_SECOND;
		}
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ApplicationProperties.TO_STRING_STYLE)
				.appendSuper(super.toString())
				.append("name", name)
				.append("description", description)
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
		Universe rhs = (Universe) obj;
		return new EqualsBuilder()
				.append(this.name, rhs.name)
				.append(this.description, rhs.description)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(name)
				.append(description)
				.toHashCode();
	}

	public abstract static class Init<E extends Init<E>> {

		private String name;
		private String description;
		private Collection<Media> medias = new TreeSet<>();

		protected abstract E self();

		public E from(@NotNull @Valid Universe entity) {
			setName(entity.getName());
			setDescription(entity.getDescription());
			addAllMedias(entity.getMedias());
			return self();
		}

		public E setName(@NotEmpty final String name) {
			this.name = name;
			return self();
		}

		public E setDescription(@NotNull final String description) {
			this.description = description;
			return self();
		}

		public E addAllMedias(@NotNull final Collection<Media> medias) {
			for (Media media : medias) {
				addMedia(media);
			}
			return self();
		}

		public E addMedia(@NotNull @Valid final Media media) {
			medias.add(media);
			return self();
		}

		public
		@Valid
		Universe build() {
			return new Universe(this);
		}
	}

	protected static class Builder extends Init<Builder> {
		@Override
		protected Builder self() {
			return this;
		}
	}
}
