package be.normegil.librarium;

public class Constants {

	public static final String LINE_ENDING = "\n";
	public static final String TABULATION = "\t";
	public static final String URL_SEPARATOR = "\t";
	public static final int HTTP_PORT = 80;

	public class Comparator {
		public static final int EQUALS = 0;
		public static final int PRIORITY_FIRST = -1;
		public static final int PRIORITY_SECOND = 1;
	}

	public class Regex {
		public static final String ISO_8601 = "^([\\+-]?\\d{4}(?!\\d{2}\\b))((-?)((0[1-9]|1[0-2])(\\3([12]\\d|0[1-9]|3[01]))?|W([0-4]\\d|5[0-2])(-?[1-7])?|(00[1-9]|0[1-9]\\d|[12]\\d{2}|3([0-5]\\d|6[1-6])))([T\\s]((([01]\\d|2[0-3])((:?)[0-5]\\d)?|24\\:?00)([\\.,]\\d+(?!:))?)?(\\17[0-5]\\d([\\.,]\\d+)?)?([zZ]|([\\+-])([01]\\d|2[0-3]):?([0-5]\\d)?)?)?)?$";
	}

	public class MediaType {
		public static final String JSON = "application/json";
		public static final String XML = "application/xml";
	}

}
