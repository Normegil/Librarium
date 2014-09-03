package be.normegil.librarium.util;

import be.normegil.librarium.ApplicationProperties;
import be.normegil.librarium.validation.constraint.ValidDateFormat;

import javax.ejb.Stateless;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Stateless
public class DateHelper {
	public String format(@NotNull LocalDate date) {
		ZonedDateTime zonedDateTime = ZonedDateTime.of(date.atStartOfDay(), ZoneId.systemDefault());
		return zonedDateTime.format(ApplicationProperties.STANDARD_TIME_FORMAT);
	}

	public LocalDate parse(@ValidDateFormat String date) {
		Instant instant = Instant.parse(date);
		ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());
		return zonedDateTime.toLocalDate();
	}
}
