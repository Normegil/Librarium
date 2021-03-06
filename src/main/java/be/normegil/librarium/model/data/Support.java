package be.normegil.librarium.model.data;

import be.normegil.librarium.ApplicationProperties;
import be.normegil.librarium.Constants;
import be.normegil.librarium.libraries.URL;
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
import java.util.TreeSet;

@javax.persistence.Entity
@Access(AccessType.FIELD)
public class Support extends Entity implements Comparable<Support>, Serializable {

	@NotEmpty
	private String name;
	@NotNull
	private URL wikipediaPage;
	@NotNull
	@ManyToMany(cascade = {
			CascadeType.PERSIST,
			CascadeType.REFRESH,
			CascadeType.DETACH,
			CascadeType.MERGE
	})
	private Collection<Media> medias = new TreeSet<>();

	public Support(@NotNull @Valid Support entity) {
		super();
		setName(entity.getName());
		setWikipediaPage(entity.getWikipediaPage());
		addAllMedias(entity.getMedias());
	}

	protected Support(@NotNull @Valid Init<?> init) {
		super();

		setName(init.name);
		setWikipediaPage(init.wikipediaPage);
		addAllMedias(init.medias);
	}

	//For JAXB
	private Support() {
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

	public URL getWikipediaPage() {
		return wikipediaPage;
	}

	public void setWikipediaPage(@NotNull @Valid final URL wikipediaPage) {
		this.wikipediaPage = wikipediaPage;
	}

	public Collection<Media> getMedias() {
		return new TreeSet<>(medias);
	}

	public void addAllMedias(@NotNull final Collection<Media> medias) {
		for (Media media : medias) {
			addMedia(media);
		}
	}

	public void addMedia(@NotNull final Media media) {
		medias.add(media);
	}

	public void removeAllMedias(@NotNull final Collection<Media> medias) {
		for (Media media : medias) {
			removeMedia(media);
		}
	}

	public void removeMedia(@NotNull final Media media) {
		medias.remove(media);
	}

	@Override
	public int compareTo(final Support o) {
		if (o != null) {
			return new CompareToBuilder()
					.append(getName(), o.getName())
					.append(getWikipediaPage(), o.getWikipediaPage())
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
		Support rhs = (Support) obj;
		return new EqualsBuilder()
				.append(this.name, rhs.name)
				.append(this.wikipediaPage, rhs.wikipediaPage)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.appendSuper(super.hashCode())
				.append(name)
				.append(wikipediaPage)
				.toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ApplicationProperties.TO_STRING_STYLE)
				.appendSuper(super.toString())
				.append("name", name)
				.toString();
	}

	public abstract static class Init<E extends Init<E>> {

		private String name;
		private URL wikipediaPage;
		private Collection<Media> medias = new TreeSet<>();

		protected abstract E self();

		public E from(@NotNull Support entity) {
			setName(entity.getName());
			setWikipediaPage(entity.getWikipediaPage());
			addAllMedias(entity.getMedias());
			return self();
		}

		public E setName(@NotEmpty final String name) {
			this.name = name;
			return self();
		}

		public E setWikipediaPage(@NotNull final URL wikipediaPage) {
			this.wikipediaPage = wikipediaPage;
			return self();
		}

		public E addAllMedias(@NotNull final Collection<Media> medias) {
			for (Media media : medias) {
				addMedia(media);
			}
			return self();
		}

		public E addMedia(@NotNull final Media media) {
			medias.add(media);
			return self();
		}

		public
		@Valid
		Support build() {
			return new Support(this);
		}
	}

	protected static class Builder extends Init<Builder> {
		@Override
		protected Builder self() {
			return this;
		}
	}
}
