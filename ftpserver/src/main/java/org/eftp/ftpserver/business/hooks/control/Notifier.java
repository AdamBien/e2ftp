/*
 *
 */
package org.eftp.ftpserver.business.hooks.control;

import javax.ejb.Asynchronous;
import javax.json.JsonObject;
import javax.ws.rs.container.AsyncResponse;

/**
 *
 * @author adam-bien.com
 */
public class Notifier {

    @Asynchronous
    public void notify(JsonObject message, AsyncResponse response) {
        response.resume(response);
    }
}
