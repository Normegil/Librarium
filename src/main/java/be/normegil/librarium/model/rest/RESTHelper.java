package be.normegil.librarium.model.rest;

import be.normegil.librarium.Constants;
import be.normegil.librarium.annotation.Default;
import be.normegil.librarium.libraries.ClassWrapper;
import be.normegil.librarium.model.data.Entity;
import be.normegil.librarium.rest.RESTService;
import be.normegil.librarium.util.exception.RESTServiceNotFoundException;

import javax.ws.rs.Path;
import java.net.URI;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class RESTHelper {

	private Set<RESTService> restServices = new HashSet<>();

	public String getPathFor(Class aClass) {
		RESTService service = getDefaultServiceFor(aClass);
		Path annotation = service.getClass().getAnnotation(Path.class);
		return annotation.value();
	}

	private RESTService getDefaultServiceFor(final Class aClass) {
		Set<RESTService> services = getServicesFor(aClass);
		for (RESTService service : services) {
			Default isDefault = service.getClass().getAnnotation(Default.class);
			if (isDefault != null) {
				return service;
			}
		}

		if (services.size() == 1) {
			return services.iterator().next();
		} else if (services.size() == 0) {
			throw new RESTServiceNotFoundException("No REST Service found for " + aClass);
		} else {
			throw new IllegalStateException("Application has more than one service for " + aClass + " but no @Default annotated service");
		}
	}

	public Set<RESTService> getServicesFor(final Class aClass) {
		Set<RESTService> services = new HashSet<>();
		for (RESTService restService : restServices) {
			if (aClass.isAssignableFrom(restService.getSupportedClass())) {
				services.add(restService);
			}
		}
		return services;
	}

	public Collection<URI> getRESTUri(final URI baseUri, final Class<? extends Entity> dataClass, final Collection<? extends Entity> entities) {
		Set<URI> uris = new HashSet<>();
		for (Entity entity : entities) {
			uris.add(getRESTUri(baseUri, dataClass, entity));
		}
		return uris;
	}

	public URI getRESTUri(final URI baseUri, final Class<? extends Entity> dataClass, final Entity entity) {
		String baseUriAsString = baseUri.toString();
		RESTHelper helper = new RESTHelper();
		return URI.create(baseUriAsString + Constants.URL.PATH_SEPARATOR + helper.getPathFor(dataClass) + Constants.URL.PATH_SEPARATOR + entity.getId());
	}

}
