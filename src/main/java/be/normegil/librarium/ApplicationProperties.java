package be.normegil.librarium;

import be.normegil.librarium.model.rest.services.game.GameREST;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath(Constants.URL.PATH_SEPARATOR + ApplicationProperties.BASE_PATH)
public class ApplicationProperties extends Application {

	public static final String ENCODING = "UTF-8";
	public static final DateTimeFormatter STANDARD_TIME_FORMAT = DateTimeFormatter.ISO_INSTANT;
	public static final String BASE_PATH = "rest";
	public static final String PERSISTENCE_UNIT_NAME = "MainPU";
	public static final ToStringStyle TO_STRING_STYLE = ToStringStyle.SHORT_PREFIX_STYLE;

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = new HashSet<Class<?>>();
		classes.add(GameREST.class);
		return classes;
	}

	public class REST {
		public static final int DEFAULT_LIMIT = 25;
		public static final int MAX_LIMIT = 500;
	}
}
