/*
 *
 */
package org.eftp.ftpserver.business.logger.boundary;

import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author adam-bien.com
 */
public class Log {

    private Logger logger;

    @Inject
    public Log(Logger logger) {
        this.logger = logger;
    }

    public void info(String message) {
        this.logger.info(message);
    }

}
