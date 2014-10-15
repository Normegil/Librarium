package be.normegil.librarium.util;

import be.normegil.librarium.ApplicationProperties;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.*;

import static org.junit.Assert.assertEquals;

public class UTDateHelper {

	private DateHelper entity;

	@Before
	public void setUp() throws Exception {
		entity = new DateHelper();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test
	public void testFormatLocalDate() throws Exception {
		LocalDate date = LocalDate.of(2014, Month.SEPTEMBER, 3);
		ZonedDateTime zonedDateTime = ZonedDateTime.of(date.atStartOfDay(), ZoneId.systemDefault());
		String expected = zonedDateTime.format(ApplicationProperties.STANDARD_TIME_FORMAT);

		assertEquals(expected, entity.format(date));
	}

	@Test
	public void testParseLocalDate() throws Exception {
		LocalDate date = LocalDate.of(2014, Month.SEPTEMBER, 3);
		ZonedDateTime zonedDateTime = ZonedDateTime.of(date.atStartOfDay(), ZoneId.systemDefault());
		String toConvert = zonedDateTime.format(ApplicationProperties.STANDARD_TIME_FORMAT);

		assertEquals(date, entity.parseLocalDate(toConvert));
	}

	@Test
	public void testFormatLocalDateTime() throws Exception {
		LocalDateTime time = LocalDateTime.of(2014, Month.SEPTEMBER, 3, 12, 25);
		ZonedDateTime zonedDateTime = ZonedDateTime.of(time, ZoneId.systemDefault());
		String expected = zonedDateTime.format(ApplicationProperties.STANDARD_TIME_FORMAT);

		assertEquals(expected, entity.format(time));
	}

	@Test
	public void testParseLocalDateTime() throws Exception {
		LocalDateTime time = LocalDateTime.of(2014, Month.SEPTEMBER, 3, 12, 25);
		ZonedDateTime zonedDateTime = ZonedDateTime.of(time, ZoneId.systemDefault());
		String toConvert = zonedDateTime.format(ApplicationProperties.STANDARD_TIME_FORMAT);

		assertEquals(time, entity.parseLocalDateTime(toConvert));
	}
}
