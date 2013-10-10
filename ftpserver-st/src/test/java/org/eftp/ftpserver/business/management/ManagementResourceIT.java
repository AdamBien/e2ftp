/*
 *
 */
package org.eftp.ftpserver.business.management;

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

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.eftp.ftpserver.business.RESTSupport;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author ZeTo
 */
public class ManagementResourceIT extends RESTSupport {

    private static final String MANAGEMENT_URI = "http://localhost:8080/ftpserver/api/management";

    @Override
    @Before
    public void init() {
        super.init(MANAGEMENT_URI, "duke", "duke");
        // make sure server is started
        this.mainTarget.request(MediaType.WILDCARD).post(Entity.form(new Form()));
    }

    @Test
    public void checkServerIsRunning() {
        boolean isRunning = this.mainTarget.request(MediaType.WILDCARD).get(Boolean.class);
        assertTrue(isRunning);
    }

    @Test
    public void testStopServer() {
        Response stopServerResponse = this.mainTarget.request(MediaType.WILDCARD).delete();
        assertThat(stopServerResponse.getStatus(), is(204));
    }

    @Test
    public void testStartServer() {
        // stop the server
        Response stopServerResponse = this.mainTarget.request(MediaType.WILDCARD).delete();
        assertThat(stopServerResponse.getStatus(), is(204));

        // server should not run at this point
        boolean isRunning = this.mainTarget.request(MediaType.WILDCARD).get(Boolean.class);
        assertFalse(isRunning);

        // start it again
        Response startServerResponse = this.mainTarget.request(MediaType.WILDCARD).post(Entity.form(new Form()));
        assertThat(startServerResponse.getStatus(), is(204));

        // check if server is running
        isRunning = this.mainTarget.request(MediaType.WILDCARD).get(Boolean.class);
        assertTrue(isRunning);
    }

    @Test
    public void testRestartServer() {
        // restart the server
        Response restartServerResponse = this.mainTarget.request(MediaType.WILDCARD).put(Entity.form(new Form()));
        assertThat(restartServerResponse.getStatus(), is(204));

        // server should run after restart
        boolean isRunning = this.mainTarget.request(MediaType.WILDCARD).get(Boolean.class);
        assertTrue(isRunning);
    }
}
