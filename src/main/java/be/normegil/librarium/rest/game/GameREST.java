package be.normegil.librarium.rest.game;

import be.normegil.librarium.Constants;
import be.normegil.librarium.model.dao.DAO;
import be.normegil.librarium.model.data.game.Game;
import be.normegil.librarium.model.data.game.GameSerie;
import be.normegil.librarium.model.data.people.Responsible;
import be.normegil.librarium.rest.AbstractMediaRestService;
import be.normegil.librarium.rest.RESTService;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.ContextResolver;
import javax.xml.bind.Marshaller;
import java.util.Set;
import java.util.UUID;

@Path(Constants.URL_SEPARATOR + "games")
public class GameREST implements RESTService<Game> {

	private DAO<Game> dao;
	private Logger logger;
	private ContextResolver<Marshaller> context;

	@Override
	protected void update(final Game loadedEntity, final Game entity) {
		super.update(loadedEntity, entity);
		loadedEntity.addAllDevelopers(entity.getDevelopers());
		loadedEntity.addAllEditors(entity.getEditors());
		loadedEntity.addAllComposers(entity.getComposers());
		loadedEntity.setSerie(entity.getSerie());
	}

	@Override
	protected void updateNullCheck(final Game loadedEntity, final Game entity) {
		super.updateNullCheck(loadedEntity, entity);

		GameSerie serie = entity.getSerie();
		if (serie != null) {
			loadedEntity.setSerie(serie);
		}

		Set<Responsible> developers = entity.getDevelopers();
		if (developers != null) {
			loadedEntity.addAllDevelopers(developers);
		}

		Set<Responsible> editors = entity.getEditors();
		if (editors != null) {
			loadedEntity.addAllEditors(editors);
		}

		Set<Responsible> composers = entity.getComposers();
		if (composers != null) {
			loadedEntity.addAllComposers(composers);
		}
	}

	@Override
	public Response getAll(@Context final UriInfo info, final Long offset, final Integer limit) {
		return null;
	}

	@Override
	public Response get(@Context final UriInfo info, final UUID id) {
		return null;
	}

	@Override
	public Response create(@Context final UriInfo info, final Game entity) {
		return null;
	}

	@Override
	public Response updateByPUT(final UUID id, final Game entity) {
		return null;
	}

	@Override
	public Response updateByPOST(@Context final UriInfo info, final UUID id, final Game entity) {
		return null;
	}

	@Override
	public Response delete(final UUID id) {
		return null;
	}
}
