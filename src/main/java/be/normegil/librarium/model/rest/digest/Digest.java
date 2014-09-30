package be.normegil.librarium.model.rest.digest;

import java.net.URI;

public interface Digest<E> {

	E toBase();

	void fromBase(final URI baseURI, final E entity);

}
