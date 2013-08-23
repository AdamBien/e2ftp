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
    private String user;
    private String password;

    public void init(String uri, String user, String password) {
        this.user = user;
        this.password = password;
        this.client = ClientBuilder.newClient().register(new Authenticator(user, password));
        this.mainTarget = this.client.target(uri);
    }

    public abstract void init();

}
