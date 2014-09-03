package be.normegil.librarium.model.dao;

import java.util.Collection;

public interface DAO<E> {

	Collection<E> getAll();

	E get(Long id);

	void save(E object);

	void remove(E object);

}
