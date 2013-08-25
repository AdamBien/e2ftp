/*
 *
 */
package org.eftp.events;

/*
 * #%L
 * ftpservice-api
 * %%
 * Copyright (C) 2013 e2ftp
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import org.apache.ftpserver.ftplet.FtpRequest;
import org.apache.ftpserver.ftplet.FtpSession;
import org.apache.ftpserver.ftplet.User;

/**
 *
 * @author adam-bien.com
 */
public class FtpEvent {

    private final FtpSession session;
    private final FtpRequest request;
    private final FtpEventName command;

    public FtpEvent(FtpEventName command, FtpSession session, FtpRequest request) {
        this.command = command;
        this.session = session;
        this.request = request;
    }

    public FtpEvent(FtpEventName command, FtpSession session) {
        this(command, session, null);
    }

    public FtpEvent(FtpSession session) {
        this(FtpEventName.EVERYTHING, session, null);
    }

    public FtpSession getSession() {
        return session;
    }

    public FtpRequest getRequest() {
        return request;
    }

    public FtpEventName getCommand() {
        return command;
    }

    public String getRequestCommand() {
        if (this.request == null) {
            return null;
        }
        return this.request.getCommand();
    }

    public String getRequestLine() {
        if (this.request == null) {
            return null;
        }
        return this.request.getRequestLine();
    }

    public String getArgument() {
        if (this.request == null) {
            return null;
        }
        return this.request.getArgument();
    }

    public User getUser() {
        if (this.session == null) {
            return null;
        }
        return this.session.getUser();
    }

    @Override
    public String toString() {
        return "FtpEvent{" + "session=" + session + ", request=" + request + '}';
    }

    public JsonObject asJson() {
        User user = this.getUser();
        String ftpCommand = this.getRequestCommand();
        JsonObjectBuilder builder = Json.createObjectBuilder();
        if (ftpCommand != null) {
            builder.add("ftpCommand", ftpCommand);
        }

        if (request != null) {
            if (request.hasArgument()) {
                builder.add("ftpArgument", request.getArgument());
            }
        }
        if (user != null) {
            builder.add("userName", user.getName());
            builder.add("homeDirectory", user.getHomeDirectory());
        }
        if (session != null) {
            String clientAddress = session.getClientAddress().getHostString();
            builder.add("clientAddress", clientAddress);
        }
        builder.add("command", this.command.name());
        return builder.build();
    }

}
