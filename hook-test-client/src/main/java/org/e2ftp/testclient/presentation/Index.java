package org.e2ftp.testclient.presentation;

/*
 * #%L
 * hook-test-client
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

import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.json.JsonObject;
import org.e2ftp.testclient.business.hooks.boundary.CallbacksResource;
import org.e2ftp.testclient.business.hooks.boundary.HookMgmt;

/**
 *
 * @author adam-bien.com
 */
@Model
public class Index {

    private static final String HOOK_URI = "http://localhost:8080/ftpserver/api/hooks";

    private String serverUri = HOOK_URI;
    private String callbackUri = "http://localhost:8080/hook-test-client/api/callbacks";

    private String user;
    private String password;

    @Inject
    HookMgmt hookMgmt;

    @Inject
    CallbacksResource resource;

    @PostConstruct
    public void initialize() {
        this.user = "duke";
        this.password = "duke";
    }

    public String getServerUri() {
        return serverUri;
    }

    public void setServerUri(String serverUri) {
        this.serverUri = serverUri;
    }

    public String getCallbackUri() {
        return callbackUri;
    }

    public void setCallbackUri(String callbackUri) {
        this.callbackUri = callbackUri;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCallback() {
        String retVal = "-nothing received yet-";
        List<JsonObject> list = resource.getCallbackObject();
        if (!list.isEmpty()) {
            retVal = list.toString();
        }
        return retVal;
    }

    public Object register() {
        this.hookMgmt.register(user, password, serverUri, callbackUri);
        return null;
    }

    public Object clear() {
        this.resource.clear();
        return null;
    }

}
