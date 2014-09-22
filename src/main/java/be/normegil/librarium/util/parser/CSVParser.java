package be.normegil.librarium.util.parser;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;

import javax.validation.constraints.NotNull;
import java.io.InputStream;
import java.io.OutputStream;

public class CSVParser<E> implements DocumentParser<E> {

	private Class<? extends E> entityClass;

	public CSVParser(Class<? extends E> entityClass) {
		this.entityClass = entityClass;
	}

	@Override
	public E from(@NotNull final InputStream stream) {
		CsvMapper mapper = new CsvMapper();
		mapper.reader(entityClass).with(/* Schema*/);
		return ;
	}

	@Override
	public void to(@NotNull final E entity, @NotNull final OutputStream stream) {

	}
}
