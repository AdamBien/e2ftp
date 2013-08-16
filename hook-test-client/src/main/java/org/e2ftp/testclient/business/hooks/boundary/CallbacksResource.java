/*
 *
 */
package org.e2ftp.testclient.business.hooks.boundary;

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

    private JsonObject callbackObject;

    @POST
    public void onCallaback(JsonObject object) {
        this.callbackObject = object;
    }

    public JsonObject getCallbackObject() {
        return callbackObject;
    }

}
