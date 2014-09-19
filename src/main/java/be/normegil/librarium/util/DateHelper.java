package be.normegil.librarium.util;

import be.normegil.librarium.ApplicationProperties;
import be.normegil.librarium.validation.constraint.ValidDateFormat;

import javax.ejb.Stateless;
import javax.validation.constraints.NotNull;
import java.time.*;

@Stateless
public class DateHelper {
	public String format(@NotNull LocalDate date) {
		ZonedDateTime zonedDateTime = ZonedDateTime.of(date.atStartOfDay(), ZoneId.systemDefault());
		return toString(zonedDateTime);
	}


	public LocalDate parseLocalDate(@ValidDateFormat String date) {
		ZonedDateTime zonedDateTime = from(date);
		return zonedDateTime.toLocalDate();
	}

	public String format(@NotNull final LocalDateTime time) {
		ZonedDateTime zonedDateTime = ZonedDateTime.of(time, ZoneId.systemDefault());
		return toString(zonedDateTime);
	}

	public LocalDateTime parseLocalDateTime(@ValidDateFormat String date) {
		ZonedDateTime zonedDateTime = from(date);
		return zonedDateTime.toLocalDateTime();
	}

	private String toString(final ZonedDateTime zonedDateTime) {
		return zonedDateTime.format(ApplicationProperties.STANDARD_TIME_FORMAT);
	}

	private ZonedDateTime from(final String date) {
		Instant instant = Instant.parse(date);
		return instant.atZone(ZoneId.systemDefault());
	}
}
