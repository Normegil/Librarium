package be.normegil.librarium.util.parser;

import be.normegil.librarium.WarningTypes;

import javax.validation.constraints.NotNull;
import java.io.InputStream;
import java.io.OutputStream;

public interface DocumentParser<E> {
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	E from(@NotNull InputStream stream);

	void to(@NotNull E entity, @NotNull OutputStream stream);
}
