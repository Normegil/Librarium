package be.normegil.librarium.rest;

import be.normegil.librarium.model.dao.GameDAO;
import be.normegil.librarium.model.data.game.Game;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.util.Collection;

@Path("/games")
public class GameREST {

	public static final String ERROR_MESSAGE = "Error while %s game";

	@Inject
	private Logger log;

	private GameDAO gameDAO;

	@GET
	@Path("/all")
	@Produces({"application/json", "application/xml"})
	public Response getGamesList() {
		try {
			Collection<Game> games = gameDAO.getAll();
			GenericEntity<Collection<Game>> entity = new GenericEntity<Collection<Game>>(games) {
			};
			return Response.ok(entity).build();
		} catch (Exception e) {
			log.error(String.format(ERROR_MESSAGE, "getting all"), e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Path("{ID}")
	@Produces({"application/json", "application/xml"})
	public Response getGame(@PathParam("ID") Long id) {
		try {
			if (id == null) {
				return Response.status(Response.Status.BAD_REQUEST).build();
			}

			Game game = gameDAO.get(id);
			if (game == null) {
				return Response.status(Response.Status.BAD_REQUEST).build();
			} else {
				return Response.ok(game).build();
			}
		} catch (Exception e) {
			log.error(String.format(ERROR_MESSAGE, "getting"), e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PUT
	@Consumes({"application/json", "application/xml"})
	public Response createGame(Game game) {
		try {
			if (game == null || game.getId() != null) {
				return Response.status(Response.Status.BAD_REQUEST).build();
			}

			gameDAO.save(game);
			return Response.ok().build();
		} catch (Exception e) {
			log.error(String.format(ERROR_MESSAGE, "creating"), e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@POST
	@Path("{ID}")
	@Consumes({"application/json", "application/xml"})
	@Transactional(Transactional.TxType.REQUIRED)
	public Response updateGame(@PathParam("ID") Long id,
	                           Game sendedGame) {

		try {
			if (id == null || sendedGame == null || !id.equals(sendedGame.getId())) {
				return Response.status(Response.Status.BAD_REQUEST).build();
			}

			Game game = gameDAO.get(id);
			if (game == null) {
				return Response.status(Response.Status.BAD_REQUEST).build();
			}

			game.setTitle(sendedGame.getTitle());
			gameDAO.save(game);
			return Response.ok().build();
		} catch (Exception e) {
			log.error(String.format(ERROR_MESSAGE, "updating"), e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DELETE
	@Path("{ID}")
	@Transactional(Transactional.TxType.REQUIRED)
	public Response deleteGame(@PathParam("ID") Long id) {
		try {
			if (id == null) {
				return Response.status(Response.Status.BAD_REQUEST).build();
			}

			Game game = gameDAO.get(id);
			if (game == null) {
				return Response.status(Response.Status.BAD_REQUEST).build();
			}

			gameDAO.remove(game);
			return Response.ok().build();
		} catch (Exception e) {
			log.error(String.format(ERROR_MESSAGE, "deleting"), e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@Inject
	public void setGameDAO(final GameDAO gameDAO) {
		this.gameDAO = gameDAO;
	}
}
