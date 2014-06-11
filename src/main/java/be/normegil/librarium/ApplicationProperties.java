package be.normegil.librarium;

import be.normegil.librarium.rest.GameREST;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/" + ApplicationProperties.BASE_PATH)
public class ApplicationProperties extends Application {

    public static final String ENCOODING = "UTF-8";
    public static final String BASE_PATH = "rest";

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<Class<?>>();
        classes.add(GameREST.class);
        return classes;
    }
}
