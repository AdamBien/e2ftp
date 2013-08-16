/*
 *
 */
package org.e2ftp.testclient.business.hooks.boundary;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;
import javax.json.JsonObject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 *
 * @author adam-bien.com
 */
@Path("callbacks")
@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class CallbacksResource {

    private List<JsonObject> callbacks;

    @PostConstruct
    public void init() {
        this.callbacks = new ArrayList<>();
    }

    @POST
    public void onCallaback(JsonObject object) {
        this.callbacks.add(object);
    }

    public List<JsonObject> getCallbackObject() {
        return callbacks;
    }

}
