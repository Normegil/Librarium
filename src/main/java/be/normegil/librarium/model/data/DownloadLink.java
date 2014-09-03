package be.normegil.librarium.model.data;

import be.normegil.librarium.ApplicationProperties;
import be.normegil.librarium.Constants;
import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.validation.constraint.NotEmpty;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;

@javax.persistence.Entity
@Access(AccessType.FIELD)
public class DownloadLink extends Entity implements Comparable<DownloadLink>, Serializable {

	@NotEmpty
	private String title;

	@NotNull
	private String description;

	@NotNull
	private URL link;

	@NotNull
	@ManyToOne(cascade = {
			CascadeType.PERSIST,
			CascadeType.REFRESH,
			CascadeType.DETACH,
			CascadeType.MERGE
	})
	@JoinColumn(name = "id_media")
	@XmlTransient
	private BaseMedia media;

	public DownloadLink(@NotNull @Valid DownloadLink entity) {
		super();

		setTitle(entity.getTitle());
		setDescription(entity.getDescription());
		setLink(entity.getLink());
		setMedia(entity.getMedia());
	}

	protected DownloadLink(@NotNull @Valid Init<?> init) {
		setTitle(init.title);
		setDescription(init.description);
		setLink(init.link);
		setMedia(init.media);
	}

	//For JAXB
	private DownloadLink() {
		super();
	}

	public static Builder builder() {
		return new Builder();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(@NotEmpty final String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(@NotNull final String description) {
		this.description = description;
	}

	public URL getLink() {
		return link;
	}

	public void setLink(@NotNull @Valid final URL link) {
		this.link = link;
	}

	public BaseMedia getMedia() {
		return media;
	}

	public void setMedia(@NotNull @Valid final BaseMedia media) {
		this.media = media;
	}

	@Override
	public int compareTo(@NotNull final DownloadLink o) {
		if (o != null) {
			return new CompareToBuilder()
					.append(getMedia(), o.getMedia())
					.append(getTitle(), o.getTitle())
					.append(getLink(), o.getLink())
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
		DownloadLink rhs = (DownloadLink) obj;
		return new EqualsBuilder()
				.append(this.media, rhs.media)
				.append(this.title, rhs.title)
				.append(this.link, rhs.link)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.appendSuper(super.hashCode())
				.append(media)
				.append(title)
				.append(link)
				.toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ApplicationProperties.TO_STRING_STYLE)
				.appendSuper(super.toString())
				.append("title", title)
				.append("description", description)
				.append("link", link)
				.toString();
	}

	public abstract static class Init<E extends Init<E>> {

		private String title;
		private String description;
		private URL link;
		private BaseMedia media;

		protected abstract E self();

		public E from(@NotNull @Valid DownloadLink entity) {
			setTitle(entity.getTitle());
			setDescription(entity.getDescription());
			setLink(entity.getLink());
			setMedia(entity.getMedia());
			return self();
		}

		public E setTitle(@NotEmpty final String title) {
			this.title = title;
			return self();
		}

		public E setDescription(@NotNull final String description) {
			this.description = description;
			return self();
		}

		public E setLink(@NotNull @Valid final URL link) {
			this.link = link;
			return self();
		}

		public E setMedia(@NotNull @Valid final BaseMedia media) {
			this.media = media;
			return self();
		}

		public
		@Valid
		DownloadLink build() {
			return new DownloadLink(this);
		}
	}

	protected static class Builder extends Init<Builder> {
		@Override
		protected Builder self() {
			return this;
		}
	}
}