package be.normegil.librarium.rest;

public interface Updater<E> {

	void updateEverything(E toUpdate, E source);

	void updateNotNull(E toUpdate, E source);

}
