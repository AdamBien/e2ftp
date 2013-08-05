/*
 *
 */
package org.eftp.ftpserver.business.monitoring.boundary;

import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author adam-bien.com
 */
public class StatisticsResourceIT {

    private Client client;

    private static final String STATISTICS_URI = "http://localhost:8080/ftpserver/api/statistics";
    private WebTarget statisticsTarget;

    @Before
    public void init() {
        this.client = ClientBuilder.newClient();
        this.statisticsTarget = this.client.target(STATISTICS_URI);
    }

    @Test
    public void fetchStatistics() {
        JsonObject statistics = this.statisticsTarget.request(MediaType.APPLICATION_JSON).get(JsonObject.class);
        assertFalse(statistics.isEmpty());
    }
}
