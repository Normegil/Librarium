package be.normegil.librarium.model.rest.services;

public interface Updater<E> {

	void updateEverything(E toUpdate, E source);

	void updateNotNull(E toUpdate, E source);

}
