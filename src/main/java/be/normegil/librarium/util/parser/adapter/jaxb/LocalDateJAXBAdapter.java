package be.normegil.librarium.util.parser.adapter.jaxb;

import be.normegil.librarium.util.DateHelper;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;

public class LocalDateJAXBAdapter extends XmlAdapter<String, LocalDate> {

	private DateHelper dateHelper = new DateHelper();

	@Override
	public LocalDate unmarshal(final String date) throws Exception {
		if (date == null || date.isEmpty()) {
			return null;
		} else {
			return dateHelper.parseLocalDate(date);
		}
	}

	@Override
	public String marshal(final LocalDate date) throws Exception {
		if (date != null) {
			return dateHelper.format(date);
		} else {
			return null;
		}
	}
}
