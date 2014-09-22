package be.normegil.librarium.model.data;

import be.normegil.librarium.ApplicationProperties;
import be.normegil.librarium.Constants;
import be.normegil.librarium.libraries.ClassWrapper;
import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.util.CollectionComparator;
import be.normegil.librarium.validation.constraint.NotEmpty;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

@javax.persistence.Entity
@Access(AccessType.FIELD)
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class BaseMedia extends Entity {

	protected static final CollectionComparator COLLECTION_COMPARATOR = new CollectionComparator();
	@NotEmpty
	private String title;

	@NotNull
	private String description = "";

	@NotNull
	@ElementCollection
	private Collection<String> tags = new TreeSet<>();

	private URL officialWebsite;

	private URL wikipediaPage;

	@NotNull
	@ElementCollection
	private Set<URL> stores = new TreeSet<>();

	@NotNull
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "media")
	private Set<DownloadLink> downloadLinks = new TreeSet<>();

	protected BaseMedia(@NotNull @Valid BaseMedia entity) {
		super();

		setTitle(entity.getTitle());
		setDescription(entity.getDescription());
		addAllTags(entity.getTags());
		setOfficialWebsite(entity.getOfficialWebsite());
		setWikipediaPage(entity.getWikipediaPage());
		addAllStores(entity.getStores());
		addAllDownloadLinks(entity.getDownloadLinks());
	}

	protected BaseMedia(@NotNull @Valid Init<?> init) {
		super();

		setTitle(init.title);
		setDescription(init.description);
		addAllTags(init.tags);
		setOfficialWebsite(init.officialWebsite);
		setWikipediaPage(init.wikipediaPage);
		addAllStores(init.stores);
		addAllDownloadLinks(init.downloadLinks);
	}

	//For JAXB
	protected BaseMedia() {
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

	public Set<String> getTags() {
		return new TreeSet<>(tags);
	}

	public void addAllTags(@NotNull Collection<String> tags) {
		for (@NotEmpty String tag : tags) {
			addTag(tag);
		}
	}

	public void addTag(@NotEmpty String tag) {
		tags.add(tag);
	}

	public void removeAllTags(@NotNull Collection<String> tags) {
		for (@NotEmpty String tag : tags) {
			removeTag(tag);
		}
	}

	public void removeTag(@NotEmpty String tag) {
		tags.remove(tag);
	}

	public void clearTags() {
		tags.clear();
	}

	public URL getOfficialWebsite() {
		return officialWebsite;
	}

	public void setOfficialWebsite(final URL officialWebsite) {
		this.officialWebsite = officialWebsite;
	}

	public URL getWikipediaPage() {
		return wikipediaPage;
	}

	public void setWikipediaPage(final URL wikipediaPage) {
		this.wikipediaPage = wikipediaPage;
	}

	public Set<URL> getStores() {
		return new TreeSet<>(stores);
	}

	public void addAllStores(@NotNull Collection<URL> stores) {
		for (URL store : stores) {
			addStore(store);
		}
	}

	public void addStore(@NotNull URL store) {
		stores.add(store);
	}

	public void removeAllStores(@NotNull Collection<URL> stores) {
		for (URL store : stores) {
			removeStore(store);
		}
	}

	public void removeStore(@NotNull URL store) {
		stores.remove(store);
	}

	public void clearStores() {
		stores.clear();
	}

	public Set<DownloadLink> getDownloadLinks() {
		return new TreeSet<>(downloadLinks);
	}

	public void addAllDownloadLinks(@NotNull final Collection<DownloadLink> downloadLinks) {
		for (DownloadLink downloadLink : downloadLinks) {
			addDownloadLink(downloadLink);
		}
	}

	public void addDownloadLink(@NotNull final DownloadLink downloadLink) {
		downloadLink.setMedia(this);
		downloadLinks.add(downloadLink);
	}

	public void removeAllDownloadLinks(@NotNull final Collection<DownloadLink> downloadLinks) {
		for (DownloadLink downloadLink : downloadLinks) {
			removeDownloadLink(downloadLink);
		}
	}

	public void removeDownloadLink(@NotNull final DownloadLink downloadLink) {
		downloadLinks.remove(downloadLink);
	}

	public void clearDownloadLinks() {
		downloadLinks.clear();
	}

	public int compareTo(final BaseMedia o) {
		if (o != null) {
			ClassWrapper<? extends BaseMedia> aClassWrapper = new ClassWrapper<>(getClass());
			ClassWrapper<? extends BaseMedia> oClassWrapper = new ClassWrapper<>(o.getClass());
			return new CompareToBuilder()
					.append(aClassWrapper, oClassWrapper)
					.append(getTitle(), o.getTitle())
					.appendSuper(COLLECTION_COMPARATOR.compare(getTags(), o.getTags()))
					.append(getDescription(), o.getDescription())
					.append(getOfficialWebsite(), o.getOfficialWebsite())
					.append(getWikipediaPage(), o.getWikipediaPage())
					.appendSuper(COLLECTION_COMPARATOR.compare(getStores(), o.getStores()))
					.toComparison();
		} else {
			return Constants.Comparator.PRIORITY_SECOND;
		}
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ApplicationProperties.TO_STRING_STYLE)
				.appendSuper(super.toString())
				.append("title", title)
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
		BaseMedia rhs = (BaseMedia) obj;
		return new EqualsBuilder()
				.append(this.title, rhs.title)
				.append(this.description, rhs.description)
				.append(this.tags, rhs.tags)
				.append(this.officialWebsite, rhs.officialWebsite)
				.append(this.wikipediaPage, rhs.wikipediaPage)
				.append(this.stores, rhs.stores)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.appendSuper(super.hashCode())
				.append(title)
				.append(description)
				.append(tags)
				.append(officialWebsite)
				.append(wikipediaPage)
				.append(stores)
				.toHashCode();
	}

	public abstract static class Init<E extends Init<E>> {

		private String title;
		private String description;
		private Collection<String> tags = new TreeSet<>();
		private URL officialWebsite;
		private URL wikipediaPage;
		private Collection<URL> stores = new TreeSet<>();
		private Collection<DownloadLink> downloadLinks = new TreeSet<>();

		public E from(@NotNull @Valid BaseMedia entity) {
			setTitle(entity.getTitle());
			setDescription(entity.getDescription());
			addAllTags(entity.getTags());
			setOfficialWebsite(entity.getOfficialWebsite());
			setWikipediaPage(entity.getWikipediaPage());
			addAllStores(entity.getStores());
			addAllDownloadLinks(entity.getDownloadLinks());
			return self();
		}

		public E setTitle(@NotEmpty final String title) {
			this.title = title;
			return self();
		}

		public E setDescription(@NotEmpty final String description) {
			this.description = description;
			return self();
		}

		public E addAllTags(@NotNull Collection<String> tags) {
			for (@NotEmpty String tag : tags) {
				addTag(tag);
			}
			return self();
		}

		public E addTag(@NotEmpty String tag) {
			tags.add(tag);
			return self();
		}

		public E setOfficialWebsite(@NotNull final URL officialWebsite) {
			this.officialWebsite = officialWebsite;
			return self();
		}

		public E setWikipediaPage(@NotNull final URL wikipediaPage) {
			this.wikipediaPage = wikipediaPage;
			return self();
		}

		public E addAllStores(@NotNull Collection<URL> stores) {
			for (URL store : stores) {
				addStore(store);
			}
			return self();
		}

		public E addStore(@NotNull URL store) {
			stores.add(store);
			return self();
		}

		public E addAllDownloadLinks(@NotNull final Collection<DownloadLink> downloadLinks) {
			for (DownloadLink downloadLink : downloadLinks) {
				addDownloadLink(downloadLink);
			}
			return self();
		}

		public E addDownloadLink(@NotNull final DownloadLink downloadLink) {
			downloadLinks.add(downloadLink);
			return self();
		}

		protected abstract E self();
	}
}