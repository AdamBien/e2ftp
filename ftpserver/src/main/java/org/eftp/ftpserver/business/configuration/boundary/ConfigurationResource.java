/*
 *
 */
package org.eftp.ftpserver.business.configuration.boundary;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import org.eftp.ftpserver.business.configuration.control.ConfigurationStore;
import org.eftp.ftpserver.business.configuration.entity.ConfigurationEntry;

/**
 *
 * @author adam-bien.com
 */
@Stateless
@Path("configuration")
public class ConfigurationResource {

    @Inject
    ConfigurationStore cs;

    @GET
    public List<ConfigurationEntry> all() {
        return this.cs.allEntries();
    }

    @GET
    @Path("{name}")
    public ConfigurationEntry find(@PathParam("name") String name) {
        return this.cs.findEntry(name);
    }

    @DELETE
    @Path("{name}")
    public void remove(@PathParam("name") String name) {
        this.cs.remove(name);
    }

    @PUT
    public void saveOrUpdate(ConfigurationEntry newEntry) {
        this.cs.saveOrUpdate(newEntry);
    }

}
