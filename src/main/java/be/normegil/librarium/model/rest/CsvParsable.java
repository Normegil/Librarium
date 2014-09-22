package be.normegil.librarium.model.rest;

import com.fasterxml.jackson.dataformat.csv.CsvSchema;

public interface CsvParsable {

	CsvSchema getSchema();

}
