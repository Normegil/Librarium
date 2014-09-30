package be.normegil.librarium.rest;

import be.normegil.librarium.Constants;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.UUID;

public interface RESTService<E> {

	@GET
	@Produces({Constants.MediaType.JSON, Constants.MediaType.XML})
	Response getAll(@Context UriInfo info, @PathParam("offset") Long offset, @PathParam("limit") Integer limit);

	@Path(Constants.URL.PATH_SEPARATOR + "{ID}")
	@GET
	@Produces({Constants.MediaType.JSON, Constants.MediaType.XML})
	Response get(@Context UriInfo info, @PathParam("ID") UUID id);

	@PUT
	@Consumes({Constants.MediaType.JSON, Constants.MediaType.XML})
	Response create(@Context UriInfo info, E entity);

	@Path(Constants.URL.PATH_SEPARATOR + "{ID}")
	@PUT
	Response updateByPUT(@PathParam("ID") UUID id, E entity);

	@Path(Constants.URL.PATH_SEPARATOR + "{ID}")
	@POST
	Response updateByPOST(@Context UriInfo info, @PathParam("ID") UUID id, E entity);

	@DELETE
	@Path(Constants.URL.PATH_SEPARATOR + "{ID}")
	Response delete(@PathParam("ID") UUID id);


}
