/*
 *
 */
package org.eftp.ftpserver.business.hooks.boundary;

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

import java.util.List;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.eftp.ftpserver.business.RESTSupport;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author adam-bien.com
 */
public class HooksResourceIT extends RESTSupport {

    private static final String CONFIGURATION_URI = "http://localhost:8080/ftpserver/api/hooks";

    @Before
    @Override
    public void init() {
        super.init(CONFIGURATION_URI, "duke", "duke");
    }

    @Test
    public void crud() {
        JsonArray allEntries = this.mainTarget.request(MediaType.APPLICATION_JSON).get(JsonArray.class);
        assertNotNull(allEntries);
        System.out.println("-- " + allEntries);
        final String createdUri = "http://localhost" + System.currentTimeMillis();

        //creation
        JsonObject newEntry = Json.createObjectBuilder().add("command", "everything").add("uri", createdUri).build();
        Response postResponse = this.mainTarget.request().post(Entity.entity(newEntry, MediaType.APPLICATION_JSON));
        assertThat(postResponse.getStatus(), is(201));
        List<Object> locationValues = postResponse.getHeaders().get("Location");
        assertThat(locationValues.size(), is(1));
        String uri = (String) locationValues.get(0);
        System.out.println("---------> " + uri);
        //fetch
        Response fetchResponse = this.client.target(uri).request(MediaType.APPLICATION_JSON).get(Response.class);
        assertThat(fetchResponse.getStatus(), is(200));
        JsonObject value = fetchResponse.readEntity(JsonObject.class);
        assertThat(value.getString("uri"), is(createdUri));
        long id = value.getJsonNumber("id").longValue();
        System.out.println("id --->" + id);

        //removal
        Response deleteResponse = this.mainTarget.path(String.valueOf(id)).request(MediaType.APPLICATION_JSON).delete();
        assertThat(deleteResponse.getStatus(), is(204));

        //empty fetch
        Response emptyFetchResponse = this.mainTarget.path(String.valueOf(id)).request(MediaType.APPLICATION_JSON).get(Response.class);
        assertThat(emptyFetchResponse.getStatus(), is(204));
    }

    @Test
    public void creationWithInvalidURI() {
        final String createdUri = "INVALID" + System.currentTimeMillis();
        //creation
        JsonObject newEntry = Json.createObjectBuilder().add("command", "everything").add("uri", createdUri).build();
        Response putResponse = this.mainTarget.request().post(Entity.entity(newEntry, MediaType.APPLICATION_JSON));
        assertThat(putResponse.getStatus(), is(Response.Status.BAD_REQUEST.getStatusCode()));
    }

    @Test
    public void creationWithoutCommand() {
        final String createdUri = "http://localhost" + System.currentTimeMillis();
        //creation
        JsonObject newEntry = Json.createObjectBuilder().add("uri", createdUri).build();

        Response postResponse = this.mainTarget.request().post(Entity.entity(newEntry, MediaType.APPLICATION_JSON));
        assertThat(postResponse.getStatus(), is(201));

        List<Object> locationValues = postResponse.getHeaders().get("Location");
        assertThat(locationValues.size(), is(1));
        String uri = (String) locationValues.get(0);

        //fetch
        Response fetchResponse = this.client.target(uri).request(MediaType.APPLICATION_JSON).get(Response.class);
        assertThat(fetchResponse.getStatus(), is(200));
        JsonObject value = fetchResponse.readEntity(JsonObject.class);
        assertThat(value.getString("command"), is("EVERYTHING"));

        //cleanup
        Response deleteResponse = this.client.target(uri).request(MediaType.APPLICATION_JSON).delete();
        assertThat(deleteResponse.getStatus(), is(204));
    }

}
