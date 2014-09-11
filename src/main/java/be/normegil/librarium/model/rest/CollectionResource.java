package be.normegil.librarium.model.rest;

import be.normegil.librarium.libraries.URL;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

public class CollectionResource {

	private Long offset;
	private int limit;

	private URL first;
	private URL previous;
	private URL next;
	private URL last;

	private List<URL> items;

	protected CollectionResource(Init<?> init) {
		offset = init.offset;
		limit = init.limit;
		first = init.first;
		previous = init.previous;
		next = init.next;
		last = init.last;
		items = init.items;
	}

	public static Builder builder() {
		return new Builder();
	}

	public Long getOffset() {
		return offset;
	}

	public int getLimit() {
		return limit;
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
		return items;
	}

	public abstract static class Init<E extends Init<E>> {
		private Long offset;
		private Integer limit;

		private URL first;
		private URL previous;
		private URL next;
		private URL last;

		private List<URL> items = new ArrayList<>();

		protected abstract E self();

		public E setOffset(final Long offset) {
			this.offset = offset;
			return self();
		}

		public E setLimit(final int limit) {
			this.limit = limit;
			return self();
		}

		public E setFirst(final URL first) {
			this.first = first;
			return self();
		}

		public E setPrevious(final URL previous) {
			this.previous = previous;
			return self();
		}

		public E setNext(final URL next) {
			this.next = next;
			return self();
		}

		public E setLast(final URL last) {
			this.last = last;
			return self();
		}

		public E addAllItem(final List<URL> items) {
			for (URL item : items) {
				addItem(item);
			}
			return self();
		}

		public E addItem(final URL item) {
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
