package be.normegil.librarium.model.dao;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;

public interface DAO<E> {

	List<E> getAll();

	List<E> getAll(long offset, int limit);

	long getNumberOfElements();

	E get(@NotNull Object id);

	void create(@NotNull @Valid E entity);

	E update(@NotNull @Valid E entity);

	void remove(@NotNull @Valid E entity);

}
