package be.normegil.librarium.libraries;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;

public class URL {

    private java.net.URL url;

    public URL(java.net.URL url) {
        this.url = url;
    }

    public File toFile() {
        URI uri = toURI();
        return new File(uri);
    }

    public Path toPath() {
        File file = toFile();
        return file.toPath();
    }

    public URI toURI() {
        try {
            return url.toURI();
        } catch (URISyntaxException e) {
            throw new be.normegil.librarium.util.exception.URISyntaxException(e);
        }
    }

    public java.net.URL getOriginalURL() {
        return url;
    }
}
