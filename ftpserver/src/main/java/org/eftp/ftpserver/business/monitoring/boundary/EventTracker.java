/*
 *
 */
package org.eftp.ftpserver.business.monitoring.boundary;

import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.eftp.events.Command;
import org.eftp.events.FtpEvent;
import org.eftp.ftpserver.business.logger.boundary.Log;

/**
 *
 * @author adam-bien.com
 */
@Stateless
public class EventTracker {

    @Inject
    Log LOG;

    public void onFtpEvent(@Observes @Command FtpEvent event) {
        LOG.info("Received event: " + event);
    }
}
