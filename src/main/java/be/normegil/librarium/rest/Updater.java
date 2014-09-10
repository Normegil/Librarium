package be.normegil.librarium.rest;

public interface Updater<E> {

	void updateEverything(E toUpdate, E Source);

	void updateNotNull(E toUpdate, E source);

}
