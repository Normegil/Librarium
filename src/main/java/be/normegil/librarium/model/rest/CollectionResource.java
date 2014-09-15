package be.normegil.librarium.model.rest;

import be.normegil.librarium.ApplicationProperties;
import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.rest.RESTCollectionHelper;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class CollectionResource {

	private static final long FIRST_OFFSET = 0L;
	@Min(0)
	private long offset;
	@Min(1)
	private int limit;

	@NotNull
	private URL first;
	@NotNull
	private URL last;

	private URL previous;
	private URL next;

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

		first = new RESTCollectionHelper().getCollectionURL(init.baseURL, FIRST_OFFSET, limit);
		last = new RESTCollectionHelper().getLastURL(init.baseURL, offset, limit, init.totalNumberOfItems);

		previous = new RESTCollectionHelper().getPreviousURL(init.baseURL, offset, limit);
		next = new RESTCollectionHelper().getNextURL(init.baseURL, offset, limit, init.totalNumberOfItems);

		items = init.items;
	}

	// For JAXB
	@SuppressWarnings(WarningTypes.UNUSED)
	private CollectionResource() {
	}

	public static Builder builder() {
		return new Builder();
	}

	private boolean offsetIsConsistant(final Long offset, final Long totalNumberOfItems) {
		return (offset == 0 && offset.equals(totalNumberOfItems))
				|| offset < totalNumberOfItems;
	}

	@XmlAttribute
	public Long getOffset() {
		return offset;
	}

	@XmlAttribute
	public int getLimit() {
		return limit;
	}

	@XmlAttribute
	public URL getURLToFirstPage() {
		return first;
	}

	@XmlAttribute
	public URL getURLToPreviousPage() {
		return previous;
	}

	@XmlAttribute
	public URL getURLToNextPage() {
		return next;
	}

	@XmlAttribute
	public URL getURLToLastPage() {
		return last;
	}

	@XmlAttribute
	public List<URL> getItems() {
		return items;
	}

	public abstract static class Init<E extends Init<E>> {
		private Long offset;
		private Integer limit;
		@NotNull @Min(0)
		private Long totalNumberOfItems;
		@NotNull
		private URL baseURL;

		private List<URL> items = new ArrayList<>();

		protected abstract E self();

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
