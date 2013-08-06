/*
 *
 */
package org.eftp.ftpserver.business.logger.boundary;

import java.util.logging.Logger;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

/**
 *
 * @author adam-bien.com
 */
public class LoggerProducer {

    @Produces
    public Logger produce(InjectionPoint ip) {
        String clazz = ip.getMember().getDeclaringClass().getName();
        return Logger.getLogger(clazz);
    }
}
