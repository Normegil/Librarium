package be.normegil.librarium.rest;

import be.normegil.librarium.ApplicationProperties;
import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.model.data.Entity;
import be.normegil.librarium.model.rest.CollectionResource;
import be.normegil.librarium.util.jaxb.adapter.UUIDToRESTURLAdapter;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RESTCollectionHelper {

	private static final long FIRST_OFFSET = 0L;

	public CollectionResource getCollectionResource(final List<? extends Entity> entities, final URL baseURL, final long totalNumberOfElements, final Long offset, final Integer limit) {
		long realOffset = getRealOffset(offset);
		int realLimit = getRealLimit(limit);
		CollectionResource.Builder builder = CollectionResource.builder()
				.setOffset(realOffset)
				.setLimit(realLimit)
				.setFirst(getCollectionURL(baseURL, FIRST_OFFSET, realLimit))
				.setLast(getCollectionURL(baseURL, totalNumberOfElements, realLimit));

		List<URL> urls = convertToURLs(entities, baseURL);
		builder.addAllItem(urls);

		long nextOffset = realOffset + realLimit;
		if (nextOffset <= totalNumberOfElements) {
			builder.setNext(getCollectionURL(baseURL, nextOffset, realLimit));
		}

		long previousOffset = realOffset - realLimit;
		if (previousOffset >= 0) {
			builder.setPrevious(getCollectionURL(baseURL, previousOffset, realLimit));
		}
		return builder.build();
	}

	private long getRealOffset(final Long offset) {
		if (offset == null) {
			return FIRST_OFFSET;
		}
		return offset;
	}

	private Integer getRealLimit(final Integer limit) {
		if (limit == null) {
			return ApplicationProperties.REST.DEFAULT_LIMIT;
		} else if (limit > ApplicationProperties.REST.MAX_LIMIT) {
			return ApplicationProperties.REST.MAX_LIMIT;
		}
		return limit;
	}

	public List<URL> convertToURLs(final List<? extends Entity> entities, final URL baseURL) {
		List<URL> urlsToEntities = new ArrayList<>();
		UUIDToRESTURLAdapter adapter = new UUIDToRESTURLAdapter(baseURL);
		for (Entity entity : entities) {
			UUID id = entity.getId();
			URL urlToEntity = adapter.marshal(id);
			urlsToEntities.add(urlToEntity);
		}
		return urlsToEntities;
	}

	protected URL getCollectionURL(@NotNull final URL baseURL, final long offset, final int limit) {
		return baseURL.addToPath("?offset=" + offset + "&limit=" + limit);
	}
}
