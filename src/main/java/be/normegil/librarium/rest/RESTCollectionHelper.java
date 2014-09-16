package be.normegil.librarium.rest;

import be.normegil.librarium.ApplicationProperties;
import be.normegil.librarium.Constants;
import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.model.data.Entity;
import be.normegil.librarium.model.rest.CollectionResource;
import be.normegil.librarium.util.jaxb.adapter.UUIDToRESTURLAdapter;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RESTCollectionHelper {

	private static final long FIRST_OFFSET = 0L;

	public CollectionResource getCollectionResource(final List<? extends Entity> entities, final URL baseURL, final long totalNumberOfElements, final Long offset, final Integer limit) {
		long realOffset = getRealOffset(offset);
		int realLimit = getRealLimit(limit);
		long lastOffset = getLastOffset(totalNumberOfElements, realLimit);
		CollectionResource.Builder builder = CollectionResource.builder()
				.setOffset(realOffset)
				.setLimit(realLimit);

		List<URL> urls = convertToURLs(entities, baseURL);
		builder.addAllItems(urls);
		return builder.build();
	}

	public URL getPreviousURL(URL baseURL, long offset, int limit) {
		if (offset != FIRST_OFFSET) {
			long previousOffset;
			if (offset - limit < FIRST_OFFSET) {
				previousOffset = FIRST_OFFSET;
			} else {
				previousOffset = offset - limit;
			}
			return getCollectionURL(baseURL, previousOffset, limit);
		}
		return null;
	}

	public URL getNextURL(URL baseURL, long offset, int limit, long totalNumberOfItems) {
		if (offset + limit < totalNumberOfItems) {
			return getCollectionURL(baseURL, offset + limit, limit);
		}
		return null;
	}

	public URL getLastURL(URL baseURL, int limit, long totalNumberOfItems) {
		long numberOfPagesMinusOne = totalNumberOfItems / limit;
		if (totalNumberOfItems % limit == 0) {
			numberOfPagesMinusOne -= 1;
		}
		long lastOffset = numberOfPagesMinusOne * limit;
		return getCollectionURL(baseURL, lastOffset, limit);
	}

	public long getLastOffset(final long totalNumberOfElements, final int realLimit) {
		long numberOfFullPage = totalNumberOfElements / realLimit;
		long lastOffset;
		if (totalNumberOfElements % realLimit == 0) {
			lastOffset = numberOfFullPage * (realLimit - 1);
		} else {
			lastOffset = numberOfFullPage * realLimit;
		}
		return lastOffset;
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

	public URL getCollectionURL(@NotNull final URL baseURL, final long offset, final int limit) {
		return baseURL.addToPath("?offset=" + offset + "&limit=" + limit);
	}

	public URL getBaseURL(@NotNull final URL collectionURL) {
		String baseURLString = StringUtils.substringBefore(collectionURL.toRepresentation(), Constants.URL.PARAMETER_SEPARATOR);
		return new URL(baseURLString);
	}
}
