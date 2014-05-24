package be.normegil.librarium;

import be.normegil.librarium.rest.GameREST;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/rest")
public class ApplicationProperties extends Application {

    public static final String ENCOODING = "UTF-8";

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<Class<?>>();
        classes.add(GameREST.class);
        return classes;
    }
}
