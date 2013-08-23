/*
 *
 */
package org.eftp.ftpserver.business;

import java.io.IOException;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.MultivaluedMap;
import org.glassfish.jersey.internal.util.Base64;

/**
 *
 * @author adam-bien.com
 */
public class Authenticator implements ClientRequestFilter {

    private String user;
    private String password;

    public Authenticator(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public void filter(ClientRequestContext requestContext) throws IOException {
        MultivaluedMap<String, Object> headers = requestContext.getHeaders();
        final String basicAuthentication = getBasicAuthentication();
        System.out.println("Sending: " + basicAuthentication);
        headers.add("Authorization", basicAuthentication);

    }

    private String getBasicAuthentication() {
        String token = this.user + ":" + this.password;
        return "BASIC " + Base64.encodeAsString(token);
    }

}
