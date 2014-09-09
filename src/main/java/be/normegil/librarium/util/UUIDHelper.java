package be.normegil.librarium.util;

import be.normegil.librarium.Constants;
import be.normegil.librarium.libraries.URL;

import java.util.UUID;

public class UUIDHelper {

	public URL toURL(final URL baseURL, final UUID uuid) {
		URL finalURL = baseURL;
		String baseFilePath = baseURL.getFilePath();
		if (!baseFilePath.endsWith(Constants.URL_SEPARATOR)) {
			finalURL.addToPath(Constants.URL_SEPARATOR);
		}
		return baseURL.addToPath(uuid.toString());
	}

}
