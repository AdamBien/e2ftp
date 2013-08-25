/*
 *
 */
package org.e2ftp.testclient.business.hooks.boundary;

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

import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author adam-bien.com
 */
@Stateless
public class HookMgmt {

    public void register(String user, String password, String ftpServerUri, String callBackUri) {
        Client client = ClientBuilder.newClient().register(new Authenticator(user, password));
        WebTarget registryTarget = client.target(ftpServerUri);

        JsonObject newEntry = Json.createObjectBuilder().add("command", "everything").add("uri", callBackUri).build();
        registryTarget.request().post(Entity.entity(newEntry, MediaType.APPLICATION_JSON));
    }
}
