/*
 *
 */
package org.eftp.ftpserver.business.files.boundary;

import java.io.File;
import java.security.Principal;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 *
 * @author adam-bien.com
 */
@Stateless
@Path("files")
public class FilesResources {

    @Inject
    Files files;

    @Inject
    Principal principal;

    @GET
    @Path("/{file-name}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getFile(@PathParam("file-name") String fileName) {
        String userName = principal.getName();
        File file = files.getFile(userName, fileName);
        if (file == null || !file.exists()) {
            throw new WebApplicationException(Status.NOT_FOUND);
        }
        return Response.ok(file).header("Content-Disposition", "attachment; filename=" + file.getName()).build();
    }
}
