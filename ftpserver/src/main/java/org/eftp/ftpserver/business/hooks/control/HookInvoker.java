/*
 *
 */
package org.eftp.ftpserver.business.hooks.control;

import java.util.concurrent.Future;
import javax.annotation.PostConstruct;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import org.eftp.events.FtpEvent;
import org.eftp.ftpserver.business.hooks.entity.Hook;

/**
 *
 * @author adam-bien.com
 */
@Stateless
public class HookInvoker {

    private Client client;

    @PostConstruct
    public void initClient() {
        this.client = ClientBuilder.newClient();
    }

    @Asynchronous
    public Future<Response> callback(Hook hook, FtpEvent event) {
        final Entity<JsonObject> jsonEvent = Entity.json(event.asJson());
        final String uri = hook.getUri();
        Response response = this.client.target(uri).request().post(jsonEvent);
        return new AsyncResult<>(response);
    }
}
