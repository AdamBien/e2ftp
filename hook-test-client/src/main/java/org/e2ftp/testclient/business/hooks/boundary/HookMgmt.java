/*
 *
 */
package org.e2ftp.testclient.business.hooks.boundary;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author adam-bien.com
 */
@Stateless
public class HookMgmt {

    private Client client;

    @PostConstruct
    public void initialize() {
        this.client = ClientBuilder.newClient();
    }

    public void register(String ftpServerUri, String callBackUri) {
        WebTarget registryTarget = this.client.target(ftpServerUri);

        JsonObject newEntry = Json.createObjectBuilder().add("command", "everything").add("uri", callBackUri).build();
        registryTarget.request().post(Entity.entity(newEntry, MediaType.APPLICATION_JSON));

    }
}
