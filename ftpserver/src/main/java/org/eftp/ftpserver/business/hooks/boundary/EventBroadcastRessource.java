package org.eftp.ftpserver.business.hooks.boundary;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import javax.ejb.Singleton;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.container.TimeoutHandler;
import javax.ws.rs.core.Response;
import org.eftp.events.Command;
import org.eftp.events.FtpEvent;
import org.eftp.ftpserver.business.hooks.control.Notifier;

/**
 *
 * @author adam-bien.com
 */
@Singleton
@Path("events")
public class EventBroadcastRessource {

    private final ConcurrentHashMap<Command.Name, List<AsyncResponse>> listeners = new ConcurrentHashMap<>();
    private final static int TIMEOUT_IN_SECONDS = 20;
    @Inject
    Notifier notifier;

    @GET
    @Path("{event-name}")
    public void registerForNotifications(@PathParam("event-name") String name,
            @Suspended AsyncResponse response) {
        Command.Name commandName = Command.Name.valueOf(name.toUpperCase());
        registerListener(commandName, response);
        setupTimeout(commandName, response);

    }

    public void onFtpEventArrival(@Observes @Command FtpEvent event) {
        JsonObject jsonEvent = convert(event);
        List<AsyncResponse> commandListeners = listeners.get(event.getCommand());
        if (commandListeners == null) {
            return;
        }
        for (AsyncResponse asyncResponse : commandListeners) {
            notifier.notify(jsonEvent, asyncResponse);
        }
    }

    void setupTimeout(final Command.Name commandName, AsyncResponse response) {
        response.setTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS);
        response.setTimeoutHandler(new TimeoutHandler() {

            @Override
            public void handleTimeout(AsyncResponse asyncResponse) {
                asyncResponse.resume(Response.status(Response.Status.NO_CONTENT).build());
                cleanup(commandName, asyncResponse);
            }
        });
    }

    void registerListener(Command.Name commandName, AsyncResponse response) {
        List<AsyncResponse> commandListeners = listeners.get(commandName);
        if (commandListeners == null) {
            commandListeners = new ArrayList<>();
            listeners.put(commandName, commandListeners);
        }
        commandListeners.add(response);
    }

    void cleanup(Command.Name commandName, AsyncResponse response) {
        List<AsyncResponse> commandListeners = listeners.get(commandName);
        if (commandListeners != null) {
            commandListeners.remove(response);
        }
    }

    JsonObject convert(FtpEvent event) {
        String command = event.getRequestCommand();
        String requestLine = event.getRequestLine();
        JsonObjectBuilder builder = Json.createObjectBuilder();
        if (requestLine != null) {
            builder.add("request", requestLine);
        }
        if (command != null) {
            builder.add("command", command);
        }
        return builder.build();
    }
}
