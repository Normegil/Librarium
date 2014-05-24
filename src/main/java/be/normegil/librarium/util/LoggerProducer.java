package be.normegil.librarium.util;

import be.normegil.librarium.WarningTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

@Stateless
@SuppressWarnings(WarningTypes.UNUSED)
public class LoggerProducer {

    @Produces
    @Default
    public Logger createLogger(final InjectionPoint ip){
        return LoggerFactory.getLogger(ip.getMember().getDeclaringClass());
    }

}
