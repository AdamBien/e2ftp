/*
 *
 */
package org.eftp.ftpserver.business;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

/**
 *
 * @author adam-bien.com
 */
public abstract class RESTSupport {

    protected Client client;
    protected WebTarget mainTarget;

    public void init(String uri) {
        this.client = ClientBuilder.newClient();
        this.mainTarget = this.client.target(uri);
    }

    public abstract void init();

}
