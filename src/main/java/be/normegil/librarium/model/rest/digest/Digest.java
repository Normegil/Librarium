package be.normegil.librarium.model.rest.digest;

public interface Digest<E> {

	E toBase();

	Digest<E> fromBase(E entity);

}
