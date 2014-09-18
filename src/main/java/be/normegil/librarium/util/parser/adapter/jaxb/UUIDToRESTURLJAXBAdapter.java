package be.normegil.librarium.util.parser.adapter.jaxb;

import be.normegil.librarium.Constants;
import be.normegil.librarium.libraries.URL;
import org.apache.commons.lang3.StringUtils;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.UUID;

public class UUIDToRESTURLJAXBAdapter extends XmlAdapter<URL, UUID> {

	private final URL baseURL;

	public UUIDToRESTURLJAXBAdapter(URL baseURL) {
		this.baseURL = baseURL;
	}

	@Override
	public UUID unmarshal(final URL v) {
		String uuidString = StringUtils.substringAfterLast(v.toRepresentation(), Constants.URL.PATH_SEPARATOR);
		return UUID.fromString(uuidString);
	}

	@Override
	public URL marshal(final UUID v) {
		String uuidAsString = v.toString();
		String pathToAdd = Constants.URL.PATH_SEPARATOR + uuidAsString;
		return baseURL.addToPath(pathToAdd);
	}
}
