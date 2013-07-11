package org.eftp.ftpserver.business.monitoring.boundary;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import org.apache.ftpserver.ftplet.FtpStatistics;

/**
 *
 * @author adam-bien.com
 */
@Stateless
@Path("statistics")
public class StatisticsResource {

    @Inject
    FtpStatistics statistics;

    @GET
    public String all() {
        return this.statistics.toString();
    }
}
