package be.normegil.librarium.model.rest;

import be.normegil.librarium.ApplicationProperties;
import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.annotation.XSD;
import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.rest.RESTCollectionHelper;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "group")
@XmlAccessorType(XmlAccessType.NONE)
@XSD("CollectionResource.xsd")
public class CollectionResource {

	private static final long FIRST_OFFSET = 0L;

	@XmlElement
	@Min(0)
	private long offset;
	@XmlElement
	@Min(1)
	private int limit;
	@XmlElement
	@Min(0)
	private long totalNumberOfItems;

	@XmlElement
	@NotNull
	private URL first;
	@XmlElement
	@NotNull
	private URL last;
	@XmlElement
	private URL previous;
	@XmlElement
	private URL next;

	@XmlElementWrapper
	@XmlElement(name = "url")
	private List<URL> items;

	protected CollectionResource(@Valid Init<?> init) {
		if (init.offset != null) {
			if (offsetIsConsistant(init.offset, init.totalNumberOfItems)) {
				offset = init.offset;
			} else {
				throw new IllegalArgumentException("Offset inconsitancy [Offset=" + init.offset + ";TotalNumberOfItems=" + init.totalNumberOfItems + "]");
			}
		} else {
			offset = 0L;
		}

		if (init.limit == null) {
			limit = ApplicationProperties.REST.DEFAULT_LIMIT;
		} else if (ApplicationProperties.REST.MAX_LIMIT < init.limit) {
			limit = ApplicationProperties.REST.MAX_LIMIT;
		} else {
			limit = init.limit;
		}

		totalNumberOfItems = init.totalNumberOfItems;

		first = new RESTCollectionHelper().getCollectionURL(init.baseURL, FIRST_OFFSET, limit);
		last = new RESTCollectionHelper().getLastURL(init.baseURL, limit, totalNumberOfItems);

		previous = new RESTCollectionHelper().getPreviousURL(init.baseURL, offset, limit);
		next = new RESTCollectionHelper().getNextURL(init.baseURL, offset, limit, totalNumberOfItems);

		items = init.items;
	}

	public CollectionResource(final CollectionResource resource) {
		offset = resource.getOffset();
		limit = resource.getLimit();
		first = resource.getURLToFirstPage();
		last = resource.getURLToLastPage();
		previous = resource.getURLToPreviousPage();
		next = resource.getURLToNextPage();
		items = new ArrayList<>(resource.getItems());
		totalNumberOfItems = resource.getTotalNumberOfItems();
	}

	// For JAXB
	@SuppressWarnings(WarningTypes.UNUSED)
	public CollectionResource() {
	}

	public static Builder builder() {
		return new Builder();
	}

	private boolean offsetIsConsistant(final Long offset, final Long totalNumberOfItems) {
		return (offset == 0 && offset.equals(totalNumberOfItems))
				|| offset < totalNumberOfItems;
	}

	public Long getOffset() {
		return offset;
	}

	public int getLimit() {
		return limit;
	}

	public long getTotalNumberOfItems() {
		return totalNumberOfItems;
	}

	public URL getURLToFirstPage() {
		return first;
	}

	public URL getURLToPreviousPage() {
		return previous;
	}

	public URL getURLToNextPage() {
		return next;
	}

	public URL getURLToLastPage() {
		return last;
	}

	public List<URL> getItems() {
		return new ArrayList<>(items);
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
		CollectionResource rhs = (CollectionResource) obj;
		return new EqualsBuilder()
				.append(this.offset, rhs.offset)
				.append(this.limit, rhs.limit)
				.append(this.first, rhs.first)
				.append(this.last, rhs.last)
				.append(this.totalNumberOfItems, rhs.totalNumberOfItems)
				.append(this.previous, rhs.previous)
				.append(this.next, rhs.next)
				.append(this.items, rhs.items)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(offset)
				.append(limit)
				.append(first)
				.append(last)
				.append(totalNumberOfItems)
				.append(previous)
				.append(next)
				.append(items)
				.toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ApplicationProperties.TO_STRING_STYLE)
				.append("offset", offset)
				.append("limit", limit)
				.append("totalNumberOfItems", totalNumberOfItems)
				.toString();
	}

	public abstract static class Init<E extends Init<E>> {
		private Long offset;
		private Integer limit;
		@NotNull
		@Min(0)
		private Long totalNumberOfItems;
		@NotNull
		private URL baseURL;

		private List<URL> items = new ArrayList<>();

		protected abstract E self();

		public E from(final CollectionResource resource) {
			items = new ArrayList<>(resource.getItems());
			offset = resource.getOffset();
			limit = resource.getLimit();
			totalNumberOfItems = resource.getTotalNumberOfItems();
			baseURL = new RESTCollectionHelper().getBaseURL(resource.getURLToFirstPage());
			return self();
		}

		public E setOffset(@Min(0) final long offset) {
			this.offset = offset;
			return self();
		}

		public E setLimit(@Min(1) final int limit) {
			this.limit = limit;
			return self();
		}

		public E setBaseURL(@NotNull final URL baseURL) {
			this.baseURL = baseURL;
			return self();
		}

		public E setTotalNumberOfItems(@Min(0) final long totalNumberOfItems) {
			this.totalNumberOfItems = totalNumberOfItems;
			return self();
		}

		public E addAllItems(@NotNull final List<URL> items) {
			for (URL item : items) {
				addItem(item);
			}
			return self();
		}

		public E addItem(@NotNull final URL item) {
			items.add(item);
			return self();
		}

		public
		@Valid
		CollectionResource build() {
			return new CollectionResource(this);
		}
	}

	public static class Builder extends Init<Builder> {
		@Override
		protected Builder self() {
			return this;
		}
	}
}
