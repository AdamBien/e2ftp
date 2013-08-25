/*
 *
 */
package org.eftp.ftpserver.business.configuration;

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

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.eftp.ftpserver.business.RESTSupport;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author adam-bien.com
 */
public class ConfigurationResourceIT extends RESTSupport {

    private static final String CONFIGURATION_URI = "http://localhost:8080/ftpserver/api/configuration";

    @Before
    public void init() {
        super.init(CONFIGURATION_URI, "duke", "duke");
    }

    @Test
    public void crud() {
        JsonArray allEntries = this.mainTarget.request(MediaType.APPLICATION_JSON).get(JsonArray.class);
        assertFalse(allEntries.isEmpty());
        System.out.println("-- " + allEntries);
        String name = "hugo " + System.currentTimeMillis();
        //creation
        JsonObject newEntry = Json.createObjectBuilder().add("name", name).add("value", 42).build();
        Response putResponse = this.mainTarget.request().put(Entity.entity(newEntry, MediaType.APPLICATION_JSON));
        assertThat(putResponse.getStatus(), is(204));

        //fetch
        Response fetchResponse = this.mainTarget.path(name).request(MediaType.APPLICATION_JSON).get(Response.class);
        assertThat(fetchResponse.getStatus(), is(200));
        JsonObject value = fetchResponse.readEntity(JsonObject.class);
        assertThat(value.getString("value"), is("42"));

        //removal
        Response deleteResponse = this.mainTarget.path(name).request(MediaType.APPLICATION_JSON).delete();
        assertThat(deleteResponse.getStatus(), is(204));

        //empty fetch
        Response emptyFetchResponse = this.mainTarget.path(name).request(MediaType.APPLICATION_JSON).get(Response.class);
        assertThat(emptyFetchResponse.getStatus(), is(204));

    }

}
