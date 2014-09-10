package be.normegil.librarium.rest;

import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.model.dao.DAO;
import be.normegil.librarium.model.dao.game.GameDatabaseDAO;
import be.normegil.librarium.model.data.Entity;
import be.normegil.librarium.model.rest.CollectionResource;
import be.normegil.librarium.util.ClassHelper;
import be.normegil.librarium.util.jaxb.adapter.UUIDToRESTURLAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.ContextResolver;
import javax.xml.bind.Marshaller;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.util.List;
import java.util.UUID;

public class RESTHelper<E extends Entity> {

	private static final Logger LOG = LoggerFactory.getLogger(RESTHelper.class);
	private final Type entityClass;
	private DAO<E> dao;
	private ContextResolver<Marshaller> context;
	private Updater<E> updater;

	public RESTHelper(final DAO<E> dao, final ContextResolver<Marshaller> context, Updater<E> updater) {
		this.dao = dao;
		this.context = context;
		this.updater = updater;
		entityClass = new ClassHelper().getClassParameters(getClass()).get(0);
	}

	public Response getAll(final UriInfo info, final Long offset, final Integer limit) {
		try {
			URL baseURL = new URL(info.getBaseUri());
			setMarshallerOptions(baseURL);

			List<E> entities = dao.getAll();
			CollectionResource resource = new RESTCollectionHelper().getCollectionResource(entities, baseURL, dao.getNumberOfElements(), offset, limit);
			return Response.ok(resource).build();
		} catch (Exception e) {
			LOG.error("Error getting all Entities [EntityType=" + entityClass.getTypeName() + "]", e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	public Response get(final UriInfo info, final UUID id) {
		try {
			if (id == null) {
				return Response.status(Response.Status.BAD_REQUEST).build();
			}

			Entity entity = dao.get(id);
			if (entity == null) {
				return Response.status(Response.Status.NOT_FOUND).build();
			} else {
				URL baseURL = new URL(info.getBaseUri().toURL());
				setMarshallerOptions(baseURL);
				return Response.ok(entity).build();
			}
		} catch (Exception e) {
			LOG.error("Error getting Game with ID : " + id, e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	public Response create(@Context final UriInfo info, final E entity) {
		try {
			if (entity == null || entity.getId() != null) {
				return Response.status(Response.Status.BAD_REQUEST).build();
			}

			dao.create(entity);
			URL baseURL = new URL(info.getBaseUri());
			UUIDToRESTURLAdapter adapter = new UUIDToRESTURLAdapter(baseURL);
			URL urlToCreatedEntity = adapter.marshal(entity.getId());
			return Response.ok().location(urlToCreatedEntity.toURI()).build();
		} catch (Exception e) {
			LOG.error("Error creating games", e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	public Response updateByPUT(final UUID id, final E entity) {
		try {
			if (id == null || entity == null || !id.equals(entity.getId())) {
				return Response.status(Response.Status.BAD_REQUEST).build();
			}

			E loadedEntity = dao.get(id);
			if (loadedEntity == null) {
				return Response.status(Response.Status.NOT_FOUND).build();
			}

			updater.updateEverything(loadedEntity, entity);

			dao.update(entity);
			return Response.ok().build();
		} catch (Exception e) {
			LOG.error("Error updating Game with ID : " + id, e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	public Response updateByPOST(final UriInfo info, final UUID id, final E entity) {
		try {
			if (id == null || entity == null || !id.equals(entity.getId())) {
				return Response.status(Response.Status.BAD_REQUEST).build();
			}

			E loadedEntity = dao.get(id);
			if (loadedEntity == null) {
				return Response.status(Response.Status.NOT_FOUND).build();
			}

			updater.updateNotNull(loadedEntity, entity);

			dao.update(entity);

			URL baseURL = new URL(info.getBaseUri());
			UUIDToRESTURLAdapter adapter = new UUIDToRESTURLAdapter(baseURL);
			URL urlToCreatedEntity = adapter.marshal(entity.getId());
			return Response.ok().location(urlToCreatedEntity.toURI()).build();
		} catch (Exception e) {
			LOG.error("Error updating Game with ID : " + id, e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	public Response delete(final UUID id) {
		try {
			if (id == null) {
				return Response.status(Response.Status.BAD_REQUEST).build();
			}

			E entity = dao.get(id);
			if (entity == null) {
				return Response.status(Response.Status.NOT_FOUND).build();
			}

			dao.remove(entity);
			return Response.ok().build();
		} catch (Exception e) {
			LOG.error("Error deleting game with ID : " + id, e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	private void setMarshallerOptions(final URL baseURL) throws MalformedURLException {
		Marshaller marshaller = context.getContext(this.getClass());
		marshaller.setAdapter(UUIDToRESTURLAdapter.class, new UUIDToRESTURLAdapter(baseURL));
	}
}
