/*
 *
 */
package org.eftp.ftpserver.business.monitoring.boundary;

import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;
import org.eftp.ftpserver.business.RESTSupport;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author adam-bien.com
 */
public class StatisticsResourceIT extends RESTSupport {

    private Client client;

    private static final String STATISTICS_URI = "http://localhost:8080/ftpserver/api/statistics";

    @Override
    @Before
    public void init() {
        super.init(STATISTICS_URI, "duke", "duke");
    }

    @Test
    public void fetchStatistics() {
        JsonObject statistics = this.mainTarget.request(MediaType.APPLICATION_JSON).get(JsonObject.class);
        assertFalse(statistics.isEmpty());
    }
}
