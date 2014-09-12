package be.normegil.librarium.rest.game;

import be.normegil.librarium.Constants;
import be.normegil.librarium.model.dao.DAO;
import be.normegil.librarium.model.data.Media;
import be.normegil.librarium.model.data.game.Game;
import be.normegil.librarium.model.data.game.GameSerie;
import be.normegil.librarium.model.data.people.Responsible;
import be.normegil.librarium.rest.AbstractMediaRestService;
import be.normegil.librarium.rest.RESTService;
import be.normegil.librarium.rest.Updater;
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
public class GameREST implements RESTService<Game>, Updater<Game>{

	private DAO<Game> dao;
	private Logger logger;
	private ContextResolver<Marshaller> context;
	@Inject
	private Updater<Media> updater;

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

	@Override
	public void updateEverything(final Game toUpdate, final Game source) {
		toUpdate.addAllDevelopers(source.getDevelopers());
		toUpdate.addAllEditors(source.getEditors());
		toUpdate.addAllComposers(source.getComposers());
		toUpdate.setSerie(source.getSerie());
	}

	@Override
	public void updateNotNull(final Game toUpdate, final Game source) {
		updater.updateNotNull(toUpdate, source);

		GameSerie serie = source.getSerie();
		if (serie != null) {
			toUpdate.setSerie(serie);
		}

		Set<Responsible> developers = source.getDevelopers();
		if (developers != null) {
			toUpdate.addAllDevelopers(developers);
		}

		Set<Responsible> editors = source.getEditors();
		if (editors != null) {
			toUpdate.addAllEditors(editors);
		}

		Set<Responsible> composers = source.getComposers();
		if (composers != null) {
			toUpdate.addAllComposers(composers);
		}
	}


}