package org.eftp.ftpserver.business.management.boundary;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import org.eftp.ftpserver.business.boot.boundary.FTPServerWrapper;

/**
 *
 * @author ZeTo
 */
@Stateless
@Path("management")
public class ManagementResource {

    @Inject
    private FTPServerWrapper ftpServer;

    @GET
    public boolean isRunning() {
        return this.ftpServer.isRunning();
    }

    @POST
    public void startFTPServer() {
        this.ftpServer.start();
    }

    @PUT
    public void restart() {
        this.ftpServer.restart();
    }

    @DELETE
    public void stop() {
        this.ftpServer.stop();
    }
}
