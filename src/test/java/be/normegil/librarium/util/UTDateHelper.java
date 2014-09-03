package be.normegil.librarium.util;

import be.normegil.librarium.ApplicationProperties;
import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.Assert.assertEquals;

public class UTDateHelper {

	private DateHelper entity;
	private LocalDate date;

	@Before
	public void setUp() throws Exception {
		entity = new DateHelper();
		date = LocalDate.of(2014, Month.SEPTEMBER, 3);
	}

	@After
	public void tearDown() throws Exception {
		date = null;
		entity = null;
	}

	@Test
	public void testFormat() throws Exception {
		ZonedDateTime zonedDateTime = ZonedDateTime.of(date.atStartOfDay(), ZoneId.systemDefault());
		String expected = zonedDateTime.format(ApplicationProperties.STANDARD_TIME_FORMAT);

		assertEquals(expected, entity.format(date));
	}

	@Test
	public void testParse() throws Exception {
		ZonedDateTime zonedDateTime = ZonedDateTime.of(date.atStartOfDay(), ZoneId.systemDefault());
		String toConvert = zonedDateTime.format(ApplicationProperties.STANDARD_TIME_FORMAT);

		assertEquals(date, entity.parse(toConvert));
	}
}
