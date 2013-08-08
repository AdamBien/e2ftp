/*
 *
 */
package org.eftp.ftpserver.business.configuration;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author adam-bien.com
 */
public class ConfigurationResourceIT {

    private Client client;
    private static final String CONFIGURATION_URI = "http://localhost:8080/ftpserver/api/configuration";
    private WebTarget configurationTarget;

    @Before
    public void init() {
        this.client = ClientBuilder.newClient();
        this.configurationTarget = this.client.target(CONFIGURATION_URI);
    }

    @Test
    public void crud() {
        JsonArray allEntries = this.configurationTarget.request(MediaType.APPLICATION_JSON).get(JsonArray.class);
        assertFalse(allEntries.isEmpty());
        System.out.println("-- " + allEntries);
        String name = "hugo " + System.currentTimeMillis();
        //creation
        JsonObject newEntry = Json.createObjectBuilder().add("name", name).add("value", 42).build();
        Response putResponse = this.configurationTarget.request().put(Entity.entity(newEntry, MediaType.APPLICATION_JSON));
        assertThat(putResponse.getStatus(), is(204));

        //fetch
        Response fetchResponse = this.configurationTarget.path(name).request(MediaType.APPLICATION_JSON).get(Response.class);
        assertThat(fetchResponse.getStatus(), is(200));
        JsonObject value = fetchResponse.readEntity(JsonObject.class);
        assertThat(value.getString("value"), is("42"));

        //removal
        Response deleteResponse = this.configurationTarget.path(name).request(MediaType.APPLICATION_JSON).delete();
        assertThat(deleteResponse.getStatus(), is(204));

        //empty fetch
        Response emptyFetchResponse = this.configurationTarget.path(name).request(MediaType.APPLICATION_JSON).get(Response.class);
        assertThat(emptyFetchResponse.getStatus(), is(204));

    }

}
