package be.normegil.librarium.util.persistence;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.util.DateHelper;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.LocalDate;

@Converter(autoApply = true)
@SuppressWarnings(WarningTypes.UNUSED)
public class LocalDatePersistenceConverter implements AttributeConverter<LocalDate, String> {

	private DateHelper dateHelper = new DateHelper();

	@Override
	public String convertToDatabaseColumn(final LocalDate date) {
		if (date != null) {
			return dateHelper.format(date);
		} else {
			return null;
		}
	}

	@Override
	public LocalDate convertToEntityAttribute(final String date) {
		if (date == null || date.isEmpty()) {
			return null;
		} else {
			return dateHelper.parseLocalDate(date);
		}
	}
}
