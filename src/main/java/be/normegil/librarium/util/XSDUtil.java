package be.normegil.librarium.util;

import be.normegil.librarium.annotation.XSD;

import javax.validation.constraints.NotNull;
import java.net.URL;

public class XSDUtil {

	public static final String XSD_FOLDER_PATH = "/XSD/";

	public URL getSchema(@NotNull Class clazz) {
		XSD xsdAnnotation = (XSD) clazz.getAnnotation(XSD.class);
		String path = xsdAnnotation.value();
		return getUrlToXSD(clazz, path);
	}

	private URL getUrlToXSD(@NotNull final Class clazz, final String path) {
		String pathToXSD = XSD_FOLDER_PATH + path;
		URL resource = getClass().getResource(pathToXSD);
		if (resource == null) {
			throw new IllegalArgumentException(clazz.getCanonicalName() + " has an empty or wrong @XSD Annotation [Path found = " + path + "]");
		}
		return resource;
	}
}
