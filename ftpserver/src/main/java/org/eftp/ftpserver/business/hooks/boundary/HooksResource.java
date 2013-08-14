/*
 *
 */
package org.eftp.ftpserver.business.hooks.boundary;

import java.net.URI;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.eftp.events.Command;
import org.eftp.ftpserver.business.hooks.entity.Hook;

/**
 *
 * @author adam-bien.com
 */
@Stateless
@Path("hooks")
public class HooksResource {

    @Inject
    HooksRegistry hr;

    @POST
    public Response register(JsonObject callback, @Context UriInfo info) {
        long id = hr.saveOrUpdate(convert(callback));
        final URI creationUri = info.getRequestUri().resolve(String.valueOf(id));
        return Response.created(creationUri).build();
    }

    @GET
    @Path("{id}")
    public JsonObject find(@PathParam("id") long id) {
        Hook hook = hr.find(id);
        return convert(hook);
    }

    @GET
    public JsonArray all() {
        JsonArrayBuilder builder = Json.createArrayBuilder();
        List<Hook> allHooks = hr.all();
        for (Hook hook : allHooks) {
            builder.add(convert(hook));
        }
        return builder.build();
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") long id) {
        hr.remove(id);
    }

    Hook convert(JsonObject object) {
        String command = object.getString("command", Command.Name.EVERYTHING.toString());
        String uri = object.getString("uri");
        return new Hook(uri, Command.Name.valueOf(command.toUpperCase()));
    }

    JsonObject convert(Hook hook) {
        return Json.createObjectBuilder().add("command", hook.getCommand().toString()).add("uri", hook.getUri()).build();
    }

}
