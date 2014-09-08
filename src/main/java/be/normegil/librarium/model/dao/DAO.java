package be.normegil.librarium.model.dao;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Collection;

public interface DAO<E> {

	Collection<E> getAll();

	E get(@NotNull Object id);

	void create(@NotNull @Valid E entity);

	E update(@NotNull @Valid E entity);

	void remove(@NotNull @Valid E entity);

}
