package be.normegil.librarium.util.parser.adapter.jaxb;

import be.normegil.librarium.libraries.URL;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class URLJAXBAdapter extends XmlAdapter<String, URL> {
	@Override
	public URL unmarshal(final String url) throws Exception {
		if (url == null || url.isEmpty()) {
			return null;
		} else {
			return new URL(url);
		}
	}

	@Override
	public String marshal(final URL url) throws Exception {
		if (url != null) {
			return url.toRepresentation();
		} else {
			return null;
		}
	}
}
