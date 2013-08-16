/*
 *
 */
package org.eftp.ftpserver.business.hooks.boundary;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.JsonObject;
import javax.ws.rs.core.Response;
import org.eftp.ftpserver.business.RESTSupport;
import org.eftp.ftpserver.st.FTPServerWrapperIT;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author adam-bien.com
 */
public class EventBroadcastRessourceIT extends RESTSupport {

    private static final String CONFIGURATION_URI = "http://localhost:8080/ftpserver/api/events";

    @Before
    @Override
    public void init() {
        super.init(CONFIGURATION_URI);
    }

    @Test
    public void receiveNotification() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                FTPServerWrapperIT wrapper = new FTPServerWrapperIT();
                wrapper.init();
                try {
                    wrapper.sendAndReceive();
                    System.out.println("-----Sending and receiving----");
                } catch (IOException ex) {
                    throw new IllegalStateException("Cannot upload file", ex);
                } finally {
                    try {
                        wrapper.cleanup();
                    } catch (IOException ex) {
                        Logger.getLogger(EventBroadcastRessourceIT.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        }, 1000);
        System.out.println("-----Waiting for events----");
        Response response = super.mainTarget.path("EVERYTHING").request().get(Response.class);
        assertThat(response.getStatus(), is(Response.Status.OK.getStatusCode()));
        JsonObject readEvent = response.readEntity(JsonObject.class);
        assertNotNull(readEvent);
        timer.cancel();
        timer.purge();
    }
}
