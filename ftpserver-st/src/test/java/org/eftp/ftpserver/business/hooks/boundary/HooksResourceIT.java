/*
 *
 */
package org.eftp.ftpserver.business.hooks.boundary;

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
        super.init(CONFIGURATION_URI);
    }

    @Test
    public void crud() {
        JsonArray allEntries = this.mainTarget.request(MediaType.APPLICATION_JSON).get(JsonArray.class);
        assertNotNull(allEntries);
        System.out.println("-- " + allEntries);
        final String createdUri = "http://localhost" + System.currentTimeMillis();

        //creation
        JsonObject newEntry = Json.createObjectBuilder().add("command", "everything").add("uri", createdUri).build();
        Response putResponse = this.mainTarget.request().post(Entity.entity(newEntry, MediaType.APPLICATION_JSON));
        assertThat(putResponse.getStatus(), is(201));
        List<Object> locationValues = putResponse.getHeaders().get("Location");
        assertThat(locationValues.size(), is(1));
        String uri = (String) locationValues.get(0);
        System.out.println("---------> " + uri);
        //fetch
        Response fetchResponse = this.client.target(uri).request(MediaType.APPLICATION_JSON).get(Response.class);
        assertThat(fetchResponse.getStatus(), is(200));
        JsonObject value = fetchResponse.readEntity(JsonObject.class);
        assertThat(value.getString("uri"), is(createdUri));
        long id = value.getJsonNumber("id").longValue();

        //removal
        Response deleteResponse = this.mainTarget.path(String.valueOf(id)).request(MediaType.APPLICATION_JSON).delete();
        assertThat(deleteResponse.getStatus(), is(204));

        //empty fetch
        Response emptyFetchResponse = this.mainTarget.path(String.valueOf(id)).request(MediaType.APPLICATION_JSON).get(Response.class);
        assertThat(emptyFetchResponse.getStatus(), is(204));

    }
}
