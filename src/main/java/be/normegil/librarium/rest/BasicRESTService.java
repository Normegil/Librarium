package be.normegil.librarium.rest;

import be.normegil.librarium.ApplicationProperties;
import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.model.dao.DAO;
import be.normegil.librarium.model.data.Entity;
import be.normegil.librarium.model.data.game.Game;
import be.normegil.librarium.model.rest.CollectionResource;
import be.normegil.librarium.util.jaxb.adapter.UUIDToRESTURLAdapter;
import org.slf4j.Logger;

import javax.validation.constraints.NotNull;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.ContextResolver;
import javax.xml.bind.Marshaller;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class BasicRESTService<E extends Entity> implements RESTService {

	@Override
	public Response getAll(final UriInfo info, final Long offset, final Integer limit) {
		try {
			URL baseURL = new URL(info.getBaseUri());
			setMarshallerOptions(baseURL);

			List<E> entities = getDao().getAll();
			CollectionResource resource = getCollectionResource(entities, baseURL, offset, limit);

			return Response.ok(resource).build();
		} catch (Exception e) {
			getLogger().error("Error getting all Games", e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@Override
	public Response get(final UriInfo info, final UUID id) {
		try {
			if (id == null) {
				return Response.status(Response.Status.BAD_REQUEST).build();
			}

			Entity entity = getDao().get(id);
			if (entity == null) {
				return Response.status(Response.Status.NOT_FOUND).build();
			} else {
				URL baseURL = new URL(info.getBaseUri().toURL());
				setMarshallerOptions(baseURL);
				return Response.ok(entity).build();
			}
		} catch (Exception e) {
			getLogger().error("Error getting Game with ID : " + id, e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@Override
	public Response create(@Context final UriInfo info, final Object entityAsObject) {
		try {
			E entity = (E) entityAsObject;
			if (entity == null || entity.getId() != null) {
				return Response.status(Response.Status.BAD_REQUEST).build();
			}

			getDao().create(entity);
			URL baseURL = new URL(info.getBaseUri());
			UUIDToRESTURLAdapter adapter = new UUIDToRESTURLAdapter(baseURL);
			URL urlToCreatedEntity = adapter.marshal(entity.getId());
			return Response.ok().location(urlToCreatedEntity.toURI()).build();
		} catch (Exception e) {
			getLogger().error("Error creating games", e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@Override
	public Response updateByPUT(final UUID id, final Object entityAsObject) {
		try {
			E entity = (E) entityAsObject;
			if (id == null || entity == null || !id.equals(entity.getId())) {
				return Response.status(Response.Status.BAD_REQUEST).build();
			}

			E loadedEntity = getDao().get(id);
			if (loadedEntity == null) {
				return Response.status(Response.Status.NOT_FOUND).build();
			}

			update(loadedEntity, entity);

			getDao().update(entity);
			return Response.ok().build();
		} catch (Exception e) {
			getLogger().error("Error updating Game with ID : " + id, e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	protected abstract void update(final E loadedEntity, final E entity);

	@Override
	public Response updateByPOST(final UriInfo info, final UUID id, final Object entityAsObject) {
		try {
			E entity = (E) entityAsObject;
			if (id == null || entity == null || !id.equals(entity.getId())) {
				return Response.status(Response.Status.BAD_REQUEST).build();
			}

			E loadedEntity = getDao().get(id);
			if (loadedEntity == null) {
				return Response.status(Response.Status.NOT_FOUND).build();
			}

			updateNullCheck(loadedEntity, entity);

			getDao().update(entity);

			URL baseURL = new URL(info.getBaseUri());
			UUIDToRESTURLAdapter adapter = new UUIDToRESTURLAdapter(baseURL);
			URL urlToCreatedEntity = adapter.marshal(entity.getId());
			return Response.ok().location(urlToCreatedEntity.toURI()).build();
		} catch (Exception e) {
			getLogger().error("Error updating Game with ID : " + id, e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	protected abstract void updateNullCheck(final E loadedEntity, final E entity);

	@Override
	public Response delete(final UUID id) {
		try {
			if (id == null) {
				return Response.status(Response.Status.BAD_REQUEST).build();
			}

			E entity = getDao().get(id);
			if (entity == null) {
				return Response.status(Response.Status.NOT_FOUND).build();
			}

			getDao().remove(entity);
			return Response.ok().build();
		} catch (Exception e) {
			getLogger().error("Error deleting game with ID : " + id, e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	private void setMarshallerOptions(final URL baseURL) throws MalformedURLException {
		Marshaller context = getContextResolver().getContext(this.getClass());
		context.setAdapter(UUIDToRESTURLAdapter.class, new UUIDToRESTURLAdapter(baseURL));
	}

	protected CollectionResource getCollectionResource(final List<? extends Entity> entities, final URL baseURL, Long offset, Integer limit) {
		List<URL> urls = convertToURLs(entities, baseURL);

		if (limit == null) {
			limit = ApplicationProperties.REST.DEFAULT_LIMIT;
		} else if (limit > ApplicationProperties.REST.MAX_LIMIT) {
			limit = ApplicationProperties.REST.MAX_LIMIT;
		}

		long numberOfElements = getDao().getNumberOfElements();
		long numberOfPages = numberOfElements / limit;
		long firstOffset = 0L;
		long lastOffset = numberOfPages * limit + 1;
		if (offset == null) {
			offset = 0L;
		}
		long previousOffset = offset - 1;
		long nextOffset = offset + 1;
		CollectionResource.Builder builder = CollectionResource.builder()
				.addAllItems(urls)
				.setOffset(offset)
				.setLimit(limit)
				.setFirst(getCollectionURL(baseURL, firstOffset, limit))
				.setLast(getCollectionURL(baseURL, lastOffset, limit));

		if (nextOffset <= numberOfElements) {
			builder.setNext(getCollectionURL(baseURL, nextOffset, limit));
		}

		if (previousOffset >= 0) {
			builder.setPrevious(getCollectionURL(baseURL, previousOffset, limit));
		}
		return builder.build();
	}

	private List<URL> convertToURLs(final List<? extends Entity> entities, final URL baseURL) {
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

	public abstract DAO<E> getDao();

	public abstract void setDAO(final DAO<Game> dao);

	public abstract Logger getLogger();

	public abstract void setLog(final Logger log);

	public abstract ContextResolver<Marshaller> getContextResolver();

	public abstract void setContextResolver(final ContextResolver<Marshaller> contextResolver);
}
