package be.normegil.librarium.util;

import be.normegil.librarium.annotation.XSD;

import java.net.URL;

public class XSDUtil {

    public static final String XSD_FOLDER_PATH = "/XSD/";

    public URL getSchema(Class clazz) {
        XSD xsdAnnotation = (XSD) clazz.getAnnotation(XSD.class);
        if (xsdAnnotation != null) {
            String path = xsdAnnotation.value();
            return getUrlToXSD(clazz, path);
        } else {
            throw new IllegalArgumentException(clazz.getCanonicalName() + " doesn't contain any @XSD Annotation");
        }
    }

    private URL getUrlToXSD(final Class clazz, final String path) {
        String pathToXSD = XSD_FOLDER_PATH + path;
        URL resource = getClass().getResource(pathToXSD);
        if (resource == null) {
            throw new IllegalArgumentException(clazz.getCanonicalName() + " has an empty or wrong @XSD Annotation [Path found = " + path + "]");
        }
        return resource;
    }
}
