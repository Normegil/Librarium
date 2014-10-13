package be.normegil.librarium.tool;

import be.normegil.librarium.libraries.URL;

public class URLFactory implements DataFactory<URL> {

	private static final String PROTOCOL = "http";
	private static final String HOST = "Host";
	private static final int HIGHEST_PORT_NUMBER = 65535;
	private static final String FILE_PATH = "/File";
	private static final int PORT = 42;
	private static Long index = 0L;

	@Override
	public URL getDefault() {
		return getDefault(true, false);
	}

	@Override
	public URL getNew() {
		return getNew(true, false);
	}

	@Override
	public URL getDefault(final boolean withLink, final boolean withIds) {
		return new URL(PROTOCOL, HOST, PORT, FILE_PATH);
	}

	@Override
	public URL getNew(final boolean withLink, final boolean withIds) {
		String host = HOST + index;
		int port = (int) (index % HIGHEST_PORT_NUMBER);
		String filePath = FILE_PATH + index;

		index += 1;
		return new URL(PROTOCOL, host, port, filePath);
	}
}
