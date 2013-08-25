/*
 *
 */
package org.eftp.ftpserver.business.monitoring.boundary;

/*
 * #%L
 * ftpserver-st
 * %%
 * Copyright (C) 2013 e2ftp
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

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
